package com.example.hospital;

import com.example.hospital.db.ConstantsDoctor;
import com.example.hospital.singleton.UserSingleton;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerDoctorMainWindow {

    @FXML
    private Button addToMedCardButton;

    @FXML
    private TableColumn<Patient, String> admissionDateCol;

    @FXML
    private Button dischargeButton;

    @FXML
    private TableColumn<Patient, String> isWardVipCol;

    @FXML
    private TableColumn<Patient, String> nameCol;

    @FXML
    private TableColumn<Patient, String> surnameCol;

    @FXML
    private TableView<Patient> tableView;

    @FXML
    private Button updateButton;

    @FXML
    private TableColumn<Patient, String> wardCol;
    @FXML
    private TextField docSurnameField;

    @FXML
    void onAddToMedCardClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("doctor-med-card-editing.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void onDischargeButtonClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patient-discharge.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
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
        String docSurname = docSurnameField.getText();
        patients=dbHandler.getPatientsByDocSurname(docSurname);
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
        admissionDateCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("admissionDate"));
        wardCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("ward"));
        isWardVipCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("isWardVip"));

        tableView.setItems(patients);
    }

}
