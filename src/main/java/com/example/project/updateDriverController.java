package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class updateDriverController {

    @FXML
    private DatePicker EExpiryDate;

    @FXML
    private TextField UCarDrivingL;

    @FXML
    private DatePicker UCarExpiryDate;

    @FXML
    private TextField UColor;

    @FXML
    private TextField UDID;

    @FXML
    private TextField UDrivingL;

    @FXML
    private TextField UEmail;

    @FXML
    private TextField UInsurnce;

    @FXML
    private TextField UName;

    @FXML
    private TextField UNumberOfPassengers;

    @FXML
    private TextField UPID;

    @FXML
    private TextField UPhoneNumber;

    @FXML
    private TextField UType;

    @FXML
    private Label message;

    @FXML
    private Button update;

    @FXML
    private Button get;

    @FXML
    void UpdateValues(MouseEvent event) {
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String query = "UPDATE drivers SET PID=" + UPID.getText() +
                    ", Dname=" +"\""+ UName.getText() +"\"" + ", Phone_number=" + UPhoneNumber.getText() +
                    ", Email=" +"\"" + UEmail.getText() +"\"" + ", Driving_license=" + UDrivingL.getText() +
                    ", expiry_date=" +"\""+ EExpiryDate.getValue()  +"\""+ " WHERE DID=" + UDID.getText();

            int rs = statement.executeUpdate(query);

            String que = "UPDATE cars SET Driving_license=" + UCarDrivingL.getText() +
                    ", Insurance=" +"\"" + UInsurnce.getText() +"\""+ ", Number_Of_Passenger=" +
                    UNumberOfPassengers.getText() + ", Color=" +"\"" + UColor.getText() +"\""+
                    ", Ctype=" +"\""+ UType.getText() +"\""+ ", expiry_date=" +"\""+
                    UCarExpiryDate.getValue() +"\""+ " WHERE Drivers_DID=" + UDID.getText() ;


            int ss = statement.executeUpdate(que);
            UCarDrivingL.clear();
            UInsurnce.clear();
            UNumberOfPassengers.clear();
            UColor.clear();
            UType.clear();
            UCarExpiryDate.setValue(null);
            UDID.clear();
            UPID.clear();
            UName.clear();
            UPhoneNumber.clear();
            UEmail.clear();
            UDrivingL.clear();
            EExpiryDate.setValue(null);

            message.setText("Updating Operation has done successfully!");
        }catch (Exception e){
            message.setText("Something went Wrong!");
        }
    }

    @FXML
    void bringData(MouseEvent event){
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Drivers WHERE DID=" + UDID.getText() + ";";

            ResultSet bring = statement.executeQuery(query);
            while (bring.next()){
                UPID.setText(bring.getString("PID"));
                UName.setText(bring.getString("Dname"));
                UPhoneNumber.setText(bring.getString("Phone_number"));
                UEmail.setText(bring.getString("Email"));
                UDrivingL.setText(bring.getString("Driving_license"));
                EExpiryDate.setValue(LocalDate.parse(bring.getString("expiry_date")));
            }

            String que = "SELECT * FROM Cars WHERE Drivers_DID=" + UDID.getText() + ";";
            System.out.println(que);
            bring = statement.executeQuery(que);
            while (bring.next()){
                UCarDrivingL.setText(bring.getString("Driving_license"));
                UInsurnce.setText(bring.getString("Insurance"));
                UNumberOfPassengers.setText(bring.getString("Number_Of_Passenger"));
                UColor.setText(bring.getString("Color"));
                UType.setText(bring.getString("Ctype"));
                UCarExpiryDate.setValue(LocalDate.parse(bring.getString("expiry_date")));
            }
        }catch (Exception e){
            e.printStackTrace();
            message.setText("No Data!");
        }
    }
}
