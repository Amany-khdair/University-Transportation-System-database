package models;

public class DriversInfo {

    int DID;
    int PID;
    String Dname;
    int Driving_license;
    int Phone_number;
    String Email;
    int Cars_Driving_license;
    String expiry_date;
    String DPassword;

    public DriversInfo(int DID, int PID, String dname, int driving_license, int phone_number, String email, int cars_Driving_license, String date, String DPassword) {
        this.DID = DID;
        this.PID = PID;
        Dname = dname;
        Driving_license = driving_license;
        Phone_number = phone_number;
        Email = email;
        Cars_Driving_license = cars_Driving_license;
        this.expiry_date = date;
        this.DPassword = DPassword;
    }

    public DriversInfo(int DID, String Dname) {
        this.DID = DID;
        this.Dname = Dname;
    }

    public int getDID() {
        return DID;
    }

    public void setDID(int DID) {
        this.DID = DID;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public int getDriving_license() {
        return Driving_license;
    }

    public void setDriving_license(int driving_license) {
        Driving_license = driving_license;
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

    public int getCars_Driving_license() {
        return Cars_Driving_license;
    }

    public void setCars_Driving_license(int cars_Driving_license) {
        Cars_Driving_license = cars_Driving_license;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getDPassword() {
        return DPassword;
    }

    public void setDPassword(String DPassword) {
        this.DPassword = DPassword;
    }
}
