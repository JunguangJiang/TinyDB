package db.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TinyDBOutput extends BaseErrorListener {
    private ArrayList<String> buffer;
    private BufferedWriter out;

    public TinyDBOutput(BufferedWriter out) {
        buffer = new ArrayList<>();
        this.out = out;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        String exception = "Syntax Error: " + line + ":" + charPositionInLine + " " + msg;
        buffer.add(exception);
    }

    public void print(SemanticError semanticError) {
        buffer.add(semanticError.toString());
    }

    public void print(String msg) {
        buffer.add(msg);
    }

    public void print(double seconds) {
        buffer.add(" (" + seconds + " sec)");
    }

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
