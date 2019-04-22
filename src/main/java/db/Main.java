package db;

import db.parser.TinyDBOutput;
import db.parser.TinyDBLexer;
import db.parser.TinyDBParser;
import db.parser.Visitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;

import java.io.*;

public class Main {
    public static void main(String[] args){
        // An example which reads an sql txt and build a parser tree
        String inFileName = "data/sql.txt";
        String outFileName = "data/out.txt";
        try{
            BufferedReader in = new BufferedReader(new FileReader(inFileName));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            in.close();
            String s = stringBuilder.toString().toUpperCase();//all sql word should be upper case!
            System.out.println(s);

            TinyDBLexer lexer = new TinyDBLexer(CharStreams.fromString(s));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            TinyDBParser parser = new TinyDBParser(tokenStream);
            parser.removeErrorListeners();
            TinyDBOutput output = new TinyDBOutput(new BufferedWriter(new FileWriter(outFileName)));
            parser.addErrorListener(output);

            ParseTree tree = parser.root();

            Visitor visitor = new Visitor(output);
            visitor.visit(tree);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
