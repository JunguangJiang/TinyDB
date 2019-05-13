package db;

import db.parser.TinyDBOutput;
import db.parser.TinyDBParser;
import db.parser.Visitor;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.*;
import db.utils.utils;

public class Server {
    String sqlPath;
    /**
     *
     * @param sqlPath the sql path where catalog and all the databases are stored in
     */
    public Server(String sqlPath) {
        this.sqlPath = sqlPath;
        if (!open()) {
            System.out.println("Can not load " + sqlPath);
            System.exit(-1);
        }
    }

    /**
     * Open the Server:
     *      load the Catalog into the memory (call GlobalManager.getCatalog().open(sqlPath))
     *      load the default Database
     * @return whether the loading is successful
     */
    public boolean open() {
        GlobalManager.getCatalog().load(sqlPath);
        return true;
    }

    /**
     * Close the Server:
     *      write the Catalog back to the disk (call GlobalManager.getCatalog().close())
     *      write the current Database to the disk
     *      flush the BufferPool
     */
    public void close() {
        GlobalManager.getCatalog().persist();
    }

    /**
     * process sql commands from BufferReader and write the result into the TinyDBOutput
     * @param in
     * @param out
     */
    private void process(BufferedReader in, TinyDBOutput out) {
        try {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            in.close();

            String s = stringBuilder.toString().toUpperCase();//all sql word should be upper case!
            TinyDBParser parser = utils.createParser(s, out);
            ParseTree tree = parser.root();
            Visitor visitor = new Visitor(out, false);
            visitor.visit(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * process sql commands from inFile and write the result into the outFile
     * @param inFile
     * @param outFile
     * @see Main give an example using process
     */
    public void process(File inFile, File outFile) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(inFile));
            TinyDBOutput out = new TinyDBOutput(new BufferedWriter(new FileWriter(outFile)));
            this.process(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server server = new Server(".");
        // TODO
    }
}
