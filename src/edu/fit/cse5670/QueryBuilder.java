package edu.fit.cse5670;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;

public class QueryBuilder {
    public static Patient getPatient(int patientID){
        Patient patient = null;

        try {
            StringBuilder query = new StringBuilder("select * from patient where patientID="+patientID);
            ResultSet rs= DBManager.getQuery(query);
            rs.last();
            String firstName= rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            Date date = rs.getTimestamp("dob");
            HCPolicy hcPolicy = getPolicy(rs.getInt("policyID"));
            patient = new Patient(firstName,lastName,address,date,phone,hcPolicy);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return patient;
    }

    public static Doctor getDoctor(int empID){
        Doctor employee = null;

        try {
            StringBuilder query = new StringBuilder("select * from employee where employeeID="+empID);
            ResultSet rs= DBManager.getQuery(query);
            rs.last();
            String firstName= rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            Date date = rs.getTimestamp("dob");
            String specialization = rs.getString("specialization");
            employee = new Doctor();//firstName,lastName,address,date,phone
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employee;
    }
    private static HCPolicy getPolicy(int policyID){
        HCPolicy policy = null;
        try {
            StringBuilder query = new StringBuilder("select * from policy where policy="+policyID);
            ResultSet rs= DBManager.getQuery(query);
            rs.last();
            String provider = rs.getString("provider");
            Date expiry = rs.getTimestamp("enddate");

            policy = new HCPolicy(policyID,provider,expiry);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return policy;

    }

    public static List<Condition> getConditions(int patientID, boolean all){
        List<Condition> conditions = new ArrayList<Condition>();
        try {
            StringBuilder query = new StringBuilder("select * from condition_ where patientID="+patientID);
            if(all==false){
                query.append(" and closed = false");
            }
            ResultSet rs= DBManager.getQuery(query);
            Condition condition ;
            while (rs.next()){
                String maindiagnosis= rs.getString("maindiagnosis");
                boolean closed = rs.getBoolean("closed");
                int conditionID = rs.getInt("conditionID");
                List<Session> sessions = getSessions(conditionID);
                condition = new Condition();
                condition.setConditionID(conditionID);
                condition.setMainDiagnosis(maindiagnosis);
                condition.setSession(sessions);
                condition.setClosed(rs.getBoolean("closed"));
                conditions.add(condition);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conditions;
    }

    public static List<Session> getSessions(int conditionID){
        List<Session> sessions = new ArrayList<Session>();
        try {
            StringBuilder query = new StringBuilder("select * from sessions where conditionID="+conditionID);
            ResultSet rs= DBManager.getQuery(query);
            Session session = null;
            Vitals vitals = null;
            Assessment assessment = null;

            while (rs.next()){
                int maindiagnosis= rs.getInt("nurseID");
                Date visitDate = rs.getTimestamp("visitdate");
                session = new Session( visitDate,maindiagnosis);
                double height = rs.getDouble("height");
                double weight = rs.getDouble("weight");
                double temperature = rs.getDouble("bodytemp");
                int bloodPressureHigh = rs.getInt("bphigh");
                int bloodPressureLow =  rs.getInt("bplow");
                int pulseRate = rs.getInt("pulse");
                vitals = new Vitals(height,weight,temperature,bloodPressureHigh,bloodPressureLow,pulseRate);
                session.setVitals(vitals);
                session.setDoctorID(rs.getInt("doctorID"));
                String symptoms = rs.getString("symptoms");
                String diagnosis = rs.getString("diagnosis");
                String recommendations = rs.getString("recommendation");
                assessment = new Assessment(symptoms,diagnosis,recommendations);
                session.setAssessment(assessment);
                sessions.add(session);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return sessions;
    }
}
