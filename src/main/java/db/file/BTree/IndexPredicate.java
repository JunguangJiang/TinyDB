package db.file.BTree;

import db.field.Field;
import db.field.Op;
import java.io.Serializable;


/**
 * IndexPredicate compares a field which has index on it against a given value
 */
public class IndexPredicate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Op op;
    private Field fieldvalue;

    /**
     * Constructor.
     *
     * @param fvalue The value that the predicate compares against.
     * @param op The operation to apply (as defined in Op); either
     *   Op.GREATER_THAN, Op.LESS_THAN, Op.EQUAL,
     *   Op.GREATER_THAN_OR_EQ, or Op.LESS_THAN_OR_EQ
     * @see Op
     */
    public IndexPredicate(Op op, Field fvalue) {
        this.op = op;
        this.fieldvalue = fvalue;
    }

    public Field getField() {
        return fieldvalue;
    }

    public Op getOp() {
        return op;
    }

    /**
     * Return true if the fieldvalue in the supplied predicate
     * is satisfied by this predicate's fieldvalue and
     * operator.
     * @param ipd The field to compare against.
     */
    public boolean equals(IndexPredicate ipd) {
        if (ipd == null)
            return false;
        return (op.equals(ipd.op) && fieldvalue.equals(ipd.fieldvalue));
    }

}
