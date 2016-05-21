package models;

import java.util.Date;

/**
 * Created by Caseru on 5/21/2016.
 */
public class Interval {
    private Date departureTime;
    private Date arrivalTime;

    public Interval(Date departureTime, Date arrivalTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Interval setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Interval setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }
}
