package db.query.pipe;

import db.DbException;
import db.GlobalManager;
import db.file.BTree.BTreeFile;
import db.file.BTree.IndexPredicate;
import db.file.DbFileIterator;
import db.file.Table;
import db.query.pipe.OpIterator;
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

    /**
     * Creates a B+ tree scan over the specified table
     * @param tableName the name of the Table
     * @param indexPredicate
     * 			  The index predicate to match. If null, the scan will return all tuples
     *            in sorted order
     */
	public BTreeScan(String tableName, IndexPredicate indexPredicate) {
        this(tableName, tableName, indexPredicate);
	}

    /**
     * Creates a B+ tree scan over the specified table
     * @param tableName the name of the Table
     * @param tableAlias the alias of the Table
     * @param indexPredicate
     *            The index predicate to match. If null, the scan will return all tuples
     *            in sorted order
     */
	public BTreeScan(String tableName, String tableAlias, IndexPredicate indexPredicate) {
        this.isOpen = false;
        Table table = GlobalManager.getDatabase().getTable(tableName);
        if (indexPredicate == null) {
            this.iterator = GlobalManager.getDatabase().getDbFile(tableName).iterator();
        } else {
            this.iterator = ((BTreeFile)GlobalManager.getDatabase().getDbFile(tableName)).indexIterator(indexPredicate);
        }
        myTd = table.getTupleDesc();
        myTd.setTableName(tableAlias);
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

	public void close() {
		iterator.close();
		isOpen = false;
	}

	public void rewind() throws DbException, NoSuchElementException {
		close();
		open();
	}
}
