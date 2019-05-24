package db.file.BTree;

import java.io.*;
import java.util.*;

import db.*;
import db.field.Field;
import db.field.Op;
import db.file.*;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

/**
 * BTreeFile is an implementation of a DbFile that stores a B+ tree.
 * Specifically, it stores a pointer to a root page,
 * a set of internal pages, and a set of leaf pages, which contain a collection of tuples
 * in sorted order.
 *
 * @see db.file.BTree.BTreeLeafPage#BTreeLeafPage
 * @see db.file.BTree.BTreeInternalPage#BTreeInternalPage
 * @see db.file.BTree.BTreeHeaderPage#BTreeHeaderPage
 * @see db.file.BTree.BTreeRootPtrPage#BTreeRootPtrPage
 */
public class BTreeFile implements DbFile {

	private final File f;
	private final TupleDesc td;
	private final int tableid;
	private int keyField;

	/**
	 * Constructs a B+ tree file backed by the specified file.
	 * 
	 * @param f - the file that stores the on-disk backing store for this B+ tree
	 *            file.
	 * @param td - the tuple descriptor of tuples in the file
     *
     * (already dropped) param key - the field which index is keyed on, now we just use the first column
     *            of PrimaryKey rather than read from this parameter
	 */
	public BTreeFile(int id, File f, TupleDesc td){
        this.f = f;
        this.tableid = id;
        this.keyField = td.getPrimaryKeyIndex();
        this.td = td;
    }

	/**
	 * Returns the File backing this BTreeFile on disk.
	 */
	public File getFile() {
		return f;
	}

	/**
	 * @return an ID uniquely identifying this BTreeFile.
	 */
	public int getId() {
		return tableid;
	}

	/**
	 * Returns the TupleDesc of the table stored in this DbFile.
	 * 
	 * @return TupleDesc of this DbFile.
	 */
	public TupleDesc getTupleDesc() {
		return td;
	}

	/**
	 * Read a page from the file on disk. This should not be called directly
	 * but should be called from the BufferPool via getPage()
	 * 
	 * @param pid - the id of the page to read from disk
	 * @return the page constructed from the contents on disk
	 */
	public Page readPage(PageId pid) {
		BTreePageId id = (BTreePageId) pid;
		BufferedInputStream bis = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(f));
			if(id.pgcateg() == BTreePageId.ROOT_PTR) {
				byte pageBuf[] = new byte[BTreeRootPtrPage.getPageSize()];
				int retval = bis.read(pageBuf, 0, BTreeRootPtrPage.getPageSize());
				if (retval == -1) {
					throw new IllegalArgumentException("Read past end of table");
				}
				if (retval < BTreeRootPtrPage.getPageSize()) {
					throw new IllegalArgumentException("Unable to read "
							+ BTreeRootPtrPage.getPageSize() + " bytes from BTreeFile");
				}
				Debug.log(1, "BTreeFile.readPage: read page %d", id.getPageNumber());
				BTreeRootPtrPage p = new BTreeRootPtrPage(id, pageBuf);
				return p;
			}
			else {
				byte pageBuf[] = new byte[BufferPool.getPageSize()];
				if (bis.skip(BTreeRootPtrPage.getPageSize() + (id.getPageNumber()-1) * BufferPool.getPageSize()) !=
						BTreeRootPtrPage.getPageSize() + (id.getPageNumber()-1) * BufferPool.getPageSize()) {
					throw new IllegalArgumentException(
							"Unable to seek to correct place in BTreeFile");
				}
				int retval = bis.read(pageBuf, 0, BufferPool.getPageSize());
				if (retval == -1) {
					throw new IllegalArgumentException("Read past end of table");
				}
				if (retval < BufferPool.getPageSize()) {
					throw new IllegalArgumentException("Unable to read "
							+ BufferPool.getPageSize() + " bytes from BTreeFile");
				}
				Debug.log(1, "BTreeFile.readPage: read page %d", id.getPageNumber());
				if(id.pgcateg() == BTreePageId.INTERNAL) {
					BTreeInternalPage p = new BTreeInternalPage(id, pageBuf, keyField);
					return p;
				}
				else if(id.pgcateg() == BTreePageId.LEAF) {
					BTreeLeafPage p = new BTreeLeafPage(id, pageBuf, keyField);
					return p;
				}
				else { // id.pgcateg() == BTreePageId.HEADER
					BTreeHeaderPage p = new BTreeHeaderPage(id, pageBuf);
					return p;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// Close the file on success or error
			try {
				if (bis != null)
					bis.close();
			} catch (IOException ioe) {
				// Ignore failures closing the file
				ioe.printStackTrace();
			}
		}
	}

	/**
	 * Write a page to disk.  This should not be called directly but should 
	 * be called from the BufferPool when pages are flushed to disk
	 * 
	 * @param page - the page to write to disk
	 */
	public void writePage(Page page) throws IOException {
		BTreePageId id = (BTreePageId) page.getId();
		
		byte[] data = page.getPageData();
		RandomAccessFile rf = new RandomAccessFile(f, "rw");
		if(id.pgcateg() == BTreePageId.ROOT_PTR) {
			rf.write(data);
			rf.close();
		}
		else {
			rf.seek(BTreeRootPtrPage.getPageSize() + (page.getId().getPageNumber()-1) * BufferPool.getPageSize());
			rf.write(data);
			rf.close();
		}
	}
	
	/**
	 * Returns the number of pages in this BTreeFile.
	 */
	public int numPages() {
		// we only ever write full pages
		return (int) ((f.length() - BTreeRootPtrPage.getPageSize())/ BufferPool.getPageSize());
	}

	/**
	 * Returns the index of the field that this B+ tree is keyed on
	 */
	public int keyField() {
		return keyField;
	}

	/**
	 * Recursive function which finds and locks the leaf page in the B+ tree corresponding to
	 * the left-most page possibly containing the key field f.
	 *
	 * If f is null, it finds the left-most leaf page -- used for the iterator
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param pid - the current page being searched
	 * @param f - the field to search for
	 * @return the left-most leaf page possibly containing the key field f
	 * 
	 */
	private BTreeLeafPage findLeafPage(HashMap<PageId, Page> dirtypages, BTreePageId pid, Field f)
					throws DbException{
		// some code goes here
		if (pid.pgcateg() == BTreePageId.LEAF) {
			return (BTreeLeafPage) this.getPage(dirtypages, pid);
		} else {
			// internal and page can not be empty
			BTreePageId nextSearchId;
			BTreeInternalPage searchPg = (BTreeInternalPage) this.getPage(dirtypages, pid);

			BTreeEntry entry;
			Iterator<BTreeEntry> it = searchPg.iterator();
			if (it.hasNext()) {
				entry = it.next();
			} else {
				throw new DbException("findLeafPage: InternalPage must contain at least one data");
			}

			if (f == null) {
				nextSearchId = entry.getLeftChild();
			} else {
				while (f.compare(Op.GREATER_THAN, entry.getKey()) && it.hasNext()) {
					entry = it.next();
				}

				if (f.compare(Op.LESS_THAN_OR_EQ, entry.getKey())) {
					nextSearchId = entry.getLeftChild();
				} else {
					// greater than the last one
					nextSearchId = entry.getRightChild();
				}
			}
			return findLeafPage(dirtypages, nextSearchId, f);
		}
	}
	
