package db.client;

import jline.console.ConsoleReader;
import jline.console.completer.*;
import jline.console.history.FileHistory;
import jline.console.history.History;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import db.utils.utils;

public class Client {
    /**
     * connect to Server
     * @param args: the console arguments
     */
    private static Connection connectServer(String [] args)
            throws ClassNotFoundException, SQLException{
        Class.forName("db.JDBC.JDBCDriver");
        String url = getUrl(args);
        return DriverManager.getConnection(url);
    }

    /**
     * to check whether the SQL is the the import command
     * @param sql: the sql to check
     * @return  if is import sequence, then 1; if is import sequence but error filename, then -1;otherwise 0
     */
    private static int checkImportSequence(String sql) {
        String upperCaseSQL = sql.toUpperCase();

        if (upperCaseSQL.startsWith("IMPORT")) {
            String[] msg = sql.split(" ", 2);
            if (msg.length != 2) {
                System.out.println("Error input, please input the correct filename!");
                return -1; // import but error input
            }
            return 1; // import && correct input
        }
        return 0; // otherwise
    }

//    private static void handleImportSequence(Statement st, String filename, ) {
//
//    }

    private static long getRunTime(Statement st, String sql) throws SQLException{
        long startTime = System.currentTimeMillis();
        ResultSet rs = st.executeQuery(sql);
        long endTime = System.currentTimeMillis();
        System.out.println(rs.getString(0));
        return (endTime - startTime);
    }

    /**
     * to handle the import command
     * @param sql: the "import file.txt" Sql
     */
    private static void handleImportSequence(Statement st, String sql) throws NoSuchElementException {
        String msg = sql.split(" ", 2)[1];
        try {
            FileInputStream fip = new FileInputStream(new File(msg.substring(0, msg.length() - 1)));
            InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
            StringBuilder sb = new StringBuilder();
            int i = 0;
            long runTime = 0;
            while (reader.ready()) {
                int c = reader.read();
                sb.append((char) c);
                if (c == ';')
                    i++;
                if (i == 100) {
                    runTime += getRunTime(st, sb.toString());
                    sb.delete(0, sb.length());
                    i = 0;
                }
            }
            if (sb.length() > 0) {
                runTime += getRunTime(st, sb.toString());
            }
            System.out.println(String.format("Total execute time: %.3f sec.", runTime / 1000.0));
            reader.close();
            fip.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Read file " + msg + " fail!");
            throw new NoSuchElementException();
        }
    }

    /**
     * send message to the Server and receive message
     * @param conn: the socket
     * @param reader: the ConsoleReader to read message from console
     */
    private static void sendAndReceiveMessage(Connection conn, ConsoleReader reader) {
        while (true) {
            try {
                Statement st = conn.createStatement();
                String sql = readSql(reader).trim();
                if (sql.toUpperCase().equals("EXIT;"))
                    break;
                switch (checkImportSequence(sql)) {
                    case 0:
                        ResultSet rs = st.executeQuery(sql);
                        System.out.println(rs.getString(0));
                        break;
                    case 1:
                        long startTime = System.currentTimeMillis();
                        handleImportSequence(st, sql);
                        long endTime = System.currentTimeMillis();
                        System.out.println(String.format("Total execute time: %.3f sec.", (endTime - startTime) / 1000.0));
                        break;
                    case -1:
                }
                st.close();
            } catch (SQLException e) {
                System.out.println("Server is closed!");
                break;
            } catch (NoSuchElementException e) { /* Nothing to do */ }
        }
    }

    /**
     * from lower case commands to corresponding upper case commands, and return both
     * @param commands: lower case commands
     */
    private static ArrayList<String> getExCommands(String ...commands) {
        ArrayList<String> exCommands = new ArrayList<>();
        for (String command: commands) {
            exCommands.add(command);
            exCommands.add(command.toUpperCase());
        }
        return exCommands;
    }

    /**
     * build a completer
     * @param commandsList: the commandsList use to build a completer
     */
    private static Completer getCompleter(String[] ...commandsList) {
        List<Completer> completerList = new ArrayList<>();
        for (String[] commands: commandsList) {
            if (commands.length == 0)
                completerList.add(new NullCompleter());
            else
                completerList.add(new StringsCompleter(getExCommands(commands)));
        }
        return new ArgumentCompleter(completerList);
    }

