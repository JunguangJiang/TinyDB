/**
 * some common functions
 */
package db.utils;

import db.parser.TinyDBLexer;
import db.parser.TinyDBOutput;
import db.parser.TinyDBParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class utils {
    /**
     * readFile:
     *      open a file and return the content
     *      use UTF-8
     * @param filename: the name of the file in string
     * @return the content of file
     */
    public static String readFile(String filename) throws Exception {
        File f = new File(filename);
        FileInputStream fip = new FileInputStream(f);
        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        StringBuilder sb = new StringBuilder();
        while (reader.ready())
            sb.append((char) reader.read());
        reader.close();
        fip.close();
        return sb.toString();
    }

    /**
     * @param s: the raw sentence of the query sentence
     * @param out
     * @return the content of file
     */
    public static TinyDBParser createParser(String s, TinyDBOutput out) {
        TinyDBLexer lexer = new TinyDBLexer(CharStreams.fromString(s));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        TinyDBParser parser = new TinyDBParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(out);
        return parser;
    }

    public static boolean removeDiretory(File dir) {
        File[] files = dir.listFiles();
        if (files == null)
            return true;
        for (File file: files) {
            if(file.isDirectory()) {
                if (!removeDiretory(file))
                    return false;
            }else {
                if (!file.delete())
                    return false;
            }
        }
        return dir.delete();
    }
}
