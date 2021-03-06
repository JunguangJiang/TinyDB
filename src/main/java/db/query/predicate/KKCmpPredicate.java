package db.query.predicate;

import db.error.SQLError;
import db.field.Op;

import db.query.plan.LogicalFilterNode;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

/**
 * KKCmpPredicate compares an FullColumnName of a Table and a FullColumnName of another Table
 */
public class KKCmpPredicate extends Predicate {
    private Op op;
    private int lhs, rhs;

    public KKCmpPredicate(LogicalFilterNode.KKCmpNode kkCmp, TupleDesc tupleDesc) {
        lhs = tupleDesc.fullColumnNameToIndex(kkCmp.lhs);
        rhs = tupleDesc.fullColumnNameToIndex(kkCmp.rhs);
        op = kkCmp.op;
    }

    @Override
    public boolean filter(Tuple tuple) throws SQLError {
        return tuple.getField(lhs).compare(op, tuple.getField(rhs));
    }
}
