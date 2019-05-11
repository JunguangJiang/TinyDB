package db.file;

import db.*;
import db.tuple.Tuple;
import db.file.heap.HeapFile;

import java.io.*;
import java.util.*;

/**
 * BufferPool manages the reading and writing of pages into memory from
 * disk. Access methods call into it to retrieve pages, and it fetches
 * pages from the appropriate location.
 *
 * @Threadsafe, all fields are final
 */
public class BufferPool {
    /** Bytes per page, including header. */
    private static final int DEFAULT_PAGE_SIZE = 4096;

    private static int pageSize = DEFAULT_PAGE_SIZE;

    /** Default number of pages passed to the constructor. This is used by
     other classes. BufferPool should use the numPages argument to the
     constructor instead. */
    public static final int DEFAULT_PAGES = 50;

    private int numPages;
    private HashMap<PageId, Page> pageHashMap;
    private HashMap<PageId, Integer> recentlyUsed;

    /**
     * Creates a BufferPool that caches up to numPages pages.
     *
     * @param numPages maximum number of pages in this buffer pool.
     */
    public BufferPool(int numPages) {
        this.numPages = numPages;
        this.pageHashMap = new HashMap<>();
        this.recentlyUsed = new HashMap<>();
    }

    public static int getPageSize() {
        return pageSize;
    }

    /**
     * Retrieve the specified page with the associated permissions.
     *
     * The retrieved page should be looked up in the buffer pool.  If it
     * is present, it should be returned.  If it is not present, it should
     * be added to the buffer pool and returned.  If there is insufficient
     * space in the buffer pool, a page should be evicted and the new page
     * should be added in its place.
     *
     * @param pid the ID of the requested page
     */
    public Page getPage(PageId pid)
            throws DbException {
        Page page;
        if(pageHashMap.containsKey(pid)){
            page = pageHashMap.get(pid);
        }else{
            DbFile file = GlobalManager.getDatabase().getDbFile(pid.getTableId());
            page = file.readPage(pid);
            if (pageHashMap.size() >= this.numPages){
                evictPage();
            }
            pageHashMap.put(pid, page);
        }
        updateRecentlyUsed();
        recentlyUsed.put(pid, 0);
        return page;
    }

    /**
     * Add a tuple to the specified table.
     *
     * Marks any pages that were dirtied by the operation as dirty by calling
     * their markDirty bit, and adds versions of any pages that have
     * been dirtied to the cache (replacing any existing versions of those pages) so
     * that future requests see up-to-date pages.
     *
     * @param tableId the table to add the tuple to
     * @param t the tuple to add
     */
    public void insertTuple(int tableId, Tuple t)
            throws IOException, PrimaryKeyViolation {
        DbFile dbFile = GlobalManager.getDatabase().getDbFile(tableId);
        ArrayList<Page> affectedPages = dbFile.insertTuple(t);
        for (Page page : affectedPages) {
            page.markDirty(true);
            pageHashMap.put(page.getId(), page);
        }
    }

    /**
     * Remove the specified tuple from the buffer pool.
     * Will acquire a write lock on the page the tuple is removed from and any
     * other pages that are updated. May block if the lock(s) cannot be acquired.
     *
     * Marks any pages that were dirtied by the operation as dirty by calling
     * their markDirty bit, and adds versions of any pages that have
     * been dirtied to the cache (replacing any existing versions of those pages) so
     * that future requests see up-to-date pages.
     *
     * @param t the tuple to delete
     */
    public void deleteTuple(Tuple t) {
        int tableId = t.getRecordId().getPageId().getTableId();
        DbFile dbFile = GlobalManager.getDatabase().getDbFile(tableId);
        ArrayList<Page> pages = ((HeapFile)dbFile).deleteTuple(t);
        for (Page page : pages) {
            page.markDirty(true);
        }
    }

    /**
     * Flush all dirty pages to disk.
     * NB: Be careful using this routine -- it writes dirty data to disk so will
     *     break file if running in NO STEAL mode.
     */
    public synchronized void flushAllPages() throws IOException {
        for (PageId pageId : this.pageHashMap.keySet()) {
            this.flushPage(pageId);
        }
    }

    /** Remove the specific page id from the buffer pool.
     Needed by the recovery manager to ensure that the
     buffer pool doesn't keep a rolled back page in its
     cache.

     Also used by B+ tree files to ensure that deleted pages
     are removed from the cache so they can be reused safely
     */
    public synchronized void discardPage(PageId pid) {
        // TODO
        // not necessary for lab1
    }

    /**
     * Flushes a certain page to disk
     * @param pid an ID indicating the page to flush
     */
    private synchronized  void flushPage(PageId pid) throws IOException {
        Page page = this.pageHashMap.get(pid);
        int tableId = pid.getTableId();
        DbFile file = GlobalManager.getDatabase().getDbFile(tableId);
        file.writePage(page);
        page.markDirty(false);
    }

    /**
     * Discards a page from the buffer pool.
     * Flushes the page to disk to ensure dirty pages are updated on disk.
     */
    private synchronized  void evictPage() throws DbException {
        PageId evictedPageId = null;
        int counter = -1;
        for (PageId pageId : recentlyUsed.keySet()) {
            int value = recentlyUsed.get(pageId);
            if (value > counter) {
                counter = value;
                evictedPageId = pageId;
            }
        }
        try {
            flushPage(evictedPageId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pageHashMap.remove(evictedPageId);
        recentlyUsed.remove(evictedPageId);
    }

    /**
     * Update the time of all the pages staying in the buffer pool
     */
    public void updateRecentlyUsed() {
        if (!recentlyUsed.isEmpty()) {
            for (PageId key : recentlyUsed.keySet()) {
                int value = recentlyUsed.get(key);
                value++;
                recentlyUsed.put(key, value);
            }
        }
    }
}
