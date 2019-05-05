package db.query;

import db.field.Field;
import db.field.TypeMismatch;
import db.field.Util;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import java.io.Serializable;

/**
 * ComparisonPredicate compares two Fields
 * Each Field can be an attribute of a Table or just constant Field value.
 */
public class ComparisonPredicate extends Predicate implements Serializable {
    private static final long serialVersionUID = 1L;
    private Op op;
    private Object lhs, rhs;
    private boolean preprocessed = false;
    private int leftIdx=-1, rightIdx=-1;
    private Field leftVal=null, rightVal=null;

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
     * When the ComparisonPredicate meets a tuple for the first time,
     * it should decide the leftIdx, rightIdx, leftVal, rightVal based on the tuple.
     * The preprocess would only happen for one time.
     *
     * If lhs is (tableName, attrName), then we can find leftIdx from TupleDesc,
     * Else we could get the most likely Field leftVal for lhs.
     * @param tuple
     * @throws TypeMismatch
     */
    private void preprocess(Tuple tuple) throws TypeMismatch {
        if (preprocessed) {
            return;
        }
        TupleDesc tupleDesc = tuple.getTupleDesc();
        if (lhs instanceof Attribute) {
            Attribute attribute = (Attribute)lhs;
            leftIdx = tupleDesc.fieldNameToIndex(attribute.tableName, attribute.attrName);
        }
        if (rhs instanceof Attribute) {
            Attribute attribute = (Attribute)rhs;
            rightIdx = tupleDesc.fieldNameToIndex(attribute.tableName, attribute.attrName);
        }
        if (!(lhs instanceof Attribute)) {
            if (rightIdx >= 0) {
                leftVal = Util.getField(lhs, tupleDesc.getField(rightIdx).fieldType,
                        tupleDesc.getField(rightIdx).maxLen);
            } else {
                leftVal = Util.getField(lhs);
            }
        }
        if (!(rhs instanceof Attribute)) {
            if (leftIdx >= 0) {
                rightVal = Util.getField(rhs, tupleDesc.getField(leftIdx).fieldType,
                        tupleDesc.getField(leftIdx).maxLen);
            } else {
                rightVal = Util.getField(rhs);
                if (leftVal.getType() != rightVal.getType()) {
                    throw new TypeMismatch(leftVal.getType(), rightVal.getType());
                }
            }
        }
        preprocessed=true;
    }

    /**
     * @param tuple
     * @return
     *      If lhs is an index, return the corresponding Field in the tuple,
     *      otherwise return the const Field value.
     */
    private Field getLeft(Tuple tuple) {
        if (leftIdx >= 0) {
            return tuple.getField(leftIdx);
        } else {
            return leftVal;
        }
    }

    /**
     * @param tuple
     * @return
     *      If rhs is an index, return the corresponding Field in the tuple,
     *      otherwise return the const Field value.
     */
    private Field getRight(Tuple tuple) {
        if (rightIdx >= 0) {
            return tuple.getField(rightIdx);
        } else {
            return rightVal;
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
    public boolean filter(Tuple tuple) throws TypeMismatch{
        preprocess(tuple);
        return this.getLeft(tuple).compare(op, this.getRight(tuple));
    }

}
