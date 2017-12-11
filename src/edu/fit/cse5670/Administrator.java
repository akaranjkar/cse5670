package edu.fit.cse5670;

import java.util.Date;
import java.util.Scanner;

public class Administrator extends Employee {
    @Override
    public void startEmployeeSession(Scanner scn) {
        boolean choice = true;
        while(choice) {
            System.out.println("1) Add employee\n2) Remove employee\n3) Log out\n\nEnter choice: ");
            int option = scn.nextInt();
            scn.nextLine();
            switch (option) {
                case 1:
                    addEmployee(scn);
                    break;
                case 2:
                    removeEmployee(scn);
                    break;
                case 3:
                    choice = false;
                    break;
            }
        }
    }

    public void addEmployee(Scanner scn) {
        System.out.println("Select role:\n1) Doctor\n2) Nurse\n3) Receptionist\n4) Administrator");
        int role = scn.nextInt() % 4;
        scn.nextLine();
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
        System.out.println("Salary: ");
        int salary = scn.nextInt();
        System.out.println("Full time\n1)Yes\n2) No");
        boolean fullTime = scn.nextInt() == 1;
        scn.nextLine();
        String specialization = "";
        if(role == EmployeeFactory.DOCTOR){
            System.out.println("Specialization: ");
            specialization = scn.nextLine();
        }
        //add employee to DB
        Employee employee = EmployeeFactory.getInstance().createEmployee(firstName,lastName,address,date,phoneNumber,salary,fullTime,specialization,role);
        int employeeID = QueryBuilder.insertEmployee(employee);
        employee.setEmployeeID(employeeID);
    }

    public void removeEmployee(Scanner scn) {
        System.out.println("Enter employee ID: ");
        int employeeID = scn.nextInt();
        //delete employee from DB
        QueryBuilder.deleteEmployee(employeeID);
    }

}
