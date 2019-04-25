package db.query;

import db.DbException;
import db.Tuple;
import db.TupleDesc;

/**
 * Filter is an operator that implements a relational select.
 */
public class Filter extends Operator{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor accepts a predicate to apply and a child operator to read
     * tuples to filter from.
     *
     * @param predicate
     *            The predicate to filter tuples with
     * @param child
     *            The child operator
     */
    public Filter(Predicate predicate, OpIterator child) {

    }

    @Override
    public TupleDesc getTupleDesc() {
        return null;
    }

    @Override
    public void open() throws DbException {
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
     * Iterates over tuples from the
     * child operator, applying the predicate to them and returning those that
     * pass the predicate (i.e. for which the Predicate.filter() returns true.)
     *
     * @return The next tuple that passes the filter, or null if there are no
     *         more tuples
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
