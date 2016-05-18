package models;

import java.util.Date;

/**
 * Created by Caseru on 5/16/2016.
 */
public class Flight {

    private Plane plane;
    private Airport departure_loc;
    private Date departure_time;
    private Airport arrival_loc;
    private Date arrival_time;

    public Flight(Plane plane, Airport departure_loc, Date departure_time, Airport arrival_loc, Date arrival_time) {
        this.plane = plane;
        this.departure_loc = departure_loc;
        this.departure_time = departure_time;
        this.arrival_loc = arrival_loc;
        this.arrival_time = arrival_time;
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
        return departure_time;
    }

    public Flight setDeparture_time(Date departure_time) {
        this.departure_time = departure_time;
        return this;
    }

    public Airport getArrival_loc() {
        return arrival_loc;
    }

    public Flight setArrival_loc(Airport arrival_loc) {
        this.arrival_loc = arrival_loc;
        return this;
    }

    public Date getArrival_time() {
        return arrival_time;
    }

    public Flight setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
        return this;
    }
}
