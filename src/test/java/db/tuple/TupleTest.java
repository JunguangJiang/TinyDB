package db.tuple;

import db.Utility;
import db.field.IntField;
import db.file.RecordId;
import db.file.heap.HeapPageId;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TupleTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void setRecordId() {
        Tuple tuple = new Tuple(Utility.getTupleDesc(1, "tup"));
        HeapPageId pageId = new HeapPageId(0, 0);
        RecordId recordId = new RecordId(pageId, 0);
        tuple.setRecordId(recordId);

        assertEquals(recordId, tuple.getRecordId());
    }

    @Test
    public void setField() {
        TupleDesc tupleDesc = Utility.getTupleDesc(2, "tuple");
        Tuple tuple = new Tuple(tupleDesc);
        tuple.setField(0, new IntField(-1));
        tuple.setField(1, new IntField(0));
        assertEquals(new IntField(-1), tuple.getField(0));
        assertEquals(new IntField(0), tuple.getField(1));

        tuple.setField(0, new IntField(1));
        assertEquals(new IntField(1), tuple.getField(0));
    }
}