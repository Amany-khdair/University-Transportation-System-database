package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.Statement;

public class addNewDriverController {

    @FXML
    private Button Add;

    @FXML
    private Label InsertionDone;

    @FXML
    private TextField NCarDrivingL;

    @FXML
    private DatePicker NCarExpiryDate;

    @FXML
    private TextField NColor;

    @FXML
    private TextField NDrivingL;

    @FXML
    private TextField NEmail;

    @FXML
    private DatePicker NExpiryDate;

    @FXML
    private TextField NInsurance;

    @FXML
    private TextField NName;

    @FXML
    private TextField NPID;

    @FXML
    private TextField NPassengers;

    @FXML
    private TextField NPhoneNumber;

    @FXML
    private TextField NType;

    @FXML
    void AddNewDriver(MouseEvent event) {
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();


            String query = "INSERT INTO drivers (PID,Dname,Driving_license,Phone_number,Email,expiry_date) Values ("+NPID.getText()+","
                    + "\"" + NName.getText() + "\"" + "," + NDrivingL.getText() + "," + NPhoneNumber.getText() + ","
                    +"\""+ NEmail.getText() + "\"" +  "," + "\""+ NExpiryDate.getValue() +"\"" + ");";


            String que = "INSERT INTO cars (Drivers_DID,Ctype,color,Number_Of_Passenger,Insurance,expiry_date) Values " +
                    "((SELECT DID FROM Drivers WHERE PID ="+ NPID.getText()+ " and Dname="+ "\"" +NName.getText() +"\""+
                    " and Driving_license ="+ NDrivingL.getText()+ " and Phone_number="+ NPhoneNumber.getText() +
                    " and Email="+"\"" +NEmail.getText() +"\""+ " and expiry_date=" +"\"" + NExpiryDate.getValue()+ "\""
                    + "),"+" +\""+NType.getText()+ "\""+"," + "\"" +NColor.getText() + "\""+ "," + NPassengers.getText() +
                    "," + "\""+ NInsurance.getText() +"\"" +  ","+ "\"" + NCarExpiryDate.getValue() +"\""+ ");";

            System.out.println(query);

            int rs = statement.executeUpdate(query);
            if(rs!=0) {
                NPID.clear();
                NName.clear();
                NPhoneNumber.clear();
                NEmail.clear();
                NDrivingL.clear();
                NExpiryDate.setValue(null);
            }

            System.out.println(que);

            int ss = statement.executeUpdate(que);
            if(ss!=0) {
                InsertionDone.setText("Insertion operation has done successfully!");
                NType.clear();
                NColor.clear();
                NPassengers.clear();
                NInsurance.clear();
                NCarExpiryDate.setValue(null);
            }

        }catch (Exception e){
            e.getStackTrace();
            NPID.clear();
            NName.clear();
            NPhoneNumber.clear();
            NEmail.clear();
            NDrivingL.clear();
            NExpiryDate.setValue(null);
            NType.clear();
            NColor.clear();
            NPassengers.clear();
            NInsurance.clear();
            NCarExpiryDate.setValue(null);
            InsertionDone.setText("Something Went Wrong!");

        }

    }

}
