package edu.fit.cse5670;
import java.util.Date;
import java.util.List;

public class Patient extends Person {
    Integer patientID;
    private List<Condition> conditions;

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
