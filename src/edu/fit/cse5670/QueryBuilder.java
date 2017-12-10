package edu.fit.cse5670;


import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;

public class QueryBuilder {
    public Patient getPatient(int patientID){
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

    public Doctor getDoctor(int empID){
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
    private HCPolicy getPolicy(int policyID){
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
}
