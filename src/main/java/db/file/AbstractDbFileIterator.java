package db.file;

import db.DbException;
import db.tuple.Tuple;

import java.util.NoSuchElementException;

/**
 * Helper for implementing DbFileIterators.
 */
public abstract class AbstractDbFileIterator implements DbFileIterator {

    public boolean hasNext() throws DbException {
        if (next == null) next = readNext();
        return next != null;
    }

    public Tuple next() throws DbException, NoSuchElementException {
        if (next == null) {
            next = readNext();
            if (next == null) throw new NoSuchElementException();
        }

        Tuple result = next;
        next = null;
        return result;
    }

    /** If subclasses override this, they should call super.close(). */
    public void close() {
        // Ensures that a future call to next() will fail
        next = null;
    }

    /** Reads the next tuple from the underlying source.
     @return the next Tuple in the iterator, null if the iteration is finished. */
    protected abstract Tuple readNext() throws DbException;

    private Tuple next = null;
}

