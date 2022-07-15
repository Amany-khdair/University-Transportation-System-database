package models;

public class TransportationTime {
    int TID;
    int Thour;
    String Tday;
    int Semester;
    int Tyear;

    public TransportationTime(int TID, int THour, String TDay, int semester, int TYear) {
        this.TID = TID;
        this.Thour = THour;
        this.Tday = TDay;
        Semester = semester;
        this.Tyear = TYear;
    }

    public TransportationTime(String TDay, int THour) {
        this.Tday = TDay;
        this.Thour = THour;
    }

    public int getTID() {
        return TID;
    }

    public void setTID(int TID) {
        this.TID = TID;
    }

    public int getThour() {
        return Thour;
    }

    public void setThour(int thour) {
        this.Thour = thour;
    }

    public String getTday() {
        return Tday;
    }

    public void setTday(String tday) {
        this.Tday = tday;
    }

    public int getSemester() {
        return Semester;
    }

    public void setSemester(int semester) {
        Semester = semester;
    }

    public int getTyear() {
        return Tyear;
    }

    public void setTyear(int tyear) {
        this.Tyear = tyear;
    }
}
