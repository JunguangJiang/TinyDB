package db;

import db.parser.TinyDBOutput;
import db.parser.TinyDBParser;
import db.parser.Visitor;
import jline.console.ConsoleReader;
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
    private String sqlPath;
    private ServerSocket serverSocket;
    private List<Socket> clientSocketList;
    /**
     *
     * @param sqlPath the sql path where catalog and all the databases are stored in
     */
    Server(String sqlPath) {
        if (sqlPath.charAt(sqlPath.length() - 1) != '/' &&  sqlPath.charAt(sqlPath.length() - 1) != '\\') {
            sqlPath += '/';
        }
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
    private String process(String sql) {
        String output_filename = sqlPath + "Client.out";
        File output = new File(output_filename);
        try (BufferedWriter outputBufferedWriter = new BufferedWriter(new FileWriter(output))){
            TinyDBOutput out = new TinyDBOutput(outputBufferedWriter);
            this.process(sql, out);
            return utils.readFile(output_filename);
        } catch (RuntimeException e) {
//            e.printStackTrace();
//            if (e.getMessage() != null && e.getMessage().equals("shutdown!!!"))
//                throw new SQLException(e.getMessage());
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
                System.out.println("Please input the working path: ");
                path = reader.readLine();
            }
            while (true) {
                if (testPath(path)) {
                    System.out.println("Opening the Server now...");
                    return path;
                } else {
                    System.out.println("Error directory, please input again:");
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

                    String result = server.process(sql);
                    int bufferSize = 60000;
                    int i =0;
                    int sum = 0;

                    while(i < result.length()){
                        int endIdx = java.lang.Math.min(result.length(), i + bufferSize);
                        String jsosPart = result.substring(i,endIdx);
                        out.writeUTF(jsosPart);
                        sum += jsosPart.length();
                        i += bufferSize;
                    }
                    out.writeUTF(System.lineSeparator() + System.lineSeparator());
                    assert sum == result.length();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}

// java -cp .\build\libs\TinyDB-1.0.jar db.Main Server data