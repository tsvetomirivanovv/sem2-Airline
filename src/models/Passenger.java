package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Andrei on 5/16/2016.
 */
public class Passenger {

    private IntegerProperty id;
    private IntegerProperty age;
    private StringProperty name;
    private IntegerProperty seat_no;

    public Passenger(int id, int age, String name, int seat_no) {
        this.id = new SimpleIntegerProperty(id);
        this.age = new SimpleIntegerProperty(age);
        this.name = new SimpleStringProperty(name);
        this.seat_no = new SimpleIntegerProperty(seat_no);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getSeat_no() {
        return seat_no.get();
    }

    public IntegerProperty seat_noProperty() {
        return seat_no;
    }

    public void setSeat_no(int seat_no) {
        this.seat_no.set(seat_no);
    }
}
