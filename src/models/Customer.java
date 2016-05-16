package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Caseru on 5/16/2016.
 */
public class Customer extends Account{

    private StringProperty name;
    private StringProperty address;
    private StringProperty country;
    private StringProperty city;
    private IntegerProperty phone;
    private Payment payment;

    public Customer(String role, int id, String email, String password ,String name, String address, String country, String city, int phone, Payment payment) {
        super(role, id, email, password);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.country = new SimpleStringProperty(country);
        this.city = new SimpleStringProperty(city);
        this.phone = new SimpleIntegerProperty(phone);
        this.payment = payment;
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

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public int getPhone() {
        return phone.get();
    }

    public IntegerProperty phoneProperty() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone.set(phone);
    }

    public Payment getPayment() {
        return payment;
    }

    public Customer setPayment(Payment payment) {
        this.payment = payment;
        return this;
    }
}
