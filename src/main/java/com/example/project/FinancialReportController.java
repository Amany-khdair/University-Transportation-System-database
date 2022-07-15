package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class FinancialReportController implements Initializable {

    @FXML
    private TextField Dname;

    @FXML
    private TextField Sem;

    @FXML
    private TextField Year;

    @FXML
    private Button chooseDriver;

    @FXML
    private NumberAxis cost;

    @FXML
    private CategoryAxis day;

    @FXML
    private BarChart<String, Integer> financialTable;

    @FXML
    private Button select;

    int costPerStudent = 10;
    double literCost = 5.99;
    int flag = 0, DID;
    String DDname;
    HelloApplication main = new HelloApplication();
    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
    XYChart.Series<String, Integer> series2 = new XYChart.Series<>();

    @FXML
    void chooseDriver(MouseEvent event) throws IOException {
        flag = 1;
        main.openScene("ChooseDriver.fxml");
    }

    @FXML
    void getData(MouseEvent event) throws SQLException {
        int sem = Integer.parseInt(Sem.getText());
        int year = Integer.parseInt(Year.getText());
        int NumSaturday = 0, NumSunday = 0, NumMonday = 0, NumTuesday = 0, NumWednesday = 0, NumThursday = 0, NumFriday = 0;
        // get RIDs(to know the route and get the cost) and TIDs (to know the total Profit per day)
        Connection connection = MySQLConnect.connectDb();
        Statement statement = connection.createStatement();
        String query1 = "SELECT * FROM responsible where Drivers_DID=" + DID;
        ResultSet rs = statement.executeQuery(query1);
        while (rs.next()){
            int transTime = rs.getInt("Transportation_time_TID");
            int RID = rs.getInt("RID");
            Statement statement2 = connection.createStatement();
            String query2 = "SELECT * FROM transportation_time WHERE TID=" + transTime;
            ResultSet rs2 = statement2.executeQuery(query2);
            String day = null;
            if(rs2.next()){
                day = rs2.getString("Tday");
            }
            String numberOfStudentQuery = "SELECT count(*) FROM register WHERE Transportation_time_TID=" + transTime;
            Statement statement3 = connection.createStatement();
            ResultSet rs3 = statement3.executeQuery(numberOfStudentQuery);
            int numberOfStudent=0, numberOfTrip=0;
            if(rs3.next()){
                numberOfStudent = rs3.getInt(1);
            }
            Statement statement4 = connection.createStatement();
            String numberOfTripsQuery = "SELECT count(*) FROM responsible WHERE Transportation_time_TID=" + transTime;
            ResultSet rs4 = statement4.executeQuery(numberOfTripsQuery);
            if (rs4.next()){
                numberOfTrip = rs4.getInt(1);
            }
            int payment = Math.ceilDiv(numberOfStudent, numberOfTrip) * costPerStudent;

            String getDistanceQuery = "SELECT * FROM routes WHERE RID=" + RID;
            Statement statement5 = connection.createStatement();
            ResultSet rs5 = statement5.executeQuery(getDistanceQuery);
            int dist = 0;
            if(rs5.next()){
                dist = rs5.getInt("Distance");
            }
            double profit = payment - (2*(dist/7)*literCost);
            switch (Objects.requireNonNull(day)) {
                case "Saturday" -> NumSaturday += profit;
                case "Sunday" -> NumSunday += profit;
                case "Monday" -> NumMonday += profit;
                case "Tuesday" -> NumTuesday += profit;
                case "Wednesday" -> NumWednesday += profit;
                case "Thursday" -> NumThursday += profit;
                case "Friday" -> NumFriday += profit;
            }
        }
        series.getData().add(new XYChart.Data<>("Saturday", NumSaturday));
        series.getData().add(new XYChart.Data<>("Sunday", NumSunday));
        series.getData().add(new XYChart.Data<>("Monday", NumMonday));
        series.getData().add(new XYChart.Data<>("Tuesday", NumTuesday));
        series.getData().add(new XYChart.Data<>("Wednesday", NumWednesday));
        series.getData().add(new XYChart.Data<>("Thursday", NumThursday));
        series.getData().add(new XYChart.Data<>("Friday", NumFriday));
        financialTable.getData().add(series);
    }

    @FXML
    void setLabel(MouseEvent event) {
        if(flag==1){
            DID = ChooseDriverController.SelectedDID;
            DDname = ChooseDriverController.SelectedName;
            if (DDname != null) {
                Dname.setText(DDname);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        series.setName("Profit");
        series1.setName("Revenue");
        series2.setName("Cost");
    }
}
