package edu.fit.cse5670;

public class Vitals {
    private Double height;
    private Double weight;
    private Double temperature;
    private Integer bloodPressureHigh;
    private Integer bloodPressureLow;
    private Integer pulseRate;

    public Vitals(Double height, Double weight, Double temperature, Integer bloodPressureHigh, Integer bloodPressureLow, Integer pulseRate) {
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.bloodPressureHigh = bloodPressureHigh;
        this.bloodPressureLow = bloodPressureLow;
        this.pulseRate = pulseRate;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public void setBloodPressureHigh(Integer bloodPressureHigh) {
        this.bloodPressureHigh = bloodPressureHigh;
    }

    public Integer getBloodPressureLow() {
        return bloodPressureLow;
    }

    public void setBloodPressureLow(Integer bloodPressureLow) {
        this.bloodPressureLow = bloodPressureLow;
    }

    public Integer getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(Integer pulseRate) {
        this.pulseRate = pulseRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(height).append(" ").append(weight).append(" ").append(temperature).append(" ").append(bloodPressureHigh)
                .append(" ").append(bloodPressureLow).append(" ").append(pulseRate);
        return sb.toString();
    }
}
