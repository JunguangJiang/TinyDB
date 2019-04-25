package db.file;

import db.DbException;
import db.Tuple;

import java.util.NoSuchElementException;

/**
 * DbFileIterator is the iterator interface that all SimpleDB Dbfile should
 * implement.
 */
public interface DbFileIterator {
    /**
     * Opens the iterator
     * @throws tinydb.DbException when there are problems opening/accessing the database.
     */
    public void open() throws DbException;

    /** @return true if there are more tuples available, false if no more tuples or iterator isn't open. */
    public boolean hasNext() throws DbException;

    /**
     * Gets the next tuple from the operator (typically implementing by reading
     * from a child operator or an access method).
     *
     * @return The next tuple in the iterator.
     * @throws NoSuchElementException if there are no more tuples
     */
    public Tuple next() throws DbException, NoSuchElementException;

    /**
     * Resets the iterator to the start.
     * @throws tinydb.DbException When rewind is unsupported.
     */
    public void rewind() throws DbException;

    /**
     * Closes the iterator.
     */
    public void close();
}
