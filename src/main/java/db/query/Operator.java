package db.query;

import db.DbException;
import db.field.TypeMismatch;
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

    public boolean hasNext() throws DbException, TypeMismatch {
        if (!this.open)
            throw new IllegalStateException("Operator not yet open");

        if (next == null)
            next = fetchNext();
        return next != null;
    }

    public Tuple next() throws DbException,
            NoSuchElementException, TypeMismatch {
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
    protected abstract Tuple fetchNext() throws DbException, TypeMismatch;

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
    public void open() throws DbException, TypeMismatch {
        this.open = true;
    }

    /**
     * @return return the children DbIterators of this operator. If there is
     *         only one child, return an array of only one element. For join
     *         operators, the order of the children is not important. But they
     *         should be consistent among multiple calls.
     * */
    public abstract OpIterator[] getChildren();

    /**
     * Set the children(child) of this operator. If the operator has only one
     * child, children[0] should be used.
     * @param children
     *            the DbIterators which are to be set as the children(child) of
     *            this operator
     * */
    public abstract void setChildren(OpIterator[] children);

    /**
     * @return return the TupleDesc of the output tuples of this operator
     * */
    public abstract TupleDesc getTupleDesc();
}