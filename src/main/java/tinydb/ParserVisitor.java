package tinydb;
import parser.TinyDBParser;
import parser.TinyDBParserBaseVisitor;

public class ParserVisitor extends TinyDBParserBaseVisitor<Void> {

    @Override
    public Void visitCreateTable(TinyDBParser.CreateTableContext ctx) {
        // TODO
        return super.visitCreateTable(ctx);
    }

    @Override
    public Void visitColumnDeclaration(TinyDBParser.ColumnDeclarationContext ctx) {
        // TODO
        return super.visitColumnDeclaration(ctx);
    }

    @Override
    public Void visitDropDatabase(TinyDBParser.DropDatabaseContext ctx) {
        return super.visitDropDatabase(ctx);
    }


}
