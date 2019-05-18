package db.query.predicate;

import db.field.Op;
import db.field.TypeMismatch;
import db.query.plan.LogicalFilterNode;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

/**
 * KVCmpPredicate compares an FullColumnName of a Table and a FullColumnName of another Table
 */
public class KKCmpPredicate extends Predicate {
    private Op op;
    private int lhs, rhs;

    public KKCmpPredicate(LogicalFilterNode.KKCmpNode kkCmp, TupleDesc tupleDesc) {
        lhs = tupleDesc.fullColunmnNameToIndex(kkCmp.lhs);
        rhs = tupleDesc.fullColunmnNameToIndex(kkCmp.rhs);
        op = kkCmp.op;
    }

    @Override
    public boolean filter(Tuple tuple) throws TypeMismatch {
        return tuple.getField(lhs).compare(op, tuple.getField(rhs));
    }
}
