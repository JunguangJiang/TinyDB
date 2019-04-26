package db.field;

/**
 * Exception when the Type is mismatched.
 */
public class TypeMismatch extends Exception {
    private Type expectedType, actualType;

    /**
     * @param expectedType the expected type
     * @param actualType the actual type
     */
    public TypeMismatch(Type expectedType, Type actualType) {
        this.expectedType = expectedType;
        this.actualType = actualType;
    }

    @Override
    public String toString() {
        return "Type mismatch: expected " + expectedType + ", yet get " + actualType;
    }
}
