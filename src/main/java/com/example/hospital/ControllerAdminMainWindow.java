package com.example.hospital;

import com.example.hospital.singleton.GlobalVariables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdminMainWindow implements Initializable {

    @FXML
    private Button addDoctorButton;
    @FXML
    private Button showAllDocButton;
    @FXML
    private Button showAllNursesButton;
    @FXML
    private Button addNurseButton;
    @FXML
    private Button helpButton;
    @FXML
    private Label label1;

    @FXML
    private Label label2;
    @FXML
    private Button secondGraphic;
    @FXML
    void onSecondGraphicClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("window-with-graphic.fxml"));
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
    void onAddDoctorClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("doctor-registration-window.fxml"));
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
    void onShowAllDocClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("all-doctors-table.fxml"));
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
    void onShowAllNursesClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show-all-nurses-window.fxml"));
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
    void onAddNurseClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("nurse-registration-window.fxml"));
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
    private BarChart<String, Integer> barChart;
    @FXML
    private NumberAxis yAxis;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label1.setText(GlobalVariables.name);
        label2.setText(GlobalVariables.surname);
        DatabaseHandler dbHandler = new DatabaseHandler();
        Integer dep1Num = dbHandler.getPatientsByDepartment("department1");
        Integer dep2Num = dbHandler.getPatientsByDepartment("department2");
        Integer dep3Num = dbHandler.getPatientsByDepartment("department3");

        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("Department1", dep1Num));
        series1.getData().add(new XYChart.Data("Department2", dep2Num));
        series1.getData().add(new XYChart.Data("Department3", dep3Num));
        barChart.getData().addAll(series1);

        yAxis.setTickUnit(1);



        addDoctorButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
                String sectionToOpen = "::/vikno_re_stratsii_likarya.htm";

                try {
                    Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        showAllDocButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
                String sectionToOpen = "::/vikno_pereglyadu_vsikh_likariv.htm";

                try {
                    Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        showAllNursesButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
                String sectionToOpen = "::/vikno_pereglyadu_vsikh_medsester.htm";

                try {
                    Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        addNurseButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                String pathToChmFile = "C:/Users/Игорь/Downloads/Hospital.chm";
                String sectionToOpen = "::/vikno_re_stratsii_medsestri.htm";

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
        String sectionToOpen = "::/golovne_vikno_administratora.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + pathToChmFile + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
