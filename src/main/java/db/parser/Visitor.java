package db.parser;

import com.github.freva.asciitable.AsciiTable;
import db.Database;
import db.DbException;
import db.GlobalManager;
import db.Setting;
import db.error.SQLError;
import db.field.Op;
import db.field.Type;
import db.error.NotNullViolation;
import db.file.BufferPool;
import db.query.pipe.*;
import db.file.Table;
import db.query.*;
import db.query.plan.*;
import db.query.predicate.Predicate;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import org.antlr.v4.runtime.misc.Interval;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Visitor visits the AST of SQL code, and execute the SQL query in the Database.
 @author jiangjunguang
 */

public class Visitor extends TinyDBParserBaseVisitor<Object> {
    private TinyDBOutput output;
    private Boolean isLog;
    /**
     * Visitor parse the sql file and print the result to the out(BufferWriter).
     * @param output query result and error information will all go to output
     */
    public Visitor(TinyDBOutput output, Boolean isLog){
        super();
        this.output = output;
        this.isLog = isLog;
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
        if (!this.output.hasSyntaxError) {
            super.visitRoot(ctx);
        }
        output.flush();
        return null;
    }

    /**
     * sqlStatement
     *     : dbSpecifiedStatement | dbUnspecifiedStatement
     *     ;
     * For each sqlStatement, we will record the query time.
     * If the statement was executed correctly, then output the query result with the query time.
     * Else, output the error message.
     * @param ctx
     * @return
     */
    @Override
    public Object visitSqlStatement(TinyDBParser.SqlStatementContext ctx) {
        long startTime = System.currentTimeMillis();
        QueryResult queryResult;
        try {
            queryResult = (QueryResult) super.visitSqlStatement(ctx);
        } catch (Exception e) {
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
     * dbSpecifiedStatement
     *     : createTable | dropTable
     *     | selectStatement | insertStatement | updateStatement | deleteStatement
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitDbSpecifiedStatement(TinyDBParser.DbSpecifiedStatementContext ctx) {
        if (GlobalManager.getDatabase() == null) {
            return new QueryResult(false, "Database not set. Please input the sql 'USE DATABASE $NAME' first");
        } else {
            return super.visitDbSpecifiedStatement(ctx);
        }
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
        String primaryKey = null;
        for (TinyDBParser.CreateDefinitionContext context : ctx.createDefinition()) {
            Object object = visit(context);
            if (object instanceof TDItem) {
                tdItemArrayList.add((TDItem)object);
            } else if (object instanceof String){
                primaryKey = (String)object;
            }
        }
        if (primaryKey == null) {
            primaryKey = "PRIMARY";
            TDItem primaryKeyItem = new TDItem(Type.LONG_TYPE, primaryKey, true);
            tdItemArrayList.add(primaryKeyItem);
        }
        TDItem[] tdItems = tdItemArrayList.toArray(new TDItem[0]);
        TupleDesc tupleDesc = new TupleDesc(tdItems, primaryKey);
        int a = ctx.start.getStartIndex();
        int b = ctx.stop.getStopIndex();
        Interval interval = new Interval(a,b);
        try {
            return GlobalManager.getDatabase().createTable(tableName, tupleDesc, ctx.start.getInputStream().getText(interval), this.isLog,
                    Setting.isBTree, true);
        } catch (SQLError e) {
            return new QueryResult(false,e.getMessage());
        }
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
            try {
                int max_len = Integer.valueOf(ctx.lengthOneDimension().DECIMAL_LITERAL().getText());
                if (max_len <= 0){
                    throw new RuntimeException("string length must be positive");
                }
                hashMap.put("maxLen", max_len);
            } catch (NumberFormatException e){
                throw new RuntimeException("string length must be 1-"+ BufferPool.getPageSize()/2);
            }
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
     * #constraintDeclaration := PRIMARY KEY '(' attrName ')'
     * @param ctx
     * @return a string array of all the attributes which are primary keys
     */
    @Override
    public Object visitConstraintDeclaration(TinyDBParser.ConstraintDeclarationContext ctx) {
        return ctx.attrName().getText();
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
     *     : INSERT INTO tableName ( '(' attrNames ')' )? VALUES '(' constants ')'
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
        if (ctx.constants() == null) {
            return new QueryResult(false, "values can not be empty");
        }
        String[] values = (String[]) visit(ctx.constants());
        try {
            Table table = Util.getTable(ctx.tableName().getText(),null);
            return table.insertTuple(attrNames, values);
        } catch (NoSuchElementException | SQLError e) {
            return new QueryResult(false, e.getMessage());
        }
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
        ArrayList<String> values = new ArrayList<>();
        for (TinyDBParser.ConstantContext childCxt : ctx.constant()) {
            values.add((String)visit(childCxt));
        }
        return values.toArray(new String[0]);
    }

    /**
     * constant
     *     : STRING_LITERAL | decimalLiteral
     *     | '-' decimalLiteral
     *     | REAL_LITERAL
     *     | NULL_LITERAL
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitConstant(TinyDBParser.ConstantContext ctx) {
        String value = ctx.getText();
        if (ctx.STRING_LITERAL() == null && ctx.NULL_LITERAL() == null) {
            value = new BigDecimal(value).toPlainString();
        }
        return value;
    }

    /**
     * predicate
     *     : andExpressionPredicate (OR andExpressionPredicate)*
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitPredicate(TinyDBParser.PredicateContext ctx) {
        LogicalFilterNode.OrNode or = new LogicalFilterNode.OrNode();
        for (TinyDBParser.AndExpressionPredicateContext context: ctx.andExpressionPredicate()) {
            or.add((LogicalFilterNode.AndNode) visit(context));
        }
        return or;
    }

    /**
     * andExpressionPredicate
     *     : comparisonExpressionPredicate (AND comparisonExpressionPredicate)*
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitAndExpressionPredicate(TinyDBParser.AndExpressionPredicateContext ctx) {
        LogicalFilterNode.AndNode and = new LogicalFilterNode.AndNode();
        for (TinyDBParser.ComparisonExpressionPredicateContext context: ctx.comparisonExpressionPredicate()) {
            and.add((LogicalFilterNode.BaseFilterNode)visit(context));
        }
        return and;
    }

    /**
     *  fullColumnName comparisonOperator constant
     * @param ctx
     * @return
     */
    @Override
    public Object visitKvCmpExpressionPredicate(TinyDBParser.KvCmpExpressionPredicateContext ctx) {
        return new LogicalFilterNode.KVCmpNode(
                (FullColumnName)visit(ctx.fullColumnName()),
                (Op) visit(ctx.comparisonOperator()),
                (String)visit(ctx.constant())
                );
    }

    /**
     *  constant comparisonOperator fullColumnName
     * @param ctx
     * @return
     */
    @Override
    public Object visitVkCmpExpressionPredicate(TinyDBParser.VkCmpExpressionPredicateContext ctx) {
        return new LogicalFilterNode.KVCmpNode(
                (FullColumnName)visit(ctx.fullColumnName()),
                Op.reverse((Op)visit(ctx.comparisonOperator())),
                (String)visit(ctx.constant())
        );
    }

    /**
     * fullColumnName comparisonOperator fullColumnName
     * @param ctx
     * @return
     */
    @Override
    public Object visitKkCmpExpressionPredicate(TinyDBParser.KkCmpExpressionPredicateContext ctx) {
        return new LogicalFilterNode.KKCmpNode(
                (FullColumnName)visit(ctx.fullColumnName(0)),
                (Op) visit(ctx.comparisonOperator()),
                (FullColumnName)visit(ctx.fullColumnName(1))
        );
    }

    /**
     * constant comparisonOperator constant
     * @param ctx
     * @return
     */
    @Override
    public Object visitVvCmpExpressionPredicate(TinyDBParser.VvCmpExpressionPredicateContext ctx) {
        try {
            return new LogicalFilterNode.VVCmpNode(
                    (String)visit(ctx.constant(0)),
                    (Op)visit(ctx.comparisonOperator()),
                    (String)visit(ctx.constant(1))
            );
        } catch (SQLError sqlError){
            throw new RuntimeException(sqlError.getMessage());
        }
    }

    /**
     * fullColumnName IS (NOT)? NULL_LITERAL
     * @param ctx
     * @return
     */
    @Override
    public Object visitIsCmpExpressionPredicate(TinyDBParser.IsCmpExpressionPredicateContext ctx) {
        Op op = Op.IS;
        if (ctx.NOT() != null) {
            op = Op.IS_NOT;
        }
        return new LogicalFilterNode.KVCmpNode((FullColumnName)visit(ctx.fullColumnName()), op, null);
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
                return Op.EQUALS;
            case ">":
                return Op.GREATER_THAN;
            case "<":
                return Op.LESS_THAN;
            case "<=":
                return Op.LESS_THAN_OR_EQ;
            case ">=":
                return Op.GREATER_THAN_OR_EQ;
            case "<>":
            case "!=":
                return Op.NOT_EQUALS;
            default:
                throw new IllegalStateException();
        }
    }


    /**
     * fullColumnName
     *     : (tableName '.')? attrName (AS alias=ID)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitFullColumnName(TinyDBParser.FullColumnNameContext ctx) {
        String attrName = ctx.attrName().getText();
        String tableName = null;
        if (ctx.tableName() != null) {
            tableName = ctx.tableName().getText();
        }
        String alias = null;
        if (ctx.alias != null) {
            alias = ctx.alias.getText();
        }
        return new FullColumnName(tableName, attrName, alias);
    }

    /**
     * selectStatement
     *     : SELECT (
     *         STAR
     *         | COUNT '(' STAR ')'
     *         | fullColumnNames
     *     )
     *     FROM tableSources (WHERE whereExpr=predicate)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitSelectStatement(TinyDBParser.SelectStatementContext ctx) {
        try {
            LogicalFilterNode.OrNode or = null;
            if (ctx.whereExpr != null) {
                or = (LogicalFilterNode.OrNode) visit(ctx.whereExpr);
            }
            ArrayList<LogicalJoinNode> joinNodes = (ArrayList<LogicalJoinNode>) visit(ctx.tableSources());

            ArrayList<FullColumnName> fullColumnNames = null;
            String[] header=null;
            if (ctx.fullColumnNames() != null) {
                fullColumnNames = (ArrayList<FullColumnName>)visit(ctx.fullColumnNames());
                header = new String[fullColumnNames.size()];
                for (int i=0; i<fullColumnNames.size(); i++) {
                    header[i] = fullColumnNames.get(i).toString();
                }
            }

            LogicalPlan plan = new LogicalPlan(or, joinNodes, fullColumnNames);
            OpIterator planIterator = plan.physicalPlan();
            PhysicalPlan physicalPlan = new PhysicalPlan(planIterator);

            if (fullColumnNames == null) {
                header = planIterator.getTupleDesc().fullNames();
            }

            return physicalPlan.execute(header, ctx.COUNT() != null);
        } catch (SQLError | NoSuchElementException | DbException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    /**
     *
     fullColumnNames
     : fullColumnName (',' fullColumnName)*
     ;
     * @param ctx
     * @return
     * null if project all the elements(*)
     * else an array list of FullColumnName
     */
    @Override
    public Object visitFullColumnNames(TinyDBParser.FullColumnNamesContext ctx) {
        ArrayList<FullColumnName> names = new ArrayList<>();
        for (TinyDBParser.FullColumnNameContext context: ctx.fullColumnName()) {
            names.add((FullColumnName)visit(context));
        }
        return names;
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
        ArrayList<LogicalJoinNode> nodes = new ArrayList<>();
        for (TinyDBParser.TableSourceContext context : ctx.tableSource()) {
            nodes.addAll((ArrayList<LogicalJoinNode>)visit(context));
        }
        return nodes;
    }

    /**
     * table
     *     : originalName=tableName (AS alias=tableName)
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitTable(TinyDBParser.TableContext ctx) {
        String alias = null;
        if (ctx.alias != null) {
            alias = ctx.alias.getText();
        }
        try {
            return new LogicalScanNode(ctx.originalName.getText(), alias);
        } catch (SQLError sqlError){
            throw new RuntimeException(sqlError.getMessage());
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
        ArrayList<LogicalJoinNode> joinNodes = new ArrayList<>();
        joinNodes.add(new LogicalJoinNode(null, (LogicalScanNode)visit(ctx.table()), LogicalJoinNode.Type.START));
        for (TinyDBParser.JoinPartContext context: ctx.joinPart()){
            joinNodes.add((LogicalJoinNode)visit(context));
        }
        return joinNodes;
    }


    /**
     * #innerJoin : JOIN table
     *       (
     *         ON comparisonExpressionPredicate
     *       )?
     * @param ctx
     * @return
     */
    @Override
    public Object visitInnerJoin(TinyDBParser.InnerJoinContext ctx) {
        LogicalFilterNode.BaseFilterNode cmp = null;
        if (ctx.comparisonExpressionPredicate() != null) {
            cmp = (LogicalFilterNode.BaseFilterNode)visit(ctx.comparisonExpressionPredicate());
        }
        return new LogicalJoinNode(cmp, (LogicalScanNode)visit(ctx.table()), LogicalJoinNode.Type.JOIN);
    }

    @Override
    public Object visitNaturalJoin(TinyDBParser.NaturalJoinContext ctx) {
        return new LogicalJoinNode(null,(LogicalScanNode)visit(ctx.table()), LogicalJoinNode.Type.NATURAL_JOIN);
    }

    /**
     *
     * @param tableName
     * @param or
     * @param isDelete
     * @param updateElements
     * @return
     */
    public Object visitUpdateOrDeleteStatement(String tableName, LogicalFilterNode.OrNode or,
                                               boolean isDelete, Update.UpdateElement[] updateElements) {
        try {
            LogicalScanNode scanNode = new LogicalScanNode(tableName);

            OpIterator opIterator;

            HashMap<String, String> attrNameToTable = new HashMap<>();
            String[] attrNames = scanNode.tupleDesc.getAttrNames();
            for(String attrName: attrNames) {
                attrNameToTable.put(attrName, scanNode.tableName);
            }

            if (or != null){
                LogicalFilterNode.AndNode andNode = null;
                boolean optimized = false;
                or.disambiguateName(attrNameToTable);
                if(or.size() == 1) {
                    andNode = or.get(0);
                    optimized = true;
                }
                opIterator = Util.getScan(scanNode,andNode,optimized);
                Predicate predicate = or.predicate(scanNode.tupleDesc);
                opIterator = new Filter(predicate, opIterator);
            } else {
                opIterator = Util.getScan(scanNode,null,false);
            }

            if (isDelete) {
                opIterator = new Delete(opIterator);
            } else {
                try {
                    opIterator = new Update(opIterator, updateElements);
                } catch (NoSuchElementException | NotNullViolation e) {
                    return new QueryResult(false, e.getMessage());
                }
            }
            opIterator.open();
            Tuple tuple = opIterator.next();
            long deleteCount = (long)tuple.getField(0).getValue();
            scanNode.table.count -= deleteCount;
            opIterator.close();
            return new QueryResult(true, "Query OK, " + tuple.getField(0).toString() + " rows affected.");
        } catch (DbException e){
            e.printStackTrace();
            return new QueryResult(false, e.toString());
        } catch (SQLError e) {
            return new QueryResult(false, e.getMessage());
        }

    }

    /**
     * deleteStatement
     *     : DELETE FROM tableName
     *     (WHERE predicate)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitDeleteStatement(TinyDBParser.DeleteStatementContext ctx) {
        LogicalFilterNode.OrNode or = null;
        if (ctx.predicate() != null) {
            or = (LogicalFilterNode.OrNode) visit(ctx.predicate());
        }
        return visitUpdateOrDeleteStatement(ctx.tableName().getText(), or, true, null);
    }

    /**
     * updateStatement
     *     : UPDATE tableName
     *     SET updatedElement (',' updatedElement)*
     *           (WHERE predicate)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitUpdateStatement(TinyDBParser.UpdateStatementContext ctx) {
        LogicalFilterNode.OrNode or = null;
        if (ctx.predicate() != null) {
            or = (LogicalFilterNode.OrNode) visit(ctx.predicate());
        }
        ArrayList<Update.UpdateElement> updateElements = new ArrayList<>();
        for (TinyDBParser.UpdatedElementContext context : ctx.updatedElement()) {
            updateElements.add((Update.UpdateElement)visit(context));
        }
        return visitUpdateOrDeleteStatement(ctx.tableName().getText(), or, false,
                updateElements.toArray(new Update.UpdateElement[0]));
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
        return new Update.UpdateElement(ctx.attrName().getText(), (String)visit(ctx.constant()));
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
            Database database = GlobalManager.getDatabase();
            if (database == null) {
                return new QueryResult(false,"Database not set. Please input the sql 'USE DATABASE $NAME' first");
            }
            String tableName = ctx.table().getText();
            Table table = database.getTable(tableName);
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

    /**
     * Visit a parse tree produced by {@link TinyDBParser#shutdownStatement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitShutdownStatement(TinyDBParser.ShutdownStatementContext ctx) {
        output.print("shutdown the server");
        System.exit(0);
        return null;
    }
}
