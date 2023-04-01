package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.stuff.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerDoctorMedCardEditing {

    @FXML
    private Button loadButton;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField surnameField;

    @FXML
    private TextArea textField;

    @FXML
    void onLoadClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String text;

        if (!checkPatient(name, surname)) {
            nameField.clear();
            surnameField.clear();
        } else {
            DatabaseHandler dbHandler = new DatabaseHandler();
            text = dbHandler.getPatientMedicalRecord(name, surname);
            textField.setText(text);
        }
    }

    @FXML
    void onSaveClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String text = textField.getText();

        if (!checkPatient(name, surname)) {
            nameField.clear();
            surnameField.clear();
        } else {
            dbHandler.updateMedCard(name, surname, text);
            Scene scene = saveButton.getScene();
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
