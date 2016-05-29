package models;


import javafx.beans.property.*;

public class Refund {

    // person name - class type - refund % - refund $.
    private StringProperty passenger_name;
    private StringProperty class_type;
    private IntegerProperty refund_percentage;
    private DoubleProperty refund_amount;


    public Refund(String passenger_name, String class_type, int refund_percentage, double refund_amount) {

        this.passenger_name = new SimpleStringProperty(passenger_name);
        this.class_type = new SimpleStringProperty(class_type);
        this.refund_percentage = new SimpleIntegerProperty(refund_percentage);
        this.refund_amount = new SimpleDoubleProperty(refund_amount);
    }

    public Refund() {

    }

    public String getPassenger_name() {
        return passenger_name.get();
    }

    public StringProperty passenger_nameProperty() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name.set(passenger_name);
    }

    public String getClass_type() {
        return class_type.get();
    }

    public StringProperty class_typeProperty() {
        return class_type;
    }

    public void setClass_type(String class_type) {
        this.class_type.set(class_type);
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

    public double getRefund_amount() {
        return refund_amount.get();
    }

    public DoubleProperty refund_amountProperty() {
        return refund_amount;
    }

    public void setRefund_amount(double refund_amount) {
        this.refund_amount.set(refund_amount);
    }
}
