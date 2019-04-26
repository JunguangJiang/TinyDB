package db.file;

/**
 * Exception when violating the primary key constraint
 */
public class PrimaryKeyViolation extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * @param s the duplicate key/value information
     */
    public PrimaryKeyViolation(String s) {
        super(s);
    }
}
