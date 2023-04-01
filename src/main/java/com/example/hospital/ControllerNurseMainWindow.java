package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.stuff.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerNurseMainWindow {

    @FXML
    private Button addToMedCard;

    @FXML
    private TableColumn<Patient, String> admissionDateCol;

    @FXML
    private ComboBox<?> departmentBox;

    @FXML
    private TableColumn<Patient, String> docSurnameCol;

    @FXML
    private TableColumn<Patient, String> nameCol;

    @FXML
    private TableColumn<Patient, String> surnameCol;

    @FXML
    private Button updateButton;

    @FXML
    private ComboBox<?> wardBox;

    @FXML
    private TableView<Patient> tableView;

    @FXML
    void onAddToMedCardClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("nurse-editing-medcard.fxml"));
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
        if (departmentBox.getValue() == null) {
            Shake shake1 = new Shake(departmentBox);
            shake1.playAnimation();
        } else if (wardBox.getValue() == null) {
            Shake shake2 = new Shake(wardBox);
            shake2.playAnimation();
        } else {
            String department = departmentBox.getValue().toString();
            String ward = wardBox.getValue().toString();

            ObservableList<Patient> patients = FXCollections.observableArrayList();
            DatabaseHandler dbHandler = new DatabaseHandler();
            patients = dbHandler.getPatientsByWardAndDep(ward, department);
            nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
            surnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
            admissionDateCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("admissionDate"));

            docSurnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("docSurname"));
            tableView.setItems(patients);
        }


    }


}