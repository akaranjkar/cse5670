package edu.fit.cse5670;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Employee extends Person {
    private int employeeID;
    private int salary;
    private boolean fullTime;

    public abstract void startEmployeeSession(Scanner scn);

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    protected Date parseDate(String dateString, Scanner scn) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        do {
            try {
                date = df.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter date in the form yyyy/MM/dd");
                System.out.print("Date of Birth: ");
                dateString = scn.nextLine();
            }
        }while(date == null);

        return date;
    }

    protected void printRecord(Scanner scn) {
        Patient patient = null;
        int patientID = 0;
        do {
            System.out.println("Enter patient ID: ");
            patientID = scn.nextInt();
            patient = QueryBuilder.getPatient(patientID);
            if(patient == null){
                System.out.println("Patient not found");
            }
        }while(patient == null);

        StringBuilder sb = new StringBuilder();
        sb.append("PatientName: ").append(patient.getFirstName()).append(" ").append(patient.getLastName()).append("\n");
        sb.append("Patient ID: ").append(patient.getPatientID()).append("\n");
        sb.append("Patient address: ").append(patient.getAddress()).append("\n\n");
        sb.append("Conditions:\n");
        Iterator<Condition> conditionIterator = patient.getConditions().iterator();
        while(conditionIterator.hasNext()){
            Condition condition = conditionIterator.next();
            sb.append("  Condition ID: ").append(condition.getConditionID()).append("\n");
            sb.append("  Main diagnosis: ").append(condition.getMainDiagnosis()).append("\n\n");
            sb.append("  Sessions:");
            Iterator<Session> sessionIterator = condition.getSessions().iterator();
            while(sessionIterator.hasNext()){
                Session session = sessionIterator.next();
                sb.append("\n    Session ID: ").append(session.getSessionID()).append("\n");
                Doctor doctor = (Doctor) QueryBuilder.getEmployee(session.getDoctorID());
                sb.append("    Doctor in charge: ").append(doctor.getFirstName()).append(" ").append(doctor.getLastName()).append("\n");
                Nurse nurse = (Nurse) QueryBuilder.getEmployee(session.getNurseID());
                sb.append("    Nurse in charge: ").append(nurse.getFirstName()).append(" ").append(nurse.getLastName()).append("\n");
                Vitals vitals = session.getVitals();
                sb.append("    Vitals:\n    Temperature: ").append(vitals.getTemperature()).append("\n");
                sb.append("    Weight: ").append(vitals.getWeight()).append("\n");
                sb.append("    Height: ").append(vitals.getHeight()).append("\n");
                sb.append("    Blood pressure: ").append(vitals.getBloodPressureHigh()).append("/").append(vitals.getBloodPressureLow()).append("\n");
                sb.append("    Pulse: ").append(vitals.getPulseRate()).append("\n");
                Assessment assessment = session.getAssessment();
                sb.append("    Assessment:\n    Symptoms: ").append(assessment.getSymptoms()).append("\n");
                sb.append("    Diagnosis: ").append(assessment.getDiagnosis()).append("\n");
                sb.append("    Recommendation: ").append(assessment.getRecommendations()).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
