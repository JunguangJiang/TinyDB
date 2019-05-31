package db;

import db.file.BufferPool;
import sun.util.resources.is.CalendarData_is;

import java.util.concurrent.atomic.AtomicReference;

/**
 * GlobalManager is a class that initializes several static variables used by the
 * database system (the catalog, the current database, the buffer pool, and the log files, in
 * particular.)
 * <p>
 * Provides a set of methods that can be used to access these variables from
 * anywhere.
 *
 * @Threadsafe
 */
public class GlobalManager {
    private static AtomicReference<GlobalManager> _instance = new AtomicReference<>(new GlobalManager());
    private final Catalog _catalog;
    private final BufferPool _bufferpool;
    private final boolean isBTree;

    private GlobalManager() {
        _catalog = new Catalog();
        _bufferpool = new BufferPool(BufferPool.DEFAULT_PAGES);
        isBTree = false;
    }

    /** Return the buffer pool of the static GlobalManager instance */
    public static BufferPool getBufferPool() {
        return _instance.get()._bufferpool;
    }

    /** Return the catalog of the static GlobalManager instance */
    public static Catalog getCatalog() {
        return _instance.get()._catalog;
    }

    /** Return the current database */
    public static Database getDatabase() {
        return getCatalog().getCurrentDatabase();
    }

    public static boolean isBTree() {return _instance.get().isBTree;}
}
