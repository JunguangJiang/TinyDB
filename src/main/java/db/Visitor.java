package db;

import db.parser.TinyDBParser;
import db.parser.TinyDBParserBaseVisitor;

public class Visitor extends TinyDBParserBaseVisitor<Void> {
    @Override
    public Void visitCreateDatabase(TinyDBParser.CreateDatabaseContext ctx) {

        return super.visitCreateDatabase(ctx);
    }
}
