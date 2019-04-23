package db.query;

import db.Field;
import db.Tuple;

import java.io.Serializable;

/**
 * ComparisonPredicate compares two Fields
 * Each Field can be an attribute of a Table or just constant Field value.
 */
public class ComparisonPredicate implements Serializable, Predicate {
    private static final long serialVersionUID = 1L;
    private int leftId=-1, rightId=-1;
    private Field left=null, right=null;
    private Op op;

    public ComparisonPredicate(int left, Op op, int right) {
        this.leftId = left;
        this.op = op;
        this.rightId = right;
    }

    public ComparisonPredicate(int left, Op op, Field right) {
        this.leftId = left;
        this.op = op;
        this.right = right;
    }

    public ComparisonPredicate(Field left, Op op, int right) {
        this.left = left;
        this.op = op;
        this.rightId = right;
    }

    public ComparisonPredicate(Field left, Op op, Field right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public Field getLeft(Tuple tuple) {
        if (this.leftId >= 0) {
            return tuple.getField(this.leftId);
        } else {
            return this.left;
        }
    }

    public Field getRight(Tuple tuple) {
        if (this.rightId >= 0) {
            return tuple.getField(this.rightId);
        } else {
            return this.right;
        }
    }

    /** Constants used for return codes in Field.compare */
    public enum Op implements Serializable {
        EQUALS, GREATER_THAN, LESS_THAN, LESS_THAN_OR_EQ, GREATER_THAN_OR_EQ, NOT_EQUALS;

        /**
         * Interface to access operations by integer value for command-line
         * convenience.
         *
         * @param i
         *            a valid integer Op index
         */
        public static Op getOp(int i) {
            return values()[i];
        }

        public String toString() {
            if (this == EQUALS)
                return "=";
            if (this == GREATER_THAN)
                return ">";
            if (this == LESS_THAN)
                return "<";
            if (this == LESS_THAN_OR_EQ)
                return "<=";
            if (this == GREATER_THAN_OR_EQ)
                return ">=";
            if (this == NOT_EQUALS)
                return "<>";
            throw new IllegalStateException("impossible to reach here");
        }
    }

    @Override
    public boolean filter(Tuple tuple) {
        return this.getLeft(tuple).compare(op, this.getRight(tuple));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
