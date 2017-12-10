package edu.fit.cse5670;

import java.sql.*;


public class DBManager {

    public static void main(String[] args) {
        StringBuilder query = new StringBuilder("SELECT * FROM patient ");
        ResultSet rs= getQuery(query);
    }

    public static ResultSet getQuery(StringBuilder sql) {
        ResultSet rs =null;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            System.out.println("Creating statement...");
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql.toString());
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null){
                    conn.close();
                    System.out.println("Closing connection");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return rs;
    }


    public static void updateQuery(StringBuilder sql) {
        Connection conn = null;
        PreparedStatement stmt= null;
        try {
            conn = ConnectionFactory.getConnection();
            System.out.println("Creating statement...");
            stmt = conn.prepareStatement(sql.toString());
            stmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null){
                    conn.close();
                    System.out.println("Closing connection");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

}
