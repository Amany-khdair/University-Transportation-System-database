package com.example.project;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class MySQLConnect {
    static Connection conn = null;


    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        MySQLConnect.conn = conn;
    }

    public static Connection connectDb() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false", "root", "1234");
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showInternalMessageDialog(null, "Error in Connecting to Database. Please contact adminstrator.");
        }
        return conn;

    }

}