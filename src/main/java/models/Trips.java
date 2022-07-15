package models;

public class Trips {

    int payment;

    int Drivers_DID;

    int RID;

    int Transportation_time_TID;

    public Trips(int payment, int drivers_DID, int RID, int transportation_time_TID) {
        this.payment = payment;
        Drivers_DID = drivers_DID;
        this.RID = RID;
        Transportation_time_TID = transportation_time_TID;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getDrivers_DID() {
        return Drivers_DID;
    }

    public void setDrivers_DID(int drivers_DID) {
        Drivers_DID = drivers_DID;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public int getTransportation_time_TID() {
        return Transportation_time_TID;
    }

    public void setTransportation_time_TID(int transportation_time_TID) {
        Transportation_time_TID = transportation_time_TID;
    }
}
