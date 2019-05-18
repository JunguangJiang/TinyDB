package db.field;
import java.io.Serializable;

/** Constants used for return codes in Field.compare */
public enum Op implements Serializable {
    EQUALS, GREATER_THAN, LESS_THAN, LESS_THAN_OR_EQ, GREATER_THAN_OR_EQ, NOT_EQUALS;

    /**
     * Reverse an Op so that when we swap the operands, the result would stay the same.
     * @return an reversed Op
     */
    public static Op reverse(Op op) {
        switch (op) {
            case LESS_THAN:
                return GREATER_THAN;
            case LESS_THAN_OR_EQ:
                return GREATER_THAN_OR_EQ;
            case GREATER_THAN_OR_EQ:
                return LESS_THAN_OR_EQ;
            case GREATER_THAN:
                return LESS_THAN;
            default:
                return op;
        }
    }
}
