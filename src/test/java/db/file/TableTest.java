package db.file;

import db.GlobalManager;
import db.error.SQLError;
import db.field.FloatField;
import db.field.IntField;
import db.field.StringField;
import db.field.Type;
import db.query.QueryResult;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TableTest {
    @Before public void setUp(){
        GlobalManager.getCatalog().createDatabase("database");
        GlobalManager.getCatalog().useDatabase("database");
    }

    @After public void tearDown() {
        GlobalManager.getCatalog().dropDatabase("database");
    }

    // Test primary key constraint
    @Test
    public void insertTuple() {
        try {
            TDItem[] tdItems = new TDItem[3];
            tdItems[0] = new TDItem(Type.INT_TYPE, "id", true);
            tdItems[1] = new TDItem(Type.STRING_TYPE,"name", true, 10);
            tdItems[2] = new TDItem(Type.FLOAT_TYPE,"value", false);
            String primaryKey = tdItems[1].fieldName;
            TupleDesc tupleDesc = new TupleDesc(tdItems, primaryKey);
            QueryResult queryResult = GlobalManager.getDatabase().createTable("table", tupleDesc);
            assertTrue(queryResult.succeeded());

            Tuple tuple = new Tuple(tupleDesc);
            tuple.setField(0, new IntField(1));
            tuple.setField(1, new StringField("1", 10));
            tuple.setField(2, new FloatField(1.1f));
            Table table = GlobalManager.getDatabase().getTable("table");
            queryResult = table.insertTuple(tuple);
            assertTrue(queryResult.succeeded());
            // TODO satisfy the primary key constraint
//        queryResult = table.insertTuple(tuple);
//        assertFalse(queryResult.succeeded());
            GlobalManager.getDatabase().dropTable("table");
        }catch (SQLError e){
            fail();
        }

}

    // Test the compatibility of attrNames and values
    @Test
    public void insertTuple1() {
        try {
            TDItem[] tdItems = new TDItem[3];
            tdItems[0] = new TDItem(Type.INT_TYPE, "id", true);
            tdItems[1] = new TDItem(Type.STRING_TYPE,"name", true, 10);
            tdItems[2] = new TDItem(Type.FLOAT_TYPE,"value", false);
            String primaryKey = tdItems[1].fieldName;
            TupleDesc tupleDesc = new TupleDesc(tdItems, primaryKey);
            QueryResult queryResult = GlobalManager.getDatabase().createTable("table", tupleDesc);
            assertTrue(queryResult.succeeded());

            String[] attrNames = {
                    "id", "name"
            };
            String [] values = {
                    "33", "'myname'"
            };
            Table table = GlobalManager.getDatabase().getTable("table");
            queryResult = table.insertTuple(attrNames, values);
            System.out.println(queryResult.getInfo());
            assertTrue(queryResult.succeeded());

            String[] full_values = {
                    "33", "'myname2'", "3.3"
            };
            queryResult = table.insertTuple(null, full_values);
            assertTrue(queryResult.succeeded());

            queryResult = table.insertTuple(attrNames, full_values);
            assertFalse(queryResult.succeeded());
            GlobalManager.getDatabase().dropTable("table");
        } catch (SQLError e){
            fail();
        }
    }

    // Test the not null constraint
    @Test
    public void insertTuple2() {
        try {
            TDItem[] tdItems = new TDItem[3];
            tdItems[0] = new TDItem(Type.INT_TYPE, "id", true);
            tdItems[1] = new TDItem(Type.STRING_TYPE,"name", true, 10);
            tdItems[2] = new TDItem(Type.FLOAT_TYPE,"value", false);
            String primaryKey = tdItems[0].fieldName;
            TupleDesc tupleDesc = new TupleDesc(tdItems, primaryKey);
            QueryResult queryResult = GlobalManager.getDatabase().createTable("table", tupleDesc);
            assertTrue(queryResult.succeeded());

            String[] attrNames = {
                    "id"
            };
            String[] values = {
                    "33"
            };
            Table table = GlobalManager.getDatabase().getTable("table");
            queryResult = table.insertTuple(attrNames, values);
            assertFalse(queryResult.succeeded());
            GlobalManager.getDatabase().dropTable("table");
        } catch (SQLError e){
            fail();
        }

    }

    // Test not find attrName
    @Test
    public void insertTuple3() {
        try {
            TDItem[] tdItems = new TDItem[3];
            tdItems[0] = new TDItem(Type.INT_TYPE, "id", true);
            tdItems[1] = new TDItem(Type.STRING_TYPE,"name", true, 10);
            tdItems[2] = new TDItem(Type.FLOAT_TYPE,"value", false);
            String primaryKey = tdItems[1].fieldName;
            TupleDesc tupleDesc = new TupleDesc(tdItems, primaryKey);
            QueryResult queryResult = GlobalManager.getDatabase().createTable("table", tupleDesc);
            assertTrue(queryResult.succeeded());

            String[] attrNames = {
                    "id", "names"
            };
            String[] values = {
                    "33", "'myname'"
            };
            Table table = GlobalManager.getDatabase().getTable("table");
            queryResult = table.insertTuple(attrNames, values);
            assertFalse(queryResult.succeeded());
            GlobalManager.getDatabase().dropTable("table");
        } catch (SQLError e){
            fail();
        }

    }

    // Test primary auto increase
    @Test
    public void insertTuple4() {
        try {
            TDItem[] tdItems = new TDItem[3];
            tdItems[0] = new TDItem(Type.LONG_TYPE, "PRIMARY", true);
            tdItems[1] = new TDItem(Type.STRING_TYPE,"name", true, 10);
            tdItems[2] = new TDItem(Type.FLOAT_TYPE,"value", false);
            String primaryKey = tdItems[0].fieldName;
            TupleDesc tupleDesc = new TupleDesc(tdItems, primaryKey);
            QueryResult queryResult = GlobalManager.getDatabase().createTable("table", tupleDesc);
            assertTrue(queryResult.succeeded());

            String[] attrNames = {
                    "name"
            };
            String[] values = {
                    "'myname'"
            };
            Table table = GlobalManager.getDatabase().getTable("table");
            queryResult = table.insertTuple(attrNames, values);
            assertTrue(queryResult.succeeded());
            assertEquals(table.autoIncrementNumber, 1);
            queryResult = table.insertTuple(attrNames, values);
            assertTrue(queryResult.succeeded());
            assertEquals(table.autoIncrementNumber, 2);
            GlobalManager.getDatabase().dropTable("table");
        } catch (SQLError e){
            fail();
        }

    }

}