	/**
	 * Convenience method to find a leaf page when there is no dirtypages HashMap.
	 * Used by the BTreeFile iterator.
	 * @see #findLeafPage(HashMap, BTreePageId, Field)
	 *
	 * @param pid - the current page being searched
	 * @param f - the field to search for
	 * @return the left-most leaf page possibly containing the key field f
	 * 
	 */
	BTreeLeafPage findLeafPage(BTreePageId pid, Field f) throws DbException {
		return findLeafPage(new HashMap<PageId, Page>(), pid, f);
	}

	/**
	 * Split a leaf page to make room for new tuples and recursively split the parent node
	 * as needed to accommodate a new entry. The new entry should have a key matching the key field
	 * of the first tuple in the right-hand page (the key is "copied up"), and child pointers 
	 * pointing to the two leaf pages resulting from the split.  Update sibling pointers and parent 
	 * pointers as needed.
	 * 
	 * Return the leaf page into which a new tuple with key field "field" should be inserted.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param page - the leaf page to split
	 * @param field - the key field of the tuple to be inserted after the split is complete. Necessary to know
	 * which of the two pages to return.
	 * @see #getParentWithEmptySlots(HashMap, BTreePageId, Field)
	 * 
	 * @return the leaf page into which the new tuple should be inserted
	 * @throws DbException
	 * @throws IOException
	 */
	protected BTreeLeafPage splitLeafPage(HashMap<PageId, Page> dirtypages, BTreeLeafPage page, Field field)
			throws DbException, IOException {
		BTreeLeafPage newRightSib = (BTreeLeafPage) getEmptyPage(dirtypages, BTreePageId.LEAF);
		// copy half tuples
		Iterator<Tuple> it = page.reverseIterator();
		Tuple[] tupleToMove = new Tuple[(page.getNumTuples()+1) / 2];
		int moveCnt = tupleToMove.length - 1;
		while (moveCnt >= 0 && it.hasNext()) {
			tupleToMove[moveCnt--] = it.next();
		}

		for (int i = tupleToMove.length-1; i >= 0; --i) {
			// t.getRecordId().getPageId().pageNumber();
			page.deleteTuple(tupleToMove[i]);
			newRightSib.insertTuple(tupleToMove[i]);
		}

		// assert newRightSib.getNumTuples() >= 1;
		Field midkey = tupleToMove[0].getField(keyField);
		BTreeInternalPage parent = getParentWithEmptySlots(dirtypages, page.getParentId(), midkey);

		BTreePageId oldRightSibId = page.getRightSiblingId();

		newRightSib.setRightSiblingId(oldRightSibId);
		newRightSib.setLeftSiblingId(page.getId());
		page.setRightSiblingId(newRightSib.getId());

		// set up parent
		newRightSib.setParentId(parent.getId());
		page.setParentId(parent.getId());

		// Leaf -> internal, copy to parent
		BTreeEntry newParentEntry = new BTreeEntry(midkey, page.getId(), newRightSib.getId());
		parent.insertEntry(newParentEntry);
		// set dirtypages and old sibs
		if (oldRightSibId != null) {
			BTreeLeafPage oldRightSib = (BTreeLeafPage) getPage(dirtypages, page.getRightSiblingId());
			oldRightSib.setLeftSiblingId(newRightSib.getId());
			dirtypages.put(oldRightSib.getId(), oldRightSib);
		}

		dirtypages.put(parent.getId(), parent);
		dirtypages.put(page.getId(), page);
		dirtypages.put(newRightSib.getId(), newRightSib);

		if (field.compare(Op.GREATER_THAN, midkey)) {
			return newRightSib;
		} else {
			return page;
		}
	}
	
	/**
	 * Split an internal page to make room for new entries and recursively split its parent page
	 * as needed to accommodate a new entry. The new entry for the parent should have a key matching 
	 * the middle key in the original internal page being split (this key is "pushed up" to the parent). 
	 * The child pointers of the new parent entry should point to the two internal pages resulting 
	 * from the split. Update parent pointers as needed.
	 * 
	 * Return the internal page into which an entry with key field "field" should be inserted
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param page - the internal page to split
	 * @param field - the key field of the entry to be inserted after the split is complete. Necessary to know
	 * which of the two pages to return.
	 * @see #getParentWithEmptySlots(HashMap, BTreePageId, Field)
	 * @see #updateParentPointers(HashMap, BTreeInternalPage)
	 * 
	 * @return the internal page into which the new entry should be inserted
	 * @throws DbException
	 * @throws IOException
	 */
	protected BTreeInternalPage splitInternalPage(HashMap<PageId, Page> dirtypages,
			BTreeInternalPage page, Field field) 
					throws DbException, IOException {
		BTreeInternalPage newInternalPg = (BTreeInternalPage) getEmptyPage(dirtypages, BTreePageId.INTERNAL);

		Iterator<BTreeEntry> it = page.reverseIterator();
		BTreeEntry[] entryToMove = new BTreeEntry[(page.getNumEntries()+1) / 2];
		int moveCnt = entryToMove.length - 1;
		BTreeEntry midKey = null;

		while (moveCnt >= 0 && it.hasNext()) {
			entryToMove[moveCnt--] = it.next();
		}

		for (int i = entryToMove.length-1; i >= 0; --i) {
			if (i == 0) {
				page.deleteKeyAndRightChild(entryToMove[i]);
				midKey = entryToMove[0];
			} else {
				// order matters
				page.deleteKeyAndRightChild(entryToMove[i]);
				newInternalPg.insertEntry(entryToMove[i]);
			}
			updateParentPointer(dirtypages, newInternalPg.getId(), entryToMove[i].getRightChild());
		}
		// update child
		midKey.setLeftChild(page.getId());
		midKey.setRightChild(newInternalPg.getId());

		BTreeInternalPage parent = getParentWithEmptySlots(dirtypages, page.getParentId(), midKey.getKey());
		parent.insertEntry(midKey);

		// update midkey
		page.setParentId(parent.getId());
		newInternalPg.setParentId(parent.getId());

		dirtypages.put(page.getId(), page);
		dirtypages.put(newInternalPg.getId(), newInternalPg);
		dirtypages.put(parent.getId(), parent);

		if (field.compare(Op.GREATER_THAN, midKey.getKey())) {
			return newInternalPg;
		} else {
			return page;
		}
	}
	
