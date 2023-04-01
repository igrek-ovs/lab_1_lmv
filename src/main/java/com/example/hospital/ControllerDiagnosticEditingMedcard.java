package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.stuff.Admin;
import com.example.hospital.stuff.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerDiagnosticEditingMedcard {

    @FXML
    private Button addMedCardButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextArea text;

    @FXML
    void onAddButtonClicked(ActionEvent event) {
        DatabaseHandler  dbHandler = new DatabaseHandler();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String medCardText = text.getText();
        if(!checkPatient(name, surname)){
            nameField.clear();
            surnameField.clear();
        } else {
            try {
                dbHandler.addMedCard(name, surname, medCardText);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Scene currentScene = addMedCardButton.getScene();
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
            Shake userNameAnim = new Shake(nameField);
            Shake userSurnameAnim = new Shake(surnameField);
            userNameAnim.playAnimation();
            userSurnameAnim.playAnimation();
            return false;
        }
    }

}