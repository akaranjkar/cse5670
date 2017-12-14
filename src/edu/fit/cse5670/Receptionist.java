package edu.fit.cse5670;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Receptionist extends Employee {
    @Override
    public void startEmployeeSession(Scanner scn) {

        boolean choice = true;
        while(choice) {
            System.out.print("1) Add new Patient\n2) Retrieve Patient Data\n3) Print Patient Record\n4) Log out\n\nEnter choice: ");
            int option = scn.nextInt();
            scn.nextLine();
            switch (option) {
                case 1:
                    addNewPatient(scn);
                    break;
                case 2:
                    RetrievePatientData(scn);
                    break;
                case 3:
                    printRecord(scn);
                    break;
                case 4:
                    choice = false;
            }
        }
    }


    private void RetrievePatientData(Scanner scn) {
        Patient patient = null;
        int patientID = 0;
        do {
            System.out.println("Enter patient ID: ");
            patientID = scn.nextInt();
            scn.nextLine();
            patient = QueryBuilder.getPatient(patientID);
            if(patient == null){
                System.out.println("Patient not found");
            }
        }while(patient == null);
        System.out.println("Start consultation for a new condition: Y/N ");
        String choice = scn.nextLine();
        Condition condition;
        if(choice.equals("Y")){
            condition = new Condition();
            //add condition to DB and update condition ID
            int conditionID = QueryBuilder.insertCondition(condition, patientID);
            condition.setConditionID(conditionID);
            //add condition to patient
            patient.addCondition(condition);
            System.out.println("ConditionID: " + conditionID);
        }else{
            List<Condition> openConditions = QueryBuilder.getConditions(patientID, false);
            if(openConditions == null || openConditions.isEmpty()){
                System.out.println("No ongoing conditions for this patient");
                return;
            }
            Iterator<Condition> iterator = openConditions.iterator();
            int i = 1;
            while(iterator.hasNext()){
                Condition cond = iterator.next();
                StringBuilder sb = new StringBuilder().append(i).append(") ID: ").append(cond.getConditionID()).append(" ").append(cond.getMainDiagnosis());
                System.out.println(sb.toString());
                i++;
            }
            int index = scn.nextInt();
            condition = openConditions.get(index - 1);
            System.out.println("ConditionID:" + condition.getConditionID());
        }


    }

    private void addNewPatient(Scanner scn){
        System.out.print("First Name: ");
        String firstName = scn.nextLine();
        System.out.print("Last Name: ");
        String lastName = scn.nextLine();
        System.out.print("Address: ");
        String address = scn.nextLine();
        System.out.print("Date of Birth: ");
        Date date = parseDate(scn.nextLine(), scn);
        System.out.print("Phone Number: ");
        String phoneNumber = scn.nextLine();
        System.out.println("\nPolicy Information");
        HCPolicy policy = null;
        do {
            System.out.print("PolicyID: ");
            int policyID = scn.nextInt();
            scn.nextLine();

            policy = QueryBuilder.getPolicy(policyID);
        }while(policy == null);
        Patient patient = new Patient(firstName, lastName, address, date, phoneNumber, policy);
        //save patient in database
        int patientID = QueryBuilder.insertPatient(patient);
        patient.setPatientID(patientID);
        System.out.println("Created Patiend ID: " + patientID+ "\n");
    }

}
