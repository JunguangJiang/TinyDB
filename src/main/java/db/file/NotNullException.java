package db.file;

/**
 * Exception when violating the not null constraint
 */
public class NotNullException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotNullException(String s) {
        super(s);
    }
}
