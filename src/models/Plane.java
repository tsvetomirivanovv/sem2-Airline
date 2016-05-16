package models;

import javafx.beans.property.IntegerProperty;
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
    private ArrayList<ClassType> class_list;

    public Plane(IntegerProperty reg_no, StringProperty model, IntegerProperty seats, IntegerProperty in_use, ArrayList<ClassType> class_list) {
        this.reg_no = reg_no;
        this.model = model;
        this.seats = seats;
        this.in_use = in_use;
        this.class_list = class_list;
    }

    public void updatePlane() {

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

    public ArrayList<ClassType> getClass_list() {
        return class_list;
    }

    public Plane setClass_list(ArrayList<ClassType> class_list) {
        this.class_list = class_list;
        return this;
    }
}
