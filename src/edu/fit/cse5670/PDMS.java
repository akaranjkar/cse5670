package edu.fit.cse5670;

import java.util.Scanner;

public class PDMS {
    private Clinic clinic;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scn.nextLine();
        System.out.println("Password: ");
        String password = scn.nextLine();

        Employee employee = EmployeeFactory.getInstance().authenticateEmployee(username, password);
        employee.startEmployeeSession(scn);
    }
}
