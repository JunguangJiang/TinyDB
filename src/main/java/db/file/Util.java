package db.file;

import db.error.NotNullViolation;
import db.tuple.TDItem;

import java.io.DataOutputStream;
import java.io.IOException;

public class Util {
    /**
     * write a byte for n times
     * @param dos
     * @param b
     * @param n
     * @throws IOException
     */
    public static void writeBytes(DataOutputStream dos, int b, int n) throws IOException {
        for (int i=0; i<n; i++) {
            dos.write((byte)b);
        }
    }

    /**
     * Check whether the not null constraint is satisfied
     * @param tdItem
     * @param value
     * @throws Exception if the constraint is not satisfied
     */
    public static void checkNotNullConstraint(TDItem tdItem, Object value) throws NotNullViolation {
        if (tdItem.notNull || tdItem.isPrimaryKey) {
            if (value == null) {
                throw new NotNullViolation(tdItem.fieldName);
            }
        }
    }
}
