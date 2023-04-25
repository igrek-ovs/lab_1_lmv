package com.example.hospital;

import com.example.hospital.db.ConstantsDoctor;
import com.example.hospital.singleton.GlobalVariables;
import com.example.hospital.singleton.UserSingleton;
import com.example.hospital.stuff.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDoctorMainWindow implements Initializable {

    @FXML
    private Button addToMedCardButton;

    @FXML
    private TextField searchTextField;

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
    private TableColumn<Patient, String> department;

    @FXML
    private TableView<Patient> tableView;

    @FXML
    private Button updateButton;
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private TableColumn<Patient, String> wardCol;
    @FXML
    private TextField docSurnameField;
    @FXML
    private Button helpButton;

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
        String docSurname;
        if (docSurnameField.getText().isEmpty()) {
            docSurname = GlobalVariables.surname;
        } else {
            docSurname = docSurnameField.getText();
        }



        patients=dbHandler.getPatientsByDocSurname(docSurname);
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
        admissionDateCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("admissionDate"));
        wardCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("ward"));
        isWardVipCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("isWardVip"));
        department.setCellValueFactory(new PropertyValueFactory<Patient, String>("department"));
        tableView.setItems(patients);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dischargeButton.setDisable(true);
        addToMedCardButton.setDisable(true);
        label1.setText(GlobalVariables.name);
        label2.setText(GlobalVariables.surname);


        ObservableList<Patient> patients = FXCollections.observableArrayList();
        DatabaseHandler dbHandler = new DatabaseHandler();
        String docSurname = GlobalVariables.surname;
        patients=dbHandler.getPatientsByDocSurname(docSurname);
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
        admissionDateCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("admissionDate"));
        wardCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("ward"));
        isWardVipCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("isWardVip"));
        department.setCellValueFactory(new PropertyValueFactory<Patient, String>("department"));
        tableView.setItems(patients);

        tableView.setOnMouseClicked(event -> {
            // получаем выделенный ряд
            Patient selectedPatient = tableView.getSelectionModel().getSelectedItem();
            if (selectedPatient != null) {
                // сохраняем имя и фамилию пациента в переменные
                GlobalVariables.patientName = selectedPatient.getName();
                GlobalVariables.patientSurname = selectedPatient.getSurname();
                dischargeButton.setDisable(false);
                addToMedCardButton.setDisable(false);
                System.out.println("Selected patient: " + GlobalVariables.patientName + " " + GlobalVariables.patientSurname);
            }
        });




        FilteredList<Patient> filteredList = new FilteredList<>(tableView.getItems());

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String lowerCaseFilter = newValue.toLowerCase();

            filteredList.setPredicate(patient -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                return patient.getSurname().toLowerCase().startsWith(lowerCaseFilter);
            });
        });

        tableView.setItems(filteredList);



        dischargeButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/golovne_vikno_likarya.htm#discharge";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        updateButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/golovne_vikno_likarya.htm#update";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        tableView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/golovne_vikno_likarya.htm#table";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        searchTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/golovne_vikno_likarya.htm#search";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        docSurnameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/golovne_vikno_likarya.htm#docfield";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        addToMedCardButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String sectionToOpen = "::/golovne_vikno_likarya.htm#addinfo";

                try {
                    Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @FXML
    void onTextChanged(InputMethodEvent event) {

    }



    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }
    private void openChmHelp() {
        String sectionToOpen = "::/golovne_vikno_likarya.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
