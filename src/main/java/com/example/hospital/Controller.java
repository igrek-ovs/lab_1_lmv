package com.example.hospital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Label welcomeText;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Button adminAuthorizationButton;

    @FXML
    private Button doctorAuthorizationButton;

    @FXML
    private Button nurseAuthorizationButton;

    @FXML
    private Button patientAuthorizationButton;
    @FXML
    private Button receptionButton;
    @FXML
    private Button diagnosticButton;

    @FXML
    void onadminAuthorizationButtonClicked(ActionEvent event) {
        Scene currentScene = adminAuthorizationButton.getScene();
        //currentScene.getWindow().hide();
        // загружаем новый fxml-файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_authorization.fxml"));
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
    void ondoctorAuthorizationButtonClckd(ActionEvent event) {
        Scene currentScene = adminAuthorizationButton.getScene();

        // загружаем новый fxml-файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("doctor-auth.fxml"));
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
    void onnurseAuthorizationButton(ActionEvent event) {
        Scene currentScene = adminAuthorizationButton.getScene();

        // загружаем новый fxml-файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("nurse_authorization.fxml"));
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
    void onDiagnosticButtonClicked(ActionEvent event) {
        Scene currentScene = adminAuthorizationButton.getScene();

        // загружаем новый fxml-файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("diagnostic-authorization-window.fxml"));
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
    void onReceptionButtonClicked(ActionEvent event) {
        Scene currentScene = adminAuthorizationButton.getScene();

        // загружаем новый fxml-файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reception_authorization.fxml"));
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