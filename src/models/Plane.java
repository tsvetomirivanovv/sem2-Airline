package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

/**
 * Created by Caseru on 5/16/2016.
 */
public class Plane {

    private IntegerProperty reg_no;
    private StringProperty model;
    private IntegerProperty seats;
    private IntegerProperty in_use;
    private IntegerProperty businessSeats;
    private IntegerProperty coachSeats;
    private IntegerProperty economySeats;

    public Plane(int reg_no, String model, int seats, int in_use, int businessSeats, int coachSeats, int economySeats) {
        this.reg_no = new SimpleIntegerProperty(reg_no);
        this.model = new SimpleStringProperty(model);
        this.seats = new SimpleIntegerProperty(seats);
        this.in_use = new SimpleIntegerProperty(in_use);
        this.businessSeats = new SimpleIntegerProperty(businessSeats);
        this.coachSeats = new SimpleIntegerProperty(coachSeats);
        this.economySeats = new SimpleIntegerProperty(economySeats);
    }

    public void updatePlane(int in_use) {
        setIn_use(in_use);
    }

    public int getReg_no() {
        return reg_no.get();
    }

    public IntegerProperty reg_noProperty() {
        return reg_no;
    }

    public void setReg_no(int reg_no) {
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

    public int getIn_use() {
        return in_use.get();
    }

    public IntegerProperty in_useProperty() {
        return in_use;
    }

    public void setIn_use(int in_use) {
        this.in_use.set(in_use);
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
}
