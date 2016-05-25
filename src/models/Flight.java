package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

import java.security.Timestamp;
import java.sql.Time;
import java.util.Date;

public class Flight {

    private Plane plane;
    private IntegerProperty flight_id;
    private Airport departure_loc;
    private Airport arrival_loc;
    private java.sql.Timestamp departure_time;
    private java.sql.Timestamp arrival_time;
    private DoubleProperty flight_price;

    public Flight(int flight_id, Plane plane, Airport departure_loc, java.sql.Timestamp departure_time, Airport arrival_loc, java.sql.Timestamp arrival_time, double flight_price) {
        this.flight_id = new SimpleIntegerProperty(flight_id);
        this.plane = plane;
        this.departure_loc = departure_loc;
        this.departure_time = departure_time;
        this.arrival_loc = arrival_loc;
        this.arrival_time = arrival_time;
        this.flight_price = new SimpleDoubleProperty(flight_price);
    }

    public Flight() {

    }

    public int getFlight_id() {
        return flight_id.get();
    }

    public IntegerProperty flight_idProperty() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id.set(flight_id);
    }

    public Plane getPlane() {
        return plane;
    }

    public Flight setPlane(Plane plane) {
        this.plane = plane;
        return this;
    }

    public double getFlight_price() {
        return flight_price.get();
    }

    public DoubleProperty flight_priceProperty() {
        return flight_price;
    }

    public void setFlight_price(double flight_price) {
        this.flight_price.set(flight_price);
    }

    public Airport getDeparture_loc() {
        return departure_loc;
    }

    public Flight setDeparture_loc(Airport departure_loc) {
        this.departure_loc = departure_loc;
        return this;
    }

    public Airport getArrival_loc() {
        return arrival_loc;
    }

    public Flight setArrival_loc(Airport arrival_loc) {
        this.arrival_loc = arrival_loc;
        return this;
    }

    public java.sql.Timestamp getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(java.sql.Timestamp departure_time) {
        this.departure_time = departure_time;
    }

    public java.sql.Timestamp getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(java.sql.Timestamp arrival_time) {
        this.arrival_time = arrival_time;
    }
}
