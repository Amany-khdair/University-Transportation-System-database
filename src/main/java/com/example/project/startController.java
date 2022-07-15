package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class startController {

    public static int DID;
    @FXML
    private Label invalidLogin;

    @FXML
    private PasswordField password;

    @FXML
    public TextField username;

    public static String name;
    @FXML
    void SignIn() throws SQLException, IOException {
        HelloApplication app = new HelloApplication();
        String userName = username.getText();
        DID = Integer.parseInt(userName);
        String pass = password.getText();
        String sql = "Select * from drivers Where DID=" + userName + " and DPassword=" + pass;
        Connection connection = MySQLConnect.connectDb();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            invalidLogin.setText("Success!");
            // Change scene to the main page
            name = rs.getString("DName");
            app.changeScene("Main.fxml");
        }

        else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            invalidLogin.setText("Please, Enter Your data!");
        }
        else {
            invalidLogin.setText("Invalid Login, Please try again!");
        }

    }
}
