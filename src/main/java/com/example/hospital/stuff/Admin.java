package com.example.hospital.stuff;

public class Admin {
    private String name;
    private String surname;
    private String password;

    public Admin(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public Admin() {}

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
}
