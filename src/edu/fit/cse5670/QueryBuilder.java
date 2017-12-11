package edu.fit.cse5670;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;

public class QueryBuilder {

    public static Patient getPatient(int patientID) {
        Patient patient = null;
        Connection conn = null;

        try {
            StringBuilder query = new StringBuilder("select * from patient where patientID=" + patientID);
            ResultSet rs = DBManager.getQuery(conn, query);
            rs.last();
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            Date date = rs.getTimestamp("dob");
            HCPolicy hcPolicy = getPolicy(rs.getInt("policyID"));
            patient = new Patient(firstName, lastName, address, date, phone, hcPolicy);
            patient.setPatientID(patientID);
            patient.setConditions(getConditions(patientID,true));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }
        return patient;
    }

    public static int insertPatient(Patient patient) {
        Connection conn = null;
try {
    StringBuilder query;
    query = new StringBuilder("insert into patient(dob,age,lastname,firstname,address,phone,policyID) values \n" +
            "(?,?,?,?,?,?,?)");
    int patientID= DBManager.insertPatient(conn,query, patient);
    return patientID;
}finally {
    //finally block used to close resources
    closeConnection(conn);
}//end try


    }

    public static int insertEmployee(Employee employee) {
        Connection conn = null;
        StringBuilder query;
        query = new StringBuilder("insert into employee(role,dob,age,lastname,firstname,address,phone,salary,specialization) values (?,?,?,?,?,?,?,?,?)");
        int role = -1;
        String specialization = "";
        if(employee instanceof Doctor){
            role =EmployeeFactory.DOCTOR;
            specialization = ((Doctor) employee).getSpecialization();
        }else if(employee instanceof Nurse){
            role = EmployeeFactory.NURSE;
        }else if(employee instanceof Receptionist){
            role = EmployeeFactory.RECEPTIONIST;
        }else if(employee instanceof Administrator){
            role = EmployeeFactory.ADMINISTRATOR;
        }
        int employeeID= DBManager.insertEmployee(conn, query, employee,role,specialization);
        return employeeID;
    }

