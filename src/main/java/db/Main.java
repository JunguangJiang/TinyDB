package db;

import db.client.Client;

import java.io.*;
import java.util.Arrays;

/**
 * Test
 * WebServer path
 * Client
 */
public class Main {
    public static void main(String[] args){
        if (args.length == 0) {
            // An example which reads an sql txt and build a parser tree
            String path = "system_test_data/select_join/";
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
                            "WebServer [path] : start a server which has the root in `path` (default is `.`)\n" +
                            "Client : start a client\n"
                    );
                default:
                    break;
            }
        }
    }
}
