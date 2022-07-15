package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Student;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ChooseStudentController implements Initializable {

    @FXML
    private Button SChoose;

    @FXML
    private TableColumn<Student, Integer> SID;

    @FXML
    private TableColumn<Student, String> SName;

    @FXML
    private TableView<Student> table;

    ObservableList<Student> studentsList = FXCollections.observableArrayList();

    public static int SelectedSID;
    public static String SelectedName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Connection connection = MySQLConnect.connectDb();
            Statement statement = connection.createStatement();
            SID.setCellValueFactory(new PropertyValueFactory<>("SID"));
            SName.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            studentsList.clear();
            String query = "Select * from student where Still_study=1;";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                studentsList.add(new Student(rs.getInt("SID"),
                        rs.getString("Sname")));
            }
            table.setItems(studentsList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void chooseStudent() {
        Student student = table.getSelectionModel().getSelectedItem();
        SelectedSID = student.getSID();
        SelectedName = student.getSname();
        Stage stage = (Stage) SChoose.getScene().getWindow();
        stage.close();
    }

}
