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
import models.DriversInfo;
import models.Route;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ChooseRouteController implements Initializable {

    @FXML
    private TableColumn<Route, Integer> Distance;

    @FXML
    private TableColumn<Route, String> Name;

    @FXML
    private Button RChoose;

    @FXML
    private TableColumn<Route, Integer> RID;

    @FXML
    private TableView<Route> table;

    ObservableList<Route> routesList = FXCollections.observableArrayList();

    public static int SelectedRID;
    public static String SelectedName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            RID.setCellValueFactory(new PropertyValueFactory<>("RID"));
            Name.setCellValueFactory(new PropertyValueFactory<>("Rname"));
            Distance.setCellValueFactory(new PropertyValueFactory<>("Distance"));
            routesList.clear();
            String query = "SELECT * FROM Routes WHERE RID=" + RID.getText();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                routesList.add(new Route(rs.getInt("RID"),
                        rs.getString("Rname"),rs.getInt("Distance")));
            }
            table.setItems(routesList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void chooseRoute() {
        Route route = table.getSelectionModel().getSelectedItem();
        SelectedRID = route.getRID();
        SelectedName = route.getRname();
        Stage stage = (Stage) RChoose.getScene().getWindow();
        stage.close();
    }

}
