package db;

import db.field.Type;
import db.tuple.TDItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class TDItemTest {

    @Test
    public void getBytes() {
        TDItem tdItem = new TDItem(Type.STRING_TYPE, "S", false, 30);
        assertEquals(tdItem.getBytes(), 35);
        TDItem tdItem1 = new TDItem(Type.FLOAT_TYPE, "F", false, 30);
        assertEquals(tdItem1.getBytes(), Float.BYTES+1);
    }

    @Test
    public void equals() {
        TDItem tdItem = new TDItem(Type.STRING_TYPE, "S", false, 30);
        TDItem tdItem1 = new TDItem(Type.STRING_TYPE, "S1", false, 30);
        assertEquals(tdItem, tdItem1);
        tdItem1.notNull = true;
        assertNotEquals(tdItem, tdItem1);

        TDItem tdItem2 = new TDItem(Type.STRING_TYPE, "S2", false, 40);
        assertNotEquals(tdItem, tdItem2);

        TDItem tdItem3 = new TDItem(Type.FLOAT_TYPE, "F", false, 30);
        assertNotEquals(tdItem, tdItem3);

        TDItem tdItem4 = new TDItem(Type.FLOAT_TYPE, "F2", false, 0);
        assertEquals(tdItem3, tdItem4);
    }

    @Test
    public void hasName() {
        TDItem tdItem = new TDItem(Type.STRING_TYPE, "S", false, 30, "table");
        assertTrue(tdItem.hasName("S"));
        assertTrue(tdItem.hasName("table", "S"));
        assertFalse(tdItem.hasName("W"));
        assertFalse(tdItem.hasName("nontable", "S"));

        TDItem tdItem2 = new TDItem(Type.STRING_TYPE, "S", false, 30);
        assertTrue(tdItem2.hasName("S"));
        assertFalse(tdItem2.hasName("table", "S"));

        TDItem tdItem3 = new TDItem(Type.STRING_TYPE, null, false, 30);
        assertFalse(tdItem3.hasName("W"));
    }
}