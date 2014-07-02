package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nikos
 */
@SuppressWarnings("ThrowableResultIgnored")
public class DbConnection {

    //================================
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sistima_vivliothikis_ergasia?zeroDateTimeBehavior=convertToNull&"
            + "autoReconnect=true&amp;useUnicode=true&characterEncoding=utf-8&characterSetResults=utf8&connectionCollation=utf8_general_ci";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "nikos";
    //================================
    private static DbConnection INSTANCE = null;
    private Connection conn = null;

    private DbConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            while ((ex = ex.getNextException()) != null) {
                System.err.println(ex.getSQLState() + "  -  " + ex.getErrorCode());
            }
        }
    }//ctor.

    public static DbConnection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DbConnection();
        }
        return INSTANCE;
    }//getInstance.

    public Connection getConnection() {
        return INSTANCE.conn;
    }

    public void closeConnection() {
        try {
            INSTANCE.conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}//DbConnection.
