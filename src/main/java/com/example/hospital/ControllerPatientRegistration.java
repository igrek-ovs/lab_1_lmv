package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.password.PasswordGenerator;
import com.example.hospital.stuff.Nurse;
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
import java.time.LocalDate;

import javax.mail.MessagingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ControllerPatientRegistration implements Initializable {

    @FXML
    private Button addPatientButton;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<?> statusBox;

    @FXML
    private TextField surnameField;
    @FXML
    private Button helpButton;

    @FXML
    void onAddPatientClicked(ActionEvent event) {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String status = statusBox.getValue().toString();
        Shake nameS = new Shake(nameField);
        Shake surnameS = new Shake(surnameField);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 9999);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 31);

// получаем количество миллисекунд с начала эпохи Unix
        long millis = cal.getTimeInMillis();

        if (name.length() < 2) {
            nameS.playAnimation();
            nameField.clear();
        } else if (surname.length() < 2) {
            surnameS.playAnimation();
            surnameField.clear();
        }  else if (statusBox.getValue() == null) {
            Shake specS = new Shake(statusBox);
            specS.playAnimation();
        }   else {
            DatabaseHandler dbHandler = new DatabaseHandler();
            Patient patient = new Patient(name, surname, "","","","",new java.sql.Date(System.currentTimeMillis()),new java.sql.Date(millis), "", status);
            Scene currentScene = addPatientButton.getScene();



            dbHandler.signUpPatient(patient);
            currentScene.getWindow().hide();
        }
    }


    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }

    private void openChmHelp() {
        String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
        String sectionToOpen = "::/vikno_re_stratsii_patsi_nta.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPatientButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
                String sectionToOpen = "::/vikno_re_stratsii_patsi_nta.htm";

                try {
                    Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
