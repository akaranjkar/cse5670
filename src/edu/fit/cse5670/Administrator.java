package edu.fit.cse5670;

import java.util.Date;
import java.util.Scanner;

public class Administrator extends Employee {
    @Override
    public void startEmployeeSession(Scanner scn) {
        System.out.println("Name: ");
        String firstName = scn.nextLine();
        System.out.println("Last Name: ");
        String lastName = scn.nextLine();
        System.out.println("Address: ");
        String address = scn.nextLine();
        System.out.println("Date of Birth: ");
        Date date = parseDate(scn.nextLine());
        System.out.println("Phone Number: ");
        String phoneNumber = scn.nextLine();
    }
}
