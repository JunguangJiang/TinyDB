package db.query.plan;

import db.GlobalManager;
import db.field.Type;
import db.query.QueryResult;
import db.tuple.TDItem;
import db.tuple.TupleDesc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import db.query.plan.LogicalFilterNode.*;

public class LogicalJoinNodeTest {

    @Before
    public void setUp() {
        GlobalManager.getCatalog().createDatabase("database");
        GlobalManager.getCatalog().useDatabase("database");

        TDItem[] tdItems = new TDItem[2];
        tdItems[0] = new TDItem(Type.LONG_TYPE, "attr1", true, 0);
        tdItems[1] = new TDItem(Type.LONG_TYPE, "attr2", true, 0);
        TupleDesc tupleDesc = new TupleDesc(tdItems, "attr2");
        GlobalManager.getDatabase().createTable("table1", tupleDesc);

        TDItem[] tdItems2 = new TDItem[]{
                new TDItem(Type.INT_TYPE, "attr3", true)
        };
        TupleDesc tupleDesc2 = new TupleDesc(tdItems2, "attr3");
        GlobalManager.getDatabase().createTable("table2", tupleDesc2);

        TDItem[] tdItems3 = new TDItem[]{
                new TDItem(Type.STRING_TYPE, "attr1", false)
        };
        TupleDesc tupleDesc3 = new TupleDesc(tdItems3, "attr1");
        GlobalManager.getDatabase().createTable("table3", tupleDesc3);
    }

    @After
    public void tearDown() {
        GlobalManager.getDatabase().dropTable("table");
        GlobalManager.getCatalog().dropDatabase("database");
    }

    @Test
    public void getAttrToTable() {
        ArrayList<LogicalJoinNode> joinNodes = new ArrayList<>();
        joinNodes.add(new LogicalJoinNode(new VVCmpNode(false), new LogicalScanNode("table1",null)));
        joinNodes.add(new LogicalJoinNode(new VVCmpNode(false),new LogicalScanNode("table2", null)));
        joinNodes.add(new LogicalJoinNode(new VVCmpNode(false), new LogicalScanNode("table3",null)));

        HashMap<String, String> attrToTable = LogicalJoinNode.getAttrToTable(joinNodes);
        assertNull(attrToTable.get("attr1"), null);
        assertEquals(attrToTable.get("attr2"), "table1");
        assertEquals(attrToTable.get("attr3"), "table2");
        assertNull(attrToTable.get("new"));
    }

    @Test
    public void getAttrToTable2() {
        ArrayList<LogicalJoinNode> joinNodes = new ArrayList<>();
        joinNodes.add(new LogicalJoinNode(new VVCmpNode(false), new LogicalScanNode("table1",null)));
        joinNodes.add(new LogicalJoinNode(new VVCmpNode(false),new LogicalScanNode("table2", null)));

        HashMap<String, String> attrToTable = LogicalJoinNode.getAttrToTable(joinNodes);
        assertEquals(attrToTable.get("attr1"), "table1");
        assertEquals(attrToTable.get("attr2"), "table1");
        assertEquals(attrToTable.get("attr3"), "table2");
    }
}