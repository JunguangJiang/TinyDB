package db.file.heap;

import db.*;
import db.error.PrimaryKeyViolation;
import db.field.Field;
import db.field.Op;
import db.file.*;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * HeapFile is an implementation of a DbFile that stores a collection of tuples
 * in no particular order. Tuples are stored on pages, each of which is a fixed
 * size, and the file is simply a collection of those pages. HeapFile works
 * closely with HeapPage. The format of HeapPages is described in the HeapPage
 * constructor.
 *
 * @see HeapPage
 */
public class HeapFile implements DbFile {

    private File file;
    private int id;
    private TupleDesc tupleDesc;
    private boolean hasPrimaryKeyConstraint=true;

    /**
     * Constructs a heap file backed by the specified file.
     *
     * @param file
     *            the file that stores the on-disk backing store for this heap
     *            file.
     */
    public HeapFile(int id, File file, TupleDesc tupleDesc, boolean hasPrimaryKeyConstraint){
        this.id = id;
        this.file = file;
        this.tupleDesc = tupleDesc;
        this.hasPrimaryKeyConstraint = hasPrimaryKeyConstraint;
    }

    /**
     * @return the File backing this HeapFile on disk.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * @return an ID uniquely identifying this HeapFile.
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return the TupleDesc of the table stored in this DbFile.
     */
    public TupleDesc getTupleDesc() {
        return this.tupleDesc;
    }

    /**
     * @see DbFile#readPage(PageId)
     */
    public Page readPage(PageId pid) {
        try{
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.file, "r");
            int offset = pid.getPageNumber() * BufferPool.getPageSize();
            byte[] bytes = new byte[BufferPool.getPageSize()];
            randomAccessFile.seek(offset);
            randomAccessFile.read(bytes, 0, BufferPool.getPageSize());
            HeapPageId heapPageId = (HeapPageId) pid;
            randomAccessFile.close();
            return new HeapPage(heapPageId, bytes, tupleDesc);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * @see DbFile#writePage(Page)
     */
    public void writePage(Page page) throws IOException {
        try {
            HeapPageId pageId = (HeapPageId)page.getId();
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            int offset = pageId.getPageNumber()*BufferPool.getPageSize();
            byte[] bytes = page.getPageData();
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

    /**
     * Check Whether a Tuple violates the primary key constraint
     * @param t
     * @throws DbException
     * @throws PrimaryKeyViolation if the primary key constraint is violated
     */
    public void checkPrimaryKeyViolated(Tuple t) throws DbException, PrimaryKeyViolation{
        int tableId = this.getId();
        int numPages = this.numPages();
        int primaryKeyIdx = t.getTupleDesc().getPrimaryKeyIndex();
        Field pkField = t.getField(primaryKeyIdx);
        for (int i = 0; i < numPages; i++) {
            HeapPageId pageId = new HeapPageId(tableId, i);
            Page page = GlobalManager.getBufferPool().getPage(pageId);
            Iterator<Tuple> iterator = ((HeapPage)page).iterator();
            while (iterator.hasNext()) {
                Tuple tuple = iterator.next();
                if (tuple.getField(primaryKeyIdx).compare(Op.EQUALS, pkField)){
                    throw new PrimaryKeyViolation(t.getTupleDesc().getPrimaryKey(), pkField.getValue());
                }
            }
        }
    }

    /**
     * @see DbFile#insertTuple(Tuple)
     */
    public ArrayList<Page> insertTuple(Tuple t)
            throws IOException, PrimaryKeyViolation {
        ArrayList<Page> affectedPages = new ArrayList<>();
        try {
            if (hasPrimaryKeyConstraint) {
                this.checkPrimaryKeyViolated(t); //Check whether the Tuple satisfies the Primary Key
            }
            HeapPage heapPage = (HeapPage)this.getEmptyPage();
            if (heapPage == null) {
                HeapPageId heapPageId = new HeapPageId(this.getId(), this.numPages());
                heapPage = new HeapPage(heapPageId, HeapPage.createEmptyPageData(), tupleDesc);
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
     * @return a page which has empty slots, if all the pages have no empty slot, then return null
     * @throws DbException
     */
    public Page getEmptyPage()
            throws DbException {
        try {
            int tableId = this.getId();
            int numPages = this.numPages();
            for (int i = 0; i < numPages; i++) {
                HeapPageId pageId = new HeapPageId(tableId, i);
                Page page = GlobalManager.getBufferPool().getPage(pageId);
                if (((HeapPage)page).getNumEmptySlots() != 0){
                    return page;
                }
            }
        } catch (DbException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @see DbFile#deleteTuple(Tuple)
     */
    public ArrayList<Page> deleteTuple(Tuple t) {
        ArrayList<Page> affectedPages = new ArrayList<>();
        try {
            PageId pageId = t.getRecordId().getPageId();
            HeapPage page = (HeapPage) GlobalManager.getBufferPool().getPage(pageId);
            page.deleteTuple(t);
            affectedPages.add(page);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return affectedPages;
    }

    /**
     * @see DbFile#iterator()
     */
    public DbFileIterator iterator() {
        return new HeapFileIterator(this);
    }
}


