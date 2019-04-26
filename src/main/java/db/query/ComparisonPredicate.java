package db.query;

import db.field.Field;
import db.tuple.Tuple;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;

/**
 * ComparisonPredicate compares two Fields
 * Each Field can be an attribute of a Table or just constant Field value.
 */
public class ComparisonPredicate extends Predicate implements Serializable {
    private static final long serialVersionUID = 1L;
    private Op op;
    private Object lhs, rhs;

    /**
     * If lhs is an int, it refers to the attribute index in a tuple.
     * Else lhs is a Field, it refers to a const Field value.
     *
     * @param lhs
     * @param op
     * @param rhs
     */
    public ComparisonPredicate(Object lhs, Op op, Object rhs) {
        this.lhs = lhs;
        this.op = op;
        this.rhs = rhs;
    }

    /**
     * @param tuple
     * @return
     *      If lhs is an index, return the corresponding Field in the tuple,
     *      otherwise return the const Field value.
     */
    public Field getLeft(Tuple tuple) {
        if (this.lhs instanceof Field){
            return (Field)this.lhs;
        } else if (this.lhs instanceof Integer){
            return tuple.getField((int)this.lhs);
        } else {
            throw new NotImplementedException();
        }
    }

    /**
     * @param tuple
     * @return
     *      If rhs is an index, return the corresponding Field in the tuple,
     *      otherwise return the const Field value.
     */
    public Field getRight(Tuple tuple) {
        if (this.rhs instanceof Field){
            return (Field)this.rhs;
        } else if (this.rhs instanceof Integer){
            return tuple.getField((int)this.rhs);
        } else {
            throw new NotImplementedException();
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

    /**
     * Filter tuple based on comparison between attributes of the tuple(or const Field values)
     * @param tuple
     *            The tuple to compare against
     * @return true if the comparison is true, false otherwise.
     */
    @Override
    public boolean filter(Tuple tuple) {
        return this.getLeft(tuple).compare(op, this.getRight(tuple));
    }

}
