package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

import java.util.Date;

/**
 * Created by Caseru on 5/16/2016.
 */
public class Flight {

    private Plane plane;
    private IntegerProperty flight_id;
    private Airport departure_loc;
    private ObservableValue<Date> departure_time;
    private Airport arrival_loc;
    private ObservableValue<Date> arrival_time;

    public Flight(int flight_id, Plane plane, Airport departure_loc, ObservableValue<Date> departure_time, Airport arrival_loc, ObservableValue<Date> arrival_time) {
        this.flight_id = new SimpleIntegerProperty(flight_id);
        this.plane = plane;
        this.departure_loc = departure_loc;
        this.departure_time = departure_time;
        this.arrival_loc = arrival_loc;
        this.arrival_time = arrival_time;
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

    public Airport getDeparture_loc() {
        return departure_loc;
    }

    public Flight setDeparture_loc(Airport departure_loc) {
        this.departure_loc = departure_loc;
        return this;
    }

    public Date getDeparture_time() {
        return departure_time.getValue();
    }

    public ObservableValue<Date> departure_timeProperty() {
        return departure_time;
    }

    public Airport getArrival_loc() {
        return arrival_loc;
    }

    public Flight setArrival_loc(Airport arrival_loc) {
        this.arrival_loc = arrival_loc;
        return this;
    }

    public Date getArrival_time() {
        return arrival_time.getValue();
    }

    public ObservableValue<Date> arrival_timeProperty() {
        return arrival_time;
    }
}
