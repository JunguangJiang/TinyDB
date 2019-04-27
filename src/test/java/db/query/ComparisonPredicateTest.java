package db.query;

import db.Utility;
import db.field.IntField;
import db.field.TypeMismatch;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComparisonPredicateTest {

    @Test
    public void filter() {
        String tableName = "table";
        TupleDesc tupleDesc = Utility.getTupleDesc(3, "tup");
        tupleDesc.setTableName(tableName);
        Attribute attribute = new Attribute(tableName, tupleDesc.getField(0).fieldName);
        ComparisonPredicate.Op op = ComparisonPredicate.Op.EQUALS;
        ComparisonPredicate predicate = new ComparisonPredicate(attribute, op, 4);

        Tuple tuple = new Tuple(tupleDesc);
        try {
            tuple.setField(0, new IntField(4));
            assertTrue(predicate.filter(tuple));

            tuple.setField(0, new IntField(5));
            assertFalse(predicate.filter(tuple));
        } catch (TypeMismatch e) {
            fail();
        }
    }

    @Test
    public void filter1() {
        String tableName = "table";
        TupleDesc tupleDesc = Utility.getTupleDesc(3, "tup");
        tupleDesc.setTableName(tableName);
        Attribute attribute = new Attribute(tableName, tupleDesc.getField(0).fieldName);
        ComparisonPredicate.Op op = ComparisonPredicate.Op.GREATER_THAN;
        ComparisonPredicate predicate = new ComparisonPredicate(4, op, attribute);

        Tuple tuple = new Tuple(tupleDesc);
        try {
            tuple.setField(0, new IntField(10));
            assertFalse(predicate.filter(tuple));

            tuple.setField(0, new IntField(1));
            assertTrue(predicate.filter(tuple));

            tuple.setField(0, new IntField(4));
            assertFalse(predicate.filter(tuple));
        } catch (TypeMismatch e) {
            fail();
        }
    }

    @Test
    public void filter2() {
        String tableName = "table";
        TupleDesc tupleDesc = Utility.getTupleDesc(3, "tup");
        tupleDesc.setTableName(tableName);
        Attribute attribute = new Attribute(tableName, tupleDesc.getField(0).fieldName);
        ComparisonPredicate.Op op = ComparisonPredicate.Op.EQUALS;
        ComparisonPredicate predicate = new ComparisonPredicate(attribute, op, 4.3);

        Tuple tuple = new Tuple(tupleDesc);
        boolean failed=false;
        try {
            tuple.setField(0, new IntField(4));
            predicate.filter(tuple);
        } catch (TypeMismatch e) {
            failed = true;
        }
        assertTrue(failed);
    }

    @Test
    public void filter3() {
        String tableName = "table";
        TupleDesc tupleDesc = Utility.getTupleDesc(3, "tup");
        tupleDesc.setTableName(tableName);
        ComparisonPredicate.Op op = ComparisonPredicate.Op.EQUALS;
        ComparisonPredicate predicate = new ComparisonPredicate("hello", op, 4.3);

        Tuple tuple = new Tuple(tupleDesc);
        boolean failed=false;
        try {
            predicate.filter(tuple);
        } catch (TypeMismatch e) {
            failed = true;
        }
        assertTrue(failed);
    }

    @Test
    public void filter4() {
        String tableName = "table";
        TupleDesc tupleDesc = Utility.getTupleDesc(3, "tup");
        tupleDesc.setTableName(tableName);
        ComparisonPredicate.Op op = ComparisonPredicate.Op.EQUALS;
        ComparisonPredicate predicate = new ComparisonPredicate("hello", op, "hello2");

        Tuple tuple = new Tuple(tupleDesc);
        try {
            assertFalse(predicate.filter(tuple));
        } catch (TypeMismatch e) {
            fail();
        }
    }

    @Test
    public void filter5() {
        String tableName = "table";
        TupleDesc tupleDesc = Utility.getTupleDesc(3, "tup");
        tupleDesc.setTableName(tableName);
        ComparisonPredicate.Op op = ComparisonPredicate.Op.EQUALS;
        ComparisonPredicate predicate = new ComparisonPredicate("hello", op, "hello");

        Tuple tuple = new Tuple(tupleDesc);
        try {
            assertTrue(predicate.filter(tuple));
        } catch (TypeMismatch e) {
            fail();
        }
    }

    @Test
    public void filter6() {
        String tableName = "table";
        TupleDesc tupleDesc = Utility.getTupleDesc(3, "tup");
        tupleDesc.setTableName(tableName);
        Attribute attribute = new Attribute(tableName, tupleDesc.getField(0).fieldName);
        Attribute attribute1 = new Attribute(tableName, tupleDesc.getField(1).fieldName);
        ComparisonPredicate.Op op = ComparisonPredicate.Op.LESS_THAN_OR_EQ;
        ComparisonPredicate predicate = new ComparisonPredicate(attribute, op, attribute1);

        Tuple tuple = new Tuple(tupleDesc);
        try {
            tuple.setField(0, new IntField(4));
            tuple.setField(1, new IntField(10));
            assertTrue(predicate.filter(tuple));

            tuple.setField(1, new IntField(4));
            assertTrue(predicate.filter(tuple));

            tuple.setField(1, new IntField(1));
            assertFalse(predicate.filter(tuple));
        } catch (TypeMismatch e) {
            fail();
        }
    }
}