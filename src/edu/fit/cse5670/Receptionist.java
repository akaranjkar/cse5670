package edu.fit.cse5670;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Receptionist extends Employee {
    @Override
    public void startEmployeeSession(Scanner scn) {
        System.out.println("1) Add new Patient\n2) Retrieve Patient Data\n3) Print Patient Record\n");
        int option = scn.nextInt();
        switch (option){
            case 1:
                addNewPatient(scn);
                break;
            case 2:
                RetrievePatientData(scn);
            case 3:
                printRecord(scn);

        }
    }

    private void printRecord(Scanner scn) {
        System.out.println("Enter patient ID: ");
        int patientID = scn.nextInt();
        StringBuilder sb = new StringBuilder("select * from condition_ where patientID = '").append(patientID).append("';");
        ResultSet rs = DBManager.getQuery(sb);
        try {
            while (rs.next()) {
                int id = rs.getInt("patientID");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //TODO get patient data from db
        //TODO print data
    }

    private void RetrievePatientData(Scanner scn) {
        System.out.println("Enter patient ID: ");
        int patientID = scn.nextInt();
        //TODO get patient data from db
        System.out.println("Start consultation for a new condition: Y/N ");
        String choice = scn.nextLine();
        if(choice.equals("Y")){
            Condition condition = new Condition();
            //TODO add condition to DB and update condition ID
            //TODO add condition to patient
        }else{
            //TODO retrieve open conditions and print the ID and the main diagnosis
            int index = scn.nextInt();

        }


    }

    private void addNewPatient(Scanner scn){
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
        System.out.println("Policy Information");
        System.out.println("PolicyID: ");
        int policyID = scn.nextInt();
        System.out.println("Policy Provider: ");
        String policyProvider = scn.nextLine();
        System.out.println("Expiration Date: ");
        Date policyExpirationDate = parseDate(scn.nextLine());

        HCPolicy policy = new HCPolicy(policyID, policyProvider, policyExpirationDate);
        Patient patient = new Patient(firstName, lastName, address, date, phoneNumber, policy);
    //TODO save patient in database
    }

}
