package edu.fit.cse5670;

import java.util.Date;

public class Session {
    private Integer sessionID;
    private Date date;
    private Vitals vitals;
    private Assessment assessment;
    private Integer doctorID;
    private Integer nurseID;

    public Session(Date date, Integer nurseID) {
        this.date = date;
        this.nurseID = nurseID;
    }

    public Integer getSessionID() {
        return sessionID;
    }

    public void setSessionID(Integer sessionID) {
        this.sessionID = sessionID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vitals getVitals() {
        return vitals;
    }

    public void setVitals(Vitals vitals) {
        this.vitals = vitals;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public Integer getNurseID() {
        return nurseID;
    }

    public void setNurseID(Integer nurseID) {
        this.nurseID = nurseID;
    }

    public void printSessionDetails() {
        System.out.println("Session ID: " + getSessionID());
        System.out.println("Date: " + getDate().toString());
        System.out.println("Doctor ID: " + getDoctorID()); // TODO
        System.out.println("Nurse ID: " + getNurseID()); // TODO
        System.out.println("Vitals:");
        System.out.println(getVitals().toString());
        System.out.println("Assessment:");
        System.out.println(getAssessment().toString());
    }
}
