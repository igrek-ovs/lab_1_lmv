package com.example.hospital.stuff;

import java.sql.Date;
import java.time.LocalDate;

public class Patient {
    private String name;
    private String surname;
    private String department;
    private String ward;
    private String isWardVip;
    private String medCard;
    private java.sql.Date admissionDate;
    private java.sql.Date dischargeDate;
    private String docSurname;
    private String status;

    public Patient(String name, String surname, String department, String ward, String isWardVip, Date admissionDate) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.ward = ward;
        this.isWardVip = isWardVip;
        this.admissionDate = admissionDate;
    }

    public Patient(String name, String surname, java.sql.Date admissionDate, String status) {
        this.name = name;
        this.surname = surname;
        this.admissionDate = admissionDate;
        this.status = status;
    }

    public Patient(String name, String surname, String department, String ward, String isWardVip, String medCard, java.sql.Date admissionDate, java.sql.Date dischargeDate, String docSurname, String status) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.ward = ward;
        this.isWardVip = isWardVip;
        this.medCard = medCard;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.docSurname = docSurname;
        this.status = status;
    }

    public Patient() {

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

    public String getIsWardVip() {
        return isWardVip;
    }

    public void setIsWardVip(String isWardVip) {
        this.isWardVip = isWardVip;
    }

    public String getMedCard() {
        return medCard;
    }

    public void setMedCard(String medCard) {
        this.medCard = medCard;
    }

    public java.sql.Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(java.sql.Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public java.sql.Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(java.sql.Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getDocSurname() {
        return docSurname;
    }

    public void setDocSurname(String docSurname) {
        this.docSurname = docSurname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
