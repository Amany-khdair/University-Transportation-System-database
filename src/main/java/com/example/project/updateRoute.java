package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class updateRoute implements Initializable {

    @FXML
    private ComboBox<String> Safety;

    @FXML
    private TextField UName;

    @FXML
    private TextField UDiscription;

    @FXML
    private TextField UDistance;

    @FXML
    private TextField URID;

    @FXML
    private Label message;

    @FXML
    private Button select;

    @FXML
    private Button update;

    @FXML
    void UpdateValues(MouseEvent event) {
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            double t = Integer.parseInt(UDistance.getText()) / 0.8333;
            int newTime = (int) t;
            int safe;
            if(Objects.equals(Safety.getValue(), "Safe"))
                safe = 1;
            else
                safe = 0;
            String query = "UPDATE routes SET Rname=\"" + UName.getText() +
                    "\", Details=" + "\""+ UDiscription.getText() + "\""+ ", Distance=" + UDistance.getText() +
                    ", Rtime=" + newTime + ", Safety="+ safe + " WHERE RID=" + URID.getText();
            int rs = statement.executeUpdate(query);
            URID.clear();
            UDistance.clear();
            UDiscription.clear();
            UName.clear();
            Safety.getSelectionModel().clearSelection();
            if(rs!=0){
                message.setText("Updating Operation has done successfully!");
            }else
                message.setText("Something went Wrong!");
        }catch (Exception e){
            URID.clear();
            UDistance.clear();
            UDiscription.clear();
            UName.clear();
            Safety.getSelectionModel().clearSelection();
            message.setText("Something went Wrong!");
        }
    }

    @FXML
    void bringData(MouseEvent event) {
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM routes WHERE RID=" + URID.getText();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                UName.setText(rs.getString("Rname"));
                UDiscription.setText(rs.getString("Details"));
                UDistance.setText(rs.getString("Distance"));
                if(rs.getInt("Safety") == 1){
                    Safety.setValue("Safe");
                }else{
                    Safety.setValue("Unsafe");
                }
            }
        }catch (Exception e){
            message.setText("Please Enter a Valid Value!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Safety.getItems().addAll(FXCollections.observableArrayList("Safe","Unsafe"));
    }

}
