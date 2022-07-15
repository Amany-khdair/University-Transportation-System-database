package com.example.project;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.table1OnDashBoard;

import javafx.scene.control.TextField;
import models.table2OnDashBoard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainController implements Initializable {

    @FXML
    private ComboBox<String> Day;
    @FXML
    private ComboBox<String> DayS;
    @FXML
    private Label CostPerStudent;

    @FXML
    private LineChart<?, ?> NumberOfStudentsInSemester;

    @FXML
    private Button aboutRoutes;

    @FXML
    private TableColumn<table2OnDashBoard, String> address;

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    private Button dashboard;

    @FXML
    private DatePicker date2;

    @FXML
    private TextField day;

    @FXML
    private TextField days;

    @FXML
    private Button driversInfo;

    @FXML
    private PieChart femalesVsMales;

    @FXML
    private Button financialReport;

    @FXML
    private Button getButton;

    @FXML
    private Button getButtonS;

    @FXML
    private ChoiceBox<Integer> hour;

    @FXML
    private Button logOut;

    @FXML
    private Button myInfo;

    @FXML
    private TableColumn<table2OnDashBoard, String> name;

    @FXML
    private TableColumn<table1OnDashBoard, Integer> numberOfStudents;

    @FXML
    private TableColumn<table1OnDashBoard, Integer> numberOfTrips;

    @FXML
    private TableColumn<table2OnDashBoard, Integer> phone;

    @FXML
    private Button registration;

    @FXML
    private Label safeRoutes;

    @FXML
    private TextField sem;

    @FXML
    private TextField semS;

    @FXML
    private ComboBox<Integer> semester;

    @FXML
    private Button studentsInfo;

    @FXML
    private TableView<table2OnDashBoard> studentsNames;

    @FXML
    private TableColumn<table1OnDashBoard, Integer> t1Hour;

    @FXML
    private Label totalDrivers;

    @FXML
    private Label totalStudents;

    @FXML
    private TableView<table1OnDashBoard> transportatinTimesInADay;

    @FXML
    private Button transportation_times;

    @FXML
    private Button trips;

    @FXML
    private Label username;

    @FXML
    private TextField year;


    @FXML
    private TextField yearS;

    @FXML
    private CategoryAxis X;

    @FXML
    private NumberAxis Y;

    @FXML
    private TextField FromYear;

    @FXML
    private TextField ToYear;


    @FXML
    private Button getButton2;

    HelloApplication app = new HelloApplication();

    @FXML
    void AboutRoutes(MouseEvent event) throws IOException {
        String fxml = "AboutRoutes.fxml";
        changeButtonColor(aboutRoutes);
        loadPage(fxml);
    }

    @FXML
    void DriversInfo(MouseEvent event) throws IOException {
        String fxml = "DriversInfo.fxml";
        changeButtonColor(driversInfo);
        loadPage(fxml);
    }

    @FXML
    void FinancialReport(MouseEvent event) throws IOException {
        String fxml = "FinancialReport.fxml";
        changeButtonColor(financialReport);
        loadPage(fxml);
    }

    @FXML
    void LogOut(MouseEvent event) throws IOException {
        String fxml = "signInPage.fxml";
        app.changeScene(fxml);
    }

    @FXML
    void MyInfo(MouseEvent event) throws IOException {
        String fxml = "my_info.fxml";
        changeButtonColor(myInfo);
        loadPage(fxml);
    }

    @FXML
    void Regitration(MouseEvent event) throws IOException {
        String fxml = "Regestration.fxml";
        changeButtonColor(registration);
        loadPage(fxml);
    }

    @FXML
    void StudentsInfo(MouseEvent event) throws IOException {
        String fxml = "student.fxml";
        changeButtonColor(studentsInfo);
        loadPage(fxml);
    }

    @FXML
    void T_Times(MouseEvent event) throws IOException {
        String fxml = "Transportation Time.fxml";
        changeButtonColor(transportation_times);
        loadPage(fxml);
    }

    @FXML
    void Trips(MouseEvent event) throws IOException {
        String fxml = "Trips.fxml";
        changeButtonColor(trips);
        loadPage(fxml);
    }

    @FXML
    void dashBoard(MouseEvent event) {
        bp.setCenter(ap);
        changeButtonColor(dashboard);
        try {
            setLabels();
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    private void setLabels() throws SQLException {
        Connection connection = MySQLConnect.connectDb();
        Statement statement = connection.createStatement();
        String queryForSafeRoutes = "SELECT Count(1) FROM routes WHERE Safety=1";
        ResultSet rs = statement.executeQuery(queryForSafeRoutes);
        while (rs.next()){
            safeRoutes.setText(rs.getString(1));
        }
        String totalN = "select count(*) from student where Still_study=1;";
        Connection connection1 = MySQLConnect.connectDb();
        Statement statement1 = connection1.createStatement();
        ResultSet rs1 = statement1.executeQuery(totalN);
        if (rs1.next()) {
            totalStudents.setText(rs1.getString(1));
        }
        String totalDriver = "select count(*) from drivers";
        Connection connection2 = MySQLConnect.connectDb();
        Statement statement2 = connection2.createStatement();
        ResultSet rs2 = statement2.executeQuery(totalDriver);
        if (rs2.next()) {
            totalDrivers.setText(rs2.getString(1));
        }
    }

    private void loadPage(String fxml) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
            bp.setCenter(root);
        } catch (IOException e) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void changeButtonColor(Button pressedButton){
        dashboard.setStyle("-fx-background-color: transparent;");
        registration.setStyle("-fx-background-color: transparent;");
        trips.setStyle("-fx-background-color: transparent;");
        transportation_times.setStyle("-fx-background-color: transparent;");
        financialReport.setStyle("-fx-background-color: transparent;");
        studentsInfo.setStyle("-fx-background-color: transparent;");
        driversInfo.setStyle("-fx-background-color: transparent;");
        myInfo.setStyle("-fx-background-color: transparent;");
        aboutRoutes.setStyle("-fx-background-color: transparent;");
        pressedButton.setStyle("-fx-background-color: #FFFFFF;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Day.getItems().addAll(FXCollections.observableArrayList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        DayS.getItems().addAll(FXCollections.observableArrayList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        try {
            username.setText(startController.name);
            setLabels();
            number();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
    ObservableList<table1OnDashBoard> list = FXCollections.observableArrayList();
    ObservableList<table2OnDashBoard> list1 = FXCollections.observableArrayList();
    @FXML
    void getFirstTableData(MouseEvent event) throws SQLException {
        String day1 = Day.getValue();
        int sem1 = Integer.parseInt(sem.getText());
        int year1 = Integer.parseInt(year.getText());
        t1Hour.setCellValueFactory(new PropertyValueFactory<>("Hour"));
        numberOfStudents.setCellValueFactory(new PropertyValueFactory<>("numberOfStudent"));
        numberOfTrips.setCellValueFactory(new PropertyValueFactory<>("numberOfTrips"));
        list.clear();
        String HoursQuery = "Select * from transportation_time where Tday=" + "\"" + day1 + "\" "
                + "and Semester=" + sem1 + " and Tyear=" + year1;
        Connection connection = MySQLConnect.connectDb();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(HoursQuery);
        int  transID, hour, numberOfStudent = 0, numberOfTrip = 0;
        while (rs.next()) {
            transID = rs.getInt("TID");
            hour = rs.getInt("Thour");
            Statement statement1 = connection.createStatement();
            String numberOfStudentQuery = "SELECT count(*) FROM register WHERE Transportation_time_TID=" + transID;
            ResultSet rs1 = statement1.executeQuery(numberOfStudentQuery);
            if (rs1.next()){
                numberOfStudent = rs1.getInt(1);
            }
            Statement statement2 = connection.createStatement();
            String numberOfTripsQuery = "SELECT count(*) FROM responsible WHERE Transportation_time_TID=" + transID;
            ResultSet rs2 = statement2.executeQuery(numberOfTripsQuery);
            if (rs2.next()){
                numberOfTrip = rs2.getInt(1);
            }
            list.add(new table1OnDashBoard(hour,numberOfStudent,numberOfTrip));
        }
        transportatinTimesInADay.setItems(list);
    }

    @FXML
    void getSecondTableData(MouseEvent event) throws SQLException {
        String day2 = DayS.getValue();
        int sem2 = Integer.parseInt(semS.getText());
        int year2 = Integer.parseInt(yearS.getText());
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        list1.clear();
        String NamesQuery = "select s.Sname, s.Phone_number, s.Address "
                + "  from Register r, student s, Transportation_time t "
                + "  where r.Student_SID = s.SID "
                + "  and r.Transportation_time_TID = t.TID"
                + "  and t.Tyear = \" " + year2 + "\""
                + "  and t.Tday =\"" + day2 + "\""
                + "  and t.Semester =\"" + sem2 + "\";";


        Connection connection = MySQLConnect.connectDb();
        Statement statement = connection.createStatement();
        ResultSet rs2 = statement.executeQuery(NamesQuery);

        String name;
        int phone;
        String address;
        while (rs2.next()) {

            name = rs2.getString("Sname");
            phone = rs2.getInt("Phone_number");
            address = rs2.getString("Address");

            list1.add(new table2OnDashBoard(name, phone, address));
        }
        studentsNames.setItems(list1);
    }

    @FXML
    void numOfStudent(MouseEvent event) {

    }

    @FXML
    void femaleVsMale(MouseEvent event) {
    }

    @FXML
    public void number() throws SQLException {

        int female = 0;
        int male = 0;

        Connection connection = MySQLConnect.connectDb();
        Statement statement = connection.createStatement();
        String femaleString = "SELECT Count(1) FROM student WHERE Gender=\"female\";";
        ResultSet rs = statement.executeQuery(femaleString);
        while (rs.next()) {
            female = rs.getInt(1);
        }


        String maleString = "SELECT Count(1) FROM student WHERE Gender=\"male\";";
        ResultSet rs1 = statement.executeQuery(maleString);
        while (rs1.next()) {
            male = rs1.getInt(1);
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Female", female),
                        new PieChart.Data("Male", male)
                );
        pieChartData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(), "amount: ", data.pieValueProperty())));
        femalesVsMales.getData().addAll(pieChartData);

    }

    @FXML
    void getLineChart(MouseEvent event) throws SQLException {
        int fYear = Integer.parseInt(FromYear.getText());
        int tYear = Integer.parseInt(ToYear.getText());
        int numberOfStudent = 0;
        Connection connection = MySQLConnect.connectDb();
        Statement statement1 = connection.createStatement();
        String numberOfStudentQuery = null;
        XYChart.Series series = new XYChart.Series();

        for (int i = fYear;i <= tYear;i++){
            for (int j = 1;j <= 3;j++){
                numberOfStudentQuery = "select count(Student_SID) from Register where Transportation_time_TID IN (" +
                        "select TID from Transportation_time where \n" +
                        "Tyear="+ fYear+ " and Semester =" + j +");" + fYear +");";
                ResultSet rs1 = statement1.executeQuery(numberOfStudentQuery);
                if (rs1.next()) {
                    numberOfStudent = rs1.getInt(1);
                }
                series.getData().add(new XYChart.Data(fYear,numberOfStudent));
            }
        }
        NumberOfStudentsInSemester.getData().add(series);
    }
}
