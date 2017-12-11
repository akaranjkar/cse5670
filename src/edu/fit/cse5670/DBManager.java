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
            closeConnection(conn,stmt);
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
            closeConnection(conn,stmt);
        }//end try
    }

    public static int updateCondition(StringBuilder sql, Condition condition, int patientID) {
        Connection conn = null;
        PreparedStatement stmt= null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql.toString());
            stmt.setInt(1,patientID);
            stmt.setString(2,condition.getMainDiagnosis());
            stmt.setBoolean(3,condition.isClosed());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    condition.setConditionID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println("Error adding condition: "+se.getErrorCode());
            return -1;
        } finally {
            //finally block used to close resources
            closeConnection(conn,stmt);
        }//end try
        return condition.getConditionID();
    }

    public static int updateSession(StringBuilder sql, Session session, int conditionID) {
        Connection conn = null;
        PreparedStatement stmt= null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql.toString());
            stmt.setInt(1,conditionID);
            stmt.setDate(2,new java.sql.Date(session.getDate().getTime()));
            stmt.setString(3,session.getAssessment().getDiagnosis());
            stmt.setInt(4,session.getDoctorID());
            stmt.setInt(5,session.getNurseID());
            stmt.setString(6,session.getAssessment().getSymptoms());
            stmt.setString(7,session.getAssessment().getDiagnosis());
            stmt.setString(8,session.getAssessment().getRecommendations());
            stmt.setDouble(9,session.getVitals().getHeight());
            stmt.setDouble(10,session.getVitals().getWeight());
            stmt.setDouble(11,session.getVitals().getTemperature());
            stmt.setDouble(12,session.getVitals().getBloodPressureHigh());
            stmt.setDouble(13,session.getVitals().getBloodPressureLow());
            stmt.setDouble(14,session.getVitals().getPulseRate());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    session.setSessionID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println("Error adding condition: "+se.getErrorCode());
            return -1;
        } finally {
            //finally block used to close resources
            closeConnection(conn,stmt);
        }//end try
        return session.getSessionID();
    }

    private static void closeConnection(Connection conn,Statement stmt) {
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
    }

    public static int insertPatient(StringBuilder query, Patient patient) {
        Connection conn = null;
        PreparedStatement stmt= null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(query.toString());
            stmt.setDate(1,new java.sql.Date(patient.getDob().getTime()));
            stmt.setInt(2,patient.getAge());
            stmt.setString(3,patient.getLastName());
            stmt.setString(4,patient.getFirstName());
            stmt.setString(5,patient.getAddress());
            stmt.setString(6,patient.getPhone());
            stmt.setInt(7,patient.getPolicy().getPolicyID());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setPatientID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println("Error adding condition: "+se.getErrorCode());
            return -1;
        } finally {
            //finally block used to close resources
            closeConnection(conn,stmt);
        }//end try
        return patient.getPatientID();

    }
}
