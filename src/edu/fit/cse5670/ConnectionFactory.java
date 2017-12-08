package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static ConnectionFactory connectionFactory = null;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/clinicsys";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "group14";

    public static synchronized Connection getConnection() {

        Connection conn = null;
        try {


            if (connectionFactory == null) {
                connectionFactory = new ConnectionFactory();
                System.out.println("Constructing ExampleFactory");
                // TEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                // STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

            } else {
                System.out.println("ExampleFactory already exists");
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return conn;
    }
}
