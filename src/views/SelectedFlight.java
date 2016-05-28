package views;

import com.sun.javafx.binding.StringFormatter;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.*;
import services.DataController;
import services.components.searchInfo;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class SelectedFlight extends Application {
    Flight flight = new Flight();
    searchInfo searchInfo = new searchInfo();
    DataController data = new DataController();

    public SelectedFlight(Flight flightItem, searchInfo searchInfoItem) {
        flight = flightItem;
        searchInfo = searchInfoItem;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Selected flight");

        Label flightdetails = new Label("Flight details");
        flightdetails.setAlignment(Pos.TOP_CENTER);

        HBox layout = new HBox(0);

        VBox vbig = new VBox(5);
        VBox vflights = new VBox(20);

        layout.getChildren().addAll(vbig, vflights);

        //setting the content for the second vbox

        Label outbound = new Label("OUTBOUND");
        Label labeloutbound = new Label();
        labeloutbound.setText(searchInfo.getStart_date());
        HBox hout = new HBox(10);
        hout.getChildren().addAll(outbound, labeloutbound);

        Label flightlabel = new Label("Flight ID");
        Label date = new Label("Date&Time");
        Label fromto = new Label("From->To");
        Label duration = new Label("Duration");
        Label price = new Label("Price");

        Label reg_no = new Label(""+flight.getFlight_id());

        Label datevalue1 = new Label(""+flight.getDeparture_time());
        Label datevalue2 = new Label(""+flight.getArrival_time());
        VBox vdates = new VBox(1);
        vdates.getChildren().addAll(datevalue1, datevalue2);

        Label fromvalue = new Label(""+flight.getDeparture_loc().getName());
        Label tovalue = new Label(""+flight.getArrival_loc().getName());
        VBox vlocations = new VBox(1);
        vlocations.getChildren().addAll(fromvalue, tovalue);

        Label durationvalue = new Label();
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(flight.getDeparture_time());
        String timeStamp1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(flight.getArrival_time());
        durationvalue.setText(data.getFlightDuration(timeStamp, timeStamp1));

        Label pricevalue = new Label(data.getFlightPrice(flight.getFlight_id())+" DKK");

        VBox v1 = new VBox(5);
        v1.getChildren().addAll(flightlabel, reg_no);

        VBox v2 = new VBox(5);
        v2.getChildren().addAll(date, vdates);

        VBox v3 = new VBox(5);
        v3.getChildren().addAll(fromto, vlocations);

        VBox v4 = new VBox(5);
        v4.getChildren().addAll(duration, durationvalue);

        VBox v5 = new VBox(5);
        v5.getChildren().addAll(price, pricevalue);

        HBox houtboundflight = new HBox(16);
        houtboundflight.getChildren().addAll(v1, v2, v3, v4, v5);

        VBox vfirst = new VBox(8);
        vfirst.getChildren().addAll(flightdetails, hout, houtboundflight);

        vflights.getChildren().addAll(vfirst);

        // setting the content for the first vbox, vbig
        Label wejustneed = new Label("We just need a few more details. Who is traveling?");
        wejustneed.setAlignment(Pos.TOP_CENTER);

        Label passengerdetails = new Label("Passenger(s) details");
        passengerdetails.setAlignment(Pos.CENTER);

        Label passengername = new Label("Passenger name:");
        TextField tfpassengername = new TextField();
        tfpassengername.setPromptText("Write the passenger's name here");
        tfpassengername.setMaxWidth(178);
        VBox vpass1 = new VBox(4);
        vpass1.getChildren().addAll(passengername, tfpassengername);

        Label birthdate = new Label("Birth date:");
        DatePicker dpbirthdate = new DatePicker();
        VBox vpass2 = new VBox(4);
        vpass2.getChildren().addAll(birthdate, dpbirthdate);

        Label seatno = new Label();
        ChoiceBox cbseatno = new ChoiceBox();
        cbseatno.setMaxWidth(30);
        cbseatno.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType()));
        VBox vpass3 = new VBox(4);
        vpass3.getChildren().addAll(seatno, cbseatno);

        Label checkedbaggage = new Label("Checked baggage");
        ChoiceBox cbbaggage = new ChoiceBox();
        cbbaggage.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg. - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");
        VBox vpass4 = new VBox(4);
        vpass4.getChildren().addAll(checkedbaggage, cbbaggage);
        VBox passengerDet = new VBox(20);

        switch(searchInfo.getPassengers()) {
            case 1:
                passengerDet.getChildren().addAll(passengerH(vpass1,vpass2,vpass3,vpass4));
                break;
            case 2:
                passengerDet.getChildren().addAll(passengerH(vpass1,vpass2,vpass3,vpass4),passengerH(vpass1,vpass2,vpass3,vpass4));
                break;
            case 3:
                passengerDet.getChildren().addAll(passengerH(vpass1,vpass2,vpass3,vpass4),passengerH(vpass1,vpass2,vpass3,vpass4),passengerH(vpass1,vpass2,vpass3,vpass4));
                break;
            case 4:
                passengerDet.getChildren().addAll(passengerH(vpass1,vpass2,vpass3,vpass4),passengerH(vpass1,vpass2,vpass3,vpass4),passengerH(vpass1,vpass2,vpass3,vpass4),passengerH(vpass1,vpass2,vpass3,vpass4));
                break;

            default:
                break;
        }

        VBox vbiggg = new VBox(6);
        vbiggg.getChildren().addAll(wejustneed, passengerdetails);

        Label Payment = new Label("Payment info");
        Label note1 = new Label("Note: (If you leave the fields empty you can still pay the reservation later,");
        Label note2 = new Label("but if you don't do it, the reservation will be canceled in 2 weeks from now)");

        VBox vpayment = new VBox(4);
        vpayment.getChildren().addAll(Payment, note1, note2);

        ImageView mastercard = new ImageView(new Image("assets\\images\\mastercard.png"));
        mastercard.setFitHeight(100);
        mastercard.setFitWidth(100);
        ImageView visa = new ImageView(new Image("assets\\images\\visa.jpg"));
        visa.setFitHeight(100);
        visa.setFitWidth(100);
        ImageView visa_electron = new ImageView(new Image("assets\\images\\visa-electron.png"));
        visa_electron.setFitHeight(100);
        visa_electron.setFitWidth(100);
        ImageView maestro = new ImageView(new Image("assets\\images\\Maestro.png"));
        maestro.setFitHeight(100);
        maestro.setFitWidth(100);

        HBox himages = new HBox(5);
        himages.getChildren().addAll(mastercard, visa, visa_electron, maestro);

        Label holderName = new Label("Card holder name:");
        TextField holderField = new TextField();
        holderField.setPromptText("Name");
        VBox holder = new VBox(20);
        holder.getChildren().addAll(holderName,holderField);

        Label cardNo = new Label("Card no:");
        TextField noField = new TextField();
        noField.setPromptText("card number");
        VBox number = new VBox(20);
        number.getChildren().addAll(cardNo,noField);

        Label expirationDate= new Label("Expiration date:");
        TextField expirationYear = new TextField();
        expirationYear.setPromptText("YYYY");
        Label dash = new Label("/");
        TextField expirationMounth = new TextField();
        expirationMounth.setPromptText("MM");
        HBox fields = new HBox(5);
        fields.getChildren().addAll(expirationMounth,dash,expirationYear);
        VBox expiration = new VBox(20);
        expiration.getChildren().addAll(expirationDate,fields);

        Label securityCode = new Label("Security code:");
        TextField cvv = new TextField();
        cvv.setPromptText("CVV");
        VBox security = new VBox(20);
        security.getChildren().addAll(securityCode,cvv);

        HBox hcard = new HBox(15);
        hcard.getChildren().addAll(holder, number, expiration, security);

        Label pricel = new Label("Price");

        HBox hloc = new HBox(5);
        Label arrowlabel = new Label("->");
        hloc.getChildren().addAll(fromvalue, arrowlabel, tovalue);
        HBox hedge = new HBox(35);
        hedge.getChildren().addAll(hloc, pricevalue);

        Label choiceboxlabel = new Label();
        choiceboxlabel.setText(cbbaggage.getValue()+"");

        ImageView line = new ImageView(new Image("assets//images//dash.png"));

        Label totalprice = new Label("TOTAL PRICE:");

        double totalpricevalue = data.getFlightPrice(flight.getFlight_id());

        if(!(cbbaggage.getSelectionModel().getSelectedItem() == null)) {
            if (cbbaggage.getSelectionModel().getSelectedItem().equals("None - 0 DKK")) {
                totalpricevalue += 0;
            } else if (cbbaggage.getSelectionModel().getSelectedItem().equals("Baggage, Max 15 Kg. - 50 DKK")) {
                totalpricevalue += 50;
            } else if (cbbaggage.getSelectionModel().getSelectedItem().equals("Baggage, Max 20 Kg - 90 DKK")) {
                totalpricevalue += 90;
            }
        }

        Label bigvalue = new Label();
        bigvalue.setText(totalpricevalue+"");

        HBox htot = new HBox(30);
        htot.getChildren().addAll(totalprice, bigvalue);

        VBox vforprice = new VBox(5);
        vforprice.getChildren().addAll(pricel, hedge, line, htot);

        vbig.getChildren().addAll(vbiggg, passengerDet, vpayment, himages, hcard, vforprice);

        Scene scene = new Scene(layout, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static HBox passengerH(VBox v1, VBox v2, VBox v3, VBox v4) {
        HBox aux = new HBox(15);
        aux.getChildren().addAll(v1, v2, v3, v4);

        return aux;
    }

}
