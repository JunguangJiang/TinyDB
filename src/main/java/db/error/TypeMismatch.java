package db.error;

import db.field.Type;

/**
 * Exception when the Type is mismatched.
 */
public class TypeMismatch extends SQLError {
    /**
     * @param expectedType the expected type
     * @param actualType the actual type
     */
    public TypeMismatch(String name, Type expectedType, Type actualType) {
        super("Type mismatch: <" + name + "> expected " + expectedType + ", yet get " + actualType);
    }
}
