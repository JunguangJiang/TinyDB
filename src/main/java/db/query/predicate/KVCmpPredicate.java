package db.query.predicate;

import db.field.Field;
import db.field.Op;
import db.error.TypeMismatch;
import db.field.Util;
import db.query.plan.LogicalFilterNode;
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

    public KVCmpPredicate(LogicalFilterNode.KVCmpNode cmp, TupleDesc tupleDesc) throws TypeMismatch{
        keyIdx = tupleDesc.fullColumnNameToIndex(cmp.fullColumnName);
        valueField = Util.getField(cmp.value, tupleDesc.getTDItem(keyIdx));
        this.op = cmp.op;
    }

    @Override
    public boolean filter(Tuple tuple) throws TypeMismatch {
        return tuple.getField(keyIdx).compare(op, valueField);
    }
}
