package db.parser;

import db.GlobalManager;
import db.TupleDesc;
import db.Type;
import db.query.*;

import java.util.ArrayList;

public class Visitor extends TinyDBParserBaseVisitor<Object> {

    private TinyDBOutput output;
    /**
     * Visitor parse the sql file and print the result to the out(BufferWriter).
     * @param output query result and error information will all go to output
     */
    public Visitor(TinyDBOutput output){
        super();
        this.output = output;
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
     * Else, output the error message(done by the child visitor).
     * @param ctx
     * @return
     */
    @Override
    public Object visitSqlStatement(TinyDBParser.SqlStatementContext ctx) {
        long startTime = System.currentTimeMillis();
        QueryResult queryResult = (QueryResult) super.visitSqlStatement(ctx);
        long endTime = System.currentTimeMillis();
        double usedTime = (endTime - startTime) / 1000.;
        if (queryResult != null) {
            output.print(queryResult.getInfo());
            if (queryResult.succeeded()) {
                output.print(usedTime);
            }
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

        ArrayList<TupleDesc.TDItem> tdItemArrayList = new ArrayList<>();
        String[] primaryKeys = new String[0];
        for (TinyDBParser.CreateDefinitionContext context : ctx.createDefinition()) {
            Object object = visit(context);
            if (object instanceof TupleDesc.TDItem) {
                tdItemArrayList.add((TupleDesc.TDItem)object);
            } else if (object instanceof String[]){
                primaryKeys = (String[]) object;
            }
        }
        TupleDesc.TDItem[] tdItems = tdItemArrayList.toArray(new TupleDesc.TDItem[0]);
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
        Type type = (Type) visit(ctx.dataType());
        String attrName = (String) visit(ctx.attrName());
        boolean notNull =  (ctx.nullNotnull() != null);
        return new TupleDesc.TDItem(type, attrName, notNull);
    }


    /**
     * dataType
     *     : INT | LONG | FLOAT | DOUBLE
     *     | (STRING) lengthOneDimension
     *     ;
     * @param ctx
     * @return Type
     */
    @Override
    public Object visitDataType(TinyDBParser.DataTypeContext ctx) {
        if (ctx.INT() != null) {
            return Type.INT_TYPE;
        } else if (ctx.STRING() != null) {
            return Type.STRING_TYPE;
        } else {
            // TODO
            return Type.INT_TYPE;
//            throw new NotImplementedException();
        }
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
        Object[] values = (Object[]) visit(ctx.constants());
        return GlobalManager.getDatabase().getTable(ctx.tableName().getText()).insertTuple(attrNames, values);
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
            Integer integer = Integer.valueOf(ctx.decimalLiteral().getText());
            if (ctx.getChildCount() == 2) {
                integer = -integer;
            }
            return integer;
        } else {
            return Float.valueOf(ctx.REAL_LITERAL().getText());
        }
    }

    /**
     * #logicalExpressionPredicate := left=predicate logicalOperator right=predicate
     * @param ctx
     * @return
     */
    @Override
    public Object visitLogicalExpressionPredicate(TinyDBParser.LogicalExpressionPredicateContext ctx) {
        Predicate left = (Predicate)visit(ctx.left);
        LogicalPredicate.Op op = (LogicalPredicate.Op)visit(ctx.logicalOperator());
        Predicate right = (Predicate)visit(ctx.right);
        return new LogicalPredicate(left, op, right);
    }

    /**
     * #comparisonExpressionPredicate := left=expressionAtom comparisonOperator right=expressionAtom
     * @param ctx
     * @return
     */
    @Override
    public Object visitComparisonExpressionPredicate(TinyDBParser.ComparisonExpressionPredicateContext ctx) {
        return null;
    }

    /**
     * fullColumnName
     *     : tableName '.' attrName
     *     | attrName
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitFullColumnName(TinyDBParser.FullColumnNameContext ctx) {
        return super.visitFullColumnName(ctx);
    }

    /**
     * selectStatement
     *     : SELECT selectElements FROM tableSources (WHERE whereExpr=predicate)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitSelectStatement(TinyDBParser.SelectStatementContext ctx) {
        // A simple query plan without optimization
        OpIterator join = (OpIterator) visit(ctx.tableSources());
        Predicate predicate = (Predicate) visit(ctx.whereExpr);
        Filter filter = new Filter(predicate, join);
        Project.ProjectElement[] projectElements = (Project.ProjectElement[]) visit(ctx.selectElements());
        Project project = new Project(projectElements, (OpIterator) filter);
        return project;
    }

    @Override
    public Object visitSelectElements(TinyDBParser.SelectElementsContext ctx) {
        ArrayList<Project.ProjectElement> arrayList = new ArrayList<>();
        for (TinyDBParser.SelectElementContext child : ctx.selectElement()) {
            arrayList.add((Project.ProjectElement) visit(child));
        }
        return arrayList.toArray();
    }

    /**
     * #selectColumnElement := fullColumnName (AS? alias=attrName)?
     * @param ctx
     * @return
     */
    @Override
    public Object visitSelectColumnElement(TinyDBParser.SelectColumnElementContext ctx) {
        String tableName = null;
        if (ctx.fullColumnName().tableName() != null) {
            tableName = ctx.fullColumnName().tableName().getText();
        }
        String attrName = ctx.fullColumnName().attrName().getText();
        String alias = null;
        if (ctx.alias != null) {
            alias = ctx.alias.getText();
        }
        return new Project.ProjectElement(tableName, attrName, alias);
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
        // TODO Currently, we simply return the first table source!
        return visit(ctx.tableSource(0));
    }

    /**
     * tableSource
     *     : tableName joinPart*                                     #tableSourceBase
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitTableSourceBase(TinyDBParser.TableSourceBaseContext ctx) {
        SeqScan seqScan = new SeqScan(ctx.tableName().getText());
        OpIterator opIterator = (OpIterator) seqScan;
        for (TinyDBParser.JoinPartContext context : ctx.joinPart()) {
            Join.JoinPart joinPart = (Join.JoinPart)visit(context);
            Join join = new Join(opIterator, joinPart);
            opIterator = (OpIterator) join;
        }
        return opIterator;
    }

    /**
     * #innerJoin := JOIN tableName ( ON predicate )?
     * @param ctx
     * @return
     */
    @Override
    public Object visitInnerJoin(TinyDBParser.InnerJoinContext ctx) {
        SeqScan seqScan = new SeqScan(ctx.tableName().getText());
        return new Join.JoinPart((LogicalPredicate)visit(ctx.predicate()),(OpIterator)seqScan);
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
        OpIterator opIterator = (OpIterator) (new SeqScan(ctx.tableName().getText()));
        if (ctx.predicate() != null) {
            LogicalPredicate predicate = (LogicalPredicate) visit(ctx.predicate());
            opIterator = (OpIterator) (new Filter(predicate, opIterator));
        }
        Delete delete = new Delete(opIterator);
        Query query = new Query((OpIterator) delete);
        return query.execute();
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
        SeqScan seqScan = new SeqScan(ctx.tableName().get(0).getText());
        Filter filter = new Filter((LogicalPredicate)visit(ctx.predicate()), (OpIterator)seqScan);
        ArrayList<Update.UpdateElement> updateElements = new ArrayList<>();
        for (TinyDBParser.UpdatedElementContext context : ctx.updatedElement()) {
            updateElements.add((Update.UpdateElement)visit(context));
        }
        Update update = new Update((OpIterator)filter, updateElements.toArray(new Update.UpdateElement[0]));
        Query query = new Query((OpIterator)update);
        return query.execute();
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
     *     | SHOW TABLE tableName
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitShowStatement(TinyDBParser.ShowStatementContext ctx) {
        return super.visitShowStatement(ctx);
    }
}
