package com.example.project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class addTransTimeController implements Initializable {

    @FXML
    private Label InsertionDone;

    @FXML
    private ComboBox<String> NDay;

    @FXML
    private TextField NHour;

    @FXML
    private TextField NSem;

    @FXML
    private TextField NYear;

    @FXML
    void AddNewTransportationTime() {
        int h = Integer.parseInt(NHour.getText());
        String d = NDay.getValue();
        int sem = Integer.parseInt(NSem.getText());
        int y = Integer.parseInt(NYear.getText());
        int TID = 0;
        try {
            // generate new TID
            int newTID = getDayNumber(d)*10 + h;
            Connection connection2 = MySQLConnect.connectDb();
            while (true){
                Statement statement2 = connection2.createStatement();
                String searchForTIDQuery = "SELECT * FROM transportation_time WHERE TID=" + newTID;
                ResultSet rs2 = statement2.executeQuery(searchForTIDQuery);
                if (rs2.next())
                    newTID *= 10;
                else{
                    TID = newTID;
                    break;
                }
            }
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO transportation_time Values ("+TID+","
                    + h + "," + "\"" + d + "\""+ "," + sem +
                    "," + y + ")";
            int rs = statement.executeUpdate(query);
            if(rs!=0) {
                InsertionDone.setText("Insertion operation has done successfully!");
                NYear.clear();
                NDay.getSelectionModel().clearSelection();
                NSem.clear();
                NHour.clear();
            }
        }catch (Exception e){
            NYear.clear();
            NDay.getSelectionModel().clearSelection();
            NSem.clear();
            NHour.clear();
            InsertionDone.setText("Something Went Wrong!");
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NDay.getItems().addAll(FXCollections.observableArrayList("Saturday","Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
    }

    private int getDayNumber(String day) {
        return switch (day) {
            case "Saturday" -> 0;
            case "Sunday" -> 1;
            case "Monday" -> 2;
            case "Tuesday" -> 3;
            case "Wednesday" -> 4;
            case "Thursday" -> 5;
            case "Friday" -> 6;
            default -> 7;
        };
    }
}
