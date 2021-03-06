package db;

import db.query.QueryResult;

import java.io.File;
import java.util.*;
import java.io.*;
import db.utils.utils;

/**
 * The Catalog keeps track of all available databases.
 * When Catalog is loaded, there should be a default database.
 */
public class Catalog {
    private HashSet<String> databaseSet;
    public Database database;// For simplicity, we load a Database in WebServer rather than a Catalog(should be deprecated later)
    private String sqlPath;

    public Catalog() {
        this.sqlPath = "test_data/";
        // TODO temp
        utils.removeDirectory(new File(this.sqlPath));
        this.databaseSet = new HashSet<>();
        this.database = null;
    }

    /**
     * Create a new database and its associated file on the disk.
     * If the database already exists, then return false.
     * @param databaseName the name of the new database
     * @return
     */
    public QueryResult createDatabase(String databaseName) {
        if (this.databaseSet.contains(databaseName))
            return new QueryResult(false, "ERROR 1007 (HY000): Can't create database '" + databaseName + "'; database exists");
        this.databaseSet.add(databaseName);
        (new Database(sqlPath, databaseName)).persist();
        return new QueryResult(true, "Query OK, 1 row affected");
    }

    /**
     * Delete an old database and its associated file on the disk.
     * If the database doesn't exist, then return false.
     * @param databaseName the name of the old database.
     * @return
     */
    public QueryResult  dropDatabase(String databaseName) {
        if (!this.databaseSet.contains(databaseName)) {
            return new QueryResult(false, "ERROR 1008 (HY000): Can't drop database '" + databaseName + "'; database doesn't exist");
        }
        if (this.database != null) {
            if (this.database.databaseName.equals(databaseName)) {
                this.database = null;
            }
        }
        this.databaseSet.remove(databaseName);
        utils.removeDirectory(new File(utils.getFilePath(sqlPath, databaseName)));
        if(this.database != null && this.database.databaseName.equals(databaseName)) {
            this.database = null;
        }
        return new QueryResult(true, "Query OK, 0 rows affected");
    }

    /**
     * Use a database and load its associated file from the disk.
     * If the database doesn't exist, then return false.
     * @param databaseName the name of the database.
     * @return
     */
    public QueryResult useDatabase(String databaseName) {
        if (!this.databaseSet.contains(databaseName))
            return new QueryResult(false, "ERROR 1049 (42000): Unknown database '" + databaseName + "'");
        if (this.database != null && databaseName.equals(this.database.databaseName))
            return new QueryResult(true, "Database changed");
        if (this.database != null)
            this.database.persist();
        else
            this.database = new Database();
        try {
            this.database.load(this.sqlPath, databaseName);
        }
        catch (NoSuchElementException e) {
            return new QueryResult(false, "The system may not be close correctly. Data file is broken! Fail to change database!");
        }
        return new QueryResult(true, "Database changed");
    }

    /**
     * @return The current database
     */
    public Database getCurrentDatabase(){
        return this.database;
    }

    /**
     * @return The names of all the databases
     */
    public String[] getDatabaseNames(){
        Iterator<String> iterator = this.databaseSet.iterator();
        String[] results = new String[this.databaseSet.size()];
        int i = 0;
        while (iterator.hasNext()){
            results[i++] = iterator.next();
        }
        return results;
    }

    /**
     * @param databaseName the name of the given Database
     * @return All the tables' name in a certain Database
     * @throws java.util.NoSuchElementException if the Database doesn't exist.
     */
    public String[] getTableNames(String databaseName) throws NoSuchElementException {
        if (this.database != null && databaseName.equals(this.database.databaseName))
            return GlobalManager.getDatabase().getTableNames();
        String scriptFilename = utils.getFilePath(this.sqlPath, databaseName, databaseName + ".script");
        try {
            String[] lines = utils.readFile(scriptFilename).split(System.lineSeparator());
            assert lines.length > 0;
            Integer n = Integer.valueOf(lines[0]);
            return Arrays.copyOfRange(lines,1, 1+n);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File " + scriptFilename + " not exist");
            throw new NoSuchElementException("Database doesn't exist");
        }
    }

    /**
     * @param content the content of the catalog.script
     */
    private void parseCatalogFile(String content) {
        String[] lines = content.split("\n");
        for (String line: lines) {
            if (line.length() == 0)
                continue;
            this.databaseSet.add(line);
        }
    }

    /**
     * load names of all the databases from file
     * @param sqlPath: file name
     */
    public void load(String sqlPath) {
        this.sqlPath = sqlPath;
        String catalogFileName = utils.getFilePath(this.sqlPath, "catalog.script");
        try {
            parseCatalogFile(utils.readFile(catalogFileName));
        }
        catch (Exception e) {
            System.out.println("File " + catalogFileName + " not exist. New database create!");
        }

    }

    /**
     * write names of all the databases back to file
     */
    public void persist() {
        String catalogFilename = utils.getFilePath(this.sqlPath, "catalog.script");
        try {
            if (this.database != null)
                this.database.persist();

            File f = new File(catalogFilename);
            OutputStream fop = new FileOutputStream(f);
            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
            Iterator<String> iterator = this.databaseSet.iterator();
            StringBuilder sb = new StringBuilder();
            while (iterator.hasNext()){
                sb.append(iterator.next());
                sb.append("\n");
            }
            writer.append(sb.toString());
            writer.close();
            fop.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Failed to persist catalog. File '" + catalogFilename + "' not found.");
        }
        catch (UnsupportedEncodingException e) {
            System.out.println("Encode error during writing file '" + catalogFilename + "'!");
        }
        catch (IOException e) {
            System.out.println("Fail to close file '" + catalogFilename + "'!");
        }
    }
}
