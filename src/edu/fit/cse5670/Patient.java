package edu.fit.cse5670;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends Person {
    Integer patientID;
    HCPolicy policy;
    private List<Condition> conditions;

    public Patient(String firstName, String lastName, String address, Date dob, String phone, HCPolicy policy){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setDob(dob);
        this.setPhone(phone);
        this.setPolicy(policy);
        conditions = new ArrayList<Condition>();

    }

    public void addCondition(Condition condition){
        conditions.add(condition);
    }

    public HCPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(HCPolicy policy) {
        this.policy = policy;
    }


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
