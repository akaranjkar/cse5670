package edu.fit.cse5670;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
        Patient patient = QueryBuilder.getPatient(patientID);

        StringBuilder sb = new StringBuilder();
        sb.append("PatientName: ").append(patient.getFirstName()).append(" ").append(patient.getLastName()).append("\n");
        sb.append("Patient ID: ").append(patient.patientID).append("\n");
        sb.append("Patient address").append(patient.getAddress()).append("\n\n");
        sb.append("Conditions:\n");
        Iterator<Condition> conditionIterator = patient.getConditions().iterator();
        while(conditionIterator.hasNext()){
            Condition condition = conditionIterator.next();
            sb.append("  Condition ID: ").append(condition.getConditionID()).append("\n");
            sb.append("  Main diagnosis: ").append(condition.getMainDiagnosis()).append("\n\n");
            sb.append("  Sessions:\n");
            Iterator<Session> sessionIterator = condition.getSessions().iterator();
            while(sessionIterator.hasNext()){
                Session session = sessionIterator.next();
                sb.append("    Session ID: ").append(session.getSessionID()).append("\n");
                Doctor doctor = (Doctor) QueryBuilder.getEmployee(session.getDoctorID());
                sb.append("    Doctor in charge: ").append(doctor.getFirstName()).append(" ").append(doctor.getLastName()).append("\n");
                Nurse nurse = (Nurse) QueryBuilder.getEmployee(session.getNurseID());
                sb.append("    Nurse in charge: ").append(nurse.getFirstName()).append(" ").append(nurse.getLastName()).append("\n");

            }
        }
        System.out.println(sb.toString());
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
        //save patient in database
        int patientID = QueryBuilder.insertPatient(patient);
        patient.setPatientID(patientID);
    }

}
