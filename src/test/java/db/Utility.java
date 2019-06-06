package db;

import db.field.Type;

import db.error.PrimaryKeyViolation;
import db.query.pipe.OpIterator;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Helper methods used for testing and implementing random features. */
public class Utility {
    /**
     * @return a Type array of length len populated with Type.INT_TYPE
     */
    public static Type[] getTypes(int len) {
        Type[] types = new Type[len];
        for (int i = 0; i < len; ++i)
            types[i] = Type.INT_TYPE;
        return types;
    }

    /**
     * @return a String array of length len populated with the (possibly null) strings in val,
     * and an appended increasing integer at the end (val1, val2, etc.).
     */
    public static String[] getStrings(int len, String val) {
        String[] strings = new String[len];
        for (int i = 0; i < len; ++i)
            strings[i] = val + i;
        return strings;
    }

    public static TDItem[] getTDItems(int len, String val) {
        TDItem[] items = new TDItem[len];
        for (int i=0; i<len; i++) {
            items[i] = new TDItem(Type.INT_TYPE, val + i, true);
        }
        return items;
    }

    /**
     * @return a TupleDesc with n fields of type Type.INT_TYPE, each named
     * name + n (name1, name2, etc.).
     */
    public static TupleDesc getTupleDesc(int n, String name) {
        return new TupleDesc(getTDItems(n, name), name+"0");
    }


    public static OpIterator getOpIterator(ArrayList<Tuple> tuples) {
        OpIterator opIterator = new OpIterator() {
            private Iterator<Tuple> iterator;
            @Override
            public void open() throws DbException, PrimaryKeyViolation {
                iterator = tuples.iterator();
            }

            @Override
            public boolean hasNext() throws DbException, PrimaryKeyViolation {
                return iterator.hasNext();
            }

            @Override
            public Tuple next() throws DbException, NoSuchElementException, PrimaryKeyViolation {
                return iterator.next();
            }

            @Override
            public void rewind() throws DbException {
                iterator = tuples.iterator();
            }

            @Override
            public TupleDesc getTupleDesc() {
                return tuples.get(0).getTupleDesc();
            }

            @Override
            public void close() {
            }

            @Override
            public long count() {
                return -1;
            }
        };
        return opIterator;
    }
}
