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
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class addRouteController implements Initializable {

    @FXML
    private TextField NDiscr;

    @FXML
    private TextField NDistance;

    @FXML
    private TextField NName;


    @FXML
    private ComboBox<String> NSafety;

    @FXML
    private Button add;

    @FXML
    private Label InsertionDone;

    @FXML
    void AddNewRoute() {
        try {
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            int safe;
            if(Objects.equals(NSafety.getValue(), "Safe"))
                safe = 1;
            else
                safe = 0;
            double t = Integer.parseInt(NDistance.getText()) / 0.8333;
            int time = (int) t;

            String query = "INSERT INTO routes(Rname, Distance, Details, Safety, Rtime) Values (\""+NName.getText()+"\""
                    +"," + NDistance.getText() + "," + "\"" + NDiscr.getText() + "\""+ "," + safe
                    + ", "+ time + ")";
            int rs = statement.executeUpdate(query);
            if(rs!=0) {
                InsertionDone.setText("Insertion operation has done successfully!");
                NName.clear();
                NDiscr.clear();
                NSafety.getSelectionModel().clearSelection();
                NDistance.clear();
            }
        }catch (Exception e){
            NName.clear();
            NDiscr.clear();
            NSafety.getSelectionModel().clearSelection();
            NDistance.clear();
            InsertionDone.setText("Something Went Wrong!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NSafety.getItems().addAll(FXCollections.observableArrayList("Safe","Unsafe"));
    }


}
