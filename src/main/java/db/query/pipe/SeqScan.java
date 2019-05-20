package db.query.pipe;

import db.DbException;
import db.file.Table;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import db.file.DbFileIterator;

import java.util.NoSuchElementException;

/**
 * SeqScan is an implementation of a sequential scan access method that reads
 * each tuple of a table in no particular order (e.g., as they are laid out on
 * disk).
 */
public class SeqScan implements OpIterator{
    private static final long serialVersionUID = 1L;
    private DbFileIterator iterator;
    private Table table;

    public SeqScan(Table table) {
        this.table = table;
        this.iterator = null;
    }

    @Override
    public void open() throws DbException {
        iterator = table.iterator();
        iterator.open();
    }

    /**
     * @return the TupleDesc with field names from the underlying HeapFile,
     */
    public TupleDesc getTupleDesc() {
        return table.getTupleDesc();
    }

    @Override
    public boolean hasNext() throws DbException {
        if (iterator == null) {
            return false;
        }
        return iterator.hasNext();
    }

    @Override
    public Tuple next() throws DbException, NoSuchElementException {
        if (iterator == null) {
            throw new NoSuchElementException("tuple is null");
        }
        return iterator.next();
    }

    @Override
    public void close() {
        iterator.close();
    }

    @Override
    public void rewind() throws DbException {
        iterator.rewind();
    }
}

