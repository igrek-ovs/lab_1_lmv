package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.stuff.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerPatientDischarge {

    @FXML
    private Button dischargeButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    void onDischargeClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = nameField.getText();
        String surname = surnameField.getText();
        DatabaseHandler dbHandler = new DatabaseHandler();


        if (!checkPatient(name, surname)) {
            nameField.clear();
            surnameField.clear();
        } else {
            dbHandler.dischargePatient(name, surname);
            Scene scene = dischargeButton.getScene();
            scene.getWindow().hide();
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
            Shake userNameAnim = new Shake(nameField);
            Shake userSurnameAnim = new Shake(surnameField);
            userNameAnim.playAnimation();
            userSurnameAnim.playAnimation();
            return false;
        }
    }

}
