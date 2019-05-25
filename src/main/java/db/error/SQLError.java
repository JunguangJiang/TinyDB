package db.error;

/**
 * All the sql error found while running the database
 */
public class SQLError extends Exception {
    public SQLError(String s){
        super(s);
    }
}
