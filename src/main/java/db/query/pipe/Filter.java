package db.query.pipe;

import db.DbException;
import db.error.SQLError;
import db.error.TypeMismatch;
import db.error.PrimaryKeyViolation;
import db.query.predicate.Predicate;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

/**
 * Filter is an operator that implements a relational select.
 */
public class Filter extends Operator{
    private static final long serialVersionUID = 1L;
    private Predicate predicate;
    private OpIterator child;

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
        this.predicate = predicate;
        this.child = child;
    }

    @Override
    public TupleDesc getTupleDesc() {
        return child.getTupleDesc();
    }

    @Override
    public void open() throws DbException, SQLError {
        child.open();
        super.open();
    }

    @Override
    public void close() {
        super.close();
        child.close();
    }

    @Override
    public void rewind() throws DbException {
        child.rewind();
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
    protected Tuple fetchNext() throws DbException, SQLError{
        while (child.hasNext()) {
            Tuple tuple = child.next();
            if (this.predicate.filter(tuple)) {
                return tuple;
            }
        }
        return null;
    }
}
