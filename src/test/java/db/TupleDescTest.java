package db;

import db.field.Type;
import db.query.FullColumnName;
import db.tuple.TDItem;
import db.tuple.TupleDesc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class TupleDescTest {
    TupleDesc tupleDesc1, tupleDesc2, tupleDesc3, tupleDesc4;

    @Before
    public void setUp() throws Exception {
        TDItem[] tdItems1 = new TDItem[3];
        tdItems1[0] = new TDItem(Type.INT_TYPE, "i1", true);
        tdItems1[1] = new TDItem(Type.STRING_TYPE, "s1", true, 100);
        tdItems1[2] = new TDItem(Type.INT_TYPE, "i2", false);
        String primaryKey1 = "i1";
        tupleDesc1 = new TupleDesc(tdItems1, primaryKey1);

        TDItem[] tdItems2 = new TDItem[3];
        tdItems2[0] = new TDItem(Type.INT_TYPE, "i1", true);
        tdItems2[1] = new TDItem(Type.STRING_TYPE, "s1", true, 200);
        tdItems2[2] = new TDItem(Type.INT_TYPE, "i2", false);
        String primaryKey2 = "i1";
        tupleDesc2 = new TupleDesc(tdItems2, primaryKey2);

        TDItem[] tdItems3 = new TDItem[3];
        tdItems3[0] = new TDItem(Type.INT_TYPE, "i1", true);
        tdItems3[1] = new TDItem(Type.STRING_TYPE, "s1", true, 200);
        tdItems3[2] = new TDItem(Type.INT_TYPE, "i2", false);
        String primaryKey3 = "s1";
        tupleDesc3 = new TupleDesc(tdItems3, primaryKey3);

        TDItem[] tdItems4 = new TDItem[3];
        tdItems4[0] = new TDItem(Type.INT_TYPE, "i1", true);
        tdItems4[1] = new TDItem(Type.STRING_TYPE, "s1", true, 200);
        tdItems4[2] = new TDItem(Type.INT_TYPE, "i2", true);
        String primaryKey4 = "i2";
        tupleDesc4 = new TupleDesc(tdItems4, primaryKey4);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fieldNameToIndex() {
        assertEquals(tupleDesc1.fieldNameToIndex("i1"),0);
        assertEquals(tupleDesc1.fieldNameToIndex("s1"), 1);
        assertEquals(tupleDesc2.fieldNameToIndex("i2"), 2);
        boolean thrown = false;
        try {
            tupleDesc1.fieldNameToIndex("none");
        }catch (NoSuchElementException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void fieldNameToIndex2() {
        TDItem[] tdItems5 = new TDItem[4];
        tdItems5[0] = new TDItem(Type.INT_TYPE, "i1", true, 0, "table1");
        tdItems5[1] = new TDItem(Type.STRING_TYPE, "s1", true, 200, "table2");
        tdItems5[2] = new TDItem(Type.INT_TYPE, "i2", false, 0,"table1");
        tdItems5[3] = new TDItem(Type.INT_TYPE, "i3", false, 0,"table3");
        String primaryKey5 = "i1";
        TupleDesc tupleDesc5 = new TupleDesc(tdItems5, primaryKey5);
        try{
            assertEquals(tupleDesc5.fieldNameToIndex("table1", "i1"),0);
            assertEquals(tupleDesc5.fieldNameToIndex("table2", "s1"),1);
            assertEquals(tupleDesc5.fieldNameToIndex("table1", "i2"),2);
            assertEquals(tupleDesc5.fieldNameToIndex("table3", "i3"),3);
        }catch (Exception e){
            fail();
        }
        boolean failed=false;
        try{
            tupleDesc5.fieldNameToIndex("table1", "i5");
        } catch (Exception e){
            failed=true;
        }
        assertTrue(failed);

        failed=false;
        try{
            tupleDesc5.fieldNameToIndex("table5", "i3");
        } catch (Exception e){
            failed=true;
        }
        assertTrue(failed);

    }


    @Test
    public void getSize() {
        assertEquals(tupleDesc1.getSize(), 115);
        assertEquals(tupleDesc2.getSize(), 215);
    }

    @Test
    public void merge() {
        TupleDesc tupleDesc = TupleDesc.merge(tupleDesc1, tupleDesc2);
        assertEquals(tupleDesc.numFields(), 6);
        for (int i=0; i<3; i++){
            assertEquals(tupleDesc.getTDItem(i).fieldName, tupleDesc1.getTDItem(i).fieldName);
            assertEquals(tupleDesc.getTDItem(i+3).fieldName, tupleDesc2.getTDItem(i).fieldName);
        }
    }

    @Test
    public void equals() {
        assertNotEquals(tupleDesc1, tupleDesc2);
        assertNotEquals(tupleDesc2, tupleDesc3);
        assertNotEquals(tupleDesc2, tupleDesc4);
        assertEquals(tupleDesc1, tupleDesc1);
    }

    @Test
    public void getPrimaryKeysIndex() {
        assertEquals(tupleDesc1.getPrimaryKeyIndex(), 0);
        assertEquals(tupleDesc4.getPrimaryKeyIndex(), 2);
    }

    @Test
    public void getAttrNames() {
        String[] attrNames = {
                "i1", "s1", "i2"
        };
        assertArrayEquals(attrNames, tupleDesc1.getAttrNames());
    }

    @Test
    public void fullColumnNames() {
        TDItem[] tdItems5 = new TDItem[4];
        tdItems5[0] = new TDItem(Type.LONG_TYPE, "PRIMARY", true, 0, "table1");
        tdItems5[1] = new TDItem(Type.STRING_TYPE, "s1", true, 200, "table2");
        tdItems5[2] = new TDItem(Type.INT_TYPE, "i2", false, 0,"table1");
        tdItems5[3] = new TDItem(Type.INT_TYPE, "i3", false, 0,"table3");
        String primaryKey5 = "PRIMARY";
        TupleDesc tupleDesc5 = new TupleDesc(tdItems5, primaryKey5);
        ArrayList<FullColumnName> fullColumnNames = tupleDesc5.fullColumnNames();
        assertEquals(fullColumnNames.size(),3);
        assertEquals(fullColumnNames.get(0).tableName, "table2");
        assertEquals(fullColumnNames.get(0).attrName, "s1");
        assertEquals(fullColumnNames.get(1).tableName, "table1");
        assertEquals(fullColumnNames.get(1).attrName, "i2");
        assertEquals(fullColumnNames.get(2).tableName, "table3");
        assertEquals(fullColumnNames.get(2).attrName, "i3");

    }
}