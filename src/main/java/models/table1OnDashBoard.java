package models;

public class table1OnDashBoard {
    int Hour;
    int numberOfStudent;
    int numberOfTrips;

    public table1OnDashBoard(int hour, int numberOfStudent, int numberOfTrips) {
        Hour = hour;
        this.numberOfStudent = numberOfStudent;
        this.numberOfTrips = numberOfTrips;
    }

    public table1OnDashBoard(int hour) {
        Hour = hour;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    public void setNumberOfTrips(int numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
    }
}
