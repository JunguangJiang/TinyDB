package db.file.heap;

import db.*;
import db.field.Field;
import db.file.BufferPool;
import db.file.Page;
import db.file.RecordId;
import db.file.Util;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.*;
import java.util.*;

/**
 * Each instance of HeapPage stores data for one page of HeapFiles and
 * implements the Page interface that is used by BufferPool.
 *
 * @see HeapFile
 * @see BufferPool
 *
 */
public class HeapPage implements Page {

    final HeapPageId pid;
    final TupleDesc td;
    final BitSet header;
    final Tuple tuples[];
    final int numSlots;
    boolean dirty;

    /**
     * Create a HeapPage from a set of bytes of data read from disk.
     * The format of a HeapPage is a set of header bytes indicating
     * the slots of the page that are in use, some number of tuple slots.
     *  Specifically, the number of tuples is equal to: <p>
     *          floor((BufferPool.getPageSize()*8) / (tuple size * 8 + 1))
     * <p> where tuple size is the size of tuples in this
     * database table.
     * The number of 8-bit header words is equal to:
     * <p>
     *      ceiling(no. tuple slots / 8)
     * <p>
     * @see BufferPool#getPageSize()
     */
    public HeapPage(HeapPageId id, byte[] data, TupleDesc tupleDesc) throws IOException {
        this.pid = id;
        this.td = tupleDesc;
        this.numSlots = getNumTuples();
        this.dirty = false;
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));

        byte[] header_buffer = new byte[getHeaderSize()];
        if(dis.read(header_buffer,0, header_buffer.length) < header_buffer.length) {
            throw new IOException("the bytes of header is less than required");
        }
        header = BitSet.valueOf(header_buffer);

        tuples = new Tuple[numSlots];
        try{
            // allocate and read the actual records of this page
            for (int i=0; i<tuples.length; i++)
                tuples[i] = readNextTuple(dis,i);
        }catch(NoSuchElementException e){
            e.printStackTrace();
        }
        dis.close();
    }

    /** Retrieve the number of tuples on this page.
     @return the number of tuples on this page
     */
    private int getNumTuples() {
        return (int)Math.floor(
                (BufferPool.getPageSize() * 8.0) / (this.td.getSize() * 8.0 + 1.0)
        );
    }

    /**
     * @return the number of bytes in the header of a page in a HeapFile with each
     *          tuple occupying tupleSize bytes
     */
    private int getHeaderSize() {
        return (int)Math.ceil(this.numSlots / 8.0);
    }

    /**
     * @return the PageId associated with this page.
     */
    public HeapPageId getId() {
        return this.pid;
    }

    /**
     * Suck up tuples from the source file.
     */
    private Tuple readNextTuple(DataInputStream dis, int slotId) throws NoSuchElementException {
        // if associated bit is not set, read forward to the next tuple, and
        // return null.
        if (!isSlotUsed(slotId)) {
            int size = td.getSize();
            for (int i=0; i<size; i++) {
                try {
                    dis.readByte();
                } catch (IOException e) {
                    throw new NoSuchElementException("error reading empty tuple");
                }
            }
            return null;
        }

        // read fields in the tuple
        Tuple t = Util.parseTuple(td, dis);
        RecordId rid = new RecordId(pid, slotId);
        t.setRecordId(rid);

        return t;
    }

    /**
     * Generates a byte array representing the contents of this page.
     * Used to serialize this page to disk.
     * <p>
     * The invariant here is that it should be possible to pass the byte
     * array generated by getPageData to the HeapPage constructor and
     * have it produce an identical HeapPage object.
     *
     * @see #HeapPage
     * @return A byte array correspond to the bytes of this page.
     */
    public byte[] getPageData() {
        int len = BufferPool.getPageSize();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(len);
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            // create the header of the page
            byte[] headBuffer = this.header.toByteArray();
            dos.write(headBuffer, 0, headBuffer.length);
            Util.writeBytes(dos, 0,getHeaderSize()-headBuffer.length);

            // create the tuples
            int tupleSize = td.getSize();
            for (int i=0; i<tuples.length; i++) {
                // empty slot
                if (!isSlotUsed(i)) {
                    Util.writeBytes(dos, 0, tupleSize);
                } else {// non-empty slot
                    tuples[i].serialize(dos);
                }
            }

            // padding
            int zerolen = BufferPool.getPageSize() - (headBuffer.length + td.getSize() * tuples.length); //- numSlots * td.getSize();
            Util.writeBytes(dos,0, zerolen);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    /**
     * Static method to generate a byte array corresponding to an empty
     * HeapPage.
     * Used to add new, empty pages to the file. Passing the results of
     * this method to the HeapPage constructor will create a HeapPage with
     * no valid tuples in it.
     *
     * @return The returned ByteArray.
     */
    public static byte[] createEmptyPageData() {
        int len = BufferPool.getPageSize();
        return new byte[len]; //all 0
    }

    /**
     * Delete the specified tuple from the page; the corresponding header bit should be updated to reflect
     *   that it is no longer stored on any page.
     * @throws DbException if this tuple is not on this page, or tuple slot is
     *         already empty.
     * @param t The tuple to delete
     */
    public void deleteTuple(Tuple t) throws DbException {
        RecordId recordId = t.getRecordId();
        int tupleNumber = recordId.getTupleNumber();

        if (recordId.getPageId() != pid) {
            throw new DbException("this tuple is not on this page");
        } else if (!isSlotUsed(tupleNumber)) {
            throw new DbException("tuple slot is already empty");
        } else {
            tuples[tupleNumber] = null;
            markSlotUsed(tupleNumber, false);
        }
    }

    /**
     * Adds the specified tuple to the page;  the tuple should be updated to reflect
     *  that it is now stored on this page.
     * @throws DbException if the page is full (no empty slots) or tupledesc
     *         is mismatch.
     * @param t The tuple to add.
     */
    public void insertTuple(Tuple t) throws DbException {
        if (this.getNumEmptySlots() == 0) {
            throw new DbException("page is full");
        }
        if (!t.getTupleDesc().equals(this.td)) {
            throw new DbException("tupledesc is mismatch");
        }
        int i = this.nextEmptySlotNum();
        tuples[i] = t;
        markSlotUsed(i, true);
        RecordId recordId = new RecordId(pid, i);
        t.setRecordId(recordId);
    }

    /**
     * get the number of next empty slot
     * @return
     * @throws DbException
     */
    public int nextEmptySlotNum() throws DbException {
        for (int i=0; i<this.numSlots; i++) {
            if (!isSlotUsed(i)){
                return i;
            }
        }
        throw new DbException("no nextEmptySlotNum available");
    }

    /**
     * Marks this page as dirty/not dirty and record that transaction
     * that did the dirtying
     */
    public void markDirty(boolean dirty) {
        this.dirty = dirty;
    }

    /**
     * Returns the tid of the transaction that last dirtied this page, or null if the page is not dirty
     */
    public boolean isDirty() {
        return this.dirty;
    }

    /**
     * Returns the number of empty slots on this page.
     */
    public int getNumEmptySlots() {
        return this.numSlots - this.header.cardinality();
    }


    /**
     * Returns true if associated slot on this page is filled.
     */
    public boolean isSlotUsed(int i) {
        if(i < 0 || i >= this.numSlots){
            return false;
        }
        return this.header.get(i);
    }

    /**
     * Abstraction to fill or clear a slot on this page.
     */
    private void markSlotUsed(int i, boolean value) {
        this.header.set(i, value);
    }

    /**
     * @return an iterator over all tuples on this page (calling remove on this iterator throws an UnsupportedOperationException)
     * (note that this iterator shouldn't return tuples in empty slots!)
     */
    public Iterator<Tuple> iterator() {
        ArrayList<Tuple> tupleArrayList = new ArrayList<>();
        for(int i=0; i<numSlots; i++){
            if(isSlotUsed(i)){
                tupleArrayList.add(tuples[i]);
            }
        }
        return tupleArrayList.iterator();
    }

}

