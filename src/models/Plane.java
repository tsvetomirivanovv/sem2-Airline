package models;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Plane {

    private IntegerProperty plane_id;
    private StringProperty reg_no;
    private StringProperty model;
    private IntegerProperty seats;
    private ObservableList<Interval> in_use;
    private IntegerProperty businessSeats;
    private IntegerProperty coachSeats;
    private IntegerProperty economySeats;
    private DoubleProperty businessPrice;
    private DoubleProperty coachPrice;

    public Plane(int plane_id, String reg_no, String model, int seats, ObservableList<Interval> in_use, int businessSeats, int coachSeats, int economySeats, double businessPrice, double coachPrice) {
        this.plane_id = new SimpleIntegerProperty(plane_id);
        this.reg_no = new SimpleStringProperty(reg_no);
        this.model = new SimpleStringProperty(model);
        this.seats = new SimpleIntegerProperty(seats);
        this.in_use = in_use;
        this.businessSeats = new SimpleIntegerProperty(businessSeats);
        this.coachSeats = new SimpleIntegerProperty(coachSeats);
        this.economySeats = new SimpleIntegerProperty(economySeats);
        this.businessPrice = new SimpleDoubleProperty(businessPrice);
        this.coachPrice = new SimpleDoubleProperty(coachPrice);
    }

    public Plane(int plane_id, String reg_no, String model, int seats, int businessSeats, int coachSeats, int economySeats, double businessPrice, double coachPrice) {
        this.plane_id = new SimpleIntegerProperty(plane_id);
        this.reg_no = new SimpleStringProperty(reg_no);
        this.model = new SimpleStringProperty(model);
        this.seats = new SimpleIntegerProperty(seats);
        this.businessSeats = new SimpleIntegerProperty(businessSeats);
        this.coachSeats = new SimpleIntegerProperty(coachSeats);
        this.economySeats = new SimpleIntegerProperty(economySeats);
        this.businessPrice = new SimpleDoubleProperty(businessPrice);
        this.coachPrice = new SimpleDoubleProperty(coachPrice);
    }
    public Plane(String reg_no, String model, int seats, int businessSeats, int coachSeats, int economySeats, double businessPrice, double coachPrice) {
        this.reg_no = new SimpleStringProperty(reg_no);
        this.model = new SimpleStringProperty(model);
        this.seats = new SimpleIntegerProperty(seats);
        this.businessSeats = new SimpleIntegerProperty(businessSeats);
        this.coachSeats = new SimpleIntegerProperty(coachSeats);
        this.economySeats = new SimpleIntegerProperty(economySeats);
        this.businessPrice = new SimpleDoubleProperty(businessPrice);
        this.coachPrice = new SimpleDoubleProperty(coachPrice);
    }

    public void updatePlane(String reg_no, String model, int seats, ObservableList<Interval> in_use, int businessSeats, int coachSeats, int economySeats) {
        setReg_no(reg_no);
        setModel(model);
        setSeats(seats);
        setIn_use(in_use);
        setBusinessSeats(businessSeats);
        setCoachSeats(coachSeats);
        setEconomySeats(economySeats);
    }

    public int getPlane_id() {
        return plane_id.get();
    }

    public IntegerProperty plane_idProperty() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id.set(plane_id);
    }

    public String getReg_no() {
        return reg_no.get();
    }

    public StringProperty reg_noProperty() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no.set(reg_no);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public int getSeats() {
        return seats.get();
    }

    public IntegerProperty seatsProperty() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats.set(seats);
    }

    public ObservableList<Interval> getIn_use() {
        return in_use;
    }

    public Plane setIn_use(ObservableList<Interval> in_use) {
        this.in_use = in_use;
        return this;
    }

    public int getBusinessSeats() {
        return businessSeats.get();
    }

    public IntegerProperty businessSeatsProperty() {
        return businessSeats;
    }

    public void setBusinessSeats(int businessSeats) {
        this.businessSeats.set(businessSeats);
    }

    public int getCoachSeats() {
        return coachSeats.get();
    }

    public IntegerProperty coachSeatsProperty() {
        return coachSeats;
    }

    public void setCoachSeats(int coachSeats) {
        this.coachSeats.set(coachSeats);
    }

    public int getEconomySeats() {
        return economySeats.get();
    }

    public IntegerProperty economySeatsProperty() {
        return economySeats;
    }

    public void setEconomySeats(int economySeats) {
        this.economySeats.set(economySeats);
    }

    public double getBusinessPrice() {
        return businessPrice.get();
    }

    public DoubleProperty businessPriceProperty() {
        return businessPrice;
    }

    public void setBusinessPrice(double businessPrice) {
        this.businessPrice.set(businessPrice);
    }

    public double getCoachPrice() {
        return coachPrice.get();
    }

    public DoubleProperty coachPriceProperty() {
        return coachPrice;
    }

    public void setCoachPrice(double coachPrice) {
        this.coachPrice.set(coachPrice);
    }
}
