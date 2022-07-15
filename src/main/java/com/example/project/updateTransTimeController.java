package com.example.project;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class updateTransTimeController implements Initializable {

    @FXML
    private ComboBox<String> UDay;

    @FXML
    private TextField UHour;

    @FXML
    private TextField USemester;

    @FXML
    private TextField UYear;

    @FXML
    private Button update;

    @FXML
    private Button select;


    @FXML
    private TextField updatedTID;

    @FXML
    private Label message;



    @FXML
    void UpdateValues(MouseEvent event){
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String query = "UPDATE transportation_time SET Thour=" + UHour.getText() +
                    ", Tday=" + "\""+ UDay.getValue() + "\""+ ", Semester=" + USemester.getText() +
                    ", Tyear=" + UYear.getText() + " WHERE TID=" + updatedTID.getText();
            int rs = statement.executeUpdate(query);
            updatedTID.clear();
            UHour.clear();
            UDay.getSelectionModel().clearSelection();;
            UYear.clear();
            USemester.clear();
            if(rs!=0)
                message.setText("Updating Operation has done successfully!");
            else
                message.setText("Something went Wrong!");
        }catch (Exception e){
            message.setText("Something went Wrong!");
            updatedTID.clear();
            UHour.clear();
            UDay.getSelectionModel().clearSelection();
            UYear.clear();
            USemester.clear();
        }
    }


    @FXML
    void bringData(MouseEvent event){
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM transportation_time WHERE TID=" + updatedTID.getText();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                UHour.setText(rs.getString("Thour"));
                UDay.setValue(rs.getString("Tday"));
                USemester.setText(rs.getString("Semester"));
                UYear.setText(rs.getString("Tyear").split("-")[0]);
            }
        }catch (Exception e){
            message.setText("Please Enter a Valid Value!");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UDay.getItems().addAll(FXCollections.observableArrayList("Saturday","Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
    }
}
