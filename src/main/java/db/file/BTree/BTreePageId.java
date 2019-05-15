package db.file.BTree;

import db.file.PageId;

/**
 * Unique identifier for BTreeInternalPage, BTreeLeafPage, BTreeHeaderPage
 *  and BTreeRootPtrPage objects. 
 */
public class BTreePageId implements PageId {

	public final static int ROOT_PTR = 0;
	public final static int INTERNAL = 1;
	public final static int LEAF = 2;
	public final static int HEADER = 3;

	private final int tableId;
	private final int pgNumber;
	private int pgcateg;

	static public String categToString(int category) {
		switch (category) {
			case ROOT_PTR:
				return "ROOT_PTR";
			case INTERNAL:
				return "INTERNAL";
			case LEAF:
				return "LEAF";
			case HEADER:
				return "HEADER";
			default:
				throw new IllegalArgumentException("category");
		}
	}

	/**
	 * Constructor. Create a page id structure for a specific page of a
	 * specific table.
	 *
	 * @param tableId The table that is being referenced
	 * @param pgNumber The page number in that table.
	 * @param pgcateg which kind of page it is
	 */
	public BTreePageId(int tableId, int pgNumber, int pgcateg) {
		this.tableId = tableId;
		this.pgNumber = pgNumber;
		this.pgcateg = pgcateg;
	}

	/** @return the table associated with this PageId */
	public int getTableId() {
		return tableId;
	}

	/**
	 * @return the page number in the table getTableId() associated with
	 *   this PageId
	 */
	public int getPageNumber() {
		return pgNumber;
	}

	/**
	 * @return the category of this page
	 */
	public int pgcateg() {
		return pgcateg;
	}

	/**
	 * @return a hash code for this page, represented by the concatenation of
	 *   the table number, page number, and pgcateg (needed if a PageId is used as a
	 *   key in a hash table in the BufferPool, for example.)
	 * @see db.file.BufferPool
	 */
	public int hashCode() {
		int code = (tableId << 16) + (pgNumber << 2) + pgcateg;
		return code;
	}

	/**
	 * Compares one PageId to another.
	 *
	 * @param o The object to compare against (must be a PageId)
	 * @return true if the objects are equal (e.g., page numbers, table
	 *   ids and pgcateg are the same)
	 */
	public boolean equals(Object o) {
		if (!(o instanceof BTreePageId))
			return false;
		BTreePageId p = (BTreePageId)o;
		return tableId == p.tableId && pgNumber == p.pgNumber && pgcateg == p.pgcateg;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(tableId: ").append(tableId)
				.append(", pgNumber: ").append(pgNumber)
				.append(", pgcateg: ").append(categToString(pgcateg))
				.append(")");

		return sb.toString();
	}

	/**
	 *  Return a representation of this object as an array of
	 *  integers, for writing to disk.  Size of returned array must contain
	 *  number of integers that corresponds to number of args to one of the
	 *  constructors.
	 */
	public int[] serialize() {
		int data[] = new int[3];

		data[0] = tableId;
		data[1] = pgNumber;
		data[2] = pgcateg;

		return data;
	}

}
