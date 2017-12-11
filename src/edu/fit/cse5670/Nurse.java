package edu.fit.cse5670;

import java.util.Date;
import java.util.Scanner;

public class Nurse extends Employee {
    @Override
    public void startEmployeeSession(Scanner scn) {
        System.out.println("1) Start new session\n2) Print Patient Record\n");
        int option = scn.nextInt();
        scn.nextLine();
        switch (option){
            case 1:
                startSession(scn);
                break;
            case 2:
                printRecord(scn);
        }
    }


    private void startSession(Scanner scn) {
        System.out.println("Enter Condition ID: ");
        int conditionID = scn.nextInt();
        Condition condition = QueryBuilder.getCondition(conditionID);
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
