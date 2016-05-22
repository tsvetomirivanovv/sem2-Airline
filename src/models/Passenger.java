package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;

public class Passenger {

    private IntegerProperty id;
    private IntegerProperty baggage;
    private java.sql.Timestamp birth_date;
    private StringProperty name;
    private IntegerProperty seat_no;

    public Passenger(int id, int baggage, java.sql.Timestamp birth_date, String name, int seat_no) {
        this.id = new SimpleIntegerProperty(id);
        this.baggage = new SimpleIntegerProperty(baggage);
        this.birth_date = birth_date;
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

    public int getBaggage() {
        return baggage.get();
    }

    public IntegerProperty baggageProperty() {
        return baggage;
    }

    public void setBaggage(int baggage) {
        this.baggage.set(baggage);
    }

    public Timestamp getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Timestamp birth_date) {
        this.birth_date = birth_date;
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
