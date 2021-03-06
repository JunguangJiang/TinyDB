package db.query.predicate;

import db.error.SQLError;

import db.tuple.Tuple;

import java.io.Serializable;

/**
 * LogicalPredicate is a compound predicate in which
 * predicates are combined by "AND" and "OR".
 */
public class LogicalPredicate extends Predicate implements Serializable {
    private static final long serialVersionUID = 1L;
    private Predicate left, right;
    private Op op;

    public LogicalPredicate(Predicate left, Op op, Predicate right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    /** Constants used for return codes in Field.compare */
    public enum Op implements Serializable {
        AND, OR
    }

    /**
     * Filter tuple based on compound predicate
     * @param tuple
     *            The tuple to compare against
     * @return true if the compound predicate is true, false otherwise.
     */
    @Override
    public boolean filter(Tuple tuple) throws SQLError {
        boolean c1 = left.filter(tuple);
        boolean c2 = right.filter(tuple);
        if (op == Op.AND) {
            return c1 && c2;
        } else if (op == Op.OR){
            return c1 || c2;
        } else {
            throw new IllegalStateException("impossible to reach here");
        }
    }
}
