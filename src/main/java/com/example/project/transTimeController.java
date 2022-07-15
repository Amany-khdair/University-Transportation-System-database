package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.TransportationTime;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class transTimeController implements Initializable {

    HelloApplication main = new HelloApplication();

    @FXML
    private Button AddTransTime;

    @FXML
    private TableColumn<TransportationTime, String> Day;

    @FXML
    private TextField DeleteTID;

    @FXML
    private TableColumn<TransportationTime, Integer> Hour;

    @FXML
    private TableColumn<TransportationTime, Integer> Semester;

    @FXML
    private TableColumn<TransportationTime, Integer> TID;

    @FXML
    private TableColumn<TransportationTime, Integer> Year;

    @FXML
    private Button delete;

    @FXML
    private Button refreshButton;

    @FXML
    private Button search;

    @FXML
    private TextField searchFor;

    @FXML
    private ComboBox<String> specifyField;

    @FXML
    private TableView<TransportationTime> transportationTimes;

    @FXML
    private Button updateTransTimeInfo;

    ObservableList<TransportationTime> transList = FXCollections.observableArrayList();

    Statement statement = null;
    TransportationTime transportationTime = null;


    private void loadData() throws SQLException {
        Connection connection = MySQLConnect.connectDb();
        statement = connection.createStatement();
        TID.setCellValueFactory(new PropertyValueFactory<>("TID"));
        Hour.setCellValueFactory(new PropertyValueFactory<>("Thour"));
        Day.setCellValueFactory(new PropertyValueFactory<>("Tday"));
        Semester.setCellValueFactory(new PropertyValueFactory<>("Semester"));
        Year.setCellValueFactory(new PropertyValueFactory<>("Tyear"));
        refreshTable();
    }

    @FXML
    void AddWindow(MouseEvent event) throws IOException {
        main.openScene("AddNewTransportationTime.fxml");
    }

    @FXML
    void DeleteTID(MouseEvent event) throws SQLException {
        String query = "DELETE FROM transportation_time WHERE TID="+ DeleteTID.getText()+";";
        int rs = statement.executeUpdate(query);
        if(rs!=0){
            JOptionPane.showInternalMessageDialog(null, "Delete operation has done successfully!");
        }else
            JOptionPane.showInternalMessageDialog(null, "Please Enter a valid TID!");
        DeleteTID.clear();
    }

    @FXML
    void searchForTrans(MouseEvent event) {

    }

    @FXML
    void updateWindow(MouseEvent event) throws IOException {
        main.openScene("UpdateTransportationTime.fxml");
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
        String query = "Select * from transportation_time;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            transList.add(new TransportationTime(rs.getInt("TID"),
                    rs.getInt("Thour"),
                    rs.getString("Tday"),
                    rs.getInt("semester"),
                    rs.getInt("Tyear")));
        }
        transportationTimes.setItems(transList);
    }
}
