package db;

import db.file.Table;
import db.query.Query;
import db.query.QueryResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
    @Test
    public void createTable() {
        QueryResult queryResult = GlobalManager.getDatabase().createTable("table", Utility.getTupleDesc(3, "tup"));
        assertTrue(queryResult.succeeded());
        Table table = GlobalManager.getDatabase().getTable("table");
        assertNotNull(table);
        assertEquals(table.getName(), "table");

        // 2 table cannot have the same name
        queryResult = GlobalManager.getDatabase().createTable("table", Utility.getTupleDesc(3, "tup"));
        assertFalse(queryResult.succeeded());
    }

    @Test
    public void getTableId() {
        QueryResult queryResult = GlobalManager.getDatabase().createTable("table1", Utility.getTupleDesc(3, "tup"));
        assertTrue(queryResult.succeeded());
        int id1 = GlobalManager.getDatabase().getTableId("table1");

        queryResult = GlobalManager.getDatabase().createTable("table2", Utility.getTupleDesc(3, "tup"));
        assertTrue(queryResult.succeeded());
        int id2 = GlobalManager.getDatabase().getTableId("table2");
        assertEquals(id1 + 1, id2);
    }
}