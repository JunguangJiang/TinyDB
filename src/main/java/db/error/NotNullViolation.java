package db.error;

public class NotNullViolation extends SQLError{
    /**
     *
     * @param attrName the name of the attribute that cannot be null
     */
    public NotNullViolation(String attrName) {
        super("FullColumnName " + attrName + " can not be null");
    }

}
