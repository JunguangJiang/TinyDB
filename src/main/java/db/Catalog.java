package db;

import db.query.QueryResult;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Catalog keeps track of all available databases.
 * When Catalog is loaded, there should be a default database.
 */
public class Catalog {
    private HashMap<String, File> databaseMap;
    Database database;// For simplicity, we load a Database in Server rather than a Catalog(should be deprecated later)

    public Catalog() {
        databaseMap = new HashMap<>();
        database = new Database();
    }

    /**
     * Create a new database and its associated file on the disk.
     * If the database already exists, then return false.
     * @param databaseName the name of the new database
     * @return
     */
    public QueryResult createDatabase(String databaseName) {
        // TODO
        return new QueryResult(false, "");
    }

    /**
     * Delete an old database and its associated file on the disk.
     * If the database doesn't exist, then return false.
     * @param databaseName the name of the old database.
     * @return
     */
    public QueryResult dropDatabase(String databaseName) {
        // TODO
        return new QueryResult(false, "");
    }

    /**
     * Use a database and load its associated file from the disk.
     * If the database doesn't exist, then return false.
     * @param databaseName the name of the database.
     * @return
     */
    public QueryResult useDatabase(String databaseName) {
        // TODO
        return new QueryResult(false, "");
    }

    /**
     * @return The current database
     */
    public Database getCurrentDatabase() {
        //TODO
        return database;
    }

    /**
     * @return The names of all the databases
     */
    public ArrayList<String> getDatabaseNames(){
        // TODO
        return null;
    }

    /**
     * load names of all the databases from file
     * @param catalogFile
     */
    public void load(String catalogFile) {
        // TODO
    }
}
