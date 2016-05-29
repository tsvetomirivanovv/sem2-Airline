package views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.*;
import services.DataController;
import services.components.searchInfo;
import java.text.SimpleDateFormat;

public class SelectedFlight extends Application {
    Flight flight = new Flight();
    searchInfo searchInfo = new searchInfo();
    DataController data = new DataController();
    double totalpricevalue;
    Label bigvalue = new Label();

    public SelectedFlight(Flight flightItem, searchInfo searchInfoItem) {
        flight = flightItem;
        searchInfo = searchInfoItem;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Selected flight");

        Label flightdetails = new Label("Flight details");
        flightdetails.setAlignment(Pos.TOP_CENTER);

        HBox layout = new HBox(2);

        VBox vbig = new VBox(30);
        VBox vflights = new VBox(400);

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

        Label fromvalue = new Label(flight.getDeparture_loc().getName()+", "+flight.getDeparture_loc().getCity());
        Label tovalue = new Label(flight.getArrival_loc().getName()+", "+flight.getArrival_loc().getCity());
        VBox vlocations = new VBox(1);
        vlocations.getChildren().addAll(fromvalue, tovalue);

        Label durationvalue = new Label();
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(flight.getDeparture_time());
        String timeStamp1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(flight.getArrival_time());
        durationvalue.setText(data.getFlightDuration(timeStamp, timeStamp1));

        Label pricevalue = new Label(data.getFlightPrice(flight.getFlight_id())*searchInfo.getPassengers()+" DKK");

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

        Button bookreservation = new Button("Book reservation");
        Button close = new Button("Close");
        HBox hbuttons = new HBox(5);
        hbuttons.getChildren().addAll(bookreservation, close);
        hbuttons.setAlignment(Pos.BASELINE_RIGHT);


        vflights.getChildren().addAll(vfirst, hbuttons);

        // setting the content for the first vbox, vbig
        Label wejustneed = new Label("We just need a few more details. Who is traveling?");
        wejustneed.setAlignment(Pos.TOP_CENTER);

        Label passengerdetails = new Label("Passenger(s) details");
        passengerdetails.setAlignment(Pos.CENTER);

        /////////////////////////////////////////////////////////////////////////
        Label passengername = new Label("Passenger name:");
        TextField tfpassengername = new TextField();
        tfpassengername.setPromptText("Passenger's name");
        tfpassengername.setMaxWidth(178);
        VBox vpass1 = new VBox(4);
        vpass1.getChildren().addAll(passengername, tfpassengername);

        Label birthdate = new Label("Birth date:");
        DatePicker dpbirthdate = new DatePicker();
        VBox vpass2 = new VBox(4);
        vpass2.getChildren().addAll(birthdate, dpbirthdate);

        Label seatno = new Label("Seat no.");
        ChoiceBox cbseatno = new ChoiceBox();
        cbseatno.setMaxWidth(40);
        cbseatno.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType()));
        VBox vpass3 = new VBox(4);
        vpass3.getChildren().addAll(seatno, cbseatno);

        Label checkedbaggage = new Label("Checked baggage");
        ChoiceBox cbbaggage = new ChoiceBox();
        cbbaggage.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg. - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");
        VBox vpass4 = new VBox(4);
        vpass4.getChildren().addAll(checkedbaggage, cbbaggage);
        HBox passengerDet = new HBox(20);
        passengerDet.getChildren().addAll(vpass1, vpass2, vpass3, vpass4);
        /////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////
        Label passengername2 = new Label("Passenger name:");
        TextField tfpassengername2 = new TextField();
        tfpassengername2.setPromptText("Passenger's name");
        tfpassengername2.setMaxWidth(178);
        VBox vpass12 = new VBox(4);
        vpass12.getChildren().addAll(passengername2, tfpassengername2);

        Label birthdate2 = new Label("Birth date:");
        DatePicker dpbirthdate2 = new DatePicker();
        VBox vpass22 = new VBox(4);
        vpass22.getChildren().addAll(birthdate2, dpbirthdate2);

        Label seatno2 = new Label("Seat no.");
        ChoiceBox cbseatno2 = new ChoiceBox();
        cbseatno2.setMaxWidth(40);
        cbseatno2.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType()));
        VBox vpass32 = new VBox(4);
        vpass32.getChildren().addAll(seatno2, cbseatno2);

        Label checkedbaggage2 = new Label("Checked baggage");
        ChoiceBox cbbaggage2 = new ChoiceBox();
        cbbaggage2.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg. - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");
        VBox vpass42 = new VBox(4);
        vpass42.getChildren().addAll(checkedbaggage2, cbbaggage2);
        HBox passengerDet2 = new HBox(20);
        passengerDet2.getChildren().addAll(vpass12, vpass22, vpass32, vpass42);
        /////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////////
        Label passengername3 = new Label("Passenger name:");
        TextField tfpassengername3 = new TextField();
        tfpassengername3.setPromptText("Passenger's name");
        tfpassengername3.setMaxWidth(178);
        VBox vpass13 = new VBox(4);
        vpass13.getChildren().addAll(passengername3, tfpassengername3);

        Label birthdate3 = new Label("Birth date:");
        DatePicker dpbirthdate3 = new DatePicker();
        VBox vpass23 = new VBox(4);
        vpass23.getChildren().addAll(birthdate3, dpbirthdate3);

        Label seatno3 = new Label("Seat no.");
        ChoiceBox cbseatno3 = new ChoiceBox();
        cbseatno3.setMaxWidth(40);
        cbseatno3.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType()));
        VBox vpass33 = new VBox(4);
        vpass33.getChildren().addAll(seatno3, cbseatno3);

        Label checkedbaggage3 = new Label("Checked baggage");
        ChoiceBox cbbaggage3 = new ChoiceBox();
        cbbaggage3.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg. - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");
        VBox vpass43 = new VBox(4);
        vpass43.getChildren().addAll(checkedbaggage3, cbbaggage3);
        HBox passengerDet3 = new HBox(20);
        passengerDet3.getChildren().addAll(vpass13, vpass23, vpass33, vpass43);
        /////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////////
        Label passengername4 = new Label("Passenger name:");
        TextField tfpassengername4 = new TextField();
        tfpassengername4.setPromptText("Passenger's name");
        tfpassengername4.setMaxWidth(178);
        VBox vpass14 = new VBox(4);
        vpass14.getChildren().addAll(passengername4, tfpassengername4);

        Label birthdate4 = new Label("Birth date:");
        DatePicker dpbirthdate4 = new DatePicker();
        VBox vpass24 = new VBox(4);
        vpass24.getChildren().addAll(birthdate4, dpbirthdate4);

        Label seatno4 = new Label("Seat no.");
        ChoiceBox cbseatno4 = new ChoiceBox();
        cbseatno4.setMaxWidth(40);
        cbseatno4.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType()));
        VBox vpass34 = new VBox(4);
        vpass34.getChildren().addAll(seatno4, cbseatno4);

        Label checkedbaggage4 = new Label("Checked baggage");
        ChoiceBox cbbaggage4 = new ChoiceBox();
        cbbaggage4.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg. - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");
        VBox vpass44 = new VBox(4);
        vpass44.getChildren().addAll(checkedbaggage4, cbbaggage4);
        HBox passengerDet4 = new HBox(20);
        passengerDet4.getChildren().addAll(vpass14, vpass24, vpass34, vpass44);
        /////////////////////////////////////////////////////////////////////////////

        VBox passengerDetVBox = new VBox(10);

        switch(searchInfo.getPassengers()) {
            case 1:
                passengerDetVBox.getChildren().addAll(passengerDet);
                System.err.println(searchInfo.getPassengers()+" passengers selected");
                System.err.println("THIS IS CASE 1");
                break;
            case 2:
                passengerDetVBox.getChildren().addAll(passengerDet, passengerDet2);
                System.err.println(searchInfo.getPassengers()+" passengers selected");
                System.err.println("THIS IS CASE 2");
                break;
            case 3:
                passengerDetVBox.getChildren().addAll(passengerDet, passengerDet2, passengerDet3);
                System.err.println(searchInfo.getPassengers()+" passengers selected");
                System.err.println("THIS IS CASE 3");
                break;
            case 4:
                passengerDetVBox.getChildren().addAll(passengerDet, passengerDet2, passengerDet3, passengerDet4);
                System.err.println(searchInfo.getPassengers()+" passengers selected");
                System.err.println("THIS IS CASE 4");
                break;

            default:
                break;
        }

        VBox vbiggg = new VBox(15);
        vbiggg.getChildren().addAll(wejustneed, passengerdetails);

        Label Payment = new Label("Payment info");
        Label note1 = new Label("Note: (If you leave the fields empty you can still pay the reservation later,");
        Label note2 = new Label("but if you don't do it, the reservation will be canceled in 2 weeks from now)");

        VBox vpayment = new VBox(6);
        vpayment.getChildren().addAll(Payment, note1, note2);

        ImageView mastercard = new ImageView(new Image("assets//images//mastercard.png"));
        mastercard.setFitHeight(100);
        mastercard.setFitWidth(100);
        ImageView visa = new ImageView(new Image("assets//images//visa.jpg"));
        visa.setFitHeight(100);
        visa.setFitWidth(100);
        ImageView visa_electron = new ImageView(new Image("assets//images//visa-electron.png"));
        visa_electron.setFitHeight(100);
        visa_electron.setFitWidth(100);
        ImageView maestro = new ImageView(new Image("assets//images//Maestro.png"));
        maestro.setFitHeight(100);
        maestro.setFitWidth(100);

        HBox himages = new HBox(30);
        himages.getChildren().addAll(mastercard, visa, visa_electron, maestro);

        Label holderName = new Label("Card holder name:");
        TextField holderField = new TextField();
        holderField.setPromptText("Name");
        VBox holder = new VBox(10);
        holder.getChildren().addAll(holderName,holderField);

        Label cardNo = new Label("Card no:");
        TextField noField = new TextField();
        noField.setPromptText("card number");
        VBox number = new VBox(10);
        number.getChildren().addAll(cardNo,noField);

        Label expirationDate= new Label("Expiration date:");
        expirationDate.setAlignment(Pos.TOP_CENTER);
        TextField expirationYear = new TextField();
        expirationYear.setPromptText("YYYY");
        expirationYear.setMaxWidth(50);
        Label dash = new Label("/");
        TextField expirationMounth = new TextField();
        expirationMounth.setPromptText("MM");
        expirationMounth.setMaxWidth(35);
        HBox fields = new HBox(5);
        fields.getChildren().addAll(expirationMounth,dash,expirationYear);
        VBox expiration = new VBox(10);
        expiration.getChildren().addAll(expirationDate,fields);

        Label securityCode = new Label("Security code:");
        TextField cvv = new TextField();
        cvv.setPromptText("CVV");
        cvv.setMaxWidth(50);
        VBox security = new VBox(10);
        security.getChildren().addAll(securityCode,cvv);

        HBox hcard = new HBox(15);
        hcard.getChildren().addAll(holder, number, expiration, security);

        Label pricel = new Label("Price");

        HBox hloc = new HBox(5);
        Label arrowlabel = new Label("->");
        Label fromvalue1 = new Label(flight.getDeparture_loc().getCity());
        Label tovalue1 = new Label(flight.getArrival_loc().getCity());
        hloc.getChildren().addAll(fromvalue1, arrowlabel, tovalue1);
        Label pricevalue1 = new Label(data.getFlightPrice(flight.getFlight_id())*searchInfo.getPassengers()+" DKK");
        HBox hedge = new HBox(35);
        hedge.getChildren().addAll(hloc, pricevalue1);

        Label choiceboxlabel = new Label("Empty");

        ImageView line = new ImageView(new Image("assets//images//dash.png"));
        line.setFitHeight(5);
        line.setFitWidth(400);

        Label totalprice = new Label("TOTAL PRICE:");

        totalpricevalue = data.getFlightPrice(flight.getFlight_id()*searchInfo.getPassengers());

        bigvalue.setText(totalpricevalue+"");
        cbbaggage.setOnAction(e -> {
                    if (!(cbbaggage.getSelectionModel().getSelectedItem() == null)) {
                        if (cbbaggage.getSelectionModel().getSelectedItem().equals("None - 0 DKK")) {
                            choiceboxlabel.setText("None - 0 DKK");
                            bigvalue.setText(totalpricevalue+"");
                        } else if (cbbaggage.getSelectionModel().getSelectedItem().equals("Baggage, Max 15 Kg. - 50 DKK")) {
                            choiceboxlabel.setText("Baggage, Max 15 Kg. - 50 DKK");
                            bigvalue.setText(totalpricevalue+50+"");
                        } else if (cbbaggage.getSelectionModel().getSelectedItem().equals("Baggage, Max 20 Kg - 90 DKK")) {
                            choiceboxlabel.setText("Baggage, Max 20 Kg - 90 DKK");
                            bigvalue.setText(totalpricevalue+90+"");
                        }
                    }
                }
        );
        cbbaggage2.setOnAction(e -> {
                    if (!(cbbaggage2.getSelectionModel().getSelectedItem() == null)) {
                        if (cbbaggage2.getSelectionModel().getSelectedItem().equals("None - 0 DKK")) {
                            choiceboxlabel.setText("None - 0 DKK");
                            bigvalue.setText(totalpricevalue+"");
                        } else if (cbbaggage2.getSelectionModel().getSelectedItem().equals("Baggage, Max 15 Kg. - 50 DKK")) {
                            choiceboxlabel.setText("Baggage, Max 15 Kg. - 50 DKK");
                            bigvalue.setText(totalpricevalue+50+"");
                        } else if (cbbaggage2.getSelectionModel().getSelectedItem().equals("Baggage, Max 20 Kg - 90 DKK")) {
                            choiceboxlabel.setText("Baggage, Max 20 Kg - 90 DKK");
                            bigvalue.setText(totalpricevalue+90+"");
                        }
                    }
                }
        );
       cbbaggage3.setOnAction(e -> {
                    if (!(cbbaggage3.getSelectionModel().getSelectedItem() == null)) {
                        if (cbbaggage3.getSelectionModel().getSelectedItem().equals("None - 0 DKK")) {
                            choiceboxlabel.setText("None - 0 DKK");
                            bigvalue.setText(totalpricevalue+"");
                        } else if (cbbaggage3.getSelectionModel().getSelectedItem().equals("Baggage, Max 15 Kg. - 50 DKK")) {
                            choiceboxlabel.setText("Baggage, Max 15 Kg. - 50 DKK");
                            bigvalue.setText(totalpricevalue+50+"");
                        } else if (cbbaggage3.getSelectionModel().getSelectedItem().equals("Baggage, Max 20 Kg - 90 DKK")) {
                            choiceboxlabel.setText("Baggage, Max 20 Kg - 90 DKK");
                            bigvalue.setText(totalpricevalue+90+"");
                        }
                    }
                }
        );
        cbbaggage4.setOnAction(e -> {
                    if (!(cbbaggage4.getSelectionModel().getSelectedItem() == null)) {
                        if (cbbaggage4.getSelectionModel().getSelectedItem().equals("None - 0 DKK")) {
                            choiceboxlabel.setText("None - 0 DKK");
                            bigvalue.setText(totalpricevalue+"");
                        } else if (cbbaggage4.getSelectionModel().getSelectedItem().equals("Baggage, Max 15 Kg. - 50 DKK")) {
                            choiceboxlabel.setText("Baggage, Max 15 Kg. - 50 DKK");
                            bigvalue.setText(totalpricevalue+50+"");
                        } else if (cbbaggage4.getSelectionModel().getSelectedItem().equals("Baggage, Max 20 Kg - 90 DKK")) {
                            choiceboxlabel.setText("Baggage, Max 20 Kg - 90 DKK");
                            bigvalue.setText(totalpricevalue+90+"");
                        }
                    }
                }
        );

        HBox htot = new HBox(30);
        htot.getChildren().addAll(totalprice, bigvalue);

        VBox vforprice = new VBox(5);
        vforprice.getChildren().addAll(pricel, hedge, choiceboxlabel, line, htot);

        vbig.getChildren().addAll(vbiggg, passengerDetVBox, vpayment, himages, hcard, vforprice);

        Scene scene = new Scene(layout, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
