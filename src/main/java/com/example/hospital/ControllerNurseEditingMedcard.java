package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.singleton.GlobalVariables;
import com.example.hospital.stuff.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerNurseEditingMedcard implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Button saveButton;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Button addAnalize;
    @FXML
    private Button helpButton;


    @FXML
    private TextArea textField;

    @FXML
    void onSaveClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        DatabaseHandler dbHandler = new DatabaseHandler();
        String name = GlobalVariables.patientName;
        String surname = GlobalVariables.patientSurname;
        String text = textField.getText();
        text = text + "  \nEDIT DATE\n" + formattedDateTime+ "\n\n";


        dbHandler.updateMedCard(name, surname, text);
        Scene scene = saveButton.getScene();
        scene.getWindow().hide();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label1.setText(GlobalVariables.patientName);
        label2.setText(GlobalVariables.patientSurname);
        String name = GlobalVariables.patientName;
        String surname = GlobalVariables.patientSurname;
        String text;


        DatabaseHandler dbHandler = new DatabaseHandler();
        try {
            text = dbHandler.getPatientMedicalRecord(name, surname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        textField.setText(text);



        addAnalize.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выбрать изображение");
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png");
            fileChooser.getExtensionFilters().add(imageFilter);
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            }
        });

        addAnalize.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.F1) {
                        String sectionToOpen = "::/vikno_redaguvannya_meditsinskoi_kartochki_patsi_nta.htm";
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

    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }

    private void openChmHelp() {
        String sectionToOpen = "::/vikno_redaguvannya_meditsinskoi_kartochki_patsi_nta.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
