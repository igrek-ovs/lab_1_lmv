package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.db.Diagnostic;
import com.example.hospital.db.Reception;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerReceptionAuth {

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
        if (!usernameField.getText().equals(Reception.RECEPTION_NAME) ) {
            Shake shake = new Shake(usernameField);
            shake.playAnimation();
        } else if (!usersurnameField.getText().equals(Reception.RECEPTION_SURNAME)) {
            Shake shake1 = new Shake(usersurnameField);
            shake1.playAnimation();
        } else if (!passwordField.getText().equals(Reception.RECEPTION_PASSWORD)) {
            Shake shake2 = new Shake(passwordField);
            shake2.playAnimation();
        } else {
            Scene currentScene = enterButton.getScene();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("reception-main-window.fxml"));
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
    }

    @FXML
    void onNameEntered(DragEvent event) {
    }

    @FXML
    void onPasswordEntered(DragEvent event) {
    }

    @FXML
    void onSurnameEntered(DragEvent event) {
    }

}