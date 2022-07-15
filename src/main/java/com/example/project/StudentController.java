package com.example.project;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Student;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    HelloApplication main = new HelloApplication();

    @FXML
    private TableColumn<Student, String> Address;

    @FXML
    private TableColumn<Student, String> Email;

    @FXML
    private TableColumn<Student, String> Gender;

    @FXML
    private TableColumn<Student, String> Major;

    @FXML
    private TableColumn<Student, String> Name;

    @FXML
    private TableColumn<Student, Integer> PID;

    @FXML
    private TableColumn<Student, Integer> PhoneNumber;

    @FXML
    private TableColumn<Student, Integer> SID;

    @FXML
    private TextField SIDDelete;
/*
    @FXML
    private TextField searchFor;


     @FXML
     private ComboBox<String> specifyField;
    */

    @FXML
    private TableColumn<Student, Boolean> state;

    @FXML
    private TableView<Student> students;

    @FXML
    void deleteStudent() {

    }

    @FXML
    ChoiceBox<Object> searchBox = new ChoiceBox<>();

    ArrayList<String> searchA = new ArrayList<>();

    @FXML
    void searchStudent() {

    }


    ObservableList<Student> student = FXCollections.observableArrayList();

    Statement statement = null;

    String sName;

    private void loadData() throws SQLException {
        Connection connection = MySQLConnect.connectDb();
        statement = connection.createStatement();

        SID.setCellValueFactory(new PropertyValueFactory<>("SID"));
        PID.setCellValueFactory(new PropertyValueFactory<>("PID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Sname"));
        PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone_number"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Major.setCellValueFactory(new PropertyValueFactory<>("Major"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        state.setCellValueFactory(new PropertyValueFactory<>("Still_study"));

        Connection con = MySQLConnect.connectDb();
        statement = con.createStatement();

        PreparedStatement ps10 = con.prepareStatement("select Sname from student;");
        ResultSet rs10 = ps10.executeQuery();

        searchA.clear();

        while (rs10.next()) {
            searchA.add(rs10.getString(1));
            searchBox.getItems().add(rs10.getString(1));
        }

        searchBox.getSelectionModel().selectedIndexProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> sName = searchA.get(new_val.intValue()));

        searchBox = new ChoiceBox<>(FXCollections.observableArrayList(searchA));


        refreshTable();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void refreshTable() throws SQLException {
        student.clear();
        String query = "Select * from student;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            student.add(new Student (rs.getInt("SID"),
                    rs.getInt("PID"),
                    rs.getString("Sname"),
                    rs.getInt("Phone_number"),
                    rs.getString("Email"),
                    rs.getString("Major"),
                    rs.getString("Address"),
                    rs.getString("Gender"),
                    rs.getBoolean("Still_study")));

            students.setItems(student);
        }

    }
    @FXML
    void AddWindow() throws IOException {
        main.openScene("AddNewStudent.fxml");
    }

    @FXML
    void DeleteSID() throws SQLException {
        String query = "DELETE FROM student WHERE SID="+ SIDDelete.getText()+";";
        int rs = statement.executeUpdate(query);
        SIDDelete.clear();
        if(rs!=0){
            JOptionPane.showInternalMessageDialog(null, "Delete operation has done successfully!");
        }else
            JOptionPane.showInternalMessageDialog(null, "Please Enter a valid TID!");
    }

    @FXML
    void updateWindow() throws IOException {
        main.openScene("UpdateStudent.fxml");
    }

}
