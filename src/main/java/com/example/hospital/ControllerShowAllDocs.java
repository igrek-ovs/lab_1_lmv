
package com.example.hospital;

        import com.example.hospital.singleton.GlobalVariables;
        import com.example.hospital.stuff.Doctor;
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

public class ControllerShowAllDocs implements Initializable {

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
    private Button helpButton;

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


    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }

    private void openChmHelp() {
        String sectionToOpen = "::/vikno_pereglyadu_vsikh_likariv.htm";

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
                        String sectionToOpen = "::/vikno_pereglyadu_vsikh_likariv.htm";
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