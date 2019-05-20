package db;

import db.parser.TinyDBOutput;
import db.parser.TinyDBParser;
import db.parser.Visitor;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.utils.utils;

public class Server {
    static private String sqlPath;
    static private ServerSocket serverSocket;
    static private Server db_server = null;
    private static List<Socket> clientSocketList = new ArrayList<>();
    /**
     *
     * @param sqlPath the sql path where catalog and all the databases are stored in
     */
     Server(String sqlPath) {
         Server.sqlPath = sqlPath;
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
        try (BufferedReader in = new BufferedReader(new FileReader(inFile))) {
            TinyDBOutput out = new TinyDBOutput(new BufferedWriter(new FileWriter(outFile)));
            this.process(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * process sql command and return result
     * @param sql: sql command
     */
    private String process(String sql) throws SQLException {
        String output_filename = sqlPath + "Client.out";
        File output = new File(output_filename);
        try (BufferedWriter outputBufferedWriter = new BufferedWriter(new FileWriter(output))){
            TinyDBOutput out = new TinyDBOutput(outputBufferedWriter);
            this.process(sql, out);
            return utils.readFile(output_filename);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("shutdown!!!"))
                throw new SQLException(e.getMessage());
            return "500 Internal Error";
        } catch (Exception e) {
            System.out.println("Read data files failed!");
            return "500 Internal Error";
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

    /**
     * new a server to handle commands and create a serverSocket to listen
     * @param arg: the directory to save data files
     */
    private static void openServer(String arg) {
        sqlPath = arg;
        db_server = new Server(sqlPath);
        try {
            serverSocket = new ServerSocket(9528);
        } catch (IOException e) {
            System.out.println("Server open failed!");
        }
    }

    public static void main(String[] args) {
        openServer(args[0]);
        while (true) {
            try {
                System.out.println("Listening: " + serverSocket.getLocalPort() + "...");
                Socket socket = serverSocket.accept();
                clientSocketList.add(socket);
                new SocketThread(socket).start();
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
        private SocketThread(Socket socket) {
            this.socket = socket;
        }

        /**
         * closeServer: close all client sockets, listen socket and server
         */
        private void closeServer() {
            try {
                for (Socket clientSocket: clientSocketList) {
                    if (!clientSocket.isClosed())
                        clientSocket.close();
                }
                serverSocket.close();
                db_server.close();
                System.out.println("Successfully shut down the Server.");
            } catch (IOException e1) {
                System.out.println("Fail to shut down the Server!!!");
            }
        }

        /**
         * run: override the thread function
         * get input from client and write back the SQL result
         */
        public void run() {
            if (socket == null) return;
            while (true) {
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    String sql = in.readUTF();
                    out.writeUTF(db_server.process(sql));
                } catch (SQLException e) {
                    closeServer();
                    break;
                } catch (IOException e) {
                    break;
                }
            }
        }
    }
}
