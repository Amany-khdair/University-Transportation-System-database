package com.example.project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.control.Label;

public class UpdateStudentController implements Initializable {

    @FXML
    private TextField UAddress;

    @FXML
    private DatePicker UBirthDate;

    @FXML
    private TextField UEmail;

    @FXML
    private ComboBox<String> UGender;

    @FXML
    private TextField UMajor;

    @FXML
    private TextField UName;

    @FXML
    private TextField UPID;

    @FXML
    private TextField UPhoneNumber;

    @FXML
    private TextField USID;

    @FXML
    private ComboBox<String> UState;

    @FXML
    private Label message;

    @FXML
    void UpdateStudent(MouseEvent event) {

        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String bringData = "SELECT * FROM student WHERE SID=" + USID.getText();
            int StillStudy;
            if(Objects.equals(UState.getValue(), "Still Study"))
                StillStudy = 1;
            else
                StillStudy = 0;
            String query = "UPDATE student SET PID=" + UPID.getText() +
                    ", Sname= \"" + UName.getText() + "\""+ ", Phone_number=" + UPhoneNumber.getText() +
                    ", Email= \"" + UEmail.getText() +"\", Major= \"" + UMajor.getText() + "\", Address= \"" + UAddress.getText() + "\", Gender= \"" + UGender.getValue() + "\", Still_study=" + StillStudy +" WHERE SID=" + USID.getText();

            int rs = statement.executeUpdate(query);
            USID.clear();
            UPID.clear();
            UName.clear();
            UEmail.clear();
            UMajor.clear();
            UAddress.clear();
            UPhoneNumber.clear();
            UGender.getSelectionModel().clearSelection();
            UState.getSelectionModel().clearSelection();
            if(rs!=0){
                message.setText("Updating Operation has done successfully!");
            }else
                message.setText("Something went Wrong!");
            message.setText("Updating Operation has done successfully!");

        }catch (Exception e){
            USID.clear();
            UPID.clear();
            UName.clear();
            UPhoneNumber.clear();
            UEmail.clear();
            UMajor.clear();
            UAddress.clear();
            UGender.getSelectionModel().clearSelection();
            UState.getSelectionModel().clearSelection();
            message.setText("Something went Wrong!");
            e.getCause();
            e.printStackTrace();
        }
    }

    @FXML
    void bringData(MouseEvent event) {

        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM student WHERE SID=" + USID.getText();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                UPID.setText(rs.getString("PID"));
                UName.setText(rs.getString("Sname"));
                UPhoneNumber.setText(rs.getString("Phone_number"));
                UEmail.setText(rs.getString("Email"));
                UMajor.setText(rs.getString("Major"));
                UAddress.setText(rs.getString("Address"));
                UGender.setValue(rs.getString("Gender"));
                if(rs.getInt("Still_study") == 1){
                    UState.setValue("Still Study");
                }else{
                    UState.setValue("Graduated");
                }
            }
        }catch (Exception e){
            message.setText("Please Enter a Valid Value!");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UState.getItems().addAll(FXCollections.observableArrayList("Still Study","Graduated"));
        UGender.getItems().addAll(FXCollections.observableArrayList("Female","Male"));
    }

}

