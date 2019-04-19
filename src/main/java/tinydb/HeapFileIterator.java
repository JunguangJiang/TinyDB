package tinydb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class HeapFileIterator implements DbFileIterator{

    private Iterator<Tuple> iterator;
    private TransactionId tid;
    private int pgNum;
    private HeapFile f;

    public HeapFileIterator(TransactionId tid, HeapFile f) {
        this.tid = tid;
        this.f = f;
    }

    /**
     * Opens the iterator
     * @throws DbException when there are problems opening/accessing the database.
     */
    @Override
    public void open() throws DbException, TransactionAbortedException{
        pgNum = 0;
        iterator = getTupleIteratorFromPage(0);
    }

    /** @return true if there are more tuples available. */
    @Override
    public boolean hasNext() throws DbException, TransactionAbortedException{
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
    public Tuple next() throws DbException, TransactionAbortedException, NoSuchElementException {
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
     * Resets the iterator to the start.
     * @throws DbException When rewind is unsupported.
     */
    @Override
    public void rewind() throws DbException, TransactionAbortedException {
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
     * @throws TransactionAbortedException
     * @throws DbException
     */
    private Iterator<Tuple> getTupleIteratorFromPage(int pgNum) throws TransactionAbortedException, DbException {
        PageId pageId = new HeapPageId(f.getId(), pgNum);
        Page page = Database.getBufferPool().getPage(tid, pageId, Permissions.READ_ONLY);
        HeapPage heapPage = (HeapPage)page;
        return heapPage.iterator();
    }
}