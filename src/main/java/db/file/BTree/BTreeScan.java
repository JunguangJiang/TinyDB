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

	private boolean isOpen = false;
	private TupleDesc myTd;
	private IndexPredicate ipred = null;
	private transient DbFileIterator it;
	private String tablename;
	private String alias;

	/**
	 * Creates a B+ tree scan over the specified table
	 *
	 * @param tableid
	 *            the table to scan.
	 * @param tableAlias
	 *            the alias of this table (needed by the parser); the returned
	 *            tupleDesc should have fields with name tableAlias.fieldName
     *            this parameter should not be null
	 * @param ipred
	 * 			  The index predicate to match. If null, the scan will return all tuples
	 *            in sorted order
	 */
	public BTreeScan(int tableid, String tableAlias, IndexPredicate ipred) {
		this.ipred = ipred;
		reset(tableid, tableAlias);
	}

	/**
	 * @return
	 *       return the table name of the table the operator scans. This should
	 *       be the actual name of the table in the catalog of the database
	 * */
	public String getTableName() {
		return this.tablename;
	}

	/**
	 * @return Return the alias of the table this operator scans. 
	 * */
	public String getAlias()
	{
		return this.alias;
	}

	/**
	 * Reset the tableid, and tableAlias of this operator.
	 * @param tableid
	 *            the table to scan.
	 * @param tableAlias
	 *            the alias of this table (needed by the parser); the returned
	 *            tupleDesc should have fields with name tableAlias.fieldName
	 *            this parameter should not be null
	 */
	public void reset(int tableid, String tableAlias) {
		this.isOpen = false;
		this.alias = tableAlias;
        Table targetTable = GlobalManager.getDatabase().getTable(tableid);
		this.tablename = targetTable.getName();
		if(ipred == null) {
			this.it = GlobalManager.getDatabase().getDbFile(tableid).iterator();
		}
		else {
			this.it = ((BTreeFile) GlobalManager.getDatabase().getDbFile(tableid)).indexIterator(ipred);
		}
		myTd = targetTable.getTupleDesc();
		LinkedList<TDItem> newItems = new LinkedList<>();
		for (int i = 0; i < myTd.numFields(); i++) {
            TDItem tmp = myTd.getField(i);
            newItems.add(new TDItem(tmp.fieldType, tableAlias + "." + tmp.fieldName, tmp.notNull, tmp.maxLen));
        }

		myTd = new TupleDesc(newItems.toArray(new TDItem[0]), myTd.getPrimaryKeys());
	}

	public BTreeScan(int tableid, IndexPredicate ipred) {
		this(tableid, GlobalManager.getDatabase().getTable(tableid).getName(), ipred);
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
