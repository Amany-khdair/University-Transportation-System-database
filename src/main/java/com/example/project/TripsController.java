package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TripsController {

    @FXML
    private Button chooseDriver;

    @FXML
    private Button chooseRoute;

    @FXML
    private Button chooseTrans;

    @FXML
    private  Label chosenDriver;

    @FXML
    private Label chosenRoute;

    @FXML
    private Label InsertionDone;

    @FXML
    private Label chosenTrans;

    @FXML
    private Button delete;

    @FXML
    private Button register;

    int DID;
    int RID;
    int Thour;
    String Dname;
    String Rname;
    String Tday;

    HelloApplication main = new HelloApplication();
    Statement statement = null;
    int flag1=0, flag2=0, flag3=0;
    @FXML
    void chooseDriver() throws IOException {
        flag1 = 1;
        InsertionDone.setText("");
        main.openScene("ChooseDriver.fxml");
    }

    @FXML
    void chooseRoute() throws IOException {
        flag2 = 1;
        InsertionDone.setText("");
        main.openScene("ChooseRoute.fxml");
    }

    @FXML
    void chooseTransTime() throws IOException {
        flag3 = 1;
        InsertionDone.setText("");
        main.openScene("ChooseTransTime.fxml");
    }

    @FXML
    public void setLabel(){
        if(flag1==1){
            DID = ChooseDriverController.SelectedDID;
            Dname = ChooseDriverController.SelectedName;
            if (Dname != null) {
                chosenDriver.setText(Dname);
            }
        }
        if(flag2==1){
            RID = ChooseRouteController.SelectedRID;
            Rname = ChooseRouteController.SelectedName;
            if (Rname != null) {
                chosenRoute.setText(Rname);
            }
        }
        if(flag3 == 1){
            Tday = ChooseTransTimeController.SelectedTday;
            Thour = ChooseTransTimeController.SelectedThour;
            if (Tday != null) {
                chosenTrans.setText(Tday +", "+ Thour);
            }
        }

    }

    @FXML
    void AddNewTrip(MouseEvent event) {
        try {
            Connection connection = MySQLConnect.connectDb();
            statement = connection.createStatement();


            String query = "INSERT INTO Responsible (Drivers_DID,RID,Transportation_time_TID) Values (" + DID + ","
                    + RID + "," + "(SELECT TID FROM Transportation_time WHERE Thour=" + Thour + " and Tday=" + "\"" +
                    Tday + "\"" + "));";

            int rs = statement.executeUpdate(query);
            if (rs != 0) {
                chosenDriver.setText("");
                chosenRoute.setText("");
                chosenTrans.setText("");
              /*  flag1=0;
                flag2=0;
                flag3=0; */
            }

        } catch (Exception e) {
            e.getStackTrace();
            chosenDriver.setText("");
            chosenRoute.setText("");
            chosenTrans.setText("");
            InsertionDone.setText("Something Went Wrong!");

        }
    }

    @FXML
    void Delete(MouseEvent event) throws SQLException {
        Connection connection = MySQLConnect.connectDb();
        Statement statement1 = connection.createStatement();
        String query = "DELETE FROM Responsible WHERE Drivers_DID="+DID+
                " and (SELECT TID FROM Transportation_time WHERE Thour=" +Thour + " and Tday=" +
                "\"" + Tday + "\"" + ") and RID=" + RID +";";
        int rs = statement1.executeUpdate(query);
        if(rs!=0){
            JOptionPane.showInternalMessageDialog(null, "Delete operation has done successfully!");
        }else
            JOptionPane.showInternalMessageDialog(null, "Please Enter a proportional values!");
    }
}
