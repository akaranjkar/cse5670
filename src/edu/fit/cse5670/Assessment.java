package edu.fit.cse5670;

public class Assessment {
    private String symptoms;
    private String diagnosis;
    private String recommendations;

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(symptoms).append("\n").append(diagnosis).append("\n").append(recommendations);
        return sb.toString();
    }
}
