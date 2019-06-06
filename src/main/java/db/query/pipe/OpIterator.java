package db.query.pipe;

import db.DbException;
import db.error.SQLError;
import db.error.TypeMismatch;
import db.error.PrimaryKeyViolation;
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
    void open() throws DbException, SQLError;

    /** Returns true if the iterator has more tuples.
     * @return true if the iterator has more tuples.
     * @throws IllegalStateException If the iterator has not been opened
     */
    boolean hasNext() throws DbException, SQLError;

    /**
     * Returns the next tuple from the operator (typically implementing by reading
     * from a child operator or an access method).
     *
     * @return the next tuple in the iteration.
     * @throws NoSuchElementException if there are no more tuples.
     * @throws IllegalStateException If the iterator has not been opened
     */
    Tuple next() throws DbException, NoSuchElementException, SQLError;

    /**
     * Delete the next tuple from the operator (Used by Delete).
     *
     */
    void deleteNext() throws DbException;

    /**
     * Resets the iterator to the start.
     * @throws DbException when rewind is unsupported.
     * @throws IllegalStateException If the iterator has not been opened
     */
    void rewind() throws DbException;

    /**
     * Returns the TupleDesc associated with this OpIterator.
     * @return the TupleDesc associated with this OpIterator.
     */
    TupleDesc getTupleDesc();

    /**
     * Closes the iterator. When the iterator is closed, calling next(),
     * hasNext(), or rewind() should fail by throwing IllegalStateException.
     */
    void close();

}
