package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.password.PasswordGenerator;
import com.example.hospital.singleton.GlobalVariables;
import com.example.hospital.stuff.Doctor;
import com.example.hospital.stuff.Nurse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDoctorRegistrationWindow implements Initializable {

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
    private Button helpButton;

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


    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }

    private void openChmHelp() {
        String sectionToOpen = "::/vikno_re_stratsii_likarya.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/vikno_re_stratsii_likarya.htm#add";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        nameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/vikno_re_stratsii_likarya.htm#name";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        surnameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/vikno_re_stratsii_likarya.htm#surname";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        specializationBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/vikno_re_stratsii_likarya.htm#spec";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        departmentBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/vikno_re_stratsii_likarya.htm#depart";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        phoneField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/vikno_re_stratsii_likarya.htm#phone";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        postField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/vikno_re_stratsii_likarya.htm#post";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}