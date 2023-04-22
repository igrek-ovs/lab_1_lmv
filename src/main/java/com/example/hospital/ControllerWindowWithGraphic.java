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

import com.example.hospital.stuff.Patient;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

        // Set categories for the x-axis
        ObservableList<String> categories = FXCollections.observableArrayList();
        LocalDate currentDate = startDatePicker.getValue();
        while (!currentDate.isAfter(endDatePicker.getValue())) {
            categories.add(currentDate.toString());
            currentDate = currentDate.plusDays(1);
        }
        xAxis.setCategories(categories);

        // Set bounds for the y-axis
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(10);
    }

    @FXML
    void onLoadDataClicked() {
       /* LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();*/

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

        // Check that startDate and endDate are not null
        if (startDate == null || endDate == null) {
            return data;
        }

        // Generate random data
        LocalDate currentDate = startDate;
        Random random = new Random();
        while (!currentDate.isAfter(endDate)) {
            String date = currentDate.toString();
            int count = random.nextInt(10); // Random number of patients per day (up to 10)
            series.getData().add(new XYChart.Data<>(date, count));
            currentDate = currentDate.plusDays(1);
        }

        data.add(series);
        return data;
    }

    /*private ObservableList<XYChart.Series<String, Integer>> getAdmissionsChartData(LocalDate startDate, LocalDate endDate) {
        ObservableList<XYChart.Series<String, Integer>> data = FXCollections.observableArrayList();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Check that startDate and endDate are not null
        if (startDate == null || endDate == null) {
            return data;
        }

        // Generate data with a fixed admission count of 5 for each day
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            String date = currentDate.toString();
            int count = 5; // Fixed number of patients per day
            series.getData().add(new XYChart.Data<>(date, count));
            currentDate = currentDate.plusDays(1);
        }

        data.add(series);
        return data;
    }*/
}
