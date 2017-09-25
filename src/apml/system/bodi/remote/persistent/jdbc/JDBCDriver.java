package apml.system.bodi.remote.persistent.jdbc;

//written quickly for mysql & java

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDriver {
    public JDBCDriver(String username, String password, String hostname, String dbname, Integer port) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + dbname, username, password);
        } catch (Exception e) {
            //
        }
    }

    public JDBCDriver(String username, String password, String dbname, Integer port) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
        } catch (Exception e) {
            //
        }
    }

    public JDBCDriver(String username, String password, String dbname) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, username, password);
        } catch (Exception e) {
            //
        }
    }
}
