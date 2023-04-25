package com.example.hospital;

import com.example.hospital.animations.Shake;
import com.example.hospital.singleton.GlobalVariables;
import com.example.hospital.stuff.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Scale;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerPatientDischarge implements Initializable {

    @FXML
    private Button dischargeButton;
    @FXML
    private Button createReport;

    @FXML
    private Label label1;

    @FXML
    private TextArea textArea;

    @FXML
    private Label label2;
    @FXML
    private Button formButton;
    @FXML
    private TextArea text1;

    @FXML
    private TextArea text2;
    @FXML
    private Button helpButton;

    @FXML
    void onDischargeClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = GlobalVariables.patientName;
        String surname = GlobalVariables.patientSurname;
        DatabaseHandler dbHandler = new DatabaseHandler();
        /*dbHandler.dischargePatient(name, surname);*/


        /*Here we can print text*/


        /*Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / textArea.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / textArea.getBoundsInParent().getHeight();
        textArea.scaleXProperty().set(scaleX);
        textArea.scaleYProperty().set(scaleY);
        PrinterJob job = PrinterJob.createPrinterJob(printer);
        if (job != null) {
            boolean success = job.printPage(textArea);
            if (success) {
                job.endJob();
                dbHandler.dischargePatient(name, surname);
            }
        }
        textArea.scaleXProperty().set(1.0);
        textArea.scaleYProperty().set(1.0);*/




        Scene scene = dischargeButton.getScene();
        scene.getWindow().hide();
    }

    @FXML
    void onCreateReportClicked(ActionEvent event){

        String textForPrinting = "ЗАКЛЮЧЕННЯ\n";
        textForPrinting = textForPrinting + text1.getText() + "\n\n\n" + text2.getText()+"\n\n";
        textArea.setText(textForPrinting);
    }

    @FXML
    void onFormButtonClicked(ActionEvent event) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        text1.setText("Заключення було зроблено " + formattedDateTime + " для пацієнта - " + label1.getText() + "  " + label2.getText()+"!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label1.setText(GlobalVariables.patientName);
        label2.setText(GlobalVariables.patientSurname);







        helpButton.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.F1) {
                        String sectionToOpen = "::/vikno_vipiski_patsi_nta.htm";
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
        String sectionToOpen = "::/vikno_vipiski_patsi_nta.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
