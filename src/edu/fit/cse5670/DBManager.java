package edu.fit.cse5670;

import java.sql.*;


public class DBManager {

    public static ResultSet getQuery(Connection conn,StringBuilder sql) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql.toString());
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return rs;
    }


    public static void updateQuery(Connection conn, StringBuilder sql) {
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql.toString());
            stmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public static int updateCondition(StringBuilder sql, Condition condition, int patientID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, patientID);
            stmt.setString(2, condition.getMainDiagnosis());
            stmt.setBoolean(3, condition.isClosed());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    condition.setConditionID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println("Error adding condition: " + se.getErrorCode());
            return -1;
        }
        return condition.getConditionID();
    }

    public static int updateSession(Connection conn, StringBuilder sql, Session session, int conditionID) {
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, conditionID);
            stmt.setDate(2, new java.sql.Date(session.getDate().getTime()));
            stmt.setString(3, session.getAssessment()==null?"":session.getAssessment().getDiagnosis());
            stmt.setInt(4, session.getDoctorID()==null?-1:session.getDoctorID());
            stmt.setInt(5, session.getNurseID());
            stmt.setString(6, session.getAssessment()==null?"":session.getAssessment().getSymptoms());
            stmt.setString(7, session.getAssessment()==null?"":session.getAssessment().getDiagnosis());
            stmt.setString(8, session.getAssessment()==null?"":session.getAssessment().getRecommendations());
            stmt.setDouble(9, session.getVitals().getHeight());
            stmt.setDouble(10, session.getVitals().getWeight());
            stmt.setDouble(11, session.getVitals().getTemperature());
            stmt.setDouble(12, session.getVitals().getBloodPressureHigh());
            stmt.setDouble(13, session.getVitals().getBloodPressureLow());
            stmt.setDouble(14, session.getVitals().getPulseRate());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    session.setSessionID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println("Error adding session: " + se.getErrorCode());
            return -1;
        }
        return session.getSessionID();
    }



    public static int insertPatient(Connection conn, StringBuilder query, Patient patient) {
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, new java.sql.Date(patient.getDob().getTime()));
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getLastName());
            stmt.setString(4, patient.getFirstName());
            stmt.setString(5, patient.getAddress());
            stmt.setString(6, patient.getPhone());
            stmt.setInt(7, patient.getPolicy().getPolicyID());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setPatientID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println("Error adding patient: " + se.getErrorCode());
            return -1;
        }
        return patient.getPatientID();

    }

    public static int insertEmployee(Connection conn, StringBuilder query, Employee employee, int role, String specialization) {
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, role);
            stmt.setDate(2, new java.sql.Date(employee.getDob().getTime()));
            stmt.setInt(3, employee.getAge());
            stmt.setString(4, employee.getLastName());
            stmt.setString(5, employee.getFirstName());
            stmt.setString(6, employee.getAddress());
            stmt.setString(7, employee.getPhone());
            stmt.setInt(8, employee.getSalary());
            stmt.setString(9, specialization);

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setEmployeeID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println("Error adding employee: " + se.getErrorCode());
            return -1;
        }
        return employee.getEmployeeID();
    }
}
