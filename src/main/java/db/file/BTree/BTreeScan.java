package db.file.BTree;

import db.DbException;
import db.GlobalManager;
import db.file.DbFileIterator;
import db.file.Table;
import db.query.OpIterator;
import db.tuple.TDItem;
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
	private transient DbFileIterator it;


	/**
	 * Creates a B+ tree scan over the specified table
	 * @param ipred
	 * 			  The index predicate to match. If null, the scan will return all tuples
	 *            in sorted order
	 */
	public BTreeScan(String tableName, IndexPredicate ipred) {
        this(tableName, tableName, ipred);
	}

	public BTreeScan(String tableName, String tableAlias, IndexPredicate ipred) {
        this.isOpen = false;
        Table table = GlobalManager.getDatabase().getTable(tableName);
        if (ipred == null) {
            this.it = GlobalManager.getDatabase().getDbFile(tableName).iterator();
        } else {
            this.it = ((BTreeFile)GlobalManager.getDatabase().getDbFile(tableName)).indexIterator(ipred);
        }
        myTd = table.getTupleDesc();
        myTd.setTableName(tableAlias);
    }

	public void open() throws DbException {
		if (isOpen)
			throw new DbException("double open on one OpIterator.");

		it.open();
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
		return it.hasNext();
	}

	public Tuple next() throws NoSuchElementException, DbException {
		if (!isOpen)
			throw new IllegalStateException("iterator is closed");

		return it.next();
	}

	public void close() {
		it.close();
		isOpen = false;
	}

	public void rewind() throws DbException, NoSuchElementException {
		close();
		open();
	}
}
