package db.query;

import db.DbException;
import db.field.TypeMismatch;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.Serializable;
import java.util.*;

/**
 * OpIterator is the iterator interface that all TinyDB operators should
 * implement. If the iterator is not open, none of the methods should work,
 * and should throw an IllegalStateException.  In addition to any
 * resource allocation/deallocation, an open method should call any
 * child iterator open methods, and in a close method, an iterator
 * should call its children's close methods.
 */
public interface OpIterator extends Serializable{
    /**
     * Opens the iterator. This must be called before any of the other methods.
     * @throws DbException when there are problems opening/accessing the database.
     */
    public void open() throws DbException, TypeMismatch;

    /** Returns true if the iterator has more tuples.
     * @return true if the iterator has more tuples.
     * @throws IllegalStateException If the iterator has not been opened
     */
    public boolean hasNext() throws DbException, TypeMismatch;

    /**
     * Returns the next tuple from the operator (typically implementing by reading
     * from a child operator or an access method).
     *
     * @return the next tuple in the iteration.
     * @throws NoSuchElementException if there are no more tuples.
     * @throws IllegalStateException If the iterator has not been opened
     */
    public Tuple next() throws DbException, NoSuchElementException, TypeMismatch;

    /**
     * Resets the iterator to the start.
     * @throws DbException when rewind is unsupported.
     * @throws IllegalStateException If the iterator has not been opened
     */
    public void rewind() throws DbException;

    /**
     * Returns the TupleDesc associated with this OpIterator.
     * @return the TupleDesc associated with this OpIterator.
     */
    public TupleDesc getTupleDesc();

    /**
     * Closes the iterator. When the iterator is closed, calling next(),
     * hasNext(), or rewind() should fail by throwing IllegalStateException.
     */
    public void close();

}