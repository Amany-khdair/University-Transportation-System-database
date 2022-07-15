package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.TransportationTime;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ChooseTransTimeController implements Initializable {

    @FXML
    private Button TChoose;

    @FXML
    private TableColumn<TransportationTime, String> Tday;

    @FXML
    private TableColumn<TransportationTime, Integer> Thour;

    @FXML
    private TableView<TransportationTime> table;

    ObservableList<TransportationTime> transList = FXCollections.observableArrayList();

    public static int SelectedThour;
    public static String SelectedTday;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            Tday.setCellValueFactory(new PropertyValueFactory<>("Tday"));
            Thour.setCellValueFactory(new PropertyValueFactory<>("Thour"));
            transList.clear();
            String query = "SELECT * FROM Transportation_time";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                transList.add(new TransportationTime(rs.getString("TDay"),
                        rs.getInt("Thour")));
            }
            table.setItems(transList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void chooseTransTime() {
        TransportationTime trnsTime = table.getSelectionModel().getSelectedItem();
        SelectedThour = trnsTime.getThour();
        SelectedTday = trnsTime.getTday();
        Stage stage = (Stage) TChoose.getScene().getWindow();
        stage.close();
    }

}
