package com.example.hospital;

import com.example.hospital.stuff.Nurse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerShowAllNursesWindow {

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

}