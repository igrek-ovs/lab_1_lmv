package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.singleton.GlobalVariables;
import com.example.hospital.singleton.UserSingleton;
import com.example.hospital.stuff.Doctor;
import com.example.hospital.stuff.Nurse;
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

public class ControllerNurseAuth {

    @FXML
    private Button backButton;

    @FXML
    private Button enterButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField usersurnameField;

    @FXML
    void onBackButtonPressed(ActionEvent event) {

    }

    @FXML
    void onEnterButtonClicked(ActionEvent event) {
        String usernameText = usernameField.getText().trim();
        String userSurnameText = usersurnameField.getText().trim();
        String userPasswordText = passwordField.getText().trim();

        if (!usernameText.equals("") && !userSurnameText.equals("") && !userPasswordText.equals("")) {
            loginUser(usernameText, userSurnameText, userPasswordText);
            UserSingleton user = UserSingleton.getInstance();
            user.setSurname(userSurnameText);
        } else {
            Shake userNameAnim = new Shake(usernameField);
            Shake userSurnameAnim = new Shake(usersurnameField);
            Shake userPasswordAnim = new Shake(passwordField);
            userNameAnim.playAnimation();
            userSurnameAnim.playAnimation();
            userPasswordAnim.playAnimation();
        }
    }


    private void loginUser(String usernameText, String userSurnameText, String userPasswordText) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Nurse nurse = new Nurse();
        nurse.setName(usernameText);
        nurse.setSurname(userSurnameText);
        nurse.setPassword(userPasswordText);
        ResultSet resultSet = dbHandler.getNurse(nurse);

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
            GlobalVariables.name = usernameField.getText();
            GlobalVariables.surname = usersurnameField.getText();
            //Scene currentScene = adminAuthorizationButton.getScene();
            //currentScene.getWindow().hide();
            // загружаем новый fxml-файл
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nurse-main-window.fxml"));
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
