package db.query;

import db.Tuple;

import java.io.Serializable;

public class LogicalPredicate implements Serializable, Predicate {
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
        AND, OR;

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
            if (this == AND)
                return "&&";
            if (this == OR)
                return "||";
            throw new IllegalStateException("impossible to reach here");
        }
    }


    @Override
    public boolean filter(Tuple tuple) {
        boolean c1 = left.filter(tuple);
        boolean c2 = right.filter(tuple);
        if (op == Op.AND) {
            return c1 && c2;
        } else {
            return c1 || c2;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