	/**
	 * Method to encapsulate the process of getting a parent page ready to accept new entries.
	 * This may mean creating a page to become the new root of the tree, splitting the existing 
	 * parent page if there are no empty slots, or simply locking and returning the existing parent page.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param parentId - the id of the parent. May be an internal page or the RootPtr page
	 * @param field - the key of the entry which will be inserted. Needed in case the parent must be split
	 * to accommodate the new entry
	 * @return the parent page, guaranteed to have at least one empty slot
	 * @see #splitInternalPage(HashMap, BTreeInternalPage, Field)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	private BTreeInternalPage getParentWithEmptySlots(HashMap<PageId, Page> dirtypages,
			BTreePageId parentId, Field field) throws DbException, IOException {
		
		BTreeInternalPage parent = null;
		
		// create a parent node if necessary
		// this will be the new root of the tree
		if(parentId.pgcateg() == BTreePageId.ROOT_PTR) {
			parent = (BTreeInternalPage) getEmptyPage(dirtypages, BTreePageId.INTERNAL);

			// update the root pointer
			BTreeRootPtrPage rootPtr = (BTreeRootPtrPage) getPage(dirtypages, BTreeRootPtrPage.getId(tableid));
			BTreePageId prevRootId = rootPtr.getRootId(); //save prev id before overwriting.
			rootPtr.setRootId(parent.getId());

			// update the previous root to now point to this new root.
			BTreePage prevRootPage = (BTreePage)getPage(dirtypages, prevRootId);
			prevRootPage.setParentId(parent.getId());
		}
		else { 
			// lock the parent page
			parent = (BTreeInternalPage) getPage(dirtypages, parentId);
		}

		// split the parent if needed
		if(parent.getNumEmptySlots() == 0) {
			parent = splitInternalPage(dirtypages, parent, field);
		}

		return parent;

	}

	/**
	 * Helper function to update the parent pointer of a node.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param pid - id of the parent node
	 * @param child - id of the child node to be updated with the parent pointer
	 * @throws DbException
	 * @throws IOException
	 */
	private void updateParentPointer(HashMap<PageId, Page> dirtypages, BTreePageId pid, BTreePageId child)
			throws DbException, IOException {

		BTreePage p = (BTreePage) getPage(dirtypages, child);

		if(!p.getParentId().equals(pid)) {
			p = (BTreePage) getPage(dirtypages, child);
			p.setParentId(pid);
		}

	}
	
