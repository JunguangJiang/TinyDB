package tinydb;

import java.io.*;
import java.util.*;

/**
 * HeapFile is an implementation of a DbFile that stores a collection of tuples
 * in no particular order. Tuples are stored on pages, each of which is a fixed
 * size, and the file is simply a collection of those pages. HeapFile works
 * closely with HeapPage. The format of HeapPages is described in the HeapPage
 * constructor.
 * 
 * @see tinydb.HeapPage#HeapPage
 * @author Sam Madden
 */
public class HeapFile implements DbFile {

    private File file;
    private TupleDesc tupleDesc;

    /**
     * Constructs a heap file backed by the specified file.
     * 
     * @param f
     *            the file that stores the on-disk backing store for this heap
     *            file.
     */
    public HeapFile(File f, TupleDesc td){
        this.file = f;
        this.tupleDesc = td;
    }

    /**
     * Returns the File backing this HeapFile on disk.
     * 
     * @return the File backing this HeapFile on disk.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Returns an ID uniquely identifying this HeapFile. Implementation note:
     * you will need to generate this tableid somewhere to ensure that each
     * HeapFile has a "unique id," and that you always return the same value for
     * a particular HeapFile. We suggest hashing the absolute file name of the
     * file underlying the heapfile, i.e. f.getAbsoluteFile().hashCode().
     * 
     * @return an ID uniquely identifying this HeapFile.
     */
    public int getId() {
        return this.file.getAbsoluteFile().hashCode();
    }

    /**
     * Returns the TupleDesc of the table stored in this DbFile.
     * 
     * @return TupleDesc of this DbFile.
     */
    public TupleDesc getTupleDesc() {
        return this.tupleDesc;
    }

    // see DbFile.java for javadocs
    public Page readPage(PageId pid) {
        try{
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.file, "r");
            int offset = pid.getPageNumber() * BufferPool.getPageSize();
            byte[] bytes = new byte[BufferPool.getPageSize()];
            randomAccessFile.seek(offset);
            randomAccessFile.read(bytes, 0, BufferPool.getPageSize());
            HeapPageId heapPageId = (HeapPageId) pid;
            randomAccessFile.close();
            return new HeapPage(heapPageId, bytes);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    // see DbFile.java for javadocs
    public void writePage(Page page) throws IOException {
        try {
            HeapPageId pageId = (HeapPageId)page.getId();
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            int offset = pageId.getPageNumber()*BufferPool.getPageSize();
            byte[] bytes = new byte[BufferPool.getPageSize()];
            bytes = page.getPageData();
            randomAccessFile.seek(offset);
            randomAccessFile.write(bytes, 0, BufferPool.getPageSize());
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the number of pages in this HeapFile.
     */
    public int numPages() {
        return (int)Math.ceil((float)file.length() / BufferPool.getPageSize());
    }

    // see DbFile.java for javadocs
    public ArrayList<Page> insertTuple(TransactionId tid, Tuple t)
            throws DbException, IOException, TransactionAbortedException {
          ArrayList<Page> affectedPages = new ArrayList<>();
          try {
              HeapPage heapPage = (HeapPage)this.getEmptyPage(tid);
              if (heapPage == null) {
                  HeapPageId heapPageId = new HeapPageId(this.getId(), this.numPages());
                  heapPage = new HeapPage(heapPageId, HeapPage.createEmptyPageData());
                  heapPage.insertTuple(t);
                  this.writePage(heapPage);
              } else {
                  heapPage.insertTuple(t);
              }
              affectedPages.add(heapPage);
          } catch (DbException e){
              e.printStackTrace();
          }
          return affectedPages;
    }

    /**
     *
     * @param tid
     * @return a page which has empty slots, if all the pages have no empty slot, then return null
     * @throws DbException
     * @throws TransactionAbortedException
     */
    public Page getEmptyPage(TransactionId tid)
            throws DbException, TransactionAbortedException {
        try {
            int tableId = this.getId();
            for (int i = 0; i < this.numPages(); i++) {
                HeapPageId pageId = new HeapPageId(tableId, i);
                Page page = Database.getBufferPool().getPage(tid, pageId, Permissions.READ_ONLY);
                if (((HeapPage)page).getNumEmptySlots() != 0){
                    return page;
                }
            }
        } catch (DbException e){
            e.printStackTrace();
        }
        return null;
    }

    // see DbFile.java for javadocs
    public ArrayList<Page> deleteTuple(TransactionId tid, Tuple t) throws DbException,
            TransactionAbortedException {
        PageId pageId = t.getRecordId().getPageId();
        HeapPage page = (HeapPage) Database.getBufferPool().getPage(tid, pageId, Permissions.READ_WRITE);
        page.deleteTuple(t);
        ArrayList<Page> affectedPages = new ArrayList<>();
        affectedPages.add(page);
        return affectedPages;
    }

    // see DbFile.java for javadocs
    public DbFileIterator iterator(TransactionId tid) {
        return new HeapFileIterator(tid, this);
    }

}

