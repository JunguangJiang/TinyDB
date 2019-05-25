package db.error;

/**
 * Exception when violating the primary key constraint
 */
public class PrimaryKeyViolation extends SQLError{
    /**
     *
     * @param primaryKeyName the name of the primary key
     * @param duplicateValue the duplicated value
     */
    public PrimaryKeyViolation(String primaryKeyName, Object duplicateValue) {
        super("Duplicate value '" + duplicateValue +
                "' for key "+ primaryKeyName);
    }
}
