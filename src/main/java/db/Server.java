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
    /**
     *
     * @param sqlPath the sql path where catalog and all the databases are stored in
     */
    Server(String sqlPath) {
//        if (sqlPath.charAt(sqlPath.length() - 1) != '/' &&  sqlPath.charAt(sqlPath.length() - 1) != '\\') {
//            sqlPath += '/';
//        }
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

    private String sendFile(DataOutputStream socketOut, String filename) {
        try (FileReader reader = new FileReader(filename);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                socketOut.writeUTF(line  + System.lineSeparator());
            }
            socketOut.writeUTF(System.lineSeparator() + System.lineSeparator());
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
    private String processAndSend(DataOutputStream socketOut, String sql) {
        String output_filename = sqlPath + "Client.out";
        File output = new File(output_filename);
        try (BufferedWriter outputBufferedWriter = new BufferedWriter(new FileWriter(output))){
            TinyDBOutput out = new TinyDBOutput(outputBufferedWriter);
            this.process(sql, out);
            return sendFile(socketOut, output_filename);
        } catch (RuntimeException e) {
            return "500 Internal Error";
        } catch (IOException e) {
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

//        private static String handleImportSequence(Server server, String sql) throws NoSuchElementException {
//            String msg = sql.split(" ", 2)[1];
//            try {
//                FileInputStream fip = new FileInputStream(new File(msg.substring(0, msg.length() - 1)));
//                InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
//                StringBuilder sb = new StringBuilder();
//                StringBuilder rs = new StringBuilder();
//                int i = 0;
//                long runTime = 0;
//                long startTime, endTime;
//                while (reader.ready()) {
//                    int c = reader.read();
//                    sb.append((char) c);
//                    if (c == ';')
//                        i++;
//                    if (i == 100) {
//                        startTime = System.currentTimeMillis();
//                        rs.append(server.process(sb.toString()));
//                        endTime = System.currentTimeMillis();
//                        runTime += (endTime - startTime);
//                        sb.delete(0, sb.length());
//                        i = 0;
//                    }
//                }
//                if (sb.length() > 0) {
//                    startTime = System.currentTimeMillis();
//                    rs.append(server.process(sb.toString()));
//                    endTime = System.currentTimeMillis();
//                    runTime += (endTime - startTime);
//                }
//                rs.append(String.format("\n\nTotal execute time: %.3f sec.", runTime / 1000.0));
//                reader.close();
//                fip.close();
//                return rs.toString();
//            } catch (FileNotFoundException e) {
//                return String.format("\nThe current path is %s , please input a correct path.\n\n", System.getProperty("user.dir"));
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//                return String.format("Read file %s fail!", msg);
//            }
//        }
//
//        /**
//         * to check whether the SQL is the the import command
//         * @param sql: the sql to check
//         * @return  if is import sequence, then 1; if is import sequence but error filename, then -1;otherwise 0
//         */
//        private static Boolean checkImportSequence(String sql) {
//            String upperCaseSQL = sql.toUpperCase();
//            if (upperCaseSQL.startsWith("IMPORT")) {
//                String[] msg = sql.split(" ", 2);
//                if (msg.length != 2) {
//                    System.out.println("Error input, please input the correct filename!");
//                    return false; // import but error input
//                }
//                return true;
//            }
//            return false; // otherwise
//        }

        private static void writeBack(DataOutputStream out, String result) throws IOException{
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
                    String result;
                    result = server.processAndSend(out, sql);
                    if (result.length() > 0)
                        writeBack(out, result);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}

// java -cp .\build\libs\TinyDB-1.0.jar db.Main Server data