    public static Employee getEmployee(int empID) {
        Employee employee = null;
        Connection conn = null;

        try {
            StringBuilder query = new StringBuilder("select * from employee where employeeID=" + empID);
            ResultSet rs = DBManager.getQuery(conn,query);
            rs.last();
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            Date date = rs.getTimestamp("dob");
            int role = rs.getInt("role");
            String specialization = rs.getString("specialization");
            employee = EmployeeFactory.getInstance().createEmployee(firstName,lastName,address,date,phone, 0, true, specialization, role);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try
        return employee;
    }

    public static HCPolicy getPolicy(int policyID) {
        Connection conn = null;
        HCPolicy policy = null;
        try {
            StringBuilder query = new StringBuilder("select * from policy where policyID=" + policyID);
            ResultSet rs = DBManager.getQuery(conn, query);
            rs.last();
            String provider = rs.getString("provider");
            Date expiry = rs.getTimestamp("enddate");

            policy = new HCPolicy(policyID, provider, expiry);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try
        return policy;

    }

    public static List<Condition> getConditions(int patientID, boolean all) {
        Connection conn = null;
        List<Condition> conditions = new ArrayList<Condition>();
        try {
            StringBuilder query = new StringBuilder("select * from condition_ where patientID=" + patientID);
            if (all == false) {
                query.append(" and closed = false");
            }
            ResultSet rs = DBManager.getQuery(conn, query);
            Condition condition;
            while (rs.next()) {
                String maindiagnosis = rs.getString("maindiagnosis");
                boolean closed = rs.getBoolean("closed");
                int conditionID = rs.getInt("conditionID");
                List<Session> sessions = getSessions(conditionID);
                condition = new Condition();
                condition.setConditionID(conditionID);
                condition.setMainDiagnosis(maindiagnosis);
                condition.setSessions(sessions);
                condition.setClosed(rs.getBoolean("closed"));
                conditions.add(condition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try
        return conditions;
    }

    public static Condition getCondition(int conditionID) {
        Condition condition = null;
        Connection conn = null;
        try {
            StringBuilder query = new StringBuilder("select * from condition_ where conditionID=" + conditionID);
            ResultSet rs = DBManager.getQuery(conn, query);
            rs.last();
            String maindiagnosis = rs.getString("maindiagnosis");
            boolean closed = rs.getBoolean("closed");
            List<Session> sessions = getSessions(conditionID);
            condition = new Condition();
            condition.setConditionID(conditionID);
            condition.setMainDiagnosis(maindiagnosis);
            condition.setSessions(sessions);
            condition.setClosed(rs.getBoolean("closed"));

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try
        return condition;
    }
    public static int insertCondition(Condition condition,int patientID) {
        StringBuilder query;
        query = new StringBuilder("insert into condition_ (patientID,maindiagnosis,closed) values(?,?,?)");
        int conditionID= DBManager.updateCondition(query, condition,patientID);

        return conditionID;
    }

    public static int updateCondition(Condition condition) {
        Connection conn = null;
        int conditionID=0;
        try{
            StringBuilder query = new StringBuilder("update condition_ set patientID=?,maindiagnosis=?,closed=? where conditionID="+condition.getConditionID());

            int patientID = getPatientIDFromConditionID(conn,condition.getConditionID());
            conditionID= DBManager.updateCondition(query, condition,patientID);
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try


        return conditionID;
    }

    public static int insertSession(Session session, int conditionID) {
        Connection conn = null;
        int sessionID;
        try {
            StringBuilder query = new StringBuilder("insert into sessions(conditionID,visitdate,assessment,doctorID,nurseID,symptoms,diagnosis,recommendation,height,weight,bodytemp,bphigh,bplow,pulse)\n" +
                    "   values(?, ?,?, ?,?,?,?, ?,?,?,?,?,?,?)");

            sessionID= DBManager.updateSession(conn, query, session,conditionID);
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try


        return sessionID;
    }

    public static int updateSession(Session session) {
        Connection conn = null;
        int sessionID;
        try {
            StringBuilder query = new StringBuilder("update sessions set conditionID=?,visitdate=?,assessment=?,doctorID=?,nurseID=?,symptoms=?,diagnosis =?,recommendation=?,height=?,weight=?,bodytemp=?,bphigh=?,bplow=?,pulse=? where sessionID="+session.getSessionID());
            int conditionID = getConditionIDFromSessionID(session.getSessionID());
            sessionID= DBManager.updateSession(conn, query, session,conditionID);
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try


        return sessionID;
    }

    public static List<Session> getSessions(int conditionID) {
        Connection conn = null;
        List<Session> sessions = new ArrayList<Session>();
        try {
            StringBuilder query = new StringBuilder("select * from sessions where conditionID=" + conditionID);
            ResultSet rs = DBManager.getQuery(conn, query);

            while (rs.next()) {
                sessions.add(buildSession(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try
        return sessions;
    }

    public static Session getSession(int sessionID) {
        Connection conn = null;
        Session session = null;
        try {
            StringBuilder query = new StringBuilder("select * from sessions where sessionID=" + sessionID);
            ResultSet rs = DBManager.getQuery(conn, query);
            rs.last();
            session = buildSession(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try
        return session;
    }

    private static Session buildSession(ResultSet rs) throws SQLException {
        int maindiagnosis = rs.getInt("nurseID");
        Date visitDate = rs.getTimestamp("visitdate");
        Session session = new Session(visitDate, maindiagnosis);
        double height = rs.getDouble("height");
        double weight = rs.getDouble("weight");
        double temperature = rs.getDouble("bodytemp");
        int bloodPressureHigh = rs.getInt("bphigh");
        int bloodPressureLow = rs.getInt("bplow");
        int pulseRate = rs.getInt("pulse");
        int sessionID = rs.getInt("sessionID");
        Vitals vitals = new Vitals(height, weight, temperature, bloodPressureHigh, bloodPressureLow, pulseRate);
        session.setVitals(vitals);
        session.setDoctorID(rs.getInt("doctorID"));
        String symptoms = rs.getString("symptoms");
        String diagnosis = rs.getString("diagnosis");
        String recommendations = rs.getString("recommendation");
        Assessment assessment = new Assessment(symptoms, diagnosis, recommendations);
        session.setAssessment(assessment);
        session.setSessionID(sessionID);
        return session;
    }

    public static int getConditionIDFromSessionID(int sessionID) {
        Connection conn = null;
        StringBuilder query = new StringBuilder("select conditionID from sessions where sessionID=" + sessionID);
        ResultSet rs = DBManager.getQuery(conn, query);
        int conditionID = 0;
        try {
            rs.last();
            conditionID = rs.getInt("conditionID");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try
        return conditionID;

    }

    public static int getPatientIDFromConditionID(Connection conn,int conditionID) {
//        Connection conn = null;
        StringBuilder query = new StringBuilder("select patientID from condition_ where conditionID=" + conditionID);
        ResultSet rs = DBManager.getQuery(conn, query);
//        int conditionID = 0;
        int patientID=0;
        try {
            rs.last();
            patientID = rs.getInt("patientID");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try
        return patientID;

    }

    public static void deleteEmployee(int employeeID) {
        Connection conn = null;
        try {
            StringBuilder query = new StringBuilder("delete from employee where employeeID= " + employeeID);
            DBManager.updateQuery(conn, query);
        }
        finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try
    }

    private static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }//end finally try
    }
}
