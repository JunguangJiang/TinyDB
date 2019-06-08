package db.JDBC;

import java.io.*;
import java.net.Socket;
import java.sql.*;

public class JDBCStatement implements Statement {

    private Socket conn;

    JDBCStatement(Socket conn) {
        this.conn = conn;
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        try {
            OutputStream outToServer = conn.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
//            byte[] data = (sql + System.lineSeparator()).getBytes();
//            out.write(data, 0, data.length);
            char[] eos = {(char)-1};
            out.writeUTF(sql + new String(eos));
//            InputStream inFromServer = conn.getInputStream();
//            DataInputStream in = new DataInputStream(inFromServer);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inFromServer));
//            StringBuilder sb = new StringBuilder();
//            String firstLine = reader.readLine();
//            sb.append(firstLine);
//            sb.append("\r\n");
//            while (reader.ready()) {
//                sb.append(reader.readLine());
//                sb.append("\r\n");
//            }
//            String temp;
//            String sep = System.lineSeparator() + System.lineSeparator();
//            while (true){
//                temp = in.readUTF();

//                if (temp.endsWith(sep)) {
//                    temp = temp.substring(0, temp.length() - sep.length());
//                    sb.append(temp);
//                    break;
//                }
//                sb.append(temp);
//            }
//            String rs = sb.toString();
//            if (rs.length() >= 2)
//                rs = rs.substring(2);
//            String rs = in.readUTF();
            return new JDBCResultSet(new DataInputStream(conn.getInputStream()));
        }
        catch (Exception e) {
//            e.printStackTrace();
            throw new SQLException("Server closed!");
        }
    }

    public ResultSet executeFile(String filename) throws SQLException {
        try {
            File file = new File(filename);
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            final int BUF_SIZE = 0xff00;
            char[] itemBuf = new char[BUF_SIZE];
            int nRead;
            while ((nRead = dataIn.read(itemBuf, 0, BUF_SIZE)) != -1) {
                out.writeUTF(new String(itemBuf, 0, nRead));
            }
            char[] eos = {(char)-1};
            out.writeUTF(new String(eos));
            return new JDBCResultSet(new DataInputStream(conn.getInputStream()));
        } catch (FileNotFoundException e) {
            System.out.printf("\nThe current path is %s , please input a correct path.\n\n", System.getProperty("user.dir"));
            throw new SQLException("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new SQLException("Server closed");
        }
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return 0;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return 0;
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {

    }

    @Override
    public int getMaxRows() throws SQLException {
        return 0;
    }

    @Override
    public void setMaxRows(int max) throws SQLException {

    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {

    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return 0;
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {

    }

    @Override
    public void cancel() throws SQLException {

    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException {

    }

    @Override
    public void setCursorName(String name) throws SQLException {

    }

    @Override
    public boolean execute(String sql) throws SQLException {
        return false;
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return null;
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return 0;
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return false;
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {

    }

    @Override
    public int getFetchDirection() throws SQLException {
        return 0;
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {

    }

    @Override
    public int getFetchSize() throws SQLException {
        return 0;
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return 0;
    }

    @Override
    public int getResultSetType() throws SQLException {
        return 0;
    }

    @Override
    public void addBatch(String sql) throws SQLException {

    }

    @Override
    public void clearBatch() throws SQLException {

    }

    @Override
    public int[] executeBatch() throws SQLException {
        return new int[0];
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return false;
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return null;
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return 0;
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return 0;
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return 0;
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return false;
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return false;
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return false;
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return 0;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {

    }

    @Override
    public boolean isPoolable() throws SQLException {
        return false;
    }

    @Override
    public void closeOnCompletion() throws SQLException {

    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
