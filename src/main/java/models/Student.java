package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    int SID;
    int PID;
    String Sname ;
    int Phone_number ;
    String Email;
    String Major;
    String Address;
    String Gender;
    boolean Still_study;


    public Student(int SID, int PID, String sname, int phone_number, String email, String major, String address, String gender, boolean still_study) {
        this.SID = SID;
        this.PID = PID;
        this.Sname = sname;
        Phone_number = phone_number;
        Email = email;
        Major = major;
        Address = address;
        Gender = gender;
        Still_study = still_study;
    }

    public Student(int sid, String name) {
        this.SID = sid;
        this.Sname = name;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        this.Sname = sname;
    }

    public int getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(int phone_number) {
        Phone_number = phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public boolean isStill_study() {
        return Still_study;
    }

    public void setStill_study(boolean still_study) {
        Still_study = still_study;
    }
}
