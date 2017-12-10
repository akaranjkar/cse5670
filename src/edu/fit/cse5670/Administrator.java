package edu.fit.cse5670;

import java.util.Date;
import java.util.Scanner;

public class Administrator extends Employee {
    @Override
    public void startEmployeeSession(Scanner scn) {
        System.out.println("1) Add employee\n2) Remove employee\n");
        int option = scn.nextInt();
        switch (option){
            case 1:
                addEmployee(scn);
                break;
            case 2:
                removeEmployee(scn);
        }
    }

    public void addEmployee(Scanner scn) {
        System.out.println("First Name: ");
        String firstName = scn.nextLine();
        System.out.println("Last Name: ");
        String lastName = scn.nextLine();
        System.out.println("Address: ");
        String address = scn.nextLine();
        System.out.println("Date of Birth: ");
        Date date = parseDate(scn.nextLine());
        System.out.println("Phone Number: ");
        String phoneNumber = scn.nextLine();
        System.out.println("Role: ");
        String role = scn.nextLine();
        //TODO add employee to DB
    }

    public void removeEmployee(Scanner scn) {
        System.out.println("Enter employee ID: ");
        int employeeID = scn.nextInt();
        //TODO delete employee from DB
    }

}
