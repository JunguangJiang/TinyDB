package db.file;

/**
 * Exception when violating the primary key constraint
 */
public class PKUniqueException extends Exception{
    private static final long serialVersionUID = 1L;

    public PKUniqueException(String s) {
        super(s);
    }
}
