package db;

import db.field.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Class representing a type in TinyDB.
 * Types are static objects defined by this class; hence, the Type
 * constructor is private.
 */
public enum Type implements Serializable {
    INT_TYPE() {
        @Override
        public int getBytes() {
            return Integer.BYTES;
        }

        @Override
        public Field parse(DataInputStream dis, int maxLen) throws ParseException {
            try {
                return new IntField(dis.readInt());
            }  catch (IOException e) {
                throw new ParseException("couldn't parse", 0);
            }
        }

    }, LONG_TYPE() {
        @Override
        public int getBytes() {
            return Long.BYTES;
        }

        @Override
        public Field parse(DataInputStream dis, int maxLen) throws ParseException {
            try {
                return new LongField(dis.readLong());
            }  catch (IOException e) {
                throw new ParseException("couldn't parse", 0);
            }
        }
    }, FLOAT_TYPE() {
        @Override
        public int getBytes() {
            return Float.BYTES;
        }

        @Override
        public Field parse(DataInputStream dis, int maxLen) throws ParseException {
            try {
                return new FloatField(dis.readFloat());
            }  catch (IOException e) {
                throw new ParseException("couldn't parse", 0);
            }
        }
    }, DOUBLE_TYPE() {
        @Override
        public int getBytes() {
            return Double.BYTES;
        }

        @Override
        public Field parse(DataInputStream dis, int maxLen) throws ParseException {
            try {
                return new DoubleField(dis.readDouble());
            }  catch (IOException e) {
                throw new ParseException("couldn't parse", 0);
            }
        }
    }, STRING_TYPE() {
        /**
         * Ignore the bytes required to store the content
         */
        @Override
        public int getBytes() {
            return Integer.BYTES;
        }

        @Override
        public Field parse(DataInputStream dis, int maxLen) throws ParseException {
            try {
                int strLen = dis.readInt();
                byte bs[] = new byte[strLen];
                dis.read(bs);
                dis.skipBytes(maxLen-strLen);
                return new StringField(new String(bs), maxLen);
            } catch (IOException e) {
                throw new ParseException("couldn't parse", 0);
            }
        }
    };

    /**
     * @return the number of bytes required to store a field of this type.
     * For StringType, simply return 4.
     */
    public abstract int getBytes();

    /**
     * @return a Field object of the same type as this object that has contents
     *   read from the specified DataInputStream.
     * @param dis The input stream to read from
     * @throws ParseException if the data read from the input stream is not
     *   of the appropriate type.
     */
    public abstract Field parse(DataInputStream dis, int maxLen) throws ParseException;


    public static Type getType(String typeString) throws NoSuchElementException {
        if (typeString.equals("INT")) {
            return INT_TYPE;
        } else if (typeString.equals("LONG")) {
            return LONG_TYPE;
        } else if (typeString.equals("FLOAT")) {
            return FLOAT_TYPE;
        } else if (typeString.equals("DOUBLE")) {
            return DOUBLE_TYPE;
        } else if (typeString.equals("STRING")) {
            return STRING_TYPE;
        } else {
            throw new NotImplementedException();
        }
    }

}