package edu.fit.cse5670;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class Employee extends Person {
    private int employeeID;
    private int salary;
    private boolean fullTime;

    public abstract void startEmployeeSession(Scanner scn);

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    protected Date parseDate(String dateString){
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try{
            date = df.parse(dateString);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return date;
    }
}
