package com.example.project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class registrationController implements Initializable {

    @FXML
    private ComboBox<String> Day;

    @FXML
    private TextField Hour;

    @FXML
    private Label InsertionDone;

    @FXML
    private TextField Sem;

    @FXML
    private TextField Year;

    @FXML
    private TextField chosenStudent;

    int SID;
    String name;
    int flag = 0;
    HelloApplication main = new HelloApplication();

    @FXML
    void chooseStudent() throws IOException {
        flag = 1;
        InsertionDone.setText("");
        main.openScene("ChooseStudent.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Day.getItems().addAll(FXCollections.observableArrayList("Saturday","Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
    }

    @FXML
    public void setLabel(){
        if(flag == 1) {
            SID = ChooseStudentController.SelectedSID;
            name = ChooseStudentController.SelectedName;
            if (name != null) {
                chosenStudent.setText(name);
            }
        }
    }

    @FXML
    void Delete() {
        try {
            int TID;
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            int h = Integer.parseInt(Hour.getText());
            String d = Day.getValue();
            int sem = Integer.parseInt(Sem.getText());
            int y = Integer.parseInt(Year.getText());
            String query = "Select * from transportation_time where Tday=" + "\"" + d + "\" "
                    + "and Semester=" + sem + " and Tyear=" + y + " and Thour=" + h;
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()) {
                TID = Integer.parseInt(rs.getString("TID"));
                String query2 = "DELETE FROM register WHERE Student_SID=" + SID + " and Transportation_time_TID= " + TID;
                Statement statement2 = connection.createStatement();
                int rs1 = statement2.executeUpdate(query2);
                if (rs1 != 0){
                    InsertionDone.setText("Deleting operation has done successfully!");
                }
                Hour.clear();
                Year.clear();
                Day.getSelectionModel().clearSelection();
                Sem.clear();
                chosenStudent.clear();
                flag=0;
            } else {
                JOptionPane.showInternalMessageDialog(null, "The values you entered is not in the system");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
            InsertionDone.setText("\tSomething Went Wrong!");
            Hour.clear();
            Year.clear();
            Day.getSelectionModel().clearSelection();
            Sem.clear();
            chosenStudent.clear();
            flag=0;
        }
    }

    @FXML
    void Register() {
        try {
            int TID;
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            int h = Integer.parseInt(Hour.getText());
            String d = Day.getValue();
            int sem = Integer.parseInt(Sem.getText());
            int y = Integer.parseInt(Year.getText());
            String query = "Select * from transportation_time where Tday=" + "\"" + d + "\" "
                    + "and Semester=" + sem + " and Tyear=" + y + " and Thour=" + h;
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()) {
                TID = Integer.parseInt(rs.getString("TID"));
            } else {
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
                String query3 = "INSERT INTO transportation_time Values ("+newTID+","
                        + h + "," + "\"" + d + "\""+ "," + sem +
                        "," + y + ")";
                Statement statement3 = connection2.createStatement();
                statement3.executeUpdate(query3);
            }
            Statement statement4 = connection.createStatement();
            String insertion = "INSERT INTO register Values ("+ SID +"," + TID + ")";
            int rs1 = statement4.executeUpdate(insertion);
            if (rs1 != 0){
                InsertionDone.setText("Insertion operation has done successfully!");
            }
            /*Hour.clear();
            Year.clear();
            Day.getSelectionModel().clearSelection();
            Sem.clear();
            chosenStudent.clear();
            flag=0;*/
        }catch (Exception e){
            InsertionDone.setText("Something Went Wrong!");
        /*    Hour.clear();
            Year.clear();
            Day.getSelectionModel().clearSelection();
            Sem.clear();
            chosenStudent.clear();
            flag=0; */
        }
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

