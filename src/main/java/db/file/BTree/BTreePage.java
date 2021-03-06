package db.file.BTree;

import db.DbException;
import db.GlobalManager;
import db.file.BufferPool;
import db.file.Page;
import db.tuple.TupleDesc;

import java.io.*;

/**
 * Each instance of BTreeInternalPage stores data for one page of a BTreeFile and 
 * implements the Page interface that is used by BufferPool.
 *
 * @see BTreeFile
 * @see db.file.BufferPool
 *
 */
public abstract class BTreePage implements Page {
	protected volatile boolean dirty = false;

	protected final static int INDEX_SIZE = 4;

	protected final BTreePageId pid;
	protected final TupleDesc td;
	protected final int keyField;

	protected int parent; // parent is always internal node or 0 for root node
	protected byte[] oldData;
	protected final Byte oldDataLock=new Byte((byte)0);

	/**
	 * @param id - the id of this page
	 * @param key - the field which the index is keyed on
	 */
	public BTreePage(BTreePageId id, int key) throws IOException {
		this.pid = id;
		this.keyField = key;
		this.td = GlobalManager.getDatabase().getTable(id.getTableId()).getTupleDesc();
	}

	/**
	 * @return the PageId associated with this page.
	 */
	public BTreePageId getId() {
		return pid;
	}

	/**
	 * Static method to generate a byte array corresponding to an empty
	 * BTreePage.
	 * Used to add new, empty pages to the file.
	 *
	 * @return The returned ByteArray.
	 */
	public static byte[] createEmptyPageData() {
		int len = BufferPool.getPageSize();
		return new byte[len]; // all 0
	}

	/**
	 * Get the parent id of this page
	 * @return the parent id
	 */
	public BTreePageId getParentId() {
		if(parent == 0) {
			return BTreeRootPtrPage.getId(pid.getTableId());
		}
		return new BTreePageId(pid.getTableId(), parent, BTreePageId.INTERNAL);
	}

	/**
	 * Set the parent id
	 * @param id - the id of the parent of this page
	 * @throws DbException if the id is not valid
	 */
	public void setParentId(BTreePageId id) throws DbException {
		if(id == null) {
			throw new DbException("parent id must not be null");
		}
		if(id.getTableId() != pid.getTableId()) {
			throw new DbException("table id mismatch in setParentId");
		}
		if(id.pgcateg() != BTreePageId.INTERNAL && id.pgcateg() != BTreePageId.ROOT_PTR) {
			throw new DbException("parent must be an internal node or root pointer");
		}
		if(id.pgcateg() == BTreePageId.ROOT_PTR) {
			parent = 0;
		}
		else {
			parent = id.getPageNumber();
		}
	}

	/**
	 * Marks this page as dirty/not dirty
	 */
	public void markDirty(boolean dirty) {
		this.dirty = dirty;
	}

	/**
	 * Returns whether the page is dirty or not
	 */
	public boolean isDirty() {
		return this.dirty;
	}

	/**
	 * Returns the number of empty slots on this page.
	 */
	public abstract int getNumEmptySlots();
	
	/**
	 * Returns true if associated slot on this page is filled.
	 */
	public abstract boolean isSlotUsed(int i);

}

