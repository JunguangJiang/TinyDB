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
        String path = "F:/TinyDBdata/";
        String inFileName = path+"in.sql";
        String outFileName = path+"in.out";
        Server server = new Server(path);
        server.process(new File(inFileName), new File(outFileName));
    }
}
