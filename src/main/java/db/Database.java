package db;

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
    public boolean createTable(String tableName, TupleDesc tupleDesc) {
        return false;
    }

    /**
     * Delete an old table and its associated file on the disk.
     * If the table doesn't exist, then return false.
     * @param tableName the name of the old table
     * @return
     */
    public boolean dropTable(String tableName) {
        return false;
    }

    /**
     *
     * @return The names of all the tables in the database
     */
    public ArrayList<String> getTables(){
        return null;
    }

    /**
     * load the tables of the database from the file
     * @param databaseFile
     */
    public void load(String databaseFile) {

    }
}
