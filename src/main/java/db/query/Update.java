package db.query;

import db.DbException;
import db.field.TypeMismatch;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

/**
 * Project is an operator that implements updating.
 */
public class Update extends Operator{
    public static class UpdateElement {
        public UpdateElement(String attribute, Object value) {

        }
    }
    public Update(OpIterator child, UpdateElement[] updateElements) {

    }

    @Override
    public TupleDesc getTupleDesc() {
        return null;
    }

    @Override
    public void open() throws DbException, TypeMismatch {
        super.open();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void rewind() throws DbException {

    }

    @Override
    protected Tuple fetchNext() throws DbException {
        return null;
    }

    @Override
    public OpIterator[] getChildren() {
        return new OpIterator[0];
    }

    @Override
    public void setChildren(OpIterator[] children) {

    }
}
