package db.JDBC;

import java.sql.*;
import java.util.Properties;

public class JDBCDriver implements Driver {
    private JDBCDriver() {
    }

    public Connection connect(String url, Properties info) throws SQLException {
        return new JDBCConnection(url);
    }

    public boolean jdbcCompliant() {
        return true;
    }

    public boolean acceptsURL(String url) {
        return true;
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
        return null;
    }

    public int getMajorVersion() {
        return 0;
    }

    public int getMinorVersion() {
        return 0;
    }

    public java.util.logging.Logger getParentLogger() throws java.sql.SQLFeatureNotSupportedException {
        throw new java.sql.SQLFeatureNotSupportedException();
    }

    public static final JDBCDriver driverInstance = new JDBCDriver();

    static {
        try {
            DriverManager.registerDriver(driverInstance);
        } catch (Exception e) {
        }
    }
}
