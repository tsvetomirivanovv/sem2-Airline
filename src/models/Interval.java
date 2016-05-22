package models;

public class Interval {
    private java.sql.Timestamp departureTime;
    private java.sql.Timestamp arrivalTime;

    public Interval(java.sql.Timestamp departureTime, java.sql.Timestamp arrivalTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public java.sql.Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(java.sql.Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public java.sql.Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(java.sql.Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
