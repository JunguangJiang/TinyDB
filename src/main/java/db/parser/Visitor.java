package db.parser;

import db.GlobalManager;
import db.Tuple;
import db.TupleDesc;
import db.Type;
import db.query.QueryResult;
import tinydb.Query;

import java.io.BufferedWriter;
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
     *     : INSERT INTO tableName ( '(' attrNames ')' )? insertStatementValue
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitInsertStatement(TinyDBParser.InsertStatementContext ctx) {
//        GlobalManager.getDatabase().getTable(ctx.tableName().getText()).insertTuple();
        return super.visitInsertStatement(ctx);
    }

    /**
     * insertStatementValue
     *     : selectStatement
     *     | VALUES '(' expressions ')'
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitInsertStatementValue(TinyDBParser.InsertStatementValueContext ctx) {
        return super.visitInsertStatementValue(ctx);
    }

    /**
     * expressions
     *     : expression (',' expression)*
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitExpressions(TinyDBParser.ExpressionsContext ctx) {
        return super.visitExpressions(ctx);
    }

    @Override
    public Object visitNotExpression(TinyDBParser.NotExpressionContext ctx) {
        return super.visitNotExpression(ctx);
    }

    @Override
    public Object visitLogicalExpression(TinyDBParser.LogicalExpressionContext ctx) {
        return super.visitLogicalExpression(ctx);
    }

    @Override
    public Object visitPredicateExpression(TinyDBParser.PredicateExpressionContext ctx) {
        return super.visitPredicateExpression(ctx);
    }

    @Override
    public Object visitIsNullPredicate(TinyDBParser.IsNullPredicateContext ctx) {
        return super.visitIsNullPredicate(ctx);
    }

    @Override
    public Object visitBinaryComparasionPredicate(TinyDBParser.BinaryComparasionPredicateContext ctx) {
        return super.visitBinaryComparasionPredicate(ctx);
    }

    @Override
    public Object visitExpressionAtomPredicate(TinyDBParser.ExpressionAtomPredicateContext ctx) {
        return super.visitExpressionAtomPredicate(ctx);
    }




    /**
     * selectStatement
     *     : SELECT selectElements fromClause
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitSelectStatement(TinyDBParser.SelectStatementContext ctx) {
        return super.visitSelectStatement(ctx);
    }

    /**
     * deleteStatement
     *     : DELETE FROM tableName
     *     (WHERE expression)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitDeleteStatement(TinyDBParser.DeleteStatementContext ctx) {
        return super.visitDeleteStatement(ctx);
    }

    /**
     * updateStatement
     *     : UPDATE tableName (AS? alias=tableName)?
     *     SET updatedElement (',' updatedElement)*
     *           (WHERE expression)?
     *     ;
     * @param ctx
     * @return
     */
    @Override
    public Object visitUpdateStatement(TinyDBParser.UpdateStatementContext ctx) {
        return super.visitUpdateStatement(ctx);
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
