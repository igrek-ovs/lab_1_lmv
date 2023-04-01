package com.example.hospital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerReceptionMainWindow {

    @FXML
    private Button addPatientButton;

    @FXML
    void onAddPatientClicked(ActionEvent event) {
        Scene currentScene = addPatientButton.getScene();

        // загружаем новый fxml-файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patient-registration.fxml"));
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