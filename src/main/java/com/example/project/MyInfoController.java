package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MyInfoController implements Initializable {

    @FXML
    private Label Dlicense;

    @FXML
    private Label ExpiryDate;

    @FXML
    private Label PID;

    @FXML
    private Label Type;

    @FXML
    private Label carDLicense;

    @FXML
    private Label carExpiryDate;

    @FXML
    private Label color;

    @FXML
    private Label email;

    @FXML
    private Label insurance;

    @FXML
    private Label name;

    @FXML
    private Label numberOfPassengers;

    @FXML
    private Label phoneNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Drivers WHERE DID=" + startController.DID;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                name.setText(rs.getString("Dname"));
                email.setText(rs.getString("Email"));
                PID.setText(rs.getString("PID"));
                Dlicense.setText(rs.getString("Driving_license"));
                phoneNumber.setText(rs.getString("Phone_number"));
                ExpiryDate.setText(rs.getString("expiry_date"));
            }
            String que = "SELECT * FROM Cars WHERE Drivers_DID=" + startController.DID;
            ResultSet rrs = statement.executeQuery(que);
            while (rrs.next()){
                Type.setText(rrs.getString("Ctype"));
                numberOfPassengers.setText(rrs.getString("Number_Of_Passenger"));
                carDLicense.setText(rrs.getString("Driving_license"));
                color.setText(rrs.getString("Color"));
                insurance.setText(rrs.getString("Insurance"));
                carExpiryDate.setText(rrs.getString("expiry_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
