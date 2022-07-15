package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.DriversInfo;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DriverInfoController implements Initializable {

    HelloApplication main = new HelloApplication();

    @FXML
    private TableColumn<DriversInfo, Integer> DID;

    @FXML
    private TableColumn<DriversInfo, Integer> DrivingL;

    @FXML
    private TableColumn<DriversInfo, String> Email;

    @FXML
    private TableColumn<DriversInfo, String> ExpiryDate;

    @FXML
    private TableColumn<DriversInfo, String> Name;

    @FXML
    private TableColumn<DriversInfo, Integer> PID;

    @FXML
    private TableColumn<DriversInfo, Integer> PhoneNumber;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private TextField deleteDID;

    @FXML
    private TableView<DriversInfo> drivers;

    @FXML
    private Button search;

    @FXML
    private TextField searchFor;

    @FXML
    private Button show;

    @FXML
    private TextField showCar;

    @FXML
    private ComboBox<DriversInfo> specifyField;

    @FXML
    private Button updateInfo;

    ObservableList<DriversInfo> transList = FXCollections.observableArrayList();

    Statement statement = null;
    DriversInfo DriversInfo = null;


    private void loadData() throws SQLException {
        Connection connection = MySQLConnect.connectDb();
        statement = connection.createStatement();
        DID.setCellValueFactory(new PropertyValueFactory<>("DID"));
        PID.setCellValueFactory(new PropertyValueFactory<>("PID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Dname"));
        PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone_number"));
        DrivingL.setCellValueFactory(new PropertyValueFactory<>("Driving_license"));
        ExpiryDate.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        refreshTable();
    }


    @FXML
    void AddWindow(MouseEvent event) throws IOException {
        main.openScene("AddNewDriver.fxml");
    }


    @FXML
    void DeleteDID(MouseEvent event) throws SQLException {
        String query = "DELETE FROM Drivers WHERE DID="+ deleteDID.getText()+";";
        int rs = statement.executeUpdate(query);
        if(rs!=0){
            JOptionPane.showInternalMessageDialog(null, "Delete operation has done successfully!");
        }else
            JOptionPane.showInternalMessageDialog(null, "Please Enter a valid TID!");
    }

    @FXML
    void searchForDriver(MouseEvent event) {

    }
    public static int showCarID;

    @FXML
    void showCarInfo(MouseEvent event) throws SQLException, IOException {
        showCarID = Integer.parseInt(showCar.getText());
        String query = "SELECT * FROM Cars WHERE Drivers_DID="+ showCarID +";";
        ResultSet rs = statement.executeQuery(query);
        if(rs.next()){
            main.openScene("CarInfo.fxml");
        }else {
            JOptionPane.showInternalMessageDialog(null, "Please Enter a valid DID!");
        }
    }

    @FXML
    void updateWindow(MouseEvent event) throws IOException {
        main.openScene("UpdateDriverInfo.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void refreshTable() throws SQLException {
        transList.clear();
        String query = "Select * from Drivers;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            transList.add(new DriversInfo(rs.getInt("DID"),
                    rs.getInt("PID"),
                    rs.getString("Dname"),
                    rs.getInt("Driving_license"),
                    rs.getInt("Phone_number"),
                    rs.getString("Email"),
                    rs.getInt("Driving_license"),
                    rs.getString("expiry_date"),
                    rs.getString("DPassword")));
            drivers.setItems(transList);
        }
    }

}
