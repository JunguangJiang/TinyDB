package db;

import db.error.SQLError;
import db.file.BufferPool;
import db.file.DbFile;
import db.file.Table;
import db.parser.TinyDBOutput;
import db.parser.TinyDBParser;
import db.parser.Visitor;
import db.query.QueryResult;
import db.tuple.TupleDesc;

import java.io.*;
import java.util.*;
import db.utils.utils;
import javafx.util.Pair;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Each database keeps track of multiple tables
 *
 */
public class Database {
    private HashMap<String, Integer> nameIdMap;
    private HashMap<Integer, Table> idTableMap;
    private HashMap<Integer, String> idSqlMap; // use to save the sql sentence of certain database
    private static int id = 0; // Each table has a unique id
    public String databaseName; // The name of database
    public String sqlPath; // The path to store sql data

    Database() {
        nameIdMap = new HashMap<>();
        idTableMap = new HashMap<>();
        idSqlMap = new HashMap<>();
    }

    Database(String sqlPath, String databaseName) {
        this();
        this.databaseName = databaseName;
        this.sqlPath = sqlPath;
    }

    /**
     * Create a new table and its associated file on the disk.
     * If the table already exists, then return false.
     * We have to ensure each Table has different ids and names
     * @param tableName the name of the new table
     * @param isBTree whether the table should build on BTree
     * @param hasPrimaryKeyConstraint whether the Table has primary key constraint
     * @return the result of the query
     */
    public QueryResult createTable(String tableName, TupleDesc tupleDesc, boolean isBTree, boolean hasPrimaryKeyConstraint) throws SQLError {
        return createTable(tableName, tupleDesc, "", false, isBTree, hasPrimaryKeyConstraint);
    }

    /**
     * create a Table based on BTree
     * @param tableName the name of the new table
     * @param tupleDesc the descriptor of the tuple
     * @return
     */
    public QueryResult createTable(String tableName, TupleDesc tupleDesc) throws SQLError{
        return createTable(tableName,tupleDesc,true,true);
    }

