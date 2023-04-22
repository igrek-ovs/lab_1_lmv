package com.example.hospital;

import com.example.hospital.db.*;
import com.example.hospital.stuff.Admin;
import com.example.hospital.stuff.Doctor;
import com.example.hospital.stuff.Nurse;
import com.example.hospital.stuff.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        if (dbConnection != null && !dbConnection.isClosed()) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to connect to the database.");
        }

        return dbConnection;
    }

    public void signUpAdmin(String name, String surname, String password) {
        String insert = "INSERT INTO " + ConstantsAdmin.ADMIN_TABLE + "(" +
                ConstantsAdmin.ADMIN_NAME + "," + ConstantsAdmin.ADMIN_SURNAME + "," + ConstantsAdmin.ADMIN_PASSWORD + ")" +
                "VALUES(?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, name);
            prSt.setString(2, surname);
            prSt.setString(3, password);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUpDoctor(Doctor doctor) {
        String insert = "INSERT INTO " + ConstantsDoctor.DOCTOR_TABLE + "(" +
                ConstantsDoctor.DOCTOR_NAME + "," + ConstantsDoctor.DOCTOR_SURNAME + "," + ConstantsDoctor.DOCTOR_PASSWORD + "," +
                ConstantsDoctor.DOCTOR_SPECIALIZATION +
                "," + ConstantsDoctor.DOCTOR_DEPARTMENT + "," + ConstantsDoctor.DOCTOR_PHONE +
                "," + ConstantsDoctor.DOCTOR_POST + ")" +
                "VALUES(?,?,?,?,?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, doctor.getName());
            prSt.setString(2, doctor.getSurname());
            prSt.setString(3, doctor.getPassword());
            prSt.setString(4, doctor.getSpecialization());
            prSt.setString(5, doctor.getDepartment());
            prSt.setString(6, doctor.getPhoneNumber());
            prSt.setString(7, doctor.getPost());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUpNurse(Nurse nurse) {
        String insert = "INSERT INTO " + ConstantsNurse.NURSE_TABLE + "(" +
                ConstantsNurse.NURSE_NAME + "," + ConstantsNurse.NURSE_SURNAME + "," + ConstantsNurse.NURSE_PASSWORD + "," +
                ConstantsNurse.NURSE_DEPARTMENT + "," + ConstantsNurse.NURSE_WARD + "," + ConstantsNurse.NURSE_PHONE + "," + ConstantsNurse.NURSE_POST + ")" +
                "VALUES(?,?,?,?,?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, nurse.getName());
            prSt.setString(2, nurse.getSurname());
            prSt.setString(3, nurse.getPassword());
            prSt.setString(4, nurse.getDepartment());
            prSt.setString(5, nurse.getWard());
            prSt.setString(6, nurse.getPhoneNumber());
            prSt.setString(7, nurse.getPost());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUpPatient(Patient patient) {
        String insert = "INSERT INTO " + ConstantsPatient.PATIENT_TABLE + "(" +
                ConstantsPatient.PATIENT_NAME + "," + ConstantsPatient.PATIENT_SURNAME + "," + ConstantsPatient.PATIENT_DEPARTMENT + "," +
                ConstantsPatient.PATIENT_IS_WARD_VIP + "," + ConstantsPatient.PATIENT_MED_CARD + "," + ConstantsPatient.PATIENT_ADMISSION_DATE +
                "," + ConstantsPatient.PATIENT_WARD + "," + ConstantsPatient.PATIENT_DISCHARGE_DATE + "," + ConstantsPatient.PATIENT_STATUS + "," + ConstantsPatient.PATIENT_DOCTOR_SURNAME+ ")"+
                "VALUES(?,?,?,?,?,?,?,?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, patient.getName());
            prSt.setString(2, patient.getSurname());
            prSt.setString(3, patient.getDepartment());
            prSt.setString(4, patient.getIsWardVip());
            prSt.setString(5, patient.getMedCard());
            prSt.setString(6, patient.getAdmissionDate().toString());
            prSt.setString(7, patient.getWard());
            prSt.setString(8, patient.getDischargeDate().toString());
            prSt.setString(9, patient.getStatus());
            prSt.setString(10, patient.getDocSurname());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getAdmin(Admin admin) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsAdmin.ADMIN_TABLE + " WHERE " +
                ConstantsAdmin.ADMIN_NAME + "=? AND " + ConstantsAdmin.ADMIN_SURNAME + "=? AND " + ConstantsAdmin.ADMIN_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, admin.getName());
            prSt.setString(2, admin.getSurname());
            prSt.setString(3, admin.getPassword());
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ObservableList<Doctor> getDoctorsByDepartment(String department) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsDoctor.DOCTOR_TABLE + " WHERE " +
                ConstantsDoctor.DOCTOR_DEPARTMENT + "=?";
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, department);
            resultSet = prSt.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String password = resultSet.getString("password");
                String specialization = resultSet.getString("specialization");
                String post = resultSet.getString("post");
                String phone = resultSet.getString("phone_number");

                Doctor doctor = new Doctor(name, surname, password, specialization, department, phone, post);
                doctors.add(doctor);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public ObservableList<Nurse> getNursesByDepartment(String department) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsNurse.NURSE_TABLE + " WHERE " +
                ConstantsNurse.NURSE_DEPARTMENT + "=?";
        ObservableList<Nurse> nurses = FXCollections.observableArrayList();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, department);
            resultSet = prSt.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String password = resultSet.getString("password");
                String ward = resultSet.getString("ward");
                String phone = resultSet.getString("phone_number");
                String post = resultSet.getString("post");

                Nurse nurse = new Nurse(name, surname, password, department, ward, phone, post);
                nurses.add(nurse);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nurses;
    }

    public ObservableList<Patient> getPatientsByStatus(String status) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsPatient.PATIENT_TABLE + " WHERE " +
                ConstantsPatient.PATIENT_STATUS + "=?";
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, status);
            resultSet = prSt.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String department = resultSet.getString("department");
                String ward = resultSet.getString("ward");
                String isWardVip = resultSet.getString("isWardVip");
                String medCard = resultSet.getString("medCard");
                java.sql.Date admissionDate = resultSet.getDate("admissionDate");
                java.sql.Date dischargeDate = resultSet.getDate("dischargeDate");
                String docSurname = resultSet.getString("docSurname");

                Patient patient = new Patient(name, surname, department, ward, isWardVip, medCard, admissionDate, dischargeDate, docSurname, status);
                patients.add(patient);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public void addMedCard(String firstName, String lastName, String medCardText) throws SQLException, ClassNotFoundException {
        String query = "UPDATE patients SET medCard = CONCAT(IFNULL(medCard, ''), ?) WHERE name = ? AND surname = ?";
        PreparedStatement statement = getDbConnection().prepareStatement(query);
        statement.setString(1, medCardText);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.executeUpdate();
        statement.close();
    }


    public ResultSet getPatient(Patient patient) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsPatient.PATIENT_TABLE + " WHERE " +
                ConstantsPatient.PATIENT_NAME + "=? AND " + ConstantsPatient.PATIENT_SURNAME + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, patient.getName());
            prSt.setString(2, patient.getSurname());
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public ResultSet getDoctor(Doctor doctor) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsDoctor.DOCTOR_TABLE + " WHERE " +
                ConstantsDoctor.DOCTOR_NAME + "=? AND " + ConstantsDoctor.DOCTOR_SURNAME + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, doctor.getName());
            prSt.setString(2, doctor.getSurname());
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getNurse(Nurse nurse) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsNurse.NURSE_TABLE + " WHERE " +
                ConstantsNurse.NURSE_NAME + "=? AND " + ConstantsNurse.NURSE_SURNAME + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, nurse.getName());
            prSt.setString(2, nurse.getSurname());
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public void putPatientInWard(String name, String surname, String department, String isWardVip, String ward, String status, String docSurname) throws SQLException, ClassNotFoundException {
        String query = "UPDATE patients SET department=?, isWardVip=?, ward=?, status=?, docSurname=? WHERE name=? AND surname=?";
        PreparedStatement statement = getDbConnection().prepareStatement(query);
        statement.setString(1, department);
        statement.setString(2, isWardVip);
        statement.setString(3, ward);
        statement.setString(4, status);
        statement.setString(5, docSurname);
        statement.setString(6, name);
        statement.setString(7, surname);
        statement.executeUpdate();
        statement.close();
    }


    public ObservableList<Patient> getPatientsByDocSurname(String docSurname) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsPatient.PATIENT_TABLE + " WHERE " +
                ConstantsPatient.PATIENT_DOCTOR_SURNAME + "=?";
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, docSurname);
            resultSet = prSt.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String department = resultSet.getString("department");
                String ward = resultSet.getString("ward");
                String isWardVip = resultSet.getString("isWardVip");
                String medCard = resultSet.getString("medCard");
                java.sql.Date admissionDate = resultSet.getDate("admissionDate");
                java.sql.Date dischargeDate = resultSet.getDate("dischargeDate");
                String status = resultSet.getString("status");

                Patient patient = new Patient(name, surname, department, ward, isWardVip, medCard, admissionDate, dischargeDate, docSurname, status);
                patients.add(patient);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public String getPatientMedicalRecord(String name, String surname) throws SQLException, ClassNotFoundException {
        String query = "SELECT medCard FROM patients WHERE name = ? AND surname = ?";
        PreparedStatement statement = getDbConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, surname);
        ResultSet resultSet = statement.executeQuery();
        String medicalRecord = null;
        if (resultSet.next()) {
            medicalRecord = resultSet.getString("medCard");
        }
        statement.close();
        return medicalRecord;
    }


    public void updateMedCard(String firstName, String lastName, String medCardText) throws SQLException, ClassNotFoundException {
        String query = "UPDATE patients SET medCard = ? WHERE name = ? AND surname = ?";
        PreparedStatement statement = getDbConnection().prepareStatement(query);
        statement.setString(1, medCardText);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.executeUpdate();
        statement.close();
    }

    public void dischargePatient(String name, String surname) throws SQLException, ClassNotFoundException {
        String query = "UPDATE patients SET dischargeDate = ?, docSurname = ?, status = ?, ward = ?, isWardVip = ?, department = ? WHERE name = ? AND surname = ?";
        PreparedStatement statement = getDbConnection().prepareStatement(query);
        statement.setDate(1, new Date(System.currentTimeMillis()));
        statement.setString(2, "");
        statement.setString(3, "");
        statement.setString(4, "");
        statement.setString(5, "");
        statement.setString(6, "");
        statement.setString(7, name);
        statement.setString(8, surname);
        statement.executeUpdate();
        statement.close();
    }


    public ObservableList<Patient> getPatientsByWardAndDep(String ward, String department) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsPatient.PATIENT_TABLE + " WHERE " +
                ConstantsPatient.PATIENT_WARD + "=? AND " + ConstantsPatient.PATIENT_DEPARTMENT + "=?";
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, ward);
            prSt.setString(2, department);
            resultSet = prSt.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String docSurname = resultSet.getString("docSurname");
                String isWardVip = resultSet.getString("isWardVip");
                String medCard = resultSet.getString("medCard");
                java.sql.Date admissionDate = resultSet.getDate("admissionDate");
                java.sql.Date dischargeDate = resultSet.getDate("dischargeDate");
                String status = resultSet.getString("status");

                Patient patient = new Patient(name, surname, department, ward, isWardVip, medCard, admissionDate, dischargeDate, docSurname, status);
                patients.add(patient);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public Integer getPatientsByDepartment(String department) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstantsPatient.PATIENT_TABLE + " WHERE " +
                ConstantsPatient.PATIENT_DEPARTMENT + "=?";
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, department);
            resultSet = prSt.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String status = resultSet.getString("status");
                String ward = resultSet.getString("ward");
                String isWardVip = resultSet.getString("isWardVip");
                String medCard = resultSet.getString("medCard");
                java.sql.Date admissionDate = resultSet.getDate("admissionDate");
                java.sql.Date dischargeDate = resultSet.getDate("dischargeDate");
                String docSurname = resultSet.getString("docSurname");

                Patient patient = new Patient(name, surname, department,ward,isWardVip,medCard,admissionDate,dischargeDate,docSurname,status);
                patients.add(patient);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return patients.size();
    }


    /*public ObservableList<XYChart.Series<String, Integer>> getAdmissionsChartData(LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException {
        List<Pair<LocalDate, Integer>> admissionsChartData = new ArrayList<>();

        Connection conn = getDbConnection();
        String query = "SELECT admissionDate, COUNT(*) FROM patients WHERE admissionDate BETWEEN ? AND ? GROUP BY admissionDate";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDate(1, Date.valueOf(startDate));
        stmt.setDate(2, Date.valueOf(endDate));

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Date admissionDate = rs.getDate("admissionDate");
            int count = rs.getInt(2);
            admissionsChartData.add(new Pair<>(admissionDate.toLocalDate(), count));
        }

        return admissionsChartData;
    }*/

    public List<XYChart.Series<String, Integer>> getAdmissionsChartData(LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException {
        List<XYChart.Series<String, Integer>> data = new ArrayList<>();

        String sql = "SELECT admissionDate, COUNT(*) FROM patients WHERE admissionDate BETWEEN ? AND ? GROUP BY admissionDate";

        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String date = resultSet.getDate(1).toString();
                    int count = resultSet.getInt(2);

                    XYChart.Data<String, Integer> chartData = new XYChart.Data<>(date, count);
                    XYChart.Series<String, Integer> series = new XYChart.Series<>();
                    series.setName("Поступлений");
                    series.getData().add(chartData);
                    data.add(series);
                }
            }
        }

        return data;
    }


}
