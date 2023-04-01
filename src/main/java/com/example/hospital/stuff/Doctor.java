package com.example.hospital.stuff;

public class Doctor {
    private String name;
    private String surname;
    private String password;
    private String specialization;
    private String department;
    private String phoneNumber;
    private String post;

    public Doctor(String name, String surname, String password, String specialization, String department, String phoneNumber, String post) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.specialization = specialization;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.post = post;
    }

    public Doctor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
