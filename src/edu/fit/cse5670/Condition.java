package edu.fit.cse5670;

import java.util.List;

public class Condition {
    private Integer conditionID;

    private String mainDiagnosis;
    private List<Session> sessions;

    public Condition() {
        this.mainDiagnosis = null;
        this.sessions = null;
    }

    public Integer getConditionID() {
        return conditionID;
    }

    public void setConditionID(Integer conditionID) {
        this.conditionID = conditionID;
    }

    public String getMainDiagnosis() {
        return mainDiagnosis;
    }

    public void setMainDiagnosis(String mainDiagnosis) {
        this.mainDiagnosis = mainDiagnosis;
    }

    public List<Session> getSession() {
        return sessions;
    }

    public void setSession(List<Session> session) {
        this.sessions = session;
    }

//    public void startSession() {
//        if (session == null) {
//            session = new FirstSession();
//        } else {
//            session = new SessionDecorator(session);
//        }
//    }
}
