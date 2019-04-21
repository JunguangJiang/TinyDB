package db;

import db.parser.TinyDBLexer;
import db.parser.TinyDBParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        String fileName = "data/sql.txt";
        try{
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                // stringBuilder.append(line);
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            in.close();
            String s = stringBuilder.toString().toUpperCase();
            System.out.println(s);
            TinyDBLexer lexer = new TinyDBLexer(new ANTLRInputStream(s));
//        TinyDBLexer lexer = new TinyDBLexer(new ANTLRInputStream(sqlContent.toUpperCase()));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            TinyDBParser parser = new TinyDBParser(tokenStream);
            ParseTree tree = parser.root();
            System.out.println(tree.toStringTree(parser));
            System.out.println();

            System.out.println("Visitor");
            Visitor visitor = new Visitor();
            visitor.visit(tree);
            System.out.println();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