    /**
     * Create a new table and its associated file on the disk.
     * If the table already exists, then return false.
     * We have to ensure each Table has different ids and names
     * @param tableName the name of the new table
     * @param tupleDesc the descriptor of the tuple
     * @param sql
     * @param isLog
     * @param isBTree whether the table should build on BTree
     * @param hasPrimaryKeyConstraint whether the Table has primary key constraint
     * @return
     */
    public QueryResult createTable(String tableName, TupleDesc tupleDesc, String sql, Boolean isLog,
                                   boolean isBTree, boolean hasPrimaryKeyConstraint) throws SQLError {
        if (tupleDesc.getSize() >= BufferPool.getPageSize() / 2){
            throw new SQLError("Row size too large. The maximum row size for table is " + BufferPool.getPageSize() / 2);
        }
        if (getTable(tableName) != null) {
            return new QueryResult(false, "Table " + tableName + " already exists.");
        } else {
            File file = new File(utils.getFilePath(sqlPath, databaseName, String.format("%s.db", tableName)));
            if (!isLog) {
                try {
                    if (!file.createNewFile())
                        System.out.println(String.format("Create %s.db failed", tableName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Table table = new Table(id, tableName, tupleDesc, file, isBTree, hasPrimaryKeyConstraint);
            nameIdMap.put(tableName, id);
            idTableMap.put(id, table);
            idSqlMap.put(id, sql);
            id++;
            return new QueryResult(true, "Query OK, 0 row affected.");
        }
    }

    /**
     * Delete an old table and its associated file on the disk.
     * If the table doesn't exist, then return false.
     * @param tableName the name of the old table
     * @return
     */
    public QueryResult dropTable(String tableName) {
        Table table = this.getTable(tableName);
        if (table == null) {
            return new QueryResult(false, "Table " + tableName + " doesn't exist.");
        } else {
            table.file.delete();
            Integer id = table.getId();
            idTableMap.remove(id);
            nameIdMap.remove(tableName);
            idSqlMap.remove(id);
            return new QueryResult(true, "Query OK, 0 row affected.");
        }
    }

    /**
     * @param tableName name of the Table
     * @return the DbFile associated with the Table if it exists, otherwise null
     */
    public DbFile getDbFile(String tableName) throws NullPointerException{
        return getDbFile(getTableId(tableName));
    }

    /**
     * @param tableId id of the Table
     * @return the DbFile associated with the Table if it exists, otherwise null
     */
    public DbFile getDbFile(Integer tableId) throws NullPointerException{
        return getTable(tableId).getDbFile();
    }

    /**
     * @param tableName name of the Table
     * @return the id of the Table if it exists, otherwise null
     */
    public Integer getTableId(String tableName){
        return nameIdMap.get(tableName);
    }

    /**
     * @param tableName name of the table
     * @return the Table if it exists, otherwise null
     */
    public Table getTable(String tableName) throws NullPointerException{
        return getTable(getTableId(tableName));
    }

    /**
     * @param tableId
     * @return the Table if it exists, otherwise return null
     */
    public Table getTable(Integer tableId) {
        return idTableMap.get(tableId);
    }

    /**
     *
     * @return The names of all the tables in the database
     */
    public String[] getTableNames(){
        Set<String> tableNames = nameIdMap.keySet();
        String[] results = new String[tableNames.size()];
        Iterator<String> iterator = tableNames.iterator();
        int i = 0;
        while (iterator.hasNext())
            results[i++] = iterator.next();
        return results;
    }

    /**
     * parseDatabaseScript: run the create table sql sentence on the DatabaseScript
     * @param content: the record on the DatabaseScript
     */
    private void parseDatabaseScript(String content, File outFile) {
        try {
            String[] lines = content.split(System.lineSeparator());
            assert lines.length > 0;
            Integer n = Integer.valueOf(lines[0]); // the number of tables
            StringBuilder sqlString = new StringBuilder();
            for(int i=1+3*n; i<lines.length; i++) {
                sqlString.append(lines[i]);
            }
            BufferedWriter log = new BufferedWriter(new FileWriter(outFile));
            TinyDBOutput out = new TinyDBOutput(log);
            TinyDBParser parser = utils.createParser(sqlString.toString(), out);
            ParseTree tree = parser.root();
            Visitor visitor = new Visitor(out, true);
            visitor.visit(tree);
            log.close();
            String[] names = Arrays.copyOfRange(lines,1,n+1);
            String[] incrementNumbers = Arrays.copyOfRange(lines, n+1, 2*n+1);
            String[] counts = Arrays.copyOfRange(lines,2*n+1,3*n+1);
            for (int i = 0; i < names.length; ++i) {
                Table table = idTableMap.get(nameIdMap.get(names[i]));
                table.autoIncrementNumber = Long.parseLong(incrementNumbers[i]);
                table.count = Long.parseLong(counts[i]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * load the tables of the database from the file
     * @param sqlPath: the directory where you save the metadata
     * @param databaseName
     */
    public void load(String sqlPath, String databaseName) throws NoSuchElementException{
        this.databaseName = databaseName;
        this.sqlPath = sqlPath;
        this.nameIdMap.clear();
        this.idSqlMap.clear();
        this.idTableMap.clear();
        String root = utils.getFilePath(sqlPath, this.databaseName);
        String databaseScriptFilename = utils.getFilePath(root, String.format("%s.script", databaseName));
        File log = new File(root + "log.txt");
        try {
            parseDatabaseScript(utils.readFile(databaseScriptFilename), log);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File " + databaseScriptFilename + " not exist");
            throw new NoSuchElementException();
        } finally {
            if (!log.delete())
                System.out.println("Log file delete failed! Please remove it by hand.");
        }
    }

    /**
     * get the meta info of all the tables
     * @return
     */
    private String getTablesMeta() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(idTableMap.size());
        stringBuilder.append(System.lineSeparator());
        for (Integer id: idTableMap.keySet()) {
            stringBuilder.append(getTable(id).getName());
            stringBuilder.append(System.lineSeparator());
        }
        for (Integer id: idTableMap.keySet()) {
            stringBuilder.append(getTable(id).autoIncrementNumber);
            stringBuilder.append(System.lineSeparator());
        }
        for (Integer id: idTableMap.keySet()) {
            stringBuilder.append(getTable(id).count);
            stringBuilder.append(System.lineSeparator());
        }
        for (Integer id: idTableMap.keySet()) {
            stringBuilder.append(idSqlMap.get(id));
            stringBuilder.append(";");
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * newDatabaseDirectory: create directory for certain database
     * @param root: the directory of certain database's message
     */
    private Boolean newDatabaseDirectory(String root) {
        File rootFile = new File(root);
        if (!rootFile.exists()) {
            if (!rootFile.mkdirs()) {
                System.out.print("Persist database " + this.databaseName + " failed!");
                return false;
            }
        }
        return true;
    }

    /**
     * persist: write the tables of the database back to the file
     */
    public void persist() {
        String root = utils.getFilePath(this.sqlPath, this.databaseName);
        if (!newDatabaseDirectory(root)) {
            return;
        }
        String databaseScriptFilename = utils.getFilePath(root, String.format("%s.script", databaseName));
        try {
            File f = new File(databaseScriptFilename);
            OutputStream fop = new FileOutputStream(f);
            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
            writer.append(getTablesMeta());
            writer.close();
            fop.close();
        }
        catch (Exception e) {
            System.out.println("Failed to persist '" + databaseScriptFilename);
        }
    }

}
