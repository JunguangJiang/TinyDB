package db.query;

import db.field.Field;
import db.field.Op;
import db.field.TypeMismatch;
import db.field.Util;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.Serializable;

/**
 * KVCmpPredicate compares an FullColumnName of a Table and a constant Field value
 */
public class KVCmpPredicate extends Predicate implements Serializable {
    private static final long serialVersionUID = 1L;
    private Op op;
    private int keyIdx;
    private Field valueField;

    public KVCmpPredicate(LogicalFilterNode.KVCmp cmp, TupleDesc tupleDesc) throws TypeMismatch{
        keyIdx = tupleDesc.fullColunmnNameToIndex(cmp.fullColumnName);
        valueField = Util.getField(cmp.value, tupleDesc.getTDItem(keyIdx));
        this.op = cmp.op;
    }

    @Override
    public boolean filter(Tuple tuple) throws TypeMismatch {
        return tuple.getField(keyIdx).compare(op, valueField);
    }
}
