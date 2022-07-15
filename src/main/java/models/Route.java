package models;

public class Route {
    int RID;
    String Rname;
    int Distance;
    String Details;
    boolean Safety;
    int Rtime;

    public Route(int RID, String rname, int distance, String details, boolean safety, int rtime) {
        this.RID = RID;
        Rname = rname;
        Distance = distance;
        Details = details;
        Safety = safety;
        Rtime = rtime;
    }

    public Route(int RID, String rname, int distance) {
        this.RID = RID;
        this.Rname = rname;
        this.Distance = distance;
    }


    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public String getRname() {
        return Rname;
    }

    public void setRname(String rname) {
        Rname = rname;
    }

    public int getDistance() {
        return Distance;
    }

    public void setDistance(int distance) {
        Distance = distance;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public boolean isSafety() {
        return Safety;
    }

    public void setSafety(boolean safety) {
        Safety = safety;
    }

    public int getRtime() {
        return Rtime;
    }

    public void setRtime(int rtime) {
        Rtime = rtime;
    }
}
