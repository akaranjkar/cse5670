package edu.fit.cse5670;
import java.util.Date;

public abstract class Person {
    private String firstName;
    private String lastName;
    private Date dob;
    private String address;
    private int age;
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
        long time = new Date().getTime() - dob.getTime();
        double ybtwn = time/ 3.156e+10;
        this.setAge((int) Math.floor(ybtwn));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
