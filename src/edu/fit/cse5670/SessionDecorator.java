package edu.fit.cse5670;

public class SessionDecorator extends Session {
    private Session session;

    public SessionDecorator(Session session) {
        this.session = session;
    }

    @Override
    public void printSessionDetails() {
        System.out.println("Session ID: " + getSessionID());
        System.out.println("Date: " + getDate().toString());
        System.out.println("Doctor ID: " + getDoctorID()); // TODO
        System.out.println("Nurse ID: " + getNurseID()); // TODO
        System.out.println("Vitals:");
        System.out.println(getVitals().toString());
        System.out.println("Assessment:");
        System.out.println(getAssessment().toString());

        session.printSessionDetails();
    }
}
