package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.singleton.GlobalVariables;
import com.example.hospital.stuff.Doctor;
import com.example.hospital.stuff.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerDiagnosticTransferWindow implements Initializable {

    @FXML
    private ComboBox<?> departmentBox;

    @FXML
    private TextField docNameField;

    @FXML
    private TextField docSurnameField;

    @FXML
    private ComboBox<?> isWardVipBox;

    @FXML
    private TextField patientNameField;

    @FXML
    private TextField patientSurnameField;

    @FXML
    private ComboBox<?> statusBox;

    @FXML
    private Button transferButton;

    @FXML
    private ComboBox<?> wardNumberBox;
    @FXML
    private Button helpButton;

    @FXML
    void onTransferButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String docName = docNameField.getText();
        String docSurname = docSurnameField.getText();

        String patName = patientNameField.getText();
        String patSurname = patientSurnameField.getText();



        if (checkPatient(patName, patSurname) == false) {
            patientNameField.clear();
            patientSurnameField.clear();
        } else if (checkDoctor(docName, docSurname) == false) {
            docNameField.clear();
            docSurnameField.clear();
        } else if (departmentBox.getValue()==null) {
            Shake shakeDep = new Shake(departmentBox);
            shakeDep.playAnimation();
        } else if (isWardVipBox.getValue()==null) {
            Shake shakeDep1 = new Shake(isWardVipBox);
            shakeDep1.playAnimation();
        }
        else if (wardNumberBox.getValue()==null) {
            Shake shakeDep2 = new Shake(wardNumberBox);
            shakeDep2.playAnimation();
        }
        else if (statusBox.getValue()==null) {
            Shake shakeDep3 = new Shake(statusBox);
            shakeDep3.playAnimation();
        } else {
            String department = departmentBox.getValue().toString();
            String isWardVip = isWardVipBox.getValue().toString();
            String wardNumber = wardNumberBox.getValue().toString();
            String status = statusBox.getValue().toString();
            DatabaseHandler dbHandler = new DatabaseHandler();
            dbHandler.putPatientInWard(GlobalVariables.patientName, GlobalVariables.patientSurname, department, isWardVip, wardNumber, status, docSurname);
            Scene currentScene = transferButton.getScene();
            currentScene.getWindow().hide();
        }
    }


    private boolean checkPatient(String usernameText, String userSurnameText) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Patient patient = new Patient();
        patient.setName(usernameText);
        patient.setSurname(userSurnameText);

        ResultSet resultSet = dbHandler.getPatient(patient);

        int counter = 0;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if (counter >= 1) {
            return true;
        } else {
            Shake userNameAnim = new Shake(patientNameField);
            Shake userSurnameAnim = new Shake(patientSurnameField);
            userNameAnim.playAnimation();
            userSurnameAnim.playAnimation();
            return false;
        }
    }


    private boolean checkDoctor(String usernameText, String userSurnameText) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Doctor doctor = new Doctor();
        doctor.setName(usernameText);
        doctor.setSurname(userSurnameText);

        ResultSet resultSet = dbHandler.getDoctor(doctor);

        int counter = 0;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if (counter >= 1) {
            return true;
        } else {
            Shake userNameAnim = new Shake(docNameField);
            Shake userSurnameAnim = new Shake(docSurnameField);
            userNameAnim.playAnimation();
            userSurnameAnim.playAnimation();
            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientNameField.setText(GlobalVariables.patientName);
        patientSurnameField.setText(GlobalVariables.patientSurname);

        transferButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
                String sectionToOpen = "::/vikno_vipiski_patsi_nta.htm";

                try {
                    Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }

    private void openChmHelp() {
        String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
        String sectionToOpen = "::/vikno_priznachennya_likuvannya.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}