package com.example.hospital.stuff;

public class Nurse {
    private String name;
    private String surname;
    private String password;
    private String department;
    private String ward;
    private String phoneNumber;
    private String post;


    public Nurse(String name, String surname, String password, String department, String ward, String phoneNumber, String post) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.department = department;
        this.ward = ward;
        this.phoneNumber = phoneNumber;
        this.post = post;
    }

    public Nurse() {

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
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
