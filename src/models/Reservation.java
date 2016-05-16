package models;

import javafx.beans.property.*;

import java.util.ArrayList;

/**
 * Created by Caseru on 5/16/2016.
 */
public class Reservation {

    private Flight flight;
    private DoubleProperty price;
    private ClassType selected_class;
    private ArrayList<Integer> seats_id;
    private StringProperty status;
    private IntegerProperty customer_id;
    private ArrayList<Passenger> passenger_list;

    public Reservation(Flight flight, double price, ClassType selected_class, ArrayList<Integer> seats_id, String status, int customer_id, ArrayList<Passenger> passenger_list) {
        this.flight = flight;
        this.price = new SimpleDoubleProperty(price);
        this.selected_class = selected_class;
        this.seats_id = seats_id;
        this.status = new SimpleStringProperty(status);
        this.customer_id = new SimpleIntegerProperty(customer_id);
        this.passenger_list = passenger_list;
    }

    public void confirmReservation() {

    }

    public void cancelReservation() {

    }

    public void updateReservation() {

    }

    public Flight getFlight() {
        return flight;

    }

    public Reservation setFlight(Flight flight) {
        this.flight = flight;
        return this;
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public ClassType getSelected_class() {
        return selected_class;
    }

    public Reservation setSelected_class(ClassType selected_class) {
        this.selected_class = selected_class;
        return this;
    }

    public ArrayList<Integer> getSeats_id() {
        return seats_id;
    }

    public Reservation setSeats_id(ArrayList<Integer> seats_id) {
        this.seats_id = seats_id;
        return this;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public int getCustomer_id() {
        return customer_id.get();
    }

    public IntegerProperty customer_idProperty() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id.set(customer_id);
    }

    public ArrayList<Passenger> getPassenger_list() {
        return passenger_list;
    }

    public Reservation setPassenger_list(ArrayList<Passenger> passenger_list) {
        this.passenger_list = passenger_list;
        return this;
    }
}
