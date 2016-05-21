package models;

import javafx.beans.property.*;

/**
 * Created by Caseru on 5/16/2016.
 */
public class Account {

    private BooleanProperty role;
    private IntegerProperty id;
    private StringProperty email;
    private StringProperty password;

    public Account(boolean role, int id, String email, String password) {
        this.role = new SimpleBooleanProperty(role);
        this.id = new SimpleIntegerProperty(id);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }

    public boolean getRole() {
        return role.get();
    }

    public BooleanProperty roleProperty() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role.set(role);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
