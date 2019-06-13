package db.file;

import db.DbException;
import db.Main;
import db.Setting;
import db.file.BTree.BTreePageId;
import db.file.heap.HeapPageId;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TupleBuffer can store the middle result of pipe opIterator
 * when the buffer is full, it will write tuples to disk automatically
 * Different from TupleBuffer, TupleBufferBTree will also persist the tuple recordID
 *
 */
public class TupleBufferBTree {
    private int max_buffer_num; // max number of tuples in the memory
    private long tuple_num; // total number of tuples(including memory and disk)
    private int load_pos;
    private long read_pos;
    private File file;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ArrayList<Tuple> tuples; // tuples in the memory
    private Iterator<Tuple> iterator;
    private TupleDesc tupleDesc;
    private boolean flushed; // whether has flushed to the disk
    static public int DEFAULT_MAX_BYTES = 1024*1024*4;//max buffer bytes in the the momory 4MB

    /**
     *
     * @param max_bytes max bytes of memory usage
     * @param file the file where the tuples should be stored when the buffer is full
     * @param tupleDesc
     * @throws DbException
     */
    public TupleBufferBTree(long max_bytes, File file, TupleDesc tupleDesc) throws DbException{
        this.flushed = false;
        this.max_buffer_num = (int)Math.ceil((double)max_bytes / tupleDesc.getSize());
        this.file = file;
        this.tupleDesc = tupleDesc;
        try {
            this.dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        } catch (FileNotFoundException e){
            throw new DbException(e.getMessage());
        }
        tuples = new ArrayList<>();
        tuple_num = 0;
        load_pos = 0;
        read_pos = 0;
    }

    /**
     *
     * @return total number of tuples(including memory and disk)
     */
    public long getTupleNumber() {
        return tuple_num;
    }

    /**
     * add a new Tuple to the buffer, if the buffer is full, then write it to the disk
     * @param tuple
     * @throws DbException
     */
    public void add(Tuple tuple) throws DbException{
        tuples.add(tuple);
        tuple_num++;
        if (tuples.size() >= max_buffer_num) {
            flush();
        }
    }

    /**
     * After the adding is finished, should call this method
     * @throws DbException
     */
    public void finisheAdding() throws DbException{
        try {
            if (flushed) {
                flush();
            }
            dos.close();
            rewind();
        } catch (IOException e){
            throw new DbException(e.getMessage());
        }

    }

    /**
     *
     * @throws DbException
     */
    public void rewind() throws DbException{
        try {
            load_pos = 0;
            read_pos = 0;
            if (flushed) {
                dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                load();
            }
            iterator = tuples.iterator();
        } catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }

    /**
     * get the next tuple
     * @return null if there are no more tuples
     */
    public Tuple next() {
        read_pos++;
        if (read_pos >= tuple_num) {
            read_pos = tuple_num;
        }
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            if (flushed) {
                load();
                iterator = tuples.iterator();
                if (iterator.hasNext()) {
                    return iterator.next();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public boolean hasNext() {
        return this.read_pos < tuple_num;
    }

    /**
     * Remove the TupleBuffer from disk
     */
    public void close() {
		try{
            if(dis != null)
                dis.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        file.delete();
    }

    public long getTupleNum() {
        return tuple_num;
    }

    /**
     * flush all the tuples in the buffer to the disk
     * @throws DbException
     */
    private void flush() throws DbException{
        try {
            System.out.println("flush "+tuples.size());
            flushed = true;
            for (Tuple tuple: tuples) {
                tuple.serialize(dos);
                dos.writeInt(tuple.getRecordId().getPageId().getTableId());
            }

            dos.flush();
            tuples.clear();
        } catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Load tuples from disk to buffer
     */
    private void load() {
        tuples.clear();
        while (load_pos < tuple_num && tuples.size() <= max_buffer_num) {
            load_pos++;
            Tuple tuple = Util.parseTuple(tupleDesc, dis);
            try {
                int tableid = dis.readInt();
                PageId tmpPageId;
                if (Setting.isBTree) {
                    tmpPageId = new BTreePageId(tableid, 0, 0);
                } else {
                    tmpPageId = new HeapPageId(tableid, 0);
                }
                tuple.setRecordId(new RecordId(tmpPageId, 0));
                tuples.add(tuple);
            }
            catch (IOException e){
                e.printStackTrace();
                throw new NoSuchElementException("parsing error!");
            }
        }
    }
}