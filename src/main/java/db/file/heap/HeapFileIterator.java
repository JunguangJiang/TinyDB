package db.file.heap;

import db.DbException;
import db.GlobalManager;
import db.tuple.Tuple;
import db.file.DbFileIterator;
import db.file.Page;
import db.file.PageId;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapFileIterator implements DbFileIterator {
    private Iterator<Tuple> iterator;
    private int pgNum;
    private HeapFile f;

    public HeapFileIterator(HeapFile f) {
        this.f = f;
    }

    /**
     * Opens the iterator
     * @throws DbException when there are problems opening/accessing the database.
     */
    @Override
    public void open() throws DbException{
        pgNum = 0;
        iterator = getTupleIteratorFromPage(0);
    }

    /** @return true if there are more tuples available. */
    @Override
    public boolean hasNext() throws DbException {
        if(iterator == null){
            return false;
        } else if (iterator.hasNext()) { // there are tuples available on pages
            return true;
        } else if (pgNum < f.numPages() - 1) { // there are more pages to iterate
            Iterator<Tuple> it = getTupleIteratorFromPage(pgNum+1);
            return it.hasNext();
        } else { // there are no more pages
            return false;
        }
    }

    /**
     * Gets the next tuple from the operator (typically implementing by reading
     * from a child operator or an access method).
     *
     * @return The next tuple in the iterator.
     * @throws NoSuchElementException if there are no more tuples
     */
    @Override
    public Tuple next() throws DbException, NoSuchElementException {
        if (iterator == null) {
            throw new NoSuchElementException("tuple is null");
        }

        if (iterator.hasNext()) { // there are tuples available on pages
            Tuple tuple = iterator.next();
            return tuple;
        } else if( pgNum < (f.numPages() - 1) ) { // there are more pages to iterate
            iterator = getTupleIteratorFromPage((++pgNum));
            if(iterator.hasNext()){
                return iterator.next();
            } else {
                throw new NoSuchElementException("No more Tuples");
            }
        } else { // there are no more pages
            throw new NoSuchElementException("No more Tuples");
        }
    }

    /**
     * Delete next tuple of the iterator.
     * @see db.query.pipe.Delete
     */
    @Override
    public void deleteNext() {
        // Unused
    }

    /**
     * Resets the iterator to the start.
     * @throws DbException When rewind is unsupported.
     */
    @Override
    public void rewind() throws DbException {
        close();
        open();
    }

    /**
     * Closes the iterator.
     */
    @Override
    public void close(){
        iterator = null;
    }

    /**
     * Get a tuple iterator from page
     * @param pgNum
     * @return the page number
     * @throws DbException
     */
    private Iterator<Tuple> getTupleIteratorFromPage(int pgNum) throws DbException {
        PageId pageId = new HeapPageId(f.getId(), pgNum);
        Page page = GlobalManager.getBufferPool().getPage(pageId);
        HeapPage heapPage = (HeapPage)page;
        return heapPage.iterator();
    }
}
