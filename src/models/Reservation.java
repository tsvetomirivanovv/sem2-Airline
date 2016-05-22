package models;

import javafx.beans.property.*;

import java.util.ArrayList;

public class Reservation {

    private Flight flight;
    private DoubleProperty price;
    private IntegerProperty reservation_id;
    private StringProperty status;
    private IntegerProperty customer_id;
    private StringProperty customer_name;
    private ArrayList<Passenger> passenger_list;
    private IntegerProperty total_passengers;

    public Reservation(Flight flight, double price, int reservation_id, String status, int customer_id, ArrayList<Passenger> passenger_list, int total_passengers, String customer_name) {
        this.flight = flight;
        this.price = new SimpleDoubleProperty(price);
        this.reservation_id = new SimpleIntegerProperty(reservation_id);
        this.status = new SimpleStringProperty(status);
        this.customer_id = new SimpleIntegerProperty(customer_id);
        this.total_passengers = new SimpleIntegerProperty(total_passengers);
        this.passenger_list = passenger_list;
        this.customer_name = new SimpleStringProperty(customer_name);
    }

    public int getReservation_id() {
        return reservation_id.get();
    }

    public IntegerProperty reservation_idProperty() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id.set(reservation_id);
    }

    public String getCustomer_name() {
        return customer_name.get();
    }

    public StringProperty customer_nameProperty() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name.set(customer_name);
    }

    public int getTotal_passengers() {
        return total_passengers.get();
    }

    public IntegerProperty total_passengersProperty() {
        return total_passengers;
    }

    public void setTotal_passengers(int total_passengers) {
        this.total_passengers.set(total_passengers);
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
