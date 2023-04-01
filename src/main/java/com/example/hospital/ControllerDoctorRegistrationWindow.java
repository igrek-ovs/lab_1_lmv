package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.password.PasswordGenerator;
import com.example.hospital.stuff.Doctor;
import com.example.hospital.stuff.Nurse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.mail.MessagingException;

public class ControllerDoctorRegistrationWindow {

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<?> departmentBox;

    @FXML
    private TextField nameField;

    private String post;

    @FXML
    private TextField phoneField;

    @FXML
    private ComboBox<?> specializationBox;

    @FXML
    private TextField surnameField;
    @FXML
    private TextField postField;

    @FXML
    void onAddButtonClicked(ActionEvent event) {
        PasswordGenerator.generatePassword();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String pass = PasswordGenerator.generatePassword();
        String phone = phoneField.getText();
        String post = postField.getText();
        Shake nameS = new Shake(nameField);
        Shake surnameS = new Shake(surnameField);
        Shake phoneS = new Shake(phoneField);
        Shake postS = new Shake(postField);


        if (name.length() < 2) {
            nameS.playAnimation();
            nameField.clear();
        } else if (surname.length() < 2) {
            surnameS.playAnimation();
            surnameField.clear();
        } else if (!phone.startsWith("+380") || phone.matches(".*[a-zA-Z].*")||phone.length()!=13) {
            phoneS.playAnimation();
            phoneField.clear();
        } else if (specializationBox.getValue() == null) {
            Shake specS = new Shake(specializationBox);
            specS.playAnimation();
        } else if (departmentBox.getValue() == null) {
            Shake departS = new Shake(departmentBox);
            departS.playAnimation();
        } else if (!post.contains("@")) {
            postS.playAnimation();
            postField.clear();
        } else {
            DatabaseHandler dbHandler = new DatabaseHandler();
            Doctor doctor = new Doctor(name, surname, pass, specializationBox.getValue().toString(), departmentBox.getValue().toString(), phone, post);
            Scene currentScene = addButton.getScene();

            try {
                PasswordGenerator.sendEmail(post, pass);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            dbHandler.signUpDoctor(doctor);
            currentScene.getWindow().hide();
        }
    }

}