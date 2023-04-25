package com.example.hospital;

import com.example.hospital.singleton.GlobalVariables;
import com.example.hospital.stuff.Nurse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowAllNursesWindow implements Initializable {

    @FXML
    private ComboBox<String> departmentBox;

    @FXML
    private TableColumn<Nurse, String> nameCol;

    @FXML
    private TableColumn<Nurse, String> phoneNumberCol;

    @FXML
    private TableColumn<Nurse, String> postCol;

    @FXML
    private TableColumn<Nurse, String> surnameCol;

    @FXML
    private TableView<Nurse> tableView;

    @FXML
    private TableColumn<Nurse, String> wardCol;

    @FXML
    private Button helpButton;

    @FXML
    void onDepartmentChoosed(ActionEvent event) {
        ObservableList<Nurse> nurses = FXCollections.observableArrayList();
        DatabaseHandler dbHandler = new DatabaseHandler();
        nurses=dbHandler.getNursesByDepartment(departmentBox.getValue().toString());
        nameCol.setCellValueFactory(new PropertyValueFactory<Nurse, String>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Nurse, String>("surname"));
        wardCol.setCellValueFactory(new PropertyValueFactory<Nurse, String>("ward"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Nurse, String>("phoneNumber"));
        postCol.setCellValueFactory(new PropertyValueFactory<Nurse, String>("department"));

        tableView.setItems(nurses);
    }



    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }

    private void openChmHelp() {
        String sectionToOpen = "::/vikno_pereglyadu_vsikh_medsester.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        helpButton.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.F1) {
                        String sectionToOpen = "::/vikno_pereglyadu_vsikh_medsester.htm";
                        try {
                            Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}