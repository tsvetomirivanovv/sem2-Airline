package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Andrei on 5/16/2016.
 */
public class Payment {

    private IntegerProperty payment_id;
    private StringProperty card_type;
    private IntegerProperty card_no;
    private StringProperty card_expiration;
    private StringProperty cardHolder_name;

    public Payment(int payment_id, String card_type, int card_no, String card_expiration, String cardHolder_name) {
        this.payment_id = new SimpleIntegerProperty(payment_id);
        this.card_type = new SimpleStringProperty(card_type);
        this.card_no = new SimpleIntegerProperty(card_no);
        this.card_expiration = new SimpleStringProperty(card_expiration);
        this.cardHolder_name = new SimpleStringProperty(cardHolder_name);
    }

    public int getPayment_id() {
        return payment_id.get();
    }

    public IntegerProperty payment_idProperty() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id.set(payment_id);
    }

    public String getCard_type() {
        return card_type.get();
    }

    public StringProperty card_typeProperty() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type.set(card_type);
    }

    public int getCard_no() {
        return card_no.get();
    }

    public IntegerProperty card_noProperty() {
        return card_no;
    }

    public void setCard_no(int card_no) {
        this.card_no.set(card_no);
    }

    public String getCard_expiration() {
        return card_expiration.get();
    }

    public StringProperty card_expirationProperty() {
        return card_expiration;
    }

    public void setCard_expiration(String card_expiration) {
        this.card_expiration.set(card_expiration);
    }

    public String getCardHolder_name() {
        return cardHolder_name.get();
    }

    public StringProperty cardHolder_nameProperty() {
        return cardHolder_name;
    }

    public void setCardHolder_name(String cardHolder_name) {
        this.cardHolder_name.set(cardHolder_name);
    }
}
