package db.query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class FullColumnNameTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void toStringTest() {
        FullColumnName fullColumnName = new FullColumnName("table", "attr", "alias");
        assertEquals(fullColumnName.toString(),"alias");
        fullColumnName = new FullColumnName("table", "attr", null);
        assertEquals(fullColumnName.toString(),"table.attr");
        fullColumnName = new FullColumnName(null, "attr", null);
        assertEquals(fullColumnName.toString(),"attr");
    }

    @Test
    public void disambiguateName() {
        HashMap<String, String> attrNameToTableName = new HashMap<>();
        FullColumnName fullColumnName = new FullColumnName(null, "attr", null);
        // no table assigned and table doesn't appear in map
        boolean failed = false;
        try{
            fullColumnName.disambiguateName(attrNameToTableName);
        }catch (Exception e) {
            failed = true;
        }
        assertTrue(failed);

        // no table assigned and table appears in map
        attrNameToTableName.put("attr", "table");
        fullColumnName.disambiguateName(attrNameToTableName);
        assertEquals(fullColumnName.tableName,"table");

        // table assigned and table appear in map
        attrNameToTableName.put("attr","table2");
        fullColumnName.disambiguateName(attrNameToTableName);
        assertEquals(fullColumnName.tableName,"table");

        // no table assigned and table appear multiple times in map
        attrNameToTableName.put("attr", null);
        fullColumnName.tableName = null;
        failed = false;
        try{
            fullColumnName.disambiguateName(attrNameToTableName);
        }catch (Exception e) {
            failed = true;
        }
        assertTrue(failed);
    }
}