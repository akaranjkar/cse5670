package edu.fit.cse5670;

import java.util.Scanner;

public class Doctor extends Employee{

    String specialization;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public void startEmployeeSession(Scanner scn) {
        System.out.println("1) Add assessment to session\n2) Update main diagnosis\n3) Print Patient Record\n");
        int option = scn.nextInt();
        switch (option){
            case 1:
                editSesion(scn);
                break;
            case 2:
                updateDiagnosis(scn);
            case 3:
                printRecord(scn);
        }
    }

    private void printRecord(Scanner scn) {
        System.out.println("Enter patient ID: ");
        int patientID = scn.nextInt();
        //TODO get patient data from db
        //TODO print data
    }

    private void updateDiagnosis(Scanner scn) {
        System.out.println("Enter session ID:");
        int sessionID = scn.nextInt();
        //TODO fetch session from DB
        //TODO fetch condition based in conditionID and update main diagnosis of condition
        System.out.println("Enter main diagnosis");
        String mainDiagnosis = scn.nextLine();
        //condition.setMainDiagnosis(mainDiagnosis);
        //TODO write condition back to DB
    }

    private void editSesion(Scanner scn) {
        System.out.println("Enter session ID:");
        int sessionID = scn.nextInt();
        //TODO Fetch session from DB
        System.out.println("Enter symptoms:");
        String symptoms = scn.nextLine();
        System.out.println("Enter diagnosis:");
        String diagnosis = scn.nextLine();
        System.out.println("Enter recommendations:");
        String recommendations = scn.nextLine();
        Assessment assessment = new Assessment(symptoms,diagnosis,recommendations);
        //TODO update assessment in session
        //TODO add doctor ID to session and write to DB
    }
}

