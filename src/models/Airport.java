package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by PULA on 5/16/2016.
 */
public class Airport {
    private StringProperty name;
    private StringProperty city;
    private IntegerProperty airport_code;

    public Airport(String name, String city, int airport_code) {
        this.name = new SimpleStringProperty(name);
        this.city = new SimpleStringProperty(city);
        this.airport_code = new SimpleIntegerProperty(airport_code);
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

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public int getAirport_code() {
        return airport_code.get();
    }

    public IntegerProperty airport_codeProperty() {
        return airport_code;
    }

    public void setAirport_code(int airport_code) {
        this.airport_code.set(airport_code);
    }
}
