
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.ConnectionFactory;

public class DBManager {

    public static void main(String[] args) {
        getQuery();
    }
    public static void getQuery() {
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = ConnectionFactory.getConnection();
            System.out.println("Creating statement...");
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql;
            sql = "SELECT personID, firstName, lastName, city FROM person ";
            ResultSet rs = stmt.executeQuery(sql);

            // Move cursor to the last row.
            System.out.println("Moving cursor to the last...");
            rs.last();

            //STEP 5: Extract data from result set
            System.out.println("Displaying record...");
            //Retrieve by column name
            int id  = rs.getInt("personID");
            String first = rs.getString("firstName");
            String last = rs.getString("lastName");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);

            // Move cursor to the first row.
            System.out.println("Moving cursor to the first row...");
            rs.first();

            //STEP 6: Extract data from result set
            System.out.println("Displaying record...");
            //Retrieve by column name
            id  = rs.getInt("personID");
            first = rs.getString("firstName");
            last = rs.getString("lastName");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);
            // Move cursor to the first row.

            System.out.println("Moving cursor to the next row...");
            rs.next();

            //STEP 7: Extract data from result set
            System.out.println("Displaying record...");
            id  = rs.getInt("personID");
            first = rs.getString("firstName");
            last = rs.getString("lastName");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);

            //STEP 8: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }
}
