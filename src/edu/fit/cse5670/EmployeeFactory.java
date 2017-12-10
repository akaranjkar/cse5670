package edu.fit.cse5670;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Employee authenticateEmployee(String username, String password){
        //TODO get employee info from DB
        int tempType = 0;
        try {
            ResultSet rs = authenticate(username,password);
            rs.last();
            tempType = rs.getInt("role");
            switch(tempType){
                case DOCTOR:
                    Doctor doctor = new Doctor();
                    setEmployeeAttr(doctor,rs);
                    doctor.setSpecialization(rs.getString("specialization"));
                    return doctor;
                case NURSE:
                    Nurse nurse = new Nurse();
                    setEmployeeAttr(nurse,rs);
                    return nurse;
                case RECEPTIONIST:
                    Receptionist receptionist = new Receptionist();
                    setEmployeeAttr(receptionist,rs);
                    return receptionist;
                case ADMINISTRATOR:
                    Administrator admin= new Administrator();
                    setEmployeeAttr(admin,rs);
                    return admin;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

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



    private ResultSet authenticate(String username, String password) throws SQLException {
        int empID=0;
        StringBuilder query = new StringBuilder("select * from login where username=\'"+username+"\'");
        ResultSet rs= DBManager.getQuery(query);
        rs.last();
        if( rs.getString("username") ==username && rs.getString("password")==password){
            empID = rs.getInt("employeeID");
            query = new StringBuilder("select * from employee where employeeid=\'"+empID+"\'");
            rs= DBManager.getQuery(query);
        }
        return rs;

    }
}