	/**
	 * Update the parent pointer of every child of the given page so that it correctly points to
	 * the parent
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param page - the parent page
	 * @see #updateParentPointer(HashMap, BTreePageId, BTreePageId)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	private void updateParentPointers(HashMap<PageId, Page> dirtypages, BTreeInternalPage page)
			throws DbException, IOException {
		Iterator<BTreeEntry> it = page.iterator();
		BTreePageId pid = page.getId();
		BTreeEntry e = null;
		while(it.hasNext()) {
			e = it.next();
			updateParentPointer(dirtypages, pid, e.getLeftChild());
		}
		if(e != null) {
			updateParentPointer(dirtypages, pid, e.getRightChild());
		}
	}
	
	/**
	 * Method to encapsulate the process of locking/fetching a page.  First the method checks the local 
	 * cache ("dirtypages"), and if it can't find the requested page there, it fetches it from the buffer pool.
	 * 
	 * This method is needed to ensure that page updates are not lost if the same pages are
	 * accessed multiple times.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param pid - the id of the requested page
	 * @return the requested page
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	Page getPage(HashMap<PageId, Page> dirtypages, BTreePageId pid)
			throws DbException {
		if(dirtypages.containsKey(pid)) {
			return dirtypages.get(pid);
		}
		else {
			Page p = GlobalManager.getBufferPool().getPage(pid);
			// TODO whether to put it in dirtypages
			/*if(perm == Permissions.READ_WRITE) {
				dirtypages.put(pid, p);
			}*/
			return p;
		}
	}

	/**
	 * Insert a tuple into this BTreeFile, keeping the tuples in sorted order. 
	 * May cause pages to split if the page where tuple t belongs is full.
	 *
	 * @param t - the tuple to insert
	 * @return a list of all pages that were dirtied by this operation. Could include
	 * many pages since parent pointers will need to be updated when an internal node splits.
	 * @see #splitLeafPage(HashMap, BTreeLeafPage, Field)
	 */
	public ArrayList<Page> insertTuple(Tuple t)
			throws DbException, IOException, PrimaryKeyViolation {
		HashMap<PageId, Page> dirtypages = new HashMap<PageId, Page>();
        // check tupleDesc equation
        if (!t.getTupleDesc().equals(td))
            throw new DbException("type mismatch, in insertTuple");

		// get a read lock on the root pointer page and use it to locate the root page
		BTreeRootPtrPage rootPtr = getRootPtrPage(dirtypages);
		BTreePageId rootId = rootPtr.getRootId();

		if(rootId == null) { // the root has just been created, so set the root pointer to point to it		
			rootId = new BTreePageId(tableid, numPages(), BTreePageId.LEAF);
			rootPtr = (BTreeRootPtrPage) getPage(dirtypages, BTreeRootPtrPage.getId(tableid));
			rootPtr.setRootId(rootId);
		}

		// find and lock the left-most leaf page corresponding to the key field,
		// and split the leaf page if there are no more slots available
		BTreeLeafPage leafPage = findLeafPage(dirtypages, rootId, t.getField(keyField));

        // check primaryKey constraint
		Iterator<Tuple> it = leafPage.iterator();
		int primaryKeyIndex = td.getPrimaryKeyIndex();
		while(it.hasNext()){
		    Tuple tuple = it.next();
		    if(tuple.getField(primaryKeyIndex).equals(t.getField(primaryKeyIndex))){
		        throw new PrimaryKeyViolation(td.getPrimaryKey(), tuple.getField(primaryKeyIndex));
            }
        }

		if(leafPage.getNumEmptySlots() == 0) {
			leafPage = splitLeafPage(dirtypages, leafPage, t.getField(keyField));
		}

		// insert the tuple into the leaf page
		leafPage.insertTuple(t);

		ArrayList<Page> dirtyPagesArr = new ArrayList<Page>();
		dirtyPagesArr.addAll(dirtypages.values());
		return dirtyPagesArr;
	}
	
	/**
	 * Handle the case when a B+ tree page becomes less than half full due to deletions.
	 * If one of its siblings has extra tuples/entries, redistribute those tuples/entries.
	 * Otherwise merge with one of the siblings. Update pointers as needed.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param page - the page which is less than half full
	 * @see #handleMinOccupancyLeafPage(HashMap, BTreeLeafPage, BTreeInternalPage, BTreeEntry, BTreeEntry)
	 * @see #handleMinOccupancyInternalPage(HashMap, BTreeInternalPage, BTreeInternalPage, BTreeEntry, BTreeEntry)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	private void handleMinOccupancyPage(HashMap<PageId, Page> dirtypages, BTreePage page)
			throws DbException, IOException {
		BTreePageId parentId = page.getParentId();
		BTreeEntry leftEntry = null;
		BTreeEntry rightEntry = null;
		BTreeInternalPage parent = null;

		// find the left and right siblings through the parent so we make sure they have
		// the same parent as the page. Find the entries in the parent corresponding to 
		// the page and siblings
		if(parentId.pgcateg() != BTreePageId.ROOT_PTR) {
			parent = (BTreeInternalPage) getPage(dirtypages, parentId);
			Iterator<BTreeEntry> ite = parent.iterator();
			while(ite.hasNext()) {
				BTreeEntry e = ite.next();
				if(e.getLeftChild().equals(page.getId())) {
					rightEntry = e;
					break;
				}
				else if(e.getRightChild().equals(page.getId())) {
					leftEntry = e;
				}
			}
		}
		
		if(page.getId().pgcateg() == BTreePageId.LEAF) {
			handleMinOccupancyLeafPage(dirtypages, (BTreeLeafPage) page, parent, leftEntry, rightEntry);
		}
		else { // BTreePageId.INTERNAL
			handleMinOccupancyInternalPage(dirtypages, (BTreeInternalPage) page, parent, leftEntry, rightEntry);
		}
	}
	
	/**
	 * Handle the case when a leaf page becomes less than half full due to deletions.
	 * If one of its siblings has extra tuples, redistribute those tuples.
	 * Otherwise merge with one of the siblings. Update pointers as needed.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param page - the leaf page which is less than half full
	 * @param parent - the parent of the leaf page
	 * @param leftEntry - the entry in the parent pointing to the given page and its left-sibling
	 * @param rightEntry - the entry in the parent pointing to the given page and its right-sibling
	 * @see #mergeLeafPages(HashMap, BTreeLeafPage, BTreeLeafPage, BTreeInternalPage, BTreeEntry)
	 * @see #stealFromLeafPage(BTreeLeafPage, BTreeLeafPage, BTreeInternalPage,  BTreeEntry, boolean)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	private void handleMinOccupancyLeafPage(HashMap<PageId, Page> dirtypages, BTreeLeafPage page,
			BTreeInternalPage parent, BTreeEntry leftEntry, BTreeEntry rightEntry)
			throws DbException, IOException {
		BTreePageId leftSiblingId = null;
		BTreePageId rightSiblingId = null;
		if(leftEntry != null) leftSiblingId = leftEntry.getLeftChild();
		if(rightEntry != null) rightSiblingId = rightEntry.getRightChild();
		
		int maxEmptySlots = page.getMaxTuples() - page.getMaxTuples()/2; // ceiling
		if(leftSiblingId != null) {
			BTreeLeafPage leftSibling = (BTreeLeafPage) getPage(dirtypages, leftSiblingId);
			// if the left sibling is at minimum occupancy, merge with it. Otherwise
			// steal some tuples from it
			if(leftSibling.getNumEmptySlots() >= maxEmptySlots) {
				mergeLeafPages(dirtypages, leftSibling, page, parent, leftEntry);
			}
			else {
				stealFromLeafPage(page, leftSibling, parent, leftEntry, false);				
			}
		}
		else if(rightSiblingId != null) {	
			BTreeLeafPage rightSibling = (BTreeLeafPage) getPage(dirtypages, rightSiblingId);
			// if the right sibling is at minimum occupancy, merge with it. Otherwise
			// steal some tuples from it
			if(rightSibling.getNumEmptySlots() >= maxEmptySlots) {
				mergeLeafPages(dirtypages, page, rightSibling, parent, rightEntry);
			}
			else {
				stealFromLeafPage(page, rightSibling, parent, rightEntry, true);				
			}
		}
	}
	
	/**
	 * Steal tuples from a sibling and copy them to the given page so that both pages are at least
	 * half full.  Update the parent's entry so that the key matches the key field of the first
	 * tuple in the right-hand page.
	 * 
	 * @param page - the leaf page which is less than half full
	 * @param sibling - the sibling which has tuples to spare
	 * @param parent - the parent of the two leaf pages
	 * @param entry - the entry in the parent pointing to the two leaf pages
	 * @param isRightSibling - whether the sibling is a right-sibling
	 * 
	 * @throws DbException
	 */
	protected void stealFromLeafPage(BTreeLeafPage page, BTreeLeafPage sibling,
			BTreeInternalPage parent, BTreeEntry entry, boolean isRightSibling) throws DbException {
		BTreeLeafPage rhs;
		int numTupleToMove = (sibling.getNumTuples() - page.getNumTuples()) / 2;
		Tuple[] tpToMove = new Tuple[numTupleToMove];
		int cntdown = tpToMove.length - 1;
		Iterator<Tuple> it;
		if (isRightSibling) {
			rhs = sibling;
			it = sibling.iterator();
		} else {
			// left sib
			rhs = page;
			it = sibling.reverseIterator();
		}
		while (cntdown >= 0 && it.hasNext()) {
			tpToMove[cntdown--] = it.next();
		}

		// keys are effectively "rotated" through the parent
		for (Tuple t : tpToMove) {
			sibling.deleteTuple(t);
			page.insertTuple(t);
		}

		if (rhs.getNumTuples() > 0) {
			entry.setKey(rhs.iterator().next().getField(keyField));
			parent.updateEntry(entry);
		}
	}

	/**
	 * Handle the case when an internal page becomes less than half full due to deletions.
	 * If one of its siblings has extra entries, redistribute those entries.
	 * Otherwise merge with one of the siblings. Update pointers as needed.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param page - the internal page which is less than half full
	 * @param parent - the parent of the internal page
	 * @param leftEntry - the entry in the parent pointing to the given page and its left-sibling
	 * @param rightEntry - the entry in the parent pointing to the given page and its right-sibling
	 * @see #mergeInternalPages(HashMap, BTreeInternalPage, BTreeInternalPage, BTreeInternalPage, BTreeEntry)
	 * @see #stealFromLeftInternalPage(HashMap, BTreeInternalPage, BTreeInternalPage, BTreeInternalPage, BTreeEntry)
	 * @see #stealFromRightInternalPage(HashMap, BTreeInternalPage, BTreeInternalPage, BTreeInternalPage, BTreeEntry)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	private void handleMinOccupancyInternalPage(HashMap<PageId, Page> dirtypages,
			BTreeInternalPage page, BTreeInternalPage parent, BTreeEntry leftEntry, BTreeEntry rightEntry) 
					throws DbException, IOException {
		BTreePageId leftSiblingId = null;
		BTreePageId rightSiblingId = null;
		if(leftEntry != null) leftSiblingId = leftEntry.getLeftChild();
		if(rightEntry != null) rightSiblingId = rightEntry.getRightChild();
		
		int maxEmptySlots = page.getMaxEntries() - page.getMaxEntries()/2; // ceiling
		if(leftSiblingId != null) {
			BTreeInternalPage leftSibling = (BTreeInternalPage) getPage(dirtypages, leftSiblingId);
			// if the left sibling is at minimum occupancy, merge with it. Otherwise
			// steal some entries from it
			if(leftSibling.getNumEmptySlots() >= maxEmptySlots) {
				mergeInternalPages(dirtypages, leftSibling, page, parent, leftEntry);
			}
			else {
				stealFromLeftInternalPage(dirtypages, page, leftSibling, parent, leftEntry);
			}
		}
		else if(rightSiblingId != null) {
			BTreeInternalPage rightSibling = (BTreeInternalPage) getPage(dirtypages, rightSiblingId);
			// if the right sibling is at minimum occupancy, merge with it. Otherwise
			// steal some entries from it
			if(rightSibling.getNumEmptySlots() >= maxEmptySlots) {
				mergeInternalPages(dirtypages, page, rightSibling, parent, rightEntry);
			}
			else {
				stealFromRightInternalPage(dirtypages, page, rightSibling, parent, rightEntry);
			}
		}
	}
	
	/**
	 * Steal entries from the left sibling and copy them to the given page so that both pages are at least
	 * half full. Keys can be thought of as rotating through the parent entry, so the original key in the 
	 * parent is "pulled down" to the right-hand page, and the last key in the left-hand page is "pushed up"
	 * to the parent.  Update parent pointers as needed.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param page - the internal page which is less than half full
	 * @param leftSibling - the left sibling which has entries to spare
	 * @param parent - the parent of the two internal pages
	 * @param leftEntry - the entry in the parent pointing to the two internal pages
	 * @see #updateParentPointers(HashMap, BTreeInternalPage)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	protected void stealFromLeftInternalPage(HashMap<PageId, Page> dirtypages,
			BTreeInternalPage page, BTreeInternalPage leftSibling, BTreeInternalPage parent,
			BTreeEntry leftEntry) throws DbException, IOException {
        int numToMove = (leftSibling.getNumEntries() - page.getNumEntries()) / 2;
        BTreeEntry[] entryToMove = new BTreeEntry[numToMove];

        Iterator<BTreeEntry> it = leftSibling.reverseIterator();
        int cntdown = entryToMove.length - 1;

        while (cntdown >= 0 && it.hasNext()) {
            entryToMove[cntdown--] = it.next();
        }

        for (int i=entryToMove.length-1; i >= 0; --i) {
            BTreeEntry entry = entryToMove[i];
            // rotated through the parent
            leftSibling.deleteKeyAndRightChild(entry);
            updateParentPointer(dirtypages, page.getId(), entry.getRightChild());
            // entry from leftsib must be inserted into first place in right page
            BTreePageId pid = entry.getRightChild();
            entry.setLeftChild(leftEntry.getLeftChild());
            entry.setRightChild(leftEntry.getRightChild());
            entry.setRecordId(leftEntry.getRecordId());

            parent.updateEntry(entry);

            leftEntry.setLeftChild(pid);
            assert page.iterator().hasNext();
            leftEntry.setRightChild(page.iterator().next().getLeftChild());

            // entry.setLeftChild(entry.getRightChild());
            // entry.setRightChild(page.getChildId(0));
            page.insertEntry(leftEntry);
            leftEntry = entry;
        }
        dirtypages.put(parent.getId(), parent);
        dirtypages.put(leftSibling.getId(), leftSibling);
        dirtypages.put(page.getId(), page);
	}
	
	/**
	 * Steal entries from the right sibling and copy them to the given page so that both pages are at least
	 * half full. Keys can be thought of as rotating through the parent entry, so the original key in the 
	 * parent is "pulled down" to the left-hand page, and the last key in the right-hand page is "pushed up"
	 * to the parent.  Update parent pointers as needed.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param page - the internal page which is less than half full
	 * @param rightSibling - the right sibling which has entries to spare
	 * @param parent - the parent of the two internal pages
	 * @param rightEntry - the entry in the parent pointing to the two internal pages
	 * @see #updateParentPointers(HashMap, BTreeInternalPage)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	protected void stealFromRightInternalPage(HashMap<PageId, Page> dirtypages,
			BTreeInternalPage page, BTreeInternalPage rightSibling, BTreeInternalPage parent,
			BTreeEntry rightEntry) throws DbException, IOException {
        int numToMove = (rightSibling.getNumEntries() - page.getNumEntries()) / 2;
        BTreeEntry[] btentries = new BTreeEntry[numToMove];

        Iterator<BTreeEntry> it = rightSibling.iterator();
        int cntdown = 0;

        // make copy avoid copy while modify in lll
        while (cntdown < btentries.length && it.hasNext()) {
            btentries[cntdown++] = it.next();
        }

        for (int i=0; i < btentries.length; ++i) {
            BTreeEntry entry = btentries[i];
            // rotated through the parent
            rightSibling.deleteKeyAndLeftChild(entry);
            updateParentPointer(dirtypages, page.getId(), entry.getLeftChild());

            BTreePageId pid = entry.getLeftChild();
            entry.setLeftChild(rightEntry.getLeftChild());
            entry.setRightChild(rightEntry.getRightChild());
            entry.setRecordId(rightEntry.getRecordId());

            parent.updateEntry(entry);

            rightEntry.setRightChild(pid);
            assert page.reverseIterator().hasNext();
            rightEntry.setLeftChild(page.reverseIterator().next().getRightChild());

            // entry.setLeftChild(entry.getRightChild());
            // entry.setRightChild(page.getChildId(0));
            page.insertEntry(rightEntry);
            rightEntry = entry;

        }
        dirtypages.put(parent.getId(), parent);
        dirtypages.put(page.getId(), page);
        dirtypages.put(rightSibling.getId(), rightSibling);
	}
	
	/**
	 * Merge two leaf pages by moving all tuples from the right page to the left page. 
	 * Delete the corresponding key and right child pointer from the parent, and recursively 
	 * handle the case when the parent gets below minimum occupancy.
	 * Update sibling pointers as needed, and make the right page available for reuse.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param leftPage - the left leaf page
	 * @param rightPage - the right leaf page
	 * @param parent - the parent of the two pages
	 * @param parentEntry - the entry in the parent corresponding to the leftPage and rightPage
	 * @see #deleteParentEntry(HashMap, BTreePage, BTreeInternalPage, BTreeEntry)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	protected void mergeLeafPages(HashMap<PageId, Page> dirtypages,
			BTreeLeafPage leftPage, BTreeLeafPage rightPage, BTreeInternalPage parent, BTreeEntry parentEntry) 
					throws DbException, IOException {
        Tuple[] tpToDelete = new Tuple[rightPage.getNumTuples()];
        int deleteCnt = tpToDelete.length - 1;
        Iterator<Tuple> it = rightPage.iterator();
        while (deleteCnt >= 0 && it.hasNext()) {
            tpToDelete[deleteCnt--] = it.next();
        }

        for (Tuple t : tpToDelete) {
            rightPage.deleteTuple(t);
            leftPage.insertTuple(t);
        }
        //
        BTreePageId rightSibId = rightPage.getRightSiblingId();
        leftPage.setRightSiblingId(rightSibId);
        if (rightSibId != null) {
            BTreeLeafPage rightSib = (BTreeLeafPage) getPage(dirtypages, rightSibId);
            rightSib.setLeftSiblingId(leftPage.getId());
            dirtypages.put(rightSibId, rightSib);
        }
        dirtypages.put(leftPage.getId(), leftPage);
        setEmptyPage(dirtypages, rightPage.getId().getPageNumber());

        deleteParentEntry(dirtypages, leftPage, parent, parentEntry);
	}

	/**
	 * Merge two internal pages by moving all entries from the right page to the left page 
	 * and "pulling down" the corresponding key from the parent entry. 
	 * Delete the corresponding key and right child pointer from the parent, and recursively 
	 * handle the case when the parent gets below minimum occupancy.
	 * Update parent pointers as needed, and make the right page available for reuse.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param leftPage - the left internal page
	 * @param rightPage - the right internal page
	 * @param parent - the parent of the two pages
	 * @param parentEntry - the entry in the parent corresponding to the leftPage and rightPage
	 * @see #deleteParentEntry(HashMap, BTreePage, BTreeInternalPage, BTreeEntry)
	 * @see #updateParentPointers(HashMap, BTreeInternalPage)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	protected void mergeInternalPages(HashMap<PageId, Page> dirtypages,
			BTreeInternalPage leftPage, BTreeInternalPage rightPage, BTreeInternalPage parent, BTreeEntry parentEntry) 
					throws DbException, IOException {
        deleteParentEntry(dirtypages, leftPage, parent, parentEntry);

        parentEntry.setLeftChild(leftPage.reverseIterator().next().getRightChild());
        parentEntry.setRightChild(rightPage.iterator().next().getLeftChild());

        leftPage.insertEntry(parentEntry);

        while (rightPage.iterator().hasNext()) {
            BTreeEntry entry = rightPage.iterator().next();
            rightPage.deleteKeyAndLeftChild(entry);
            updateParentPointer(dirtypages, leftPage.getId(), entry.getLeftChild());
            updateParentPointer(dirtypages, leftPage.getId(), entry.getRightChild());
            leftPage.insertEntry(entry);
        }
        dirtypages.put(leftPage.getId(), leftPage);
        setEmptyPage(dirtypages, rightPage.getId().getPageNumber());
	}
	
	/**
	 * Method to encapsulate the process of deleting an entry (specifically the key and right child) 
	 * from a parent node.  If the parent becomes empty (no keys remaining), that indicates that it 
	 * was the root node and should be replaced by its one remaining child.  Otherwise, if it gets 
	 * below minimum occupancy for non-root internal nodes, it should steal from one of its siblings or 
	 * merge with a sibling.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param leftPage - the child remaining after the key and right child are deleted
	 * @param parent - the parent containing the entry to be deleted
	 * @param parentEntry - the entry to be deleted
	 * @see #handleMinOccupancyPage(HashMap, BTreePage)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	private void deleteParentEntry(HashMap<PageId, Page> dirtypages,
			BTreePage leftPage, BTreeInternalPage parent, BTreeEntry parentEntry) 
					throws DbException, IOException {
		
		// delete the entry in the parent.  If
		// the parent is below minimum occupancy, get some tuples from its siblings
		// or merge with one of the siblings
		parent.deleteKeyAndRightChild(parentEntry);
		int maxEmptySlots = parent.getMaxEntries() - parent.getMaxEntries()/2; // ceiling
		if(parent.getNumEmptySlots() == parent.getMaxEntries()) {
			// This was the last entry in the parent.
			// In this case, the parent (root node) should be deleted, and the merged 
			// page will become the new root
			BTreePageId rootPtrId = parent.getParentId();
			if(rootPtrId.pgcateg() != BTreePageId.ROOT_PTR) {
				throw new DbException("attempting to delete a non-root node");
			}
			BTreeRootPtrPage rootPtr = (BTreeRootPtrPage) getPage(dirtypages, rootPtrId);
			leftPage.setParentId(rootPtrId);
			rootPtr.setRootId(leftPage.getId());

			// release the parent page for reuse
			setEmptyPage(dirtypages, parent.getId().getPageNumber());
		}
		else if(parent.getNumEmptySlots() > maxEmptySlots) { 
			handleMinOccupancyPage(dirtypages, parent);
		}
	}

	/**
	 * Delete a tuple from this BTreeFile. 
	 * May cause pages to merge or redistribute entries/tuples if the pages 
	 * become less than half full.
	 *
	 * @param t - the tuple to delete
	 * @return a list of all pages that were dirtied by this operation. Could include
	 * many pages since parent pointers will need to be updated when an internal node merges.
	 * @see #handleMinOccupancyPage(HashMap, BTreePage)
	 */
	public ArrayList<Page> deleteTuple(Tuple t)
			throws DbException, IOException {
		HashMap<PageId, Page> dirtypages = new HashMap<PageId, Page>();

		BTreePageId pageId = new BTreePageId(tableid, t.getRecordId().getPageId().getPageNumber(),
				BTreePageId.LEAF);
		BTreeLeafPage page = (BTreeLeafPage) getPage(dirtypages, pageId);
		page.deleteTuple(t);

		// if the page is below minimum occupancy, get some tuples from its siblings
		// or merge with one of the siblings
		int maxEmptySlots = page.getMaxTuples() - page.getMaxTuples()/2; // ceiling
		if(page.getNumEmptySlots() > maxEmptySlots) { 
			handleMinOccupancyPage(dirtypages, page);
		}

		ArrayList<Page> dirtyPagesArr = new ArrayList<Page>();
		dirtyPagesArr.addAll(dirtypages.values());
		return dirtyPagesArr;
	}

	/**
	 * Get a read lock on the root pointer page. Create the root pointer page and root page
	 * if necessary.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages 
	 * @return the root pointer page
	 * @throws DbException
	 * @throws IOException
	 */
	BTreeRootPtrPage getRootPtrPage(HashMap<PageId, Page> dirtypages) throws DbException, IOException {
		synchronized(this) {
			if(f.length() == 0) {
				// create the root pointer page and the root page
				BufferedOutputStream bw = new BufferedOutputStream(
						new FileOutputStream(f, true));
				byte[] emptyRootPtrData = BTreeRootPtrPage.createEmptyPageData();
				byte[] emptyLeafData = BTreeLeafPage.createEmptyPageData();
				bw.write(emptyRootPtrData);
				bw.write(emptyLeafData);
				bw.close();
			}
		}

		// get a read lock on the root pointer page
		return (BTreeRootPtrPage) getPage(dirtypages, BTreeRootPtrPage.getId(tableid));
	}

	/**
	 * Get the page number of the first empty page in this BTreeFile.
	 * Creates a new page if none of the existing pages are empty.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @return the page number of the first empty page
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	protected int getEmptyPageNo(HashMap<PageId, Page> dirtypages)
			throws DbException, IOException {
		// get a read lock on the root pointer page and use it to locate the first header page
		BTreeRootPtrPage rootPtr = getRootPtrPage(dirtypages);
		BTreePageId headerId = rootPtr.getHeaderId();
		int emptyPageNo = 0;

		if(headerId != null) {
			BTreeHeaderPage headerPage = (BTreeHeaderPage) getPage(dirtypages, headerId);
			int headerPageCount = 0;
			// try to find a header page with an empty slot
			while(headerPage != null && headerPage.getEmptySlot() == -1) {
				headerId = headerPage.getNextPageId();
				if(headerId != null) {
					headerPage = (BTreeHeaderPage) getPage(dirtypages, headerId);
					headerPageCount++;
				}
				else {
					headerPage = null;
				}
			}

			// if headerPage is not null, it must have an empty slot
			if(headerPage != null) {
				headerPage = (BTreeHeaderPage) getPage(dirtypages, headerId);
				int emptySlot = headerPage.getEmptySlot();
				headerPage.markSlotUsed(emptySlot, true);
				emptyPageNo = headerPageCount * BTreeHeaderPage.getNumSlots() + emptySlot;
			}
		}

		// at this point if headerId is null, either there are no header pages 
		// or there are no free slots
		if(headerId == null) {		
			synchronized(this) {
				// create the new page
				BufferedOutputStream bw = new BufferedOutputStream(
						new FileOutputStream(f, true));
				byte[] emptyData = BTreeInternalPage.createEmptyPageData();
				bw.write(emptyData);
				bw.close();
				emptyPageNo = numPages();
			}
		}

		return emptyPageNo; 
	}
	
	/**
	 * Method to encapsulate the process of creating a new page.  It reuses old pages if possible,
	 * and creates a new page if none are available.  It wipes the page on disk and in the cache and 
	 * returns a clean copy
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param pgcateg - the BTreePageId category of the new page.  Either LEAF, INTERNAL, or HEADER
	 * @return the new empty page
	 * @see #getEmptyPageNo(HashMap)
	 * @see #setEmptyPage(HashMap, int)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	private Page getEmptyPage(HashMap<PageId, Page> dirtypages, int pgcateg)
			throws DbException, IOException {
		// create the new page
		int emptyPageNo = getEmptyPageNo(dirtypages);
		BTreePageId newPageId = new BTreePageId(tableid, emptyPageNo, pgcateg);
		
		// write empty page to disk
		RandomAccessFile rf = new RandomAccessFile(f, "rw");
		rf.seek(BTreeRootPtrPage.getPageSize() + (emptyPageNo-1) * BufferPool.getPageSize());
		rf.write(BTreePage.createEmptyPageData());
		rf.close();
		
		// make sure the page is not in the buffer pool	or in the local cache		
		GlobalManager.getBufferPool().discardPage(newPageId);
		dirtypages.remove(newPageId);
		
		return getPage(dirtypages, newPageId);
	}

	/**
	 * Mark a page in this BTreeFile as empty. Find the corresponding header page 
	 * (create it if needed), and mark the corresponding slot in the header page as empty.
	 *
	 * @param dirtypages - the list of dirty pages which should be updated with all new dirty pages
	 * @param emptyPageNo - the page number of the empty page
	 * @see #getEmptyPage(HashMap, int)
	 * 
	 * @throws DbException
	 * @throws IOException
	 */
	protected void setEmptyPage(HashMap<PageId, Page> dirtypages, int emptyPageNo)
			throws DbException, IOException{

		// if this is the last page in the file (and not the only page), just 
		// truncate the file
		// otherwise, get a read lock on the root pointer page and use it to locate 
		// the first header page
		BTreeRootPtrPage rootPtr = getRootPtrPage(dirtypages);
		BTreePageId headerId = rootPtr.getHeaderId();
		BTreePageId prevId = null;
		int headerPageCount = 0;

		// if there are no header pages, create the first header page and update
		// the header pointer in the BTreeRootPtrPage
		if(headerId == null) {
			rootPtr = (BTreeRootPtrPage) getPage(dirtypages, BTreeRootPtrPage.getId(tableid));
			
			BTreeHeaderPage headerPage = (BTreeHeaderPage) getEmptyPage(dirtypages, BTreePageId.HEADER);
			headerId = headerPage.getId();
			headerPage.init();
			rootPtr.setHeaderId(headerId);
		}

		// iterate through all the existing header pages to find the one containing the slot
		// corresponding to emptyPageNo
		while(headerId != null && (headerPageCount + 1) * BTreeHeaderPage.getNumSlots() < emptyPageNo) {
			BTreeHeaderPage headerPage = (BTreeHeaderPage) getPage(dirtypages, headerId);
			prevId = headerId;
			headerId = headerPage.getNextPageId();
			headerPageCount++;
		}

		// at this point headerId should either be null or set with 
		// the headerPage containing the slot corresponding to emptyPageNo.
		// Add header pages until we have one with a slot corresponding to emptyPageNo
		while((headerPageCount + 1) * BTreeHeaderPage.getNumSlots() < emptyPageNo) {
			BTreeHeaderPage prevPage = (BTreeHeaderPage) getPage(dirtypages, prevId);
			
			BTreeHeaderPage headerPage = (BTreeHeaderPage) getEmptyPage(dirtypages, BTreePageId.HEADER);
			headerId = headerPage.getId();
			headerPage.init();
			headerPage.setPrevPageId(prevId);
			prevPage.setNextPageId(headerId);
			
			headerPageCount++;
			prevId = headerId;
		}

		// now headerId should be set with the headerPage containing the slot corresponding to 
		// emptyPageNo
		BTreeHeaderPage headerPage = (BTreeHeaderPage) getPage(dirtypages, headerId);
		int emptySlot = emptyPageNo - headerPageCount * BTreeHeaderPage.getNumSlots();
		headerPage.markSlotUsed(emptySlot, false);
	}

	/**
	 * get the specified tuples from the file based on its IndexPredicate value.
	 *
	 * @param ipred - the index predicate value to filter on
	 * @return an iterator for the filtered tuples
	 */
	public DbFileIterator indexIterator(IndexPredicate ipred) {
		return new BTreeSearchIterator(this, ipred);
	}

	/**
	 * Get an iterator for all tuples in this B+ tree file in sorted order.
	 *
	 * @return an iterator for all the tuples in this file
	 */
	public DbFileIterator iterator() {
		return new BTreeFileIterator(this);
	}

}

