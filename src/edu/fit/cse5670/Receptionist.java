package edu.fit.cse5670;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Receptionist extends Employee {
    @Override
    public void startEmployeeSession(Scanner scn) {
        System.out.print("1) Add new Patient\n2) Retrieve Patient Data\n3) Print Patient Record\n\nEnter choice: ");
        int option = scn.nextInt();
        scn.nextLine();
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


    private void RetrievePatientData(Scanner scn) {
        System.out.println("Enter patient ID: ");
        int patientID = scn.nextInt();
        Patient patient = QueryBuilder.getPatient(patientID);
        System.out.println("Start consultation for a new condition: Y/N ");
        String choice = scn.nextLine();
        Condition condition;
        if(choice.equals("Y")){
            condition = new Condition();
            //add condition to DB and update condition ID
            int conditionID = QueryBuilder.insertCondition(condition, patientID);
            condition.setConditionID(conditionID);
            //add condition to patient
            patient.addContion(condition);
        }else{
            List<Condition> openConditions = QueryBuilder.getConditions(patientID, false);
            Iterator<Condition> iterator = openConditions.iterator();
            int i = 1;
            while(iterator.hasNext()){
                Condition cond = iterator.next();
                StringBuilder sb = new StringBuilder(i).append(") ID: ").append(cond.getConditionID()).append(" ").append(cond.getMainDiagnosis());
                System.out.println(sb.toString());
                i++;
            }
            int index = scn.nextInt();
            condition = openConditions.get(index - 1);
        }
        //TODO what to do with the condition???

    }

    private void addNewPatient(Scanner scn){
        System.out.print("First Name: ");
        String firstName = scn.nextLine();
        System.out.print("Last Name: ");
        String lastName = scn.nextLine();
        System.out.print("Address: ");
        String address = scn.nextLine();
        System.out.print("Date of Birth: ");
        Date date = parseDate(scn.nextLine());
        System.out.print("Phone Number: ");
        String phoneNumber = scn.nextLine();
        System.out.println("Policy Information");
        System.out.print("PolicyID: ");
        int policyID = scn.nextInt();
        scn.nextLine();

        HCPolicy policy = QueryBuilder.getPolicy(policyID);
        Patient patient = new Patient(firstName, lastName, address, date, phoneNumber, policy);
        //save patient in database
        int patientID = QueryBuilder.insertPatient(patient);
        patient.setPatientID(patientID);
    }

}
