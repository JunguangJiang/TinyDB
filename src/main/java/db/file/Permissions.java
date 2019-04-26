package db.file;

/**
 * Read Write Permissions
 */
public enum Permissions {
    READ_ONLY(){
        @Override
        public String toString() {
            return "READ_ONLY";
        }
    },READ_WRITE(){
        @Override
        public String toString() {
            return "READ_WRITE";
        }
    }
}
