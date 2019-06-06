package db.query.pipe;

import db.DbException;
import db.GlobalManager;
import db.error.SQLError;
import db.file.BTree.BTreeFile;
import db.file.BTree.IndexPredicate;
import db.file.DbFileIterator;
import db.file.Table;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.util.*;

/**
 * BTreeScan is an operator which reads tuples in sorted order 
 * according to a predicate
 */
public class BTreeScan implements OpIterator {

	private static final long serialVersionUID = 1L;

	private boolean isOpen;
	private TupleDesc myTd;
	private transient DbFileIterator iterator;
	private Table table;

    /**
     * Creates a B+ tree scan over the specified table
     * @param table
     * @param indexPredicate The index predicate to match. If null, the scan will return all tuples
     *                       in sorted order
     */
    public BTreeScan(Table table, IndexPredicate indexPredicate) {
	    this.isOpen = false;
	    this.table = table;
	    if (indexPredicate == null) {
	        this.iterator = table.getDbFile().iterator();
        } else {
	        this.iterator = ((BTreeFile)table.getDbFile()).indexIterator(indexPredicate);
        }
	    myTd = table.getTupleDesc();
    }

	public void open() throws DbException {
		if (isOpen)
			throw new DbException("double open on one OpIterator.");

		iterator.open();
		isOpen = true;
	}

	/**
	 * Returns the TupleDesc with field names from the underlying BTreeFile,
	 * prefixed with the tableAlias string from the constructor. This prefix
	 * becomes useful when joining tables containing a field(s) with the same
	 * name.
	 * 
	 * @return the TupleDesc with field names from the underlying BTreeFile,
	 *         prefixed with the tableAlias string from the constructor.
	 */
	public TupleDesc getTupleDesc() {
		return myTd;
	}

	public boolean hasNext() throws DbException {
		if (!isOpen)
			throw new IllegalStateException("iterator is closed");
		return iterator.hasNext();
	}

	public Tuple next() throws NoSuchElementException, DbException {
		if (!isOpen)
			throw new IllegalStateException("iterator is closed");

		return iterator.next();
	}

	public void deleteNext() throws DbException{
		if(!isOpen)
			throw new IllegalStateException("iterator is closed");
		iterator.deleteNext();
	}

	public void close() {
		iterator.close();
		isOpen = false;
	}

	public void rewind() throws DbException, NoSuchElementException {
		close();
		open();
	}

    @Override
    public long count() {
        return table.count;
    }
}
