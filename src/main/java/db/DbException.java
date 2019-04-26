package db;

import java.lang.Exception;

/** DbException is to check whether the code is correct*/
public class DbException extends Exception {
    private static final long serialVersionUID = 1L;

    public DbException(String s) {
        super(s);
    }
}
