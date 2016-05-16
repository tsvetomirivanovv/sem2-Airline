package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Caseru on 5/16/2016.
 */
public class Account {

    private StringProperty role;
    private IntegerProperty id;
    private StringProperty email;
    private StringProperty password;

    public Account(StringProperty role, IntegerProperty id, StringProperty email, StringProperty password) {
        this.role = role;
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getRole() {
        return role.get();
    }

    public StringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
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