/**
 * Helper class that implements the Java Iterator for tuples on a BTreeFile
 */
class BTreeFileIterator extends AbstractDbFileIterator {

	Iterator<Tuple> it = null;
	BTreeLeafPage curp = null;

	BTreeFile f;

	/**
	 * Constructor for this iterator
	 * @param f - the BTreeFile containing the tuples
	 */
	public BTreeFileIterator(BTreeFile f) {
		this.f = f;
	}

	/**
	 * Open this iterator by getting an iterator on the first leaf page
	 */
	public void open() throws DbException {
		BTreeRootPtrPage rootPtr = (BTreeRootPtrPage) GlobalManager.getBufferPool().getPage(BTreeRootPtrPage.getId(f.getId()));
		BTreePageId root = rootPtr.getRootId();
		curp = f.findLeafPage(root, null);
		it = curp.iterator();
	}

	/**
	 * Read the next tuple either from the current page if it has more tuples or
	 * from the next page by following the right sibling pointer.
	 * 
	 * @return the next tuple, or null if none exists
	 */
	@Override
	protected Tuple readNext() throws DbException {
		if (it != null && !it.hasNext())
			it = null;

		while (it == null && curp != null) {
			BTreePageId nextp = curp.getRightSiblingId();
			if(nextp == null) {
				curp = null;
			}
			else {
				curp = (BTreeLeafPage) GlobalManager.getBufferPool().getPage(nextp);
				it = curp.iterator();
				if (!it.hasNext())
					it = null;
			}
		}

		if (it == null)
			return null;
		return it.next();
	}

