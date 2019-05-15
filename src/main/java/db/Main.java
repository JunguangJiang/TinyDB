package db;

import db.client.Client;
import db.parser.TinyDBOutput;
import db.parser.TinyDBLexer;
import db.parser.TinyDBParser;
import db.parser.Visitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;

import java.io.*;
import java.util.Arrays;

/**
 * Test
 * Server path
 * Client
 */
public class Main {
    public static void main(String[] args){
        if (args.length == 0) {
            // An example which reads an sql txt and build a parser tree
            String path = "data/";
            String inFileName = path+"test.sql";
            String outFileName = path+"test.out";
            Server server = new Server(path);
            server.process(new File(inFileName), new File(outFileName));
            server.close();
        } else {
            String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
            switch (args[0]) {
                case "Server":
                    Server.main(newArgs);
                    break;
                case "Client":
                    Client.main(newArgs);
                    break;
                case "Test":
                    SqlTest.main(newArgs);
                    break;
                case "Help":
                    System.out.println(
                            "Test : run the system test\n" +
                            "Server [path] : start a server which has the root in `path` (default is `.`)\n" +
                            "Client : start a client\n"
                    );
                default:
                    break;
            }
        }
    }
}
