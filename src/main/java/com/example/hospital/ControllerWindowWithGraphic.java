/*
package com.example.hospital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ControllerWindowWithGraphic implements Initializable {

    @FXML
    private LineChart<String, Integer> admissionsChart;
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();

    private final ObservableList<XYChart.Series<String, Integer>> admissionsChartData = FXCollections.observableArrayList();

    private final DatabaseHandler dbHandler = new DatabaseHandler();

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button loadDataButton;

    @FXML
    private DatePicker startDatePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admissionsChart.setTitle("Количество поступлений");
        xAxis.setLabel("Дата");
        yAxis.setLabel("Количество поступлений");
        admissionsChart.setAnimated(false);
        admissionsChart.setCreateSymbols(true);
        admissionsChart.getData().addAll(admissionsChartData);
    }

    @FXML
    void onLoadDataClicked() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate == null || endDate == null) {

            return;
        }

        try {
            admissionsChartData.clear();
            admissionsChartData.addAll(dbHandler.getAdmissionsChartData(startDate, endDate).stream()
                    .map(data -> {
                        XYChart.Series<String, Integer> series = new XYChart.Series<>();
                        series.getData().add(new XYChart.Data<>(data.get(0).toString(), (Integer) data.get(1)));
                        return series;
                    })
                    .collect(Collectors.toList()));

        } catch (SQLException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}*/

package com.example.hospital;

import com.example.hospital.singleton.GlobalVariables;
import com.example.hospital.stuff.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerWindowWithGraphic implements Initializable {

    @FXML
    private LineChart<String, Integer> admissionsChart;
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    @FXML
    private Button helpButton;

    private final ObservableList<XYChart.Series<String, Integer>> admissionsChartData = FXCollections.observableArrayList();

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button loadDataButton;

    @FXML
    private DatePicker startDatePicker;
    private LocalDate startDate = LocalDate.now().minusDays(7);
    private LocalDate endDate = LocalDate.now();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admissionsChart.setTitle("Кількість поступів");
        xAxis.setLabel("Дата");
        yAxis.setLabel("Кількість поступів");
        admissionsChart.setAnimated(false);
        admissionsChart.setCreateSymbols(true);
        admissionsChart.getData().addAll(admissionsChartData);
        startDatePicker.setValue(startDate);
        endDatePicker.setValue(endDate);

        ObservableList<String> categories = FXCollections.observableArrayList();
        LocalDate currentDate = startDatePicker.getValue();
        while (!currentDate.isAfter(endDatePicker.getValue())) {
            categories.add(currentDate.toString());
            currentDate = currentDate.plusDays(1);
        }
        xAxis.setCategories(categories);

        yAxis.setLowerBound(0);
        yAxis.setUpperBound(10);



        helpButton.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.F1) {
                        String sectionToOpen = "::/grafik_kilkosti_postupiv_u_likarnyu.htm";
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
    void onLoadDataClicked() {

        admissionsChart.getData().clear();
        if (startDate == null || endDate == null) {
            System.out.println("Netu niche");
            return;
        }
        System.out.println(startDate + "  " + endDate);
        admissionsChartData.clear();
        System.out.println((getAdmissionsChartData(startDate, endDate)));
        admissionsChartData.addAll(getAdmissionsChartData(startDate, endDate));
        admissionsChart.getData().addAll(admissionsChartData);

    }

    private ObservableList<XYChart.Series<String, Integer>> getAdmissionsChartData(LocalDate startDate, LocalDate endDate) {
        ObservableList<XYChart.Series<String, Integer>> data = FXCollections.observableArrayList();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        if (startDate == null || endDate == null) {
            return data;
        }

        LocalDate currentDate = startDate;
        Random random = new Random();
        while (!currentDate.isAfter(endDate)) {
            String date = currentDate.toString();
            int count = random.nextInt(10);
            series.getData().add(new XYChart.Data<>(date, count));
            currentDate = currentDate.plusDays(1);
        }

        data.add(series);
        return data;
    }


    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        openChmHelp();
    }

    private void openChmHelp() {
        String sectionToOpen = "::/grafik_kilkosti_postupiv_u_likarnyu.htm";

        try {
            Runtime.getRuntime().exec("hh.exe " + GlobalVariables.PATH_TO_CHM + sectionToOpen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
