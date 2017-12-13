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


    static String JDBC_DRIVER , DB_URL , USER ,PASS;

    static {

        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in= new FileInputStream("resources\\PDMS.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        // JDBC driver name and database URL
        JDBC_DRIVER = props.getProperty("drivername");
        DB_URL = props.getProperty("db_url");

        // Database credentials
        USER = props.getProperty("user");
        PASS = props.getProperty("pass");
    }



    public static synchronized Connection getConnection() {

        try {


            if (connection == null) {
                Class.forName(JDBC_DRIVER);

                //Open a connection
                connection = DriverManager.getConnection(DB_URL, USER, PASS);

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
