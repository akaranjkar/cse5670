package edu.fit.cse5670;

import javax.print.Doc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmployeeFactory {
    private static EmployeeFactory ourInstance = new EmployeeFactory();

    public static final int DOCTOR = 1;
    public static final int NURSE = 2;
    public static final int RECEPTIONIST = 3;
    public static final int ADMINISTRATOR = 0;

    public static EmployeeFactory getInstance() {
        return ourInstance;
    }

    private EmployeeFactory() {
    }

    public Employee createEmployee(String firstName, String lastName, String address, Date dob, String phone, int salary, boolean fullTime, String specialization, int role) {
        switch (role) {
            case DOCTOR:
                Doctor doctor = new Doctor();
                setEmployeeAttr(doctor, firstName, lastName, address, dob, phone, salary, fullTime, specialization);
                return doctor;
            case NURSE:
                Nurse nurse = new Nurse();
                setEmployeeAttr(nurse, firstName, lastName, address, dob, phone, salary, fullTime, specialization);
                return nurse;
            case RECEPTIONIST:
                Receptionist receptionist = new Receptionist();
                setEmployeeAttr(receptionist, firstName, lastName, address, dob, phone, salary, fullTime, specialization);
                return receptionist;
            case ADMINISTRATOR:
                Administrator admin = new Administrator();
                setEmployeeAttr(admin, firstName, lastName, address, dob, phone, salary, fullTime, specialization);
                return admin;
        }
        return null;
    }

    public Employee authenticateEmployee(String username, String password) {
        //TODO get employee info from DB
        Connection conn =null;
        int tempType = 0;
        try {
            ResultSet rs = authenticate(conn,username, password);
            rs.last();
            tempType = rs.getInt("role");
            switch (tempType) {
                case DOCTOR:
                    Doctor doctor = new Doctor();
                    setEmployeeAttr(doctor, rs);
                    doctor.setSpecialization(rs.getString("specialization"));
                    return doctor;
                case NURSE:
                    Nurse nurse = new Nurse();
                    setEmployeeAttr(nurse, rs);
                    return nurse;
                case RECEPTIONIST:
                    Receptionist receptionist = new Receptionist();
                    setEmployeeAttr(receptionist, rs);
                    return receptionist;
                case ADMINISTRATOR:
                    Administrator admin = new Administrator();
                    setEmployeeAttr(admin, rs);
                    return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            closeConnection(conn);
        }//end try

        return null;
    }

    private void setEmployeeAttr(Employee employee, ResultSet rs) throws SQLException {
        employee.setEmployeeID(rs.getInt("employeeID"));
        employee.setDob(rs.getTimestamp("dob"));
        employee.setAge(rs.getInt("age"));
        employee.setLastName(rs.getString("lastName"));
        employee.setFirstName(rs.getString("firstName"));
        employee.setAddress(rs.getString("address"));
        employee.setPhone(rs.getString("phone"));
        employee.setSalary(rs.getInt("salary"));
    }

    private void setEmployeeAttr(Employee employee, String firstName, String lastName, String address, Date dob, String phone, int salary, boolean fullTime, String specialization) {
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAddress(address);
        employee.setDob(dob);
        employee.setPhone(phone);
        employee.setSalary(salary);
        employee.setFullTime(fullTime);
        if (employee instanceof Doctor) {
            ((Doctor) employee).setSpecialization(specialization);
        }
    }

    private ResultSet authenticate(Connection conn, String username, String password) throws SQLException {
        int empID = 0;
//        Connection conn = null;
        StringBuilder query = new StringBuilder("select * from login where username=\'" + username + "\'");
        ResultSet rs = DBManager.getQuery(conn,query);
        rs.last();
        if (username.equals(rs.getString("username"))  && password.equals(rs.getString("passwd"))) {
            empID = rs.getInt("employeeID");
            query = new StringBuilder("select * from employee where employeeid=\'" + empID + "\'");
            rs = DBManager.getQuery(conn,query);
        }
        return rs;

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
