package edu.fit.cse5670;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionFactory {
    public static Connection connection = null;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/clinicsys";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "group14";



//    public ConnectionFactory() throws FileNotFoundException {
//
//        Properties props = new Properties();
//        FileInputStream in = new FileInputStream("PDMS");
//        try {
//            props.load(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println();
//        // JDBC driver name and database URL
//        final String JDBC_DRIVER = props.getProperty("drivername");
//        final String DB_URL = props.getProperty("db_url");
//
//        // Database credentials
//        final String USER = props.getProperty("user");
//        final String PASS = props.getProperty("pass");
//    }



    public static synchronized Connection getConnection() {

        try {


            if (connection == null) {
                Class.forName(JDBC_DRIVER);

                //Open a connection
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(DB_URL, USER, PASS);

            } else {
                System.out.println("Connection already exists");
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return connection;
    }
}
