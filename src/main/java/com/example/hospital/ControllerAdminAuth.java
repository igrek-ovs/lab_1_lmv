package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.stuff.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerAdminAuth {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField usersurnameField;
    @FXML
    private Button enterButton;


    @FXML
    private Button backButton;

    @FXML
    void onBackButtonPressed(ActionEvent event) {
        Scene currentScene = backButton.getScene();
        currentScene.getWindow().hide();
        // загружаем новый fxml-файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void onEnterButtonClicked(ActionEvent event) {
        String usernameText = usernameField.getText().trim();
        String userSurnameText = usersurnameField.getText().trim();
        String userPasswordText = passwordField.getText().trim();

        if (!usernameText.equals("") && !userSurnameText.equals("") && !userPasswordText.equals("")) {
            loginUser(usernameText, userSurnameText, userPasswordText);
        } else {
            System.out.println("Empty field!");
        }



        /*DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.signUpAdmin(usernameField.getText(), usersurnameField.getText(), passwordField.getText());*/
    }

    private void loginUser(String usernameText, String userSurnameText, String userPasswordText) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Admin admin = new Admin();
        admin.setName(usernameText);
        admin.setSurname(userSurnameText);
        admin.setPassword(userPasswordText);
        ResultSet resultSet = dbHandler.getAdmin(admin);

        int counter = 0;
        while(true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if(counter>=1){
            //Scene currentScene = adminAuthorizationButton.getScene();
            //currentScene.getWindow().hide();
            // загружаем новый fxml-файл
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-main-window.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } else {
            Shake userNameAnim = new Shake(usernameField);
            Shake userSurnameAnim = new Shake(usersurnameField);
            Shake userPasswordAnim = new Shake(passwordField);
            userNameAnim.playAnimation();
            userSurnameAnim.playAnimation();
            userPasswordAnim.playAnimation();
        }
    }


}