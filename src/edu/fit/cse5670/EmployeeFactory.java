package edu.fit.cse5670;

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

    public Employee getEmployee(String name, String password){
        //TODO get employee info from DB
        int tempType = 0;
        switch(tempType){
            case DOCTOR:
                return new Doctor();
            case NURSE:
                return new Nurse();
            case RECEPTIONIST:
                return new Receptionist();
            case ADMINISTRATOR:
                return new Administrator();
        }
        return null;
    }
}
