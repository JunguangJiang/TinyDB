package db;

import db.query.QueryResult;

import java.util.ArrayList;

/**
 * Each database keeps track of multiple tables
 */
public class Database {

    /**
     * Create a new table and its associated file on the disk.
     * If the table already exists, then return false.
     * @param tableName the name of the new table
     * @param tupleDesc the descriptor of the tuple
     * @return
     */
    public QueryResult createTable(String tableName, TupleDesc tupleDesc) {
        // TODO
        return new QueryResult(false, "");
    }

    /**
     * Delete an old table and its associated file on the disk.
     * If the table doesn't exist, then return false.
     * @param tableName the name of the old table
     * @return
     */
    public QueryResult dropTable(String tableName) {
        // TODO
        return new QueryResult(false, "");
    }

    /**
     *
     * @param tableName
     * @return the table if it exists, otherwise return null
     */
    public Table getTable(String tableName) {
        // TODO
        return new Table(tableName, null, null);
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
     * @param databaseFile
     */
    public void load(String databaseFile) {
        // TODO
    }
}
