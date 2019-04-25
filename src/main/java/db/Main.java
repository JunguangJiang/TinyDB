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
            TinyDBOutput out = new TinyDBOutput(new BufferedWriter(new FileWriter(outFileName)));
            Server server = new Server(".");
            server.process(in, out);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
