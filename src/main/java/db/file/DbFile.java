package db.file;

import db.DbException;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The interface for database files on disk. Each table is represented by a
 * single DbFile. DbFiles can fetch pages and iterate through tuples. Each
 * file has a unique id used to store metadata about the table in the Database.
 * DbFiles are generally accessed through the buffer pool, rather than directly
 * by operators.
 */
public interface DbFile {
    /**
     * Read the specified page from disk.
     *
     * @throws IllegalArgumentException if the page does not exist in this file.
     */
    public Page readPage(PageId id);

    /**
     * Push the specified page to disk.
     *
     * @param p The page to write.  page.getId().pageno() specifies the offset into the file where the page should be written.
     * @throws IOException if the write fails
     *
     */
    public void writePage(Page p) throws IOException;

    /**
     * Inserts the specified tuple to the file.
     *
     * @param t The tuple to add.  This tuple should be updated to reflect that
     *          it is now stored in this file.
     * @return An ArrayList contain the pages that were modified
     * @throws DbException if the tuple cannot be added
     * @throws IOException if the needed file can't be read/written
     * @throws PrimaryKeyViolation if the tuple violates the primary key constraint
     */
    public ArrayList<Page> insertTuple(Tuple t) throws IOException, PrimaryKeyViolation;

    /**
     * Removes the specified tuple from the file
     *
     * @param t The tuple to delete.  This tuple should be updated to reflect that
     *          it is no longer stored on any page.
     * @return An ArrayList contain the pages that were modified
     *   of the file
     */
    public ArrayList<Page> deleteTuple(Tuple t) throws IOException;

    /**
     * @return an iterator over all the tuples stored in this DbFile.
     */
    public DbFileIterator iterator();

    /**
     * @return a unique ID used to identify this DbFile in the Database.
     */
    public int getId();

    /**
     * @return the TupleDesc of the table stored in this DbFile.
     */
    public TupleDesc getTupleDesc();
}
