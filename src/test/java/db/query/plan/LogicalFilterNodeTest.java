package db.query.plan;

import db.GlobalManager;
import db.field.Op;
import db.field.Type;
import db.error.TypeMismatch;
import db.file.BTree.IndexPredicate;
import db.query.FullColumnName;
import db.query.QueryResult;
import db.tuple.TDItem;
import db.tuple.TupleDesc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import db.query.plan.LogicalFilterNode.*;

public class LogicalFilterNodeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void AndNodeExtractIndexPredicate() {
        GlobalManager.getCatalog().createDatabase("database");
        GlobalManager.getCatalog().useDatabase("database");

        TDItem[] tdItems = new TDItem[2];
        tdItems[0] = new TDItem(Type.LONG_TYPE, "attr1", true, 0);
        tdItems[1] = new TDItem(Type.LONG_TYPE, "attr2", true, 0);
        TupleDesc tupleDesc = new TupleDesc(tdItems, "attr2");
        QueryResult queryResult = GlobalManager.getDatabase().createTable("table", tupleDesc);
        assertTrue(queryResult.succeeded());
        LogicalScanNode scanNode = new LogicalScanNode("table","table");

        FullColumnName attr1 = new FullColumnName("table", "attr1", null);
        FullColumnName attr2 = new FullColumnName("table", "attr2", null);
        FullColumnName attr3 = new FullColumnName("table2", "attr1", null);
        KKCmpNode node1 = new KKCmpNode(attr1, Op.GREATER_THAN, attr2);
        KVCmpNode node2 = new KVCmpNode(attr1, Op.NOT_EQUALS, 4);
        VVCmpNode node3 = new VVCmpNode(false);
        KVCmpNode node4 = new KVCmpNode(attr2,Op.EQUALS, 5);
        KVCmpNode node5 = new KVCmpNode(attr3, Op.LESS_THAN_OR_EQ, 5);


        try{
            AndNode andNode = new AndNode();
            andNode.add(node1);
            andNode.add(node2);
            andNode.add(node3);
            andNode.add(node4);
            andNode.add(node5);

            IndexPredicate indexPredicate = andNode.extractIndexPredicate(scanNode);
            assertEquals(indexPredicate.getOp(), node4.op);

            andNode.remove(node4);
            indexPredicate = andNode.extractIndexPredicate(scanNode);
            assertEquals(indexPredicate, null);
        } catch (TypeMismatch e) {
            fail();
        }

        GlobalManager.getDatabase().dropTable("table");
        GlobalManager.getCatalog().dropDatabase("database");
    }

    @Test
    public void AndNodeGetKVCmpNodes() {
        AndNode andNode = new AndNode();
        FullColumnName attr1 = new FullColumnName("table", "attr1", null);
        FullColumnName attr2 = new FullColumnName("table", "attr2", null);
        FullColumnName attr3 = new FullColumnName("table2", "attr1", null);
        KKCmpNode node1 = new KKCmpNode(attr1, Op.GREATER_THAN, attr2);
        KVCmpNode node2 = new KVCmpNode(attr1, Op.NOT_EQUALS, 4);
        VVCmpNode node3 = new VVCmpNode(false);
        KVCmpNode node4 = new KVCmpNode(attr2,Op.EQUALS, 5);
        KVCmpNode node5 = new KVCmpNode(attr3, Op.LESS_THAN_OR_EQ, 5);
        andNode.add(node1);
        andNode.add(node2);
        andNode.add(node3);
        andNode.add(node4);
        andNode.add(node5);
        try{
            AndNode result = andNode.getKVCmpNodes("table", null);
            AndNode expected = new AndNode();
            expected.add(node2);
            expected.add(node4);
            assertArrayEquals(result.toArray(), expected.toArray());

            result = andNode.getKVCmpNodes("table", "attr1");
            expected = new AndNode();
            expected.add(node2);
            assertArrayEquals(result.toArray(), expected.toArray());

            result = andNode.getKVCmpNodes(null, null);
            expected = new AndNode();
            expected.add(node2);
            expected.add(node4);
            expected.add(node5);
            assertArrayEquals(result.toArray(), expected.toArray());
        } catch (TypeMismatch e) {
            fail();
        }

    }

    @Test
    public void AndNodePredicate(){

    }

    @Test
    public void OrNodePredicate() {

    }


}