    /**
     * new a String[] from some Strings
     * @param commands: the Strings
     */
    private static String[] _S(String ...commands) {
        return commands;
    }

    /**
     * make some complete
     * @param reader: the ConsoleReader to read message from console
     */
    private static void completeHelper(ConsoleReader reader) {
        reader.addCompleter(getCompleter(_S("create", "drop" ), _S("database", "table"), _S()));
        reader.addCompleter(getCompleter(_S("show", "shutdown;"), _S("database", "databases;", "table"), _S()));
        reader.addCompleter(getCompleter(_S("import"), _S()));
        reader.addCompleter(getCompleter(_S("insert into"), _S(), _S("values();"), _S()));
        reader.addCompleter(getCompleter(_S("select"), _S(), _S("from"), _S(), _S("where")));
        reader.addCompleter(getCompleter(_S("delete from"), _S()));
        reader.addCompleter(getCompleter(_S("use database"), _S()));
        reader.addCompleter(getCompleter(_S("update"), _S(), _S("set"), _S()));
        reader.addCompleter(getCompleter(_S("exit;"), _S()));
    }

    public static void main(String [] args) {
        try {
            Connection conn = connectServer(args);
            ConsoleReader reader = new ConsoleReader();
            reader.setExpandEvents(false);
            completeHelper(reader);
            sendAndReceiveMessage(conn, reader);
            conn.close();
        } catch(SQLException e) {
            System.out.println("Connect to server fail.");
        } catch (IOException e) {
            System.out.println("Console read fail.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * maintain a user-friendly history record
     * @param reader: the ConsoleReader to read message from console
     * @param sb: a StringBuilder
     * @return  the StringBuilder toString
     */
    private static String handleTerminalHistory (ConsoleReader reader, StringBuilder sb) {
        History history = reader.getHistory();
        if (history.size() > 1)
            history.removeLast();
        try {
            while (history.size() > 1) {
                int curIndex = history.index();
                history.moveToEnd();
                int offset = history.index() - history.size();
                history.moveTo(curIndex);
                CharSequence sequence = history.get(history.size() - 1 + offset);
                if (sequence.length() > 0 && sequence.charAt(sequence.length() - 1) == ';')
                    break;
                history.removeLast();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(history.size());
            throw e;
        }
        history.add(sb.toString());
        return sb.toString();
    }

    /**
     * get a SQL sentence from console
     * @param reader: the ConsoleReader to read message from console
     * @return  String: the SQL sentence
     */
    private static String readSql(ConsoleReader reader) {
        try {
            StringBuilder sb = new StringBuilder();
//            final FileHistory history = setupHistory(reader);
//            if (history != null) {
//                reader.setHistory(history);
//            }
            while (true) {
                String line = reader.readLine(sb.length() == 0 ? " TinyDB> " : "      -> ");
                int i = line.length() - 1;
                while (i >= 0 && line.charAt(i) == ' ')
                    --i;
                line = line.substring(0, i + 1);
                sb.append(line);
                if (line.endsWith(";")) {
                    return handleTerminalHistory (reader, sb);
                }
                else {
                    sb.append(" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    /**
     * from the terminal arguments to get the url
     * if not, give a tip to get the url
     * @param args: the terminal arguments of the Server
     * @return  String: the url
     */
    private static String getUrl(String [] args) {
        String url = "";
        if (args.length == 0) {
            System.out.println("Enter the url: ");
            try {
                ConsoleReader reader = new ConsoleReader();
                while (true) {
                    url = reader.readLine().trim();
                    if (testUrl(url)) {
                        return url;
                    } else {
                        System.out.println("Wrong url! Please enter again:  ");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            url = args[0];
        }
        return url;
    }

    /**
     * test a url whether satisfy a certain pattern
     * @param url: the url to test
     * @return true means it is satisfied;
     */
    private static Boolean testUrl(String url) {
//        String _pattern = "[12][0-9][0-9]|[1-9][0-9]|[0-9]";
        String pattern = "^jdbc:TinyDB://\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+$";
        String pattern2 = "^jdbc:TinyDB://[A-Za-z0-9+@&#/%?=~_|:;.,]+[A-Za-z0-9+@&#/%?=~_|]$";
        return Pattern.matches(pattern, url) || Pattern.matches(pattern2, url);
    }
}

// java -cp .\build\libs\TinyDB-1.0.jar db.Main Client jdbc:TinyDB://127.0.0.1:9528
//  import ./data/SQL.txt;