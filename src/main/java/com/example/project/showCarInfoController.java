package com.example.project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class showCarInfoController implements Initializable{

    @FXML
    private Label Color;

    @FXML
    private Label ExpiryDate;

    @FXML
    private Label Insurance;

    @FXML
    private Label Type;

    @FXML
    private Label carDrivingLicense;

    @FXML
    private Label passengers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Cars WHERE Driving_license=" + DriverInfoController.showCarID;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                carDrivingLicense.setText(rs.getString("Driving_license"));
                Insurance.setText(rs.getString("Insurance"));
                passengers.setText(rs.getString("Number_Of_Passenger"));
                Color.setText(rs.getString("Color"));
                Type.setText(rs.getString("Ctype"));
                ExpiryDate.setText(rs.getString("expiry_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
