package db;

import db.file.Table;
import db.query.QueryResult;
import org.junit.Test;
import static org.junit.Assert.*;

public class CatalogTest {
    @Test
    public void createDatabase() {
        QueryResult queryResult = GlobalManager.getCatalog().createDatabase("database");
        assertTrue(queryResult.succeeded());
        String[] databaseNames = GlobalManager.getCatalog().getDatabaseNames();
        assertArrayEquals(databaseNames, new String[]{"database"});

        // 2 databases cannot have the same name
        queryResult = GlobalManager.getCatalog().createDatabase("database");
        assertFalse(queryResult.succeeded());

        GlobalManager.getCatalog().dropDatabase("database");
    }

    @Test
    public void dropDatabase() {
        //cann't drop a database which doesn't exist
        QueryResult queryResult = GlobalManager.getCatalog().dropDatabase("database");
        assertFalse(queryResult.succeeded());
        String[] databaseNames = GlobalManager.getCatalog().getDatabaseNames();
        assertArrayEquals(databaseNames, new String[]{});

        GlobalManager.getCatalog().createDatabase("database");
        queryResult = GlobalManager.getCatalog().dropDatabase("database");
        assertTrue(queryResult.succeeded());
        databaseNames = GlobalManager.getCatalog().getDatabaseNames();
        assertArrayEquals(databaseNames, new String[]{});
    }

    @Test
    public void getDatabaseNames() {
        // 0 database
        String[] databaseNames = GlobalManager.getCatalog().getDatabaseNames();
        assertArrayEquals(databaseNames, new String[]{});

        // one database
        GlobalManager.getCatalog().createDatabase("database");
        databaseNames = GlobalManager.getCatalog().getDatabaseNames();
        assertArrayEquals(databaseNames, new String[]{"database"});

        // more than one databases
        GlobalManager.getCatalog().createDatabase("database1");
        GlobalManager.getCatalog().createDatabase("database2");

        databaseNames = GlobalManager.getCatalog().getDatabaseNames();
        assertArrayEquals(databaseNames, new String[]{"database2, database1, database"});

        GlobalManager.getCatalog().dropDatabase("database");
        GlobalManager.getCatalog().dropDatabase("database1");
        GlobalManager.getCatalog().dropDatabase("database2");
    }

    @Test
    public void useDatabase() {
        //cann't use a database which doesn't exist
        QueryResult queryResult = GlobalManager.getCatalog().useDatabase("database1");
        assertFalse(queryResult.succeeded());

        GlobalManager.getCatalog().createDatabase("database1");
        GlobalManager.getCatalog().createDatabase("database2");
        GlobalManager.getCatalog().createDatabase("database3");

        GlobalManager.getCatalog().useDatabase("database2");
        assertTrue(queryResult.succeeded());
        Database database = GlobalManager.getCatalog().getCurrentDatabase();
        assertEquals(database.databaseName, "database2");

        GlobalManager.getCatalog().useDatabase("database1");
        assertTrue(queryResult.succeeded());
        database = GlobalManager.getCatalog().getCurrentDatabase();
        assertEquals(database.databaseName, "database1");

        GlobalManager.getCatalog().dropDatabase("database3");
        GlobalManager.getCatalog().dropDatabase("database1");
        GlobalManager.getCatalog().dropDatabase("database2");
    }
}
