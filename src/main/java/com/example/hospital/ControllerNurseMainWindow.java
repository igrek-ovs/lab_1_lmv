package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.password.PasswordGenerator;
import com.example.hospital.singleton.GlobalVariables;
import com.example.hospital.stuff.Patient;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNurseMainWindow implements Initializable {
    @FXML
    private Label label1;
    @FXML
    private Button sendLetterButton;
    @FXML
    private TextArea textForDoctor;

    @FXML
    private Label label2;
    @FXML
    private ComboBox<String> themeComboBox;

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
    private Label messageLabel;

    @FXML
    private Button updateButton;
    @FXML
    private Text textFlow;
    @FXML
    private Button helpButton;

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





    }




    @FXML
    void onSendButtonClicked(ActionEvent event) throws MessagingException {
        String text = textForDoctor.getText();
        PasswordGenerator.sendLetter("igrekvovcyn@gmail.com", text);
        showMessage("Відправлено!");
        textForDoctor.clear();
    }

    private void showMessage(String message) {
        messageLabel.setText(message);

        // создаем анимацию для скрытия сообщения через 3 секунды
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), e -> messageLabel.setText(""));
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendLetterButton.setDisable(true);
        addToMedCard.setDisable(true);
        label1.setText(GlobalVariables.name);
        label2.setText(GlobalVariables.surname);


        String department = "department2";
        String ward = "ward1";

        ObservableList<Patient> patients = FXCollections.observableArrayList();
        DatabaseHandler dbHandler = new DatabaseHandler();
        patients = dbHandler.getPatientsByWardAndDep(ward, department);
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
        admissionDateCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("admissionDate"));

        docSurnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("docSurname"));
        tableView.setItems(patients);


        tableView.setOnMouseClicked(event -> {
            // получаем выделенный ряд
            Patient selectedPatient = tableView.getSelectionModel().getSelectedItem();
            if (selectedPatient != null) {
                // сохраняем имя и фамилию пациента в переменные
                GlobalVariables.patientName = selectedPatient.getName();
                GlobalVariables.patientSurname = selectedPatient.getSurname();
                GlobalVariables.docSurnameSelected = selectedPatient.getDocSurname();
                textFlow.setText(selectedPatient.getMedCard());
                addToMedCard.setDisable(false);
                sendLetterButton.setDisable(false);
                System.out.println("Selected patient: " + GlobalVariables.patientName + " " + GlobalVariables.patientSurname);
            }
        });

        themeComboBox.getItems().addAll("Біла", "Жовта", "Зелена");

        // Устанавливаем обработчик выбора элемента в ComboBox
        themeComboBox.setOnAction((event) -> {
            String theme = themeComboBox.getValue();

            if (theme.equals("Біла")) {
                // Устанавливаем задний фон для окна
                Scene scene = themeComboBox.getScene();
                scene.getRoot().setStyle("-fx-background-color: #FFFFFF;");
            } else if (theme.equals("Жовта")) {
                // Устанавливаем другой задний фон для окна
                Scene scene = themeComboBox.getScene();
                scene.getRoot().setStyle("-fx-background-color: #FFFF00;");
            } else if (theme.equals("Зелена")) {
                // Устанавливаем третий задний фон для окна
                Scene scene = themeComboBox.getScene();
                scene.getRoot().setStyle("-fx-background-color: #00FF00;");
            }
        });





        sendLetterButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
                String sectionToOpen = "::/golovne_vikno_medsestri.htm";

                try {
                    Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        updateButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
                String sectionToOpen = "::/golovne_vikno_medsestri.htm";

                try {
                    Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }

    private void openChmHelp() {
        String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
        String sectionToOpen = "::/golovne_vikno_medsestri.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}