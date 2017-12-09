package edu.fit.cse5670;

import java.util.Scanner;

public abstract class Employee extends Person {
    private int employeeID;
    private int salary;
    private boolean fullTime;

    public abstract void startSession(Scanner scn);

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
}
