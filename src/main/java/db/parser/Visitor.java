package db.parser;

import com.github.freva.asciitable.AsciiTable;
import db.*;
import db.field.Type;
import db.field.TypeMismatch;
import db.file.Table;
import db.query.*;
import db.query.Predicate;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Visitor visits the AST of SQL code, and execute the SQL query in the Database.
 @author jiangjunguang
 */

public class Visitor extends TinyDBParserBaseVisitor<Object> {
    /**
     * AttributeTable records the table each attribute belonging to
     * in a certain parsing context.
     *
     * E.g. If we have Table A (attr1 type1, attr2 type2),
     * then in the context "from table A",
     * we have maps :
     *      attr1 -> A
     *      attr2 -> A
     *
     * If two tables has the same attribute, then throw NoSuchElementException
     * to indicate ambiguity.
     *
     */
    private class AttributeTable {
        private HashMap<String, Table> map;
        private AttributeTable() {
            map = new HashMap<>();
        }

        /**
         * Clear all the attributes in the AttributeTable.
         */
        private void clear(){
            map.clear();
        }

        /**
         * Add a table and all its associated attributes into the AttributeTable
         * @param table
         */
        private void addTable(Table table){
            String[] attrNames = table.getTupleDesc().getAttrNames();
            for (String attrName : attrNames) {
                if (map.containsKey(attrName)) { // If two tables has the same attribute,
                    map.put(attrName, null); // then return null.
                } else {
                    map.put(attrName, table);
                }
            }
            table.getTupleDesc().setTableName(table.getName());
        }

        /**
         * @param attrName the name of an attribute
         * @return the Table which an attribute belongs to,
         * @throws NoSuchElementException if the attrName doesn't exist or is ambiguous
         */
        private Table getBelongingTable(String attrName) {
            if (map.containsKey(attrName)) {
                Table table = map.get(attrName);
                if (table == null) {
                    throw new NoSuchElementException("Attribute " + attrName + " is ambiguous.");
                } else {
                    return table;
                }
            } else {
                throw new NoSuchElementException("Attribute " + attrName + " doesn't exist.");
            }
        }
    }

    private TinyDBOutput output;
    private AttributeTable attributeTable;

    /**
     * Visitor parse the sql file and print the result to the out(BufferWriter).
     * @param output query result and error information will all go to output
     */
    public Visitor(TinyDBOutput output){
        super();
        this.output = output;
        this.attributeTable = new AttributeTable();
    }

    /**
     * root
     *     : sqlStatements EOF
     *     ;
     * after visiting the root, we will flush the output
     * @param ctx
     * @return
     */
    @Override
    public Object visitRoot(TinyDBParser.RootContext ctx) {
        super.visitRoot(ctx);
        output.flush();
        return null;
    }

