package db.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A buffered writer used in the TinyDB parser.
 */
public class TinyDBOutput extends BaseErrorListener {
    private ArrayList<String> buffer;
    private BufferedWriter out;

    public TinyDBOutput(BufferedWriter out) {
        buffer = new ArrayList<>();
        this.out = out;
    }

    /**
     * Handle the syntax error.
     * Don't have to call this function explicitly.
     */
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        String exception = "Syntax Error: " + line + ":" + charPositionInLine + " " + msg;
        buffer.add(exception);
    }

    /**
     * Print a semantic error to the buffer
     * @param semanticError
     */
    public void print(SemanticError semanticError) {
        buffer.add(semanticError.toString());
    }

    /**
     * Print a string msg to the buffer
     * @param msg
     */
    public void print(String msg) {
        buffer.add(msg);
    }

    /**
     * Print a execution time to the buffer
     * @param seconds
     */
    public void print(double seconds) {
        buffer.add(" (" + seconds + " sec)");
    }

    /**
     * flush the buffer
     */
    public void flush() {
        try {
            for (String error : buffer) {
                out.write(error);
                out.newLine();
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
