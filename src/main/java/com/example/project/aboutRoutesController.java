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
import models.Route;
import models.TransportationTime;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class aboutRoutesController implements Initializable {

    HelloApplication main = new HelloApplication();

    @FXML
    private TableColumn<Route, String> Description;

    @FXML
    private TableColumn<Route, Integer> Distance;

    @FXML
    private TableColumn<Route, String> Name;

    @FXML
    private TableColumn<Route, Integer> RID;

    @FXML
    private TableColumn<Route, Boolean> Safety;

    @FXML
    private TableColumn<Route, Integer> Time;

    @FXML
    private Button addRoute;

    @FXML
    private Button delete;

    @FXML
    private TextField deleteRID;

    @FXML
    private Button refresh;

    @FXML
    private TableView<Route> routes;

    @FXML
    private Button search;

    @FXML
    private TextField searchFor;

    @FXML
    private ComboBox<?> specifyField;

    @FXML
    private Button updateRoute;

    ObservableList<Route> routesList = FXCollections.observableArrayList();

    Statement statement = null;
    TransportationTime transportationTime = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws SQLException {
        Connection connection = MySQLConnect.connectDb();
        statement = connection.createStatement();
        RID.setCellValueFactory(new PropertyValueFactory<>("RID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Rname"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Details"));
        Distance.setCellValueFactory(new PropertyValueFactory<>("Distance"));
        Time.setCellValueFactory(new PropertyValueFactory<>("Rtime"));
        Safety.setCellValueFactory(new PropertyValueFactory<>("Safety"));
        refreshTable();
    }

    @FXML
    void refreshTable() throws SQLException {
        routesList.clear();
        String query = "Select * from routes;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            routesList.add(new Route(rs.getInt("RID"),
                    rs.getString("Rname"),
                    rs.getInt("Distance"),
                    rs.getString("Details"),
                    rs.getBoolean("Safety"),
                    rs.getInt("Rtime")));
        }
        routes.setItems(routesList);
    }

    @FXML
    void DeleteRID(MouseEvent event) throws SQLException {
        String query = "DELETE FROM routes WHERE RID="+ deleteRID.getText();
        int rs = statement.executeUpdate(query);
        if(rs!=0){
            JOptionPane.showInternalMessageDialog(null, "Delete operation has done successfully!");
        }else
            JOptionPane.showInternalMessageDialog(null, "Please Enter a valid TID!");
        deleteRID.clear();
    }

    @FXML
    void updateWindow(MouseEvent event) throws IOException {
        main.openScene("UpdateRoute.fxml");
    }

    @FXML
    void AddWindow(MouseEvent event) throws IOException {
        main.openScene("AddNewRoute.fxml");
    }

    @FXML
    void searchForRoute(MouseEvent event) {

    }

}
