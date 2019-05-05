package db;

import db.file.DbFile;
import db.file.Table;
import db.query.QueryResult;
import db.tuple.TupleDesc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Each database keeps track of multiple tables
 *
 */
public class Database {

    HashMap<String, Integer> nameIdMap;
    HashMap<Integer, Table> idTableMap;
    private static int id = 0; // Each table has a unique id
    private String databaseName; // The name of database

    Database() {
        nameIdMap = new HashMap<>();
        idTableMap = new HashMap<>();
    }

    /**
     * Create a new table and its associated file on the disk.
     * If the table already exists, then return false.
     * We have to ensure each Table has different ids and names
     * @param tableName the name of the new table
     * @param tupleDesc the descriptor of the tuple
     * @return
     */
    public QueryResult createTable(String tableName, TupleDesc tupleDesc) {
        if (getTable(tableName) != null) {
            return new QueryResult(false, "Table " + tableName + " already exists.");
        } else {
            // TODO currently we create file in the same directory,
            //  we need to create file in a tree structure
            File file = new File(tableName + ".db");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Table table = new Table(id, tableName, tupleDesc, file);
            nameIdMap.put(tableName, id);
            idTableMap.put(id, table);
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
        // TODO
        Table table = this.getTable(tableName);
        if (table == null) {
            return new QueryResult(false, "Table " + tableName + " doesn't exist.");
        } else {
            table.file.delete();
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
    public ArrayList<String> getTableNames(){
        // TODO
        return null;
    }

    /**
     * load the tables of the database from the file
     * @param databaseScriptFile
     */
    public void load(String databaseScriptFile) {
        // TODO
    }

    /**
     * write the tables of the database back to the file
     * @param databaseScriptFile
     */
    public void persist(String databaseScriptFile) {
        // TODO
    }
}
