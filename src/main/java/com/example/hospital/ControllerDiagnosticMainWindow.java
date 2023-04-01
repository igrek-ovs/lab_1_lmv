package com.example.hospital;

import com.example.hospital.stuff.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerDiagnosticMainWindow {

    @FXML
    private Button addToMedCardButton;

    @FXML
    private TableColumn<Patient, String> admissionDateCol;

    @FXML
    private TableColumn<Patient, String> medCardCol;

    @FXML
    private TableColumn<Patient, String> nameCol;

    @FXML
    private TableColumn<Patient, String> surnameCol;

    @FXML
    private TableView<Patient> tableView;

    @FXML
    private Button transferButton;

    @FXML
    private Button updateButton;

    @FXML
    void onAddToMedCardClicked(ActionEvent event) {
        Scene currentScene = updateButton.getScene();

        // загружаем новый fxml-файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("diagnostic-editing-medcard.fxml"));
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
    void onTransferButtonClicked(ActionEvent event) {
        Scene currentScene = updateButton.getScene();

        // загружаем новый fxml-файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("diagnostic-transfer-window.fxml"));
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
    void onUpdateButtonClicked(ActionEvent event) {
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        DatabaseHandler dbHandler = new DatabaseHandler();
        String status = "діагностика";
        patients=dbHandler.getPatientsByStatus(status);
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
        admissionDateCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("admissionDate"));
        medCardCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("medCard"));

        tableView.setItems(patients);
    }

}