	/**
	 * rewind this iterator back to the beginning of the tuples
	 */
	public void rewind() throws DbException{
		close();
		open();
	}

	/**
	 * close the iterator
	 */
	public void close() {
		super.close();
		it = null;
		curp = null;
	}
}

/**
 * Helper class that implements the DbFileIterator for search tuples on a
 * B+ Tree File
 */
class BTreeSearchIterator extends AbstractDbFileIterator {

	Iterator<Tuple> it = null;
	BTreeLeafPage curp = null;

	BTreeFile f;
	IndexPredicate ipred;

	/**
	 * Constructor for this iterator
	 * @param f - the BTreeFile containing the tuples
	 * @param ipred - the predicate to filter on
	 */
	public BTreeSearchIterator(BTreeFile f, IndexPredicate ipred) {
		this.f = f;
		this.ipred = ipred;
	}

	/**
	 * Open this iterator by getting an iterator on the first leaf page applicable
	 * for the given predicate operation
	 */
	public void open() throws DbException {
		BTreeRootPtrPage rootPtr = (BTreeRootPtrPage) GlobalManager.getBufferPool().getPage(
		        BTreeRootPtrPage.getId(f.getId()));
		BTreePageId root = rootPtr.getRootId();
		if(ipred.getOp() == Op.EQUALS || ipred.getOp() == Op.GREATER_THAN
				|| ipred.getOp() == Op.GREATER_THAN_OR_EQ) {
			curp = f.findLeafPage(root, ipred.getField());
		}
		else {
			curp = f.findLeafPage(root, null);
		}
		it = curp.iterator();
	}

