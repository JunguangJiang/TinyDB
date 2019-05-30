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
        String helpInfo = "Test : run the system test\n" +
                "Server [path] : start a server which has the root in `path` (default is `.`)\n" +
                "Client [url]: start a client\n";
        if (args.length == 0) {
              System.out.println(helpInfo);
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
                    System.out.println(helpInfo);
                default:
                    break;
            }
        }
    }
}
