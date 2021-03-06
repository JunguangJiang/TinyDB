package db;

import db.parser.TinyDBOutput;
import db.parser.TinyDBParser;
import db.parser.Visitor;
import jline.console.ConsoleReader;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import db.utils.utils;

public class Server {
    private String sqlPath;
    private ServerSocket serverSocket;
    private List<Socket> clientSocketList;

    private class Pair{
        long time;
        String result;

        Pair(String r, long t) {
            result = r;
            time = t;
        }
    }
    /**
     *
     * @param sqlPath the sql path where catalog and all the databases are stored in
     */
    Server(String sqlPath) {
        this.sqlPath = sqlPath;
        GlobalManager.getCatalog().load(sqlPath);

        try {
            serverSocket = new ServerSocket(9528);
        } catch (IOException e) {
            System.out.println("Server open failed!");
        }
        clientSocketList = new ArrayList<>();
    }

    /**
     * Close the Server:
     *      close the socket connection
     *      write the Catalog back to the disk (call GlobalManager.getCatalog().close())
     *      write the current Database to the disk
     *      flush the BufferPool
     */
    public void close() {
        try {
            for (Socket clientSocket: clientSocketList) {
                if (!clientSocket.isClosed())
                    clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Fail to close socket!!!");
        }

        GlobalManager.getCatalog().persist();
        Database database = GlobalManager.getDatabase();
        if (database != null) {
            GlobalManager.getDatabase().persist();
        }
        try {
            GlobalManager.getBufferPool().flushAllPages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * process sql commands from BufferReader and write the result into the TinyDBOutput
     * @param in: in
     * @param out: out
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
            this.process(stringBuilder.toString(), out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * process sql commands from inFile and write the result into the outFile
     * @param inFile: inFile
     * @param outFile: outFile
     * @see Main give an example using process
     */
    void process(File inFile, File outFile) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inFile), "UTF-8"))) {
            TinyDBOutput out = new TinyDBOutput(new BufferedWriter(new FileWriter(outFile)));
            this.process(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sendFile(DataOutputStream socketOut, String filename) {
        try (DataInputStream reader = new DataInputStream(new FileInputStream(filename))) {
            byte[] buf = new byte[0xffffff];  // 1MB
            int num;
            while((num = reader.read(buf)) != -1){
                socketOut.write(buf, 0, num);
            }
            return "";
        } catch (IOException e) {
            System.out.println("Read data files failed!");
            return "500 Internal Error";
        }
    }

    /**
     * process sql command and return result
     * @param sql: sql command
     */
    private Pair processAndSend(DataOutputStream socketOut, String sql) {
        String output_filename = utils.getFilePath(sqlPath, "Client.out");
        File output = new File(output_filename);
        try (BufferedWriter outputBufferedWriter = new BufferedWriter(new FileWriter(output))){
            TinyDBOutput out = new TinyDBOutput(outputBufferedWriter);
            long startTime = System.currentTimeMillis();
            this.process(sql, out);
            long endTime = System.currentTimeMillis();
            return new Pair(sendFile(socketOut, output_filename), endTime - startTime);
        } catch (RuntimeException e) {
            return new Pair("500 Internal Error", 0L);
        } catch (IOException e) {
            System.out.println("Read data files failed!");
            return new Pair("500 Internal Error", 0L);
        } finally {
            if (!output.delete()) {
                System.out.println("Delete client.out failed! Please delete it manually.");
            }
        }
    }

    /**
     * use the parser to process sql command
     * @param sql: sql command
     * @param out: out
     */
    private void process(String sql, TinyDBOutput out) {
        String s = sql.toUpperCase();
        TinyDBParser parser = utils.createParser(s, out);
        ParseTree tree = parser.root();
        Visitor visitor = new Visitor(out, false);
        visitor.visit(tree);
    }

    private static Boolean testPath(String path) {
        File file = new File(path);
        return (file.exists() && file.isDirectory());
    }

    private static String getPath(String[] args) {
        String path = "";
        if (args.length != 0) {
            path = args[0];
        }
        try {
            ConsoleReader reader = new ConsoleReader();
            if (path.equals("")) {
                System.out.printf("\nThe current path is %s\n\n", System.getProperty("user.dir"));
                System.out.println("Please input a folder name to save data: ");
                path = reader.readLine();
            }
            while (true) {
                if (testPath(path)) {
                    System.out.println("Opening the Server now...");
                    return path;
                } else {
                    System.out.println("Error directory, please input again:");
                    System.out.printf("The current path is %s\n", System.getProperty("user.dir"));
                    path = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static void main(String[] args) {
        String path = getPath(args);
        Server server = new Server(path);
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("persist before shutdown");
            server.close();
        }));

        while (true) {
            try {
                System.out.println("Listening: " + server.serverSocket.getLocalPort() + "...");
                Socket socket = server.serverSocket.accept();
                server.clientSocketList.add(socket);
                new SocketThread(socket, server).start();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                System.out.println("Close Server!");
                break;
            }
        }
    }

    /**
     * SocketThread: the thread to handle client
     */
    static class SocketThread extends Thread {

        private Socket socket;
        private Server server;
        private SocketThread(Socket socket, Server server) {
            this.socket = socket;
            this.server = server;
        }

        private static void writeBack(DataOutputStream out, String result) throws IOException{
            result += "\r\n";
            byte[] data = result.getBytes();
            out.write(data, 0, data.length);
        }

        /**
         * run: override the thread function
         * get input from client and write back the SQL result
         */
        public void run() {
            if (socket == null) return;
            while (true) {
                try {
                    DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                    String inputFilename = utils.getFilePath(server.sqlPath, "client.in");
                    FileWriter fileWriter = new FileWriter(inputFilename);

                    while (true) {
                        String temp = dataIn.readUTF();
                        if (temp.charAt(temp.length() - 1) == (char)-1) {
                            fileWriter.write(temp.substring(0, temp.length() - 1));
                            break;
                        }
                        fileWriter.write(temp);
                    }


                    fileWriter.close();

                    FileReader fileReader = new FileReader(inputFilename);
                    BufferedReader in = new BufferedReader(fileReader);
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                    StringBuilder total = new StringBuilder();
                    StringBuilder current = new StringBuilder();
                    long runTime = 0;
                    int c;
                    while ((c = in.read()) != -1 || total.length() > 0) {
                        if (c != -1)
                            current.append((char)c);
                        if (c == -1 || c == ';') {
                            if (c == -1 || total.length() + current.length() > 0xffff) {
                                Pair result = server.processAndSend(out, total.toString());
                                runTime += result.time;
                                if (result.result.length() > 0)
                                    writeBack(out, result.result);
                                total.delete(0, total.length());
                            }
                            if (c == -1)
                                break;
                            total.append(current);
                            current.delete(0, current.length());
                        }
                    }
                    writeBack(out, String.format("Execute Time: %.3f", runTime / 1000.0)
                            + "\r\n\r\n");
                    fileReader.close();
                    in.close();
                    if (!(new File(inputFilename)).delete())
                        System.out.println("Delete " + inputFilename + " failed.");
                } catch (IOException e) {
//                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}