    /**
     * sqlStatement
     *     : ddlStatement | dmlStatement | administrationStatement
     *     ;
     * For each sqlStatement, we will record the query time.
     * If the statement was executed correctly, then output the query result with the query time.
     * Else, output the error message.
     * @param ctx
     * @return
     */
    @Override
    public Object visitSqlStatement(TinyDBParser.SqlStatementContext ctx) {
        this.attributeTable.clear();
        long startTime = System.currentTimeMillis();
        QueryResult queryResult;
        try {
            queryResult = (QueryResult) super.visitSqlStatement(ctx);
        } catch (NoSuchElementException e) {
            queryResult = new QueryResult(false, e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        double usedTime = (endTime - startTime) / 1000.;
        if (queryResult != null) {
            String msg;
            if (!queryResult.succeeded()) {
                msg = "Line " + ctx.start.getLine() + " : " + queryResult.getInfo();
            } else {
                msg = queryResult.getInfo() + " (" + usedTime + " sec)";
            }
            output.print(msg);
        }
        return null;
    }

    /**
     * createDatabase
     *     : CREATE DATABASE dbName
     *     ;
     * @param ctx
     * @return whether the statement was successfully executed
     */
    @Override
    public Object visitCreateDatabase(TinyDBParser.CreateDatabaseContext ctx) {
        return GlobalManager.getCatalog().createDatabase(ctx.dbName().getText());
    }

    /**
     * useDatabase
     *     : USE DATABASE dbName
     *     ;
     * @param ctx
     * @return whether the statement was successfully executed
     */
    @Override
    public Object visitUseDatabase(TinyDBParser.UseDatabaseContext ctx) {
        return GlobalManager.getCatalog().useDatabase(ctx.dbName().getText());
    }

    /**
     * dropDatabase
     *     : DROP DATABASE dbName
     *     ;
     * @param ctx
     * @return whether the statement was successfully executed
     */
    @Override
    public Object visitDropDatabase(TinyDBParser.DropDatabaseContext ctx) {
        return GlobalManager.getCatalog().dropDatabase(ctx.dbName().getText());
    }

    /**
     * createTable
     *     : CREATE TABLE tableName '(' createDefinition (',' createDefinition)* ')'
     *     ;
     * @param ctx
     * @return whether the statement was successfully executed
     */
    @Override
    public Object visitCreateTable(TinyDBParser.CreateTableContext ctx){
        String tableName = ctx.tableName().getText();

        ArrayList<TDItem> tdItemArrayList = new ArrayList<>();
        String[] primaryKeys = new String[0];
        for (TinyDBParser.CreateDefinitionContext context : ctx.createDefinition()) {
            Object object = visit(context);
            if (object instanceof TDItem) {
                tdItemArrayList.add((TDItem)object);
            } else if (object instanceof String[]){
                primaryKeys = (String[]) object;
            }
        }
        TDItem[] tdItems = tdItemArrayList.toArray(new TDItem[0]);
        TupleDesc tupleDesc = new TupleDesc(tdItems, primaryKeys);

        return GlobalManager.getDatabase().createTable(tableName, tupleDesc);
    }

    /**
     * #columnDeclaration := attrName dataType nullNotnull?
     * @param ctx
     * @return a TDItem which describe the associated column
     */
    @Override
    public Object visitColumnDeclaration(TinyDBParser.ColumnDeclarationContext ctx) {
        HashMap<String, Object> hashMap = (HashMap<String, Object>) visit(ctx.dataType());
        Type type = (Type) hashMap.get("type");
        int maxLen = (int) hashMap.get("maxLen");

        String attrName = ctx.attrName().getText();
        boolean notNull =  (ctx.nullNotnull() != null);
        return new TDItem(type, attrName, notNull, maxLen);
    }


    /**
     * dataType
     *     : INT | LONG | FLOAT | DOUBLE
     *     | (STRING) lengthOneDimension
     *     ;
     * @param ctx
     * @return A HashMap where
     *          type is the Type of the data
     *          maxLen is the max length of the data(only for STRING_TYPE)
     */
    @Override
    public Object visitDataType(TinyDBParser.DataTypeContext ctx) {
        HashMap<String, Object> hashMap = new HashMap<>();

        if (ctx.STRING() != null) {
            hashMap.put("type", Type.STRING_TYPE);
            hashMap.put("maxLen", Integer.valueOf(ctx.lengthOneDimension().DECIMAL_LITERAL().getText()));
        } else {
            hashMap.put("type", Type.getType(ctx.getText()));
            hashMap.put("maxLen", 0);
        }
        return hashMap;
    }

    /**
     * attrNames
     *     : attrName (',' attrName)*
     *     ;
     * @param ctx
     * @return a string array of all the attributes
     */
    @Override
    public Object visitAttrNames(TinyDBParser.AttrNamesContext ctx) {
        ArrayList<String> attrNames = new ArrayList<>();
        for(TinyDBParser.AttrNameContext context: ctx.attrName()){
            attrNames.add(context.getText());
        }
        return attrNames.toArray(new String[0]);
    }

    /**
     * #constraintDeclaration := PRIMARY KEY '(' attrNames ')'
     * @param ctx
     * @return a string array of all the attributes which are primary keys
     */
    @Override
    public Object visitConstraintDeclaration(TinyDBParser.ConstraintDeclarationContext ctx) {
        return visit(ctx.attrNames());
    }

    /**
     * dropTable
     *     : DROP TABLE tableName
     *     ;
     * @param ctx
     * @return whether the statement was successfully executed
     */
    @Override
    public Object visitDropTable(TinyDBParser.DropTableContext ctx) {
        return GlobalManager.getDatabase().dropTable(ctx.tableName().getText());
    }

    /**
     * insertStatement
     *     : INSERT INTO table ( '(' attrNames ')' )? VALUES '(' constants ')'
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitInsertStatement(TinyDBParser.InsertStatementContext ctx) {
        String[] attrNames = null;
        if (ctx.attrNames() != null) {
            attrNames = (String[])visit(ctx.attrNames());
        }
        Object[] values = (Object[]) visit(ctx.constants());
        Table table = (Table)visit(ctx.table());
        return table.insertTuple(attrNames, values);
    }

    /**
     * constants
     *     : constant (',' constant)*
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitConstants(TinyDBParser.ConstantsContext ctx) {
        ArrayList<Object> values = new ArrayList<>();
        for (TinyDBParser.ConstantContext childCxt : ctx.constant()) {
            values.add(visit(childCxt));
        }
        return values.toArray();
    }

    /**
     * constant
     *     : STRING_LITERAL | decimalLiteral
     *     | '-' decimalLiteral
     *     | REAL_LITERAL
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitConstant(TinyDBParser.ConstantContext ctx) {
        if (ctx.STRING_LITERAL() != null) {
            String s = ctx.STRING_LITERAL().getText();
            return s.substring(1, s.length()-1);
        } else if (ctx.decimalLiteral() != null) {
            Long number = Long.valueOf(ctx.decimalLiteral().getText());
            if (ctx.getChildCount() == 2) {
                number = -number;
            }
            return number;
        } else {
            return Double.valueOf(ctx.REAL_LITERAL().getText());
        }
    }

    /**
     * #logicalExpressionPredicate := left=predicate logicalOperator right=predicate
     * @param ctx
     * @return
     */
    @Override
    public Object visitLogicalExpressionPredicate(TinyDBParser.LogicalExpressionPredicateContext ctx) {
        LogicalPredicate.Op op = (LogicalPredicate.Op)visit(ctx.logicalOperator());
        return new LogicalPredicate((Predicate)visit(ctx.left), op, (Predicate)visit(ctx.right));
    }

    /**
     *logicalOperator
     *     : AND | '&' '&' | OR | '|' '|'
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitLogicalOperator(TinyDBParser.LogicalOperatorContext ctx) {
        String text = ctx.getText();
        if (text.equals("AND") || text.equals("&&")){
            return LogicalPredicate.Op.AND;
        } else if (text.equals("OR") || text.equals("||")) {
            return LogicalPredicate.Op.OR;
        } else {
            throw new NotImplementedException();
        }
    }

    /**
     * comparisonOperator
     *     : '=' | '>' | '<' | '<' '=' | '>' '='
     *     | '<' '>' | '!' '='
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitComparisonOperator(TinyDBParser.ComparisonOperatorContext ctx) {
        String text = ctx.getText();
        switch (text) {
            case "=":
                return ComparisonPredicate.Op.EQUALS;
            case ">":
                return ComparisonPredicate.Op.GREATER_THAN;
            case "<":
                return ComparisonPredicate.Op.LESS_THAN;
            case "<=":
                return ComparisonPredicate.Op.LESS_THAN_OR_EQ;
            case ">=":
                return ComparisonPredicate.Op.GREATER_THAN_OR_EQ;
            case "<>":
            case "!=":
                return ComparisonPredicate.Op.NOT_EQUALS;
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * #comparisonExpressionPredicate := left=expressionAtom comparisonOperator right=expressionAtom
     * @param ctx
     * @return
     */
    @Override
    public Object visitComparisonExpressionPredicate(TinyDBParser.ComparisonExpressionPredicateContext ctx) {
        ComparisonPredicate.Op op = (ComparisonPredicate.Op)visit(ctx.comparisonOperator());
        return new ComparisonPredicate(visit(ctx.left), op, visit(ctx.right));
    }

    /**
     * fullColumnName
     *     : table '.' attrName
     *     | attrName
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitFullColumnName(TinyDBParser.FullColumnNameContext ctx) {
        String attrName = ctx.attrName().getText();
        String tableName;
        if (ctx.table() != null) {
            tableName = ctx.table().getText();
            return new Attribute(tableName, attrName, tableName+'.'+attrName);
        } else {
            tableName = attributeTable.getBelongingTable(attrName).getName();
            return new Attribute(tableName, attrName, attrName);
        }
    }

    /**
     * selectStatement
     *     : SELECT fullColumnName (',' fullColumnName)* FROM tableSources (WHERE whereExpr=predicate)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitSelectStatement(TinyDBParser.SelectStatementContext ctx) {
        // A simple query plan without optimization
        OpIterator join = (OpIterator) visit(ctx.tableSources());
        // After we visit table sources, we have attributeTable ready to look up.

        OpIterator filter;
        if (ctx.whereExpr != null) {
            Predicate predicate = (Predicate) visit(ctx.whereExpr);
            filter = new Filter(predicate, join);
        } else {
            filter = join;
        }

        ArrayList<Attribute> projectElements = new ArrayList<>();
        for(TinyDBParser.FullColumnNameContext context : ctx.fullColumnName()) {
            projectElements.add((Attribute) visit(context));
        }
        Project project = new Project(projectElements.toArray(new Attribute[0]), filter);
        Query query = new Query(project);

        String[] header = new String[projectElements.size()];
        for (int i=0; i<projectElements.size(); i++) {
            header[i] = projectElements.get(i).alias;
        }
        return query.executeSelect(header);
    }

    /**
     * tableSources
     *     : tableSource (',' tableSource)*
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitTableSources(TinyDBParser.TableSourcesContext ctx) {
        OpIterator opIterator = null;
        for (TinyDBParser.TableSourceContext context : ctx.tableSource()) {
            if (opIterator == null) {
                opIterator = (OpIterator)visit(context);
            } else {
                opIterator = new Join(opIterator, new Predicate() {
                    @Override
                    public boolean filter(Tuple tuple) {
                        return true;
                    }
                }, (OpIterator)visit(context));
            }
        }
        return opIterator;
    }

    /**
     * table
     *     : tableName
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitTable(TinyDBParser.TableContext ctx) {
        String tableName = ctx.getText();
        Table table = GlobalManager.getDatabase().getTable(tableName);
        if (table == null) {
            throw new NoSuchElementException("Table " + tableName + " doesn't exist.");
        } else {
            return table;
        }
    }

    /**
     * tableSource
     *     : table joinPart*                                     #tableSourceBase
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitTableSourceBase(TinyDBParser.TableSourceBaseContext ctx) {
        Table table = (Table) visit(ctx.table());
        this.attributeTable.addTable(table);
        OpIterator opIterator = new SeqScan(table);
        for (TinyDBParser.JoinPartContext context : ctx.joinPart()) {
            Join.JoinPart joinPart = (Join.JoinPart)visit(context);
            opIterator = new Join(opIterator, joinPart);
        }
        return opIterator;
    }


    /**
     * #innerJoin := JOIN table ( ON predicate )?
     * @param ctx
     * @return
     */
    @Override
    public Object visitInnerJoin(TinyDBParser.InnerJoinContext ctx) {
        Table table = (Table) visit(ctx.table());
        this.attributeTable.addTable(table);
        SeqScan seqScan = new SeqScan(table);
        return new Join.JoinPart((Predicate) visit(ctx.predicate()),seqScan);
    }

    /**
     * deleteStatement
     *     : DELETE FROM table
     *     (WHERE predicate)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitDeleteStatement(TinyDBParser.DeleteStatementContext ctx) {
        Table table = (Table) visit(ctx.table());
        this.attributeTable.addTable(table);

        OpIterator opIterator = new SeqScan(table);
        if (ctx.predicate() != null) {
            Predicate predicate = (Predicate) visit(ctx.predicate());
            opIterator = new Filter(predicate, opIterator);
        }

        Delete delete = new Delete(opIterator);
        Query query = new Query(delete);
        return query.executeDeleteOrUpdate();
    }

    /**
     * updateStatement
     *     : UPDATE table
     *     SET updatedElement (',' updatedElement)*
     *           (WHERE predicate)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitUpdateStatement(TinyDBParser.UpdateStatementContext ctx) {
        Table table = (Table) visit(ctx.table());
        this.attributeTable.addTable(table);

        OpIterator opIterator = new SeqScan(table);
        if (ctx.predicate() != null) {
            opIterator = new Filter((Predicate) visit(ctx.predicate()), opIterator);
        }

        ArrayList<Update.UpdateElement> updateElements = new ArrayList<>();
        for (TinyDBParser.UpdatedElementContext context : ctx.updatedElement()) {
            updateElements.add((Update.UpdateElement)visit(context));
        }

        try {
            Update update = new Update(opIterator, updateElements.toArray(new Update.UpdateElement[0]));
            Query query = new Query(update);
            return query.executeDeleteOrUpdate();
        } catch (NoSuchElementException | TypeMismatch e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    /**
     * updatedElement
     *     : attrName '=' constant
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitUpdatedElement(TinyDBParser.UpdatedElementContext ctx) {
        return new Update.UpdateElement(ctx.attrName().getText(), visit(ctx.constant()));
    }

    /**
     * showStatement
     *     : SHOW DATABASES
     *     | SHOW DATABASE dbName
     *     | SHOW TABLE table
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitShowStatement(TinyDBParser.ShowStatementContext ctx) {
        if (ctx.TABLE() != null) {
            // SHOW TABLE table
            Table table = (Table)visit(ctx.table());
            String tableName = ctx.table().getText();
            if (table == null) {
                throw new NoSuchElementException("Table " + tableName + " doesn't exist.");
            } else {
                return new QueryResult(true, tableName + " : " + table.getTupleDesc().toString());
            }
        } else {
            String[] header;
            String[] originData;

            if (ctx.DATABASES() != null) {
                // SHOW DATABASES
                header = new String[]{"Database"};
                originData = GlobalManager.getCatalog().getDatabaseNames();
            } else {
                // SHOW DATABASE dbName
                header = new String[] {"Table"};
                originData = GlobalManager.getCatalog().getTableNames(ctx.dbName().getText());
            }
            String[][] data = new String[originData.length][1];
            for (int i=0; i<originData.length; i++) {
                data[i][0] = originData[i];
            }
            String asciiTable = AsciiTable.getTable(header, data);
            return new QueryResult(true, asciiTable +
                    System.lineSeparator() + String.format("%d rows in set", data.length));
        }

    }
}
