package edu.fit.cse5670;

import java.util.List;

public class Condition {
    private Integer conditionID;
    private String mainDiagnosis;
    private Session session;

    public Condition() {
        session = null;
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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void startSession() {
        if (session == null) {
            session = new FirstSession();
        } else {
            session = new SessionDecorator(session);
        }
    }
}
