package db.parser;

import db.GlobalManager;
import db.TupleDesc;
import db.Type;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Visitor extends TinyDBParserBaseVisitor<Object> {

    private BufferedWriter out;

    /**
     * Visitor parse the sql file and print the result to the out(BufferWriter).
     * @param out
     */
    public Visitor(BufferedWriter out){
        super();
        this.out = out;
    }

    /**
     * print string to the out with a line feed.
     * @param string
     */
    private void println(String string){
        print(string+System.getProperty("line.separator"));
    }

    /**
     * print string to the out without a line feed.
     * @param string
     */
    private void print(String string) {
        try{
            out.write(string);
        } catch (IOException e){
            e.printStackTrace();
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
    public Object visitCreateTable(TinyDBParser.CreateTableContext ctx) {
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

        boolean succeed = GlobalManager.getDatabase().createTable(tableName, tupleDesc);
        if (!succeed) { // table already exists
            println("Table " + tableName + " already exists!");
        }
        return succeed;
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
        return super.visitInsertStatement(ctx);
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
