package models;

public class table2OnDashBoard {

    String Name;
    int Phone;
    String Address;

    public table2OnDashBoard(String Name, int Phone, String Address) {
        this.Name=Name;
        this.Phone=Phone;
        this.Address=Address;
    }

    public String getName() {return Name;}

    public void setName(String name) {Name = name;}

    public int getPhone() {return Phone;}

    public void setPhone(int phone) {Phone = phone;}

    public String getAddress() {return Address;}

    public void setAddress(String address) {Address = address;}
}
