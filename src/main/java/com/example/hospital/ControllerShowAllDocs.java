
package com.example.hospital;

        import com.example.hospital.stuff.Doctor;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerShowAllDocs {

    @FXML
    private ComboBox<?> departmentBox;

    @FXML
    private TableColumn<Doctor, String> nameCol;

    @FXML
    private TableColumn<Doctor, String> phoneCol;

    @FXML
    private TableColumn<Doctor, String> specializationCol;

    @FXML
    private TableColumn<Doctor, String> surnameCol;

    @FXML
    private TableView<Doctor> tableView;

    @FXML
    private TableColumn<Doctor, String> postCol;

    @FXML
    void onDepartmentBoxClicked(ActionEvent event) {
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        DatabaseHandler dbHandler = new DatabaseHandler();
        doctors=dbHandler.getDoctorsByDepartment(departmentBox.getValue().toString());
        nameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("surname"));
        specializationCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));

        postCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("post"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phoneNumber"));
        tableView.setItems(doctors);
    }

}