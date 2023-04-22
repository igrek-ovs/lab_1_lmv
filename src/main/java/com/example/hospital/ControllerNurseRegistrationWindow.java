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
import java.io.IOException;

public class ControllerNurseRegistrationWindow {

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<?> departmentBox;

    @FXML
    private Button generatePasswordButton;

    @FXML
    private TextField nameField;

    private String password;
    @FXML
    private TextField phoneField;

    @FXML
    private TextField postField;

    @FXML
    private TextField surnameField;
    @FXML
    private Button helpButton;

    @FXML
    private ComboBox<?> wardBox;

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
        } else if (wardBox.getValue() == null) {
            Shake specS = new Shake(wardBox);
            specS.playAnimation();
        } else if (departmentBox.getValue() == null) {
            Shake departS = new Shake(departmentBox);
            departS.playAnimation();
        } else if (!post.contains("@")) {
            postS.playAnimation();
            postField.clear();
        } else {
            DatabaseHandler dbHandler = new DatabaseHandler();
            Nurse nurse = new Nurse(name, surname, pass, departmentBox.getValue().toString(), wardBox.getValue().toString(), phone, post);
            Scene currentScene = addButton.getScene();

            try {
                PasswordGenerator.sendEmail(post, pass);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            dbHandler.signUpNurse(nurse);
            currentScene.getWindow().hide();
        }
    }


    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }

    private void openChmHelp() {
        String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
        String sectionToOpen = "::/vikno_re_stratsii_medsestri.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}