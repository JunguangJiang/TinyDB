package db.query.pipe;

import db.DbException;
import db.error.SQLError;

import db.error.PrimaryKeyViolation;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.util.NoSuchElementException;

/**
 * Abstract class for implementing operators. It handles <code>close</code>,
 * <code>next</code> and <code>hasNext</code>. Subclasses only need to implement
 * <code>open</code> and <code>readNext</code>.
 */
public abstract class Operator implements OpIterator{
    private static final long serialVersionUID = 1L;
    private Tuple next = null;
    private boolean open = false;

    public boolean hasNext() throws DbException, SQLError {
        if (!this.open)
            throw new IllegalStateException("Operator not yet open");

        if (next == null)
            next = fetchNext();
        return next != null;
    }

    public Tuple next() throws DbException,
            NoSuchElementException, SQLError {
        if (next == null) {
            next = fetchNext();
            if (next == null)
                throw new NoSuchElementException();
        }

        Tuple result = next;
        next = null;
        return result;
    }

    /**
     * Returns the next Tuple in the iterator, or null if the iteration is
     * finished. Operator uses this method to implement both <code>next</code>
     * and <code>hasNext</code>.
     *
     * @return the next Tuple in the iterator, or null if the iteration is
     *         finished.
     */
    protected abstract Tuple fetchNext() throws DbException, SQLError;

    /**
     * Closes this iterator. If overridden by a subclass, they should call
     * super.close() in order for Operator's internal state to be consistent.
     */
    public void close() {
        // Ensures that a future call to next() will fail
        next = null;
        this.open = false;
    }

    /**
     * Opens the iterator. This must be called before any of the other methods.
     * @throws DbException when there are problems opening/accessing the database.
     */
    public void open() throws DbException, SQLError{
        this.open = true;
    }

    /**
     * @return return the TupleDesc of the output tuples of this operator
     * */
    public abstract TupleDesc getTupleDesc();

    /**
     * @return the number of tuples in the opIterator
     *          -1 if has not counted
     */
    @Override
    public long count() {
        return -1;
    }
}
