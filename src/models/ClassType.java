package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by  on 5/16/2016.
 */
public class ClassType {

    private StringProperty name;
    private IntegerProperty refund_percentage;
    private IntegerProperty from_seat;
    private IntegerProperty to_seat;
    private IntegerProperty class_id;

    public ClassType(String name, int refund_percentage, int from_seat, int to_seat, int class_id) {
        this.name = new SimpleStringProperty(name);
        this.refund_percentage = new SimpleIntegerProperty(refund_percentage);
        this.from_seat = new SimpleIntegerProperty(from_seat);
        this.to_seat = new SimpleIntegerProperty(to_seat);
        this.class_id = new SimpleIntegerProperty(class_id);
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

    public int getRefund_percentage() {
        return refund_percentage.get();
    }

    public IntegerProperty refund_percentageProperty() {
        return refund_percentage;
    }

    public void setRefund_percentage(int refund_percentage) {
        this.refund_percentage.set(refund_percentage);
    }

    public int getFrom_seat() {
        return from_seat.get();
    }

    public IntegerProperty from_seatProperty() {
        return from_seat;
    }

    public void setFrom_seat(int from_seat) {
        this.from_seat.set(from_seat);
    }

    public int getTo_seat() {
        return to_seat.get();
    }

    public IntegerProperty to_seatProperty() {
        return to_seat;
    }

    public void setTo_seat(int to_seat) {
        this.to_seat.set(to_seat);
    }

    public int getClass_id() {
        return class_id.get();
    }

    public IntegerProperty class_idProperty() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id.set(class_id);
    }
}
