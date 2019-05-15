package db;

import db.field.Type;
import db.tuple.TDItem;
import db.tuple.TupleDesc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        String[] primaryKeys1 = new String []{
                "i1", "s1"
        };
        tupleDesc1 = new TupleDesc(tdItems1, primaryKeys1);

        TDItem[] tdItems2 = new TDItem[3];
        tdItems2[0] = new TDItem(Type.INT_TYPE, "i1", true);
        tdItems2[1] = new TDItem(Type.STRING_TYPE, "s1", true, 200);
        tdItems2[2] = new TDItem(Type.INT_TYPE, "i2", false);
        String[] primaryKeys2 = new String []{
                "i1", "i2"
        };
        tupleDesc2 = new TupleDesc(tdItems2, primaryKeys2);

        TDItem[] tdItems3 = new TDItem[3];
        tdItems3[0] = new TDItem(Type.INT_TYPE, "i1", true);
        tdItems3[1] = new TDItem(Type.STRING_TYPE, "s1", true, 200);
        tdItems3[2] = new TDItem(Type.INT_TYPE, "i2", false);
        String[] primaryKeys3 = new String []{
                "i1", "s1"
        };
        tupleDesc3 = new TupleDesc(tdItems3, primaryKeys3);

        TDItem[] tdItems4 = new TDItem[3];
        tdItems4[0] = new TDItem(Type.INT_TYPE, "i1", true);
        tdItems4[1] = new TDItem(Type.STRING_TYPE, "s1", true, 200);
        tdItems4[2] = new TDItem(Type.INT_TYPE, "i2", true);
        String[] primaryKeys4 = new String []{
                "i1", "i2"
        };
        tupleDesc4 = new TupleDesc(tdItems4, primaryKeys4);
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
    public void getSize() {
        assertEquals(tupleDesc1.getSize(), 115);
        assertEquals(tupleDesc2.getSize(), 215);
    }

    @Test
    public void merge() {
        TupleDesc tupleDesc = TupleDesc.merge(tupleDesc1, tupleDesc2);
        assertEquals(tupleDesc.numFields(), 6);
        for (int i=0; i<3; i++){
            assertEquals(tupleDesc.getField(i).fieldName, tupleDesc1.getField(i).fieldName);
            assertEquals(tupleDesc.getField(i+3).fieldName, tupleDesc2.getField(i).fieldName);
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
        assertArrayEquals(tupleDesc1.getPrimaryKeysIndex(), new int[]{0,1});
        assertArrayEquals(tupleDesc2.getPrimaryKeysIndex(), new int[]{0, 2});
    }

    @Test
    public void getAttrNames() {
        String[] attrNames = {
                "i1", "s1", "i2"
        };
        assertArrayEquals(attrNames, tupleDesc1.getAttrNames());
    }

    @Test
    public void getPrimaryKeysIndex1() {
        TupleDesc tupleDesc = new TupleDesc(Utility.getTDItems(3, "wow"), null);
        assertArrayEquals(tupleDesc.getPrimaryKeysIndex(), new int[0]);
    }
}