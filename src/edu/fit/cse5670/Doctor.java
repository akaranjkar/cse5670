package edu.fit.cse5670;

import java.util.Scanner;

public class Doctor extends Employee{

    String specialization;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public void startSession(Scanner scn) {

    }
}

