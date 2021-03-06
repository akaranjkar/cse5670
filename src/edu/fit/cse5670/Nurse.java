package edu.fit.cse5670;

import java.util.Date;
import java.util.Scanner;

public class Nurse extends Employee {
    @Override
    public void startEmployeeSession(Scanner scn) {
        boolean choice = true;
        while(choice) {
            System.out.println("1) Start new session\n2) Print Patient Record\n3) Log out\n\nEnter choice: ");
            int option = scn.nextInt();
            scn.nextLine();
            switch (option) {
                case 1:
                    startSession(scn);
                    break;
                case 2:
                    printRecord(scn);
                    break;
                case 3:
                    choice = false;
                    break;
            }
        }
    }


    private void startSession(Scanner scn) {
        Condition condition = null;
        int conditionID;
        do {
            System.out.println("Enter Condition ID: ");
            conditionID = scn.nextInt();
            condition = QueryBuilder.getCondition(conditionID);
            if(condition == null){
                System.out.println("Condition " + conditionID + " does not exist");
            }
        }while(condition == null);
        Session session = new Session(new Date(), getEmployeeID());
        System.out.println("Enter height");
        Double height = scn.nextDouble();
        System.out.println("Enter weight");
        Double weight = scn.nextDouble();
        System.out.println("Enter temperature");
        Double temperature= scn.nextDouble();
        System.out.println("Enter BP (high)");
        int bpHigh = scn.nextInt();
        System.out.println("Enter BP (low)");
        int bpLow = scn.nextInt();
        System.out.println("Enter pulse rate");
        int pulseRate = scn.nextInt();
        Vitals vitals = new Vitals(height,weight,temperature,bpHigh,bpLow,pulseRate);
        session.setVitals(vitals);

        int sessionID = QueryBuilder.insertSession(session, conditionID);
        session.setSessionID(sessionID);
        condition.addSession(session);
        System.out.println("Created Session ID: "+sessionID);
        scn.nextLine();
    }
}
