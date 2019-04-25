package db.parser;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Semantic error which is used in the TinyDB Visitor
 */
public class SemanticError extends Exception {
    private int line;
    private int colunmn;
    private String msg;

    /**
     *
     * @param msg the error message
     * @param context the ParserRule context
     */
    public SemanticError(String msg, ParserRuleContext context){
        super();
        if (context != null) {
            line = context.start.getLine();
            colunmn = context.start.getCharPositionInLine();
        } else {
            line = 0;
            colunmn = 0;
        }
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SemanticError: " + line + ":" + colunmn + " " + msg;
    }
}
