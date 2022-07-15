package models;

public class Registration {
    int Student_SID;
    int Transportation_time_TID;

    public Registration(int student_SID, int transportation_time_TID) {
        Student_SID = student_SID;
        Transportation_time_TID = transportation_time_TID;
    }

    public int getStudent_SID() {
        return Student_SID;
    }

    public void setStudent_SID(int student_SID) {
        Student_SID = student_SID;
    }

    public int getTransportation_time_TID() {
        return Transportation_time_TID;
    }

    public void setTransportation_time_TID(int transportation_time_TID) {
        Transportation_time_TID = transportation_time_TID;
    }
}