	/**
	 * Read the next tuple either from the current page if it has more tuples matching
	 * the predicate or from the next page by following the right sibling pointer.
	 * 
	 * @return the next tuple matching the predicate, or null if none exists
	 */
	@Override
	protected Tuple readNext() throws DbException, NoSuchElementException {
		while (it != null) {

			while (it.hasNext()) {
				Tuple t = it.next();
				if (t.getField(f.keyField()).compare(ipred.getOp(), ipred.getField())) {
					return t;
				}
				else if(ipred.getOp() == Op.LESS_THAN || ipred.getOp() == Op.LESS_THAN_OR_EQ) {
					// if the predicate was not satisfied and the operation is less than, we have
					// hit the end
					return null;
				}
				else if(ipred.getOp() == Op.EQUALS &&
						t.getField(f.keyField()).compare(Op.GREATER_THAN, ipred.getField())) {
					// if the tuple is now greater than the field passed in and the operation
					// is equals, we have reached the end
					return null;
				}
			}

			BTreePageId nextp = curp.getRightSiblingId();
			// if there are no more pages to the right, end the iteration
			if(nextp == null) {
				return null;
			}
			else {
				curp = (BTreeLeafPage) GlobalManager.getBufferPool().getPage(nextp);
				it = curp.iterator();
			}
		}

		return null;
	}

	/**
	 * rewind this iterator back to the beginning of the tuples
	 */
	public void rewind() throws DbException {
		close();
		open();
	}

	/**
	 * close the iterator
	 */
	public void close() {
		super.close();
		it = null;
	}
}
