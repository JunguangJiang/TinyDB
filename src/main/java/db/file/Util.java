package db.file;

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
}
