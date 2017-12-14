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
        boolean choice = true;
        while(choice) {
            System.out.println("1) Add assessment to session\n2) Update main diagnosis\n3) Print Patient Record\n4) Log out\n\nEnter choice: ");
            int option = scn.nextInt();
            scn.nextLine();
            switch (option) {
                case 1:
                    editSession(scn);
                    break;
                case 2:
                    updateDiagnosis(scn);
                    break;
                case 3:
                    printRecord(scn);
                    break;
                case 4:
                    choice = false;
                    break;
            }
        }
    }


    private void updateDiagnosis(Scanner scn) {
        int sessionID, conditionID;
        do {
            System.out.println("Enter session ID:");
            sessionID = scn.nextInt();
            scn.nextLine();
            conditionID = QueryBuilder.getConditionIDFromSessionID(sessionID);
            if(conditionID == -1){
                System.out.println("Session " + sessionID + " does not exist");
            }
        }while(conditionID >=0);

        Condition condition = QueryBuilder.getCondition(conditionID);

        System.out.println("Enter main diagnosis");
        String mainDiagnosis = scn.nextLine();
        condition.setMainDiagnosis(mainDiagnosis);
        QueryBuilder.updateCondition(condition);
    }

    private void editSession(Scanner scn) {
        Session session;
        do {
            System.out.println("Enter session ID:");
            int sessionID = scn.nextInt();
            scn.nextLine();
            session = QueryBuilder.getSession(sessionID);
            if(session == null){
                System.out.println("Session " + sessionID + " does not exist");
            }
        }while(session == null);
        System.out.println("Enter symptoms:");
        String symptoms = scn.nextLine();
        System.out.println("Enter diagnosis:");
        String diagnosis = scn.nextLine();
        System.out.println("Enter recommendations:");
        String recommendations = scn.nextLine();
        Assessment assessment = new Assessment(symptoms,diagnosis,recommendations);
        session.setAssessment(assessment);
        session.setDoctorID(getEmployeeID());
        QueryBuilder.updateSession(session);
    }
}

