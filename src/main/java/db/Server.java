package db;

import db.parser.TinyDBLexer;
import db.parser.TinyDBOutput;
import db.parser.TinyDBParser;
import db.parser.Visitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.xml.crypto.Data;
import java.io.*;

public class Server {
    String sqlPath;
    /**
     *
     * @param sqlPath the sql path where catalog and all the databases are stored in
     */
    public Server(String sqlPath) {
        this.sqlPath = sqlPath;
        if (!load()) {
            System.out.println("Can not load " + sqlPath);
            System.exit(-1);
        }
    }

    /**
     * load the catalog into the memory
     * @return whether the loading is successful
     */
    private boolean load() {
        // TODO
        return true;
    }

    /**
     * process sql commands from BufferReader and write the result into the TinyDBOutput
     * @param in
     * @param out
     * @see Main give an example using process
     */
    public void process(BufferedReader in, TinyDBOutput out) {
        try{
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            in.close();

            String s = stringBuilder.toString().toUpperCase();//all sql word should be upper case!
            TinyDBLexer lexer = new TinyDBLexer(CharStreams.fromString(s));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            TinyDBParser parser = new TinyDBParser(tokenStream);
            parser.removeErrorListeners();
            parser.addErrorListener(out);

            ParseTree tree = parser.root();

            Visitor visitor = new Visitor(out);
            visitor.visit(tree);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server server = new Server(".");
        // TODO
    }
}
