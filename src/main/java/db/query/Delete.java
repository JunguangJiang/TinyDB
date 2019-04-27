package db.query;

import db.DbException;
import db.field.TypeMismatch;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

public class Delete extends Operator{
    private static final long serialVersionUID = 1L;

    /**
     * @param child
     *            The child operator from which to read tuples for deletion
     */
    public Delete(OpIterator child) {

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

    /**
     * Deletes tuples as they are read from the child operator. Deletes are
     * processed via the buffer pool (which can be accessed via the
     * Database.getBufferPool() method.
     *
     * @return A 1-field tuple containing the number of deleted records.
     */
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
