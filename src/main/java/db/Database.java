package db;

import com.sun.org.apache.xpath.internal.operations.Bool;
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

    Database() {
        nameIdMap = new HashMap<>();
        idTableMap = new HashMap<>();
        idSqlMap = new HashMap<>();
    }

    /**
     * Create a new table and its associated file on the disk.
     * If the table already exists, then return false.
     * We have to ensure each Table has different ids and names
     * @param tableName the name of the new table
     * @param tupleDesc the descriptor of the tuple
     * @return the result of the query
     */
    public QueryResult createTable(String tableName, TupleDesc tupleDesc) {
        return createTable(tableName, tupleDesc, "", false);
    }

    /**
     * Create a new table and its associated file on the disk.
     * If the table already exists, then return false.
     * We have to ensure each Table has different ids and names
     * @param tableName the name of the new table
     * @param tupleDesc the descriptor of the tuple
     * @return
     */
    public QueryResult createTable(String tableName, TupleDesc tupleDesc, String sql, Boolean isLog) {
        if (getTable(tableName) != null) {
            return new QueryResult(false, "Table " + tableName + " already exists.");
        } else {
            // TODO currently we create file in the same directory,
            //  we need to create file in a tree structure
            File file = new File(tableName + ".db");
            if (!isLog) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Table table = new Table(id, tableName, tupleDesc, file);
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
    public DbFile getDbFile(String tableName) {
        return getDbFile(getTableId(tableName));
    }

    /**
     * @param tableId id of the Table
     * @return the DbFile associated with the Table if it exists, otherwise null
     */
    public DbFile getDbFile(Integer tableId) {
        return getTable(tableId).getDbFile();
    }

    /**
     * @param tableName name of the Table
     * @return the id of the Table if it exists, otherwise null
     */
    public Integer getTableId(String tableName) {
        return nameIdMap.get(tableName);
    }

    /**
     * @param tableName name of the table
     * @return the Table if it exists, otherwise null
     */
    public Table getTable(String tableName) {
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
            content = content.split("\n", 2)[1];
            TinyDBOutput out = new TinyDBOutput(new BufferedWriter(new FileWriter(outFile)));
            TinyDBParser parser = utils.createParser(content, out);
            ParseTree tree = parser.root();
            Visitor visitor = new Visitor(out, true);
            visitor.visit(tree);
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
        String root = sqlPath + this.databaseName + "/";
        String databaseScriptFilename = root + String.format("%s.script", databaseName);
        try {
            parseDatabaseScript(utils.readFile(databaseScriptFilename), new File(root + "log.txt"));
        } catch (Exception e) {
            System.out.println("File " + databaseScriptFilename + " not exist");
            throw new NoSuchElementException();
        }
    }

    /**
     * @param iterator: the iterator of tablesName
     */
    private String getTablesMessage(Iterator<String> iterator) {
        boolean first = true;
        StringBuilder sbSql = new StringBuilder();
        StringBuilder sbTableNames = new StringBuilder();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (first)
                first = false;
            else
                sbTableNames.append("\t");
            sbTableNames.append(name);
            sbSql.append(idSqlMap.get(nameIdMap.get(name)));
            sbSql.append(";\n");
        }
        sbTableNames.append("\n");
        return sbTableNames.toString() + sbSql.toString();
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
     * @param sqlPath: the directory where you save the metadata
     */
    public void persist(String sqlPath) {
        String root = sqlPath + this.databaseName + "/";
        if (!newDatabaseDirectory(root)) {
            return;
        }
        String databaseScriptFilename = root + String.format("%s.script", databaseName);
        try {
            File f = new File(databaseScriptFilename);
            OutputStream fop = new FileOutputStream(f);
            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
            Set<String> tableNames = nameIdMap.keySet();
            writer.append(getTablesMessage(tableNames.iterator()));
            writer.close();
            fop.close();
        }
        catch (Exception e) {
            System.out.println("Failed to persist '" + databaseScriptFilename);
        }
    }

}
