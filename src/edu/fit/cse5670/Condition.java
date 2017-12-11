package edu.fit.cse5670;

import java.util.ArrayList;
import java.util.List;

public class Condition {
    private Integer conditionID;

    private String mainDiagnosis;
    private List<Session> sessions;
    private boolean closed;

    public Condition() {
        this.mainDiagnosis = null;
        this.sessions = new ArrayList<Session>();
        this.closed = false;
    }

    public void addSession(Session session){
        sessions.add(session);
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

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> session) {
        this.sessions = session;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    //    public void startSession() {
//        if (session == null) {
//            session = new FirstSession();
//        } else {
//            session = new SessionDecorator(session);
//        }
//    }
}
