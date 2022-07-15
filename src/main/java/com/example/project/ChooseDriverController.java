package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import models.DriversInfo;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ChooseDriverController implements Initializable {

    @FXML
    private Button DChoose;

    @FXML
    private TableColumn<DriversInfo, Integer> DID;

    @FXML
    private TableColumn<DriversInfo, String> Dname;

    @FXML
    private TableView<DriversInfo> table;

    ObservableList<DriversInfo> driversList = FXCollections.observableArrayList();

    public static int SelectedDID;
    public static String SelectedName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            DID.setCellValueFactory(new PropertyValueFactory<>("DID"));
            Dname.setCellValueFactory(new PropertyValueFactory<>("Dname"));
            driversList.clear();
            String query = "SELECT * FROM drivers WHERE DID=" + DID.getText();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                driversList.add(new DriversInfo(rs.getInt("DID"),
                        rs.getString("Dname")));
            }
            table.setItems(driversList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void chooseDriver() {
        DriversInfo driver = table.getSelectionModel().getSelectedItem();
        SelectedDID = driver.getDID();
        SelectedName = driver.getDname();
        Stage stage = (Stage) DChoose.getScene().getWindow();
        stage.close();
    }
}
