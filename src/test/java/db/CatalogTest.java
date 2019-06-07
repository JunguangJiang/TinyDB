package db;

import db.error.SQLError;
import db.query.QueryResult;
import db.tuple.TupleDesc;
import org.junit.Test;
import java.util.Arrays;
import java.util.NoSuchElementException;

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
        String[] expected = new String[]{"database2", "database1", "database"};
        Arrays.sort(databaseNames);
        Arrays.sort(expected);
        assertArrayEquals(expected, databaseNames);

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

        queryResult = GlobalManager.getCatalog().useDatabase("database2");
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

    @Test
    public void getCurrentDatabase() {
        // use database, set current database
        GlobalManager.getCatalog().createDatabase("database");
        GlobalManager.getCatalog().useDatabase("database");

        Database database = GlobalManager.getCatalog().getCurrentDatabase();
        assertEquals(database.databaseName, "database");

        GlobalManager.getCatalog().dropDatabase("database");
    }

    @Test
    public void getTableNames() {
        try {
            // create two databases
            GlobalManager.getCatalog().createDatabase("database1");
            GlobalManager.getCatalog().createDatabase("database2");
            GlobalManager.getCatalog().useDatabase("database1");
            GlobalManager.getDatabase().createTable("table1", Utility.getTupleDesc(3, "tup"));
            GlobalManager.getDatabase().createTable("table2", Utility.getTupleDesc(3, "tup"));
            GlobalManager.getDatabase().createTable("table3", Utility.getTupleDesc(3, "tup"));

            String[] names = GlobalManager.getCatalog().getTableNames("database1");
            Arrays.sort(names);
            String[] expected = new String[]{"table1", "table2", "table3"};
            Arrays.sort(expected);
            assertArrayEquals(expected, names);

            GlobalManager.getCatalog().dropDatabase("database1");
            GlobalManager.getCatalog().dropDatabase("database2");
        } catch (SQLError e){
            fail();
        }
    }

}
