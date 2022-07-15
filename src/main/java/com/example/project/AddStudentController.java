package com.example.project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {

    @FXML
    private Label InsertionDone;

    @FXML
    private TextField NAddress;

    @FXML
    private TextField NEmail;

    @FXML
    private ComboBox<String> NGender;

    @FXML
    private TextField NMajor;

    @FXML
    private TextField NName;

    @FXML
    private TextField NPID;

    @FXML
    private TextField NPhoneNumber;

    @FXML
    private ComboBox<String> NState;

    @FXML
    private Button add;

    @FXML
    void AddStudent() {
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            int study;
            if(Objects.equals(NState.getValue(), "Still Study"))
                study = 1;
            else
                study = 0;

            String query = "INSERT INTO student(PID, Sname, Phone_number, Email, Major, Address, Gender, Still_study)"
                    + " Values ("+NPID.getText()+ "," +
                    "\"" + NName.getText() + "\"" + "," + NPhoneNumber.getText() + ", \"" + NEmail.getText() + "\" ," +
                    "\"" + NMajor.getText() + "\"," + "\"" + NAddress.getText() + "\"," + "\"" + NGender.getValue() + "\"," +
                    + study + ")";

            int rs = statement.executeUpdate(query);
            if(rs!=0) {
                InsertionDone.setText("Insertion operation has done successfully!");
                NPID.clear();
                NName.clear();
                NPhoneNumber.clear();
                NEmail.clear();
                NMajor.clear();
                NAddress.clear();
                NGender.getSelectionModel().clearSelection();
                NState.getSelectionModel().clearSelection();

            }
        }catch (Exception e){
            NPID.clear();
            NName.clear();
            NPhoneNumber.clear();
            NEmail.clear();
            NMajor.clear();
            NAddress.clear();
            NGender.getSelectionModel().clearSelection();
            NState.getSelectionModel().clearSelection();
            InsertionDone.setText("Something Went Wrong!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NState.getItems().addAll(FXCollections.observableArrayList("Still Study","Graduated"));
        NGender.getItems().addAll(FXCollections.observableArrayList("Female","Male"));
    }




}
