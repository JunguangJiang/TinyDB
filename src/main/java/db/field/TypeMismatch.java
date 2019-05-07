package db.field;

/**
 * Exception when the Type is mismatched.
 */
public class TypeMismatch extends Exception {
    private Type expectedType, actualType;
    private String name;

    /**
     * @param expectedType the expected type
     * @param actualType the actual type
     */
    public TypeMismatch(String name, Type expectedType, Type actualType) {
        this.name = name;
        this.expectedType = expectedType;
        this.actualType = actualType;
    }

    @Override
    public String toString() {
        return "Type mismatch: <" + name + "> expected " + expectedType + ", yet get " + actualType;
    }
}
