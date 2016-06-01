package views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.*;
import services.DataController;
import services.components.checkLogin;
import services.components.searchInfo;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SelectedFlight extends Application {
    Flight flight           = new Flight();
    searchInfo searchInfo   = new searchInfo();
    static DataController data     = new DataController();
    double price1 = 0, price2 = 0, price3 = 0, price4 = 0;

    static String cardType;
    Label bigvalue          = new Label();
    HBox choiceboxesHBox    = new HBox(5);
    ArrayList<Integer> excludeSeats      = new ArrayList<>();
    static int ok;

    public SelectedFlight(Flight flightItem, searchInfo searchInfoItem) {
        flight = flightItem;
        searchInfo = searchInfoItem;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Selected flight");

        Label flightdetails = new Label("Flight details");
        flightdetails.getStyleClass().add("selectedFlightSubtitle");
        flightdetails.setAlignment(Pos.CENTER);

        HBox layout = new HBox(2);

        VBox vbig = new VBox(30);

        layout.getChildren().addAll(vbig);
        String css = this.getClass().getResource("/assets/styles/style.css").toExternalForm();
        layout.getStylesheets().add(css);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);

        //setting the content for the second vbox

        Label outbound = new Label("Departure");
        outbound.getStyleClass().addAll("bold", "p");
        Label labeloutbound = new Label();
        labeloutbound.setText(searchInfo.getStart_date());
        labeloutbound.getStyleClass().addAll("bold", "p");

        //setting css to the outbound label, and for the value of the labeloutbound
//        outbound.getStyleClass().add("");
//        labeloutbound.getStyleClass().add("");

        HBox hout = new HBox(10);
        hout.getChildren().addAll(outbound, labeloutbound);

        Label flightlabel = new Label("Flight ID");
        Label date = new Label("Date & Time");
        Label fromto = new Label("From -> To");
        Label duration = new Label("Duration");
        Label price = new Label("Price/Person");

        flightlabel.getStyleClass().addAll("bold", "p");
        date.getStyleClass().addAll("bold", "p");
        fromto.getStyleClass().addAll("bold", "p");
        duration.getStyleClass().addAll("bold", "p");
        price.getStyleClass().addAll("bold", "p");

        //setting css to the labels declared aboce
        flightlabel.getStyleClass().add("label");
        date.getStyleClass().add("label");
        fromto.getStyleClass().add("label");
        duration.getStyleClass().add("label");
        price.getStyleClass().add("label");

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

        HBox houtboundflight = new HBox(13);
        houtboundflight.getChildren().addAll(v1, v2, v3, v4, v5);

        VBox vfirst = new VBox(8);
        vfirst.getChildren().addAll(flightdetails, hout, houtboundflight);

        Button bookreservation = new Button("Book reservation");
        Button back = new Button("Back");
        back.setOnAction(event -> {
            searchResults results = new searchResults(searchInfo);
            results.start(primaryStage);
        });

        HBox hbuttons = new HBox(5);
        hbuttons.getChildren().addAll(bookreservation, back);
        hbuttons.setAlignment(Pos.BASELINE_RIGHT);

        //asigning css to the buttons
        bookreservation.getStyleClass().addAll("btn", "btn-info");
        back.getStyleClass().addAll("btn", "btn-danger");

        // setting the content for the first vbox, vbig
        Label wejustneed = new Label("We just need a few more details. Who is traveling?");
        wejustneed.setAlignment(Pos.TOP_CENTER);
        wejustneed.getStyleClass().add("selectedFlightTitle");

        /* =========================
                Passenger row 1
           ========================= */

        // Passenger name
        Label passengername         = new Label("Passenger name:");
        TextField tfpassengername   = new TextField();
        tfpassengername.setPromptText("Passenger's name");
        tfpassengername.setMaxWidth(178);
        VBox vpass1                 = new VBox(4);
        vpass1.getChildren().addAll(passengername, tfpassengername);


        // Birth date picker
        Label birthdate             = new Label("Birth date:");
        DatePicker dpbirthdate      = new DatePicker();
        VBox vpass2                 = new VBox(4);
        vpass2.getChildren().addAll(birthdate, dpbirthdate);


        // Seats number dropdown
        Label seatno                = new Label("Seat no.");
        ChoiceBox cbseatno          = new ChoiceBox();
        cbseatno.setMaxWidth(40);
        cbseatno.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType(), excludeSeats));
        VBox vpass3                 = new VBox(4);
        vpass3.getChildren().addAll(seatno, cbseatno);


        // Baggage dropdown
        Label checkedbaggage        = new Label("Checked baggage");
        ChoiceBox cbbaggage         = new ChoiceBox();
        cbbaggage.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg. - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");
        VBox vpass4                 = new VBox(4);
        vpass4.getChildren().addAll(checkedbaggage, cbbaggage);
        HBox passengerDet           = new HBox(20);
        passengerDet.getChildren().addAll(vpass1, vpass2, vpass3, vpass4);
        /////////////////////////////////////////////////////////////////////////////


        /* =========================
                Passenger row 2
           ========================= */

        // Passenger name
        Label passengername2        = new Label("Passenger name:");
        TextField tfpassengername2  = new TextField();
        tfpassengername2.setPromptText("Passenger's name");
        tfpassengername2.setMaxWidth(178);
        VBox vpass12                = new VBox(4);
        vpass12.getChildren().addAll(passengername2, tfpassengername2);

        // Birth date picker
        Label birthdate2            = new Label("Birth date:");
        DatePicker dpbirthdate2     = new DatePicker();
        VBox vpass22                = new VBox(4);
        vpass22.getChildren().addAll(birthdate2, dpbirthdate2);

        // Seats number dropdown
        Label seatno2               = new Label("Seat no.");
        ChoiceBox cbseatno2         = new ChoiceBox();
        cbseatno2.setMaxWidth(40);
        cbseatno2.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType(), excludeSeats));
        VBox vpass32                = new VBox(4);
        vpass32.getChildren().addAll(seatno2, cbseatno2);

        // Baggage dropdown
        Label checkedbaggage2       = new Label("Checked baggage");
        ChoiceBox cbbaggage2        = new ChoiceBox();
        cbbaggage2.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg. - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");
        VBox vpass42                = new VBox(4);
        vpass42.getChildren().addAll(checkedbaggage2, cbbaggage2);
        HBox passengerDet2          = new HBox(20);
        passengerDet2.getChildren().addAll(vpass12, vpass22, vpass32, vpass42);
        /////////////////////////////////////////////////////////////////////////////

        /* =========================
                Passenger row 3
           ========================= */

        // Passenger name
        Label passengername3        = new Label("Passenger name:");
        TextField tfpassengername3  = new TextField();
        tfpassengername3.setPromptText("Passenger's name");
        tfpassengername3.setMaxWidth(178);
        VBox vpass13                = new VBox(4);
        vpass13.getChildren().addAll(passengername3, tfpassengername3);

        // Birth date picker
        Label birthdate3            = new Label("Birth date:");
        DatePicker dpbirthdate3     = new DatePicker();
        VBox vpass23                = new VBox(4);
        vpass23.getChildren().addAll(birthdate3, dpbirthdate3);


        // Seats number dropdown
        Label seatno3               = new Label("Seat no.");
        ChoiceBox cbseatno3         = new ChoiceBox();
        cbseatno3.setMaxWidth(40);
        cbseatno3.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType(), excludeSeats));
        VBox vpass33                = new VBox(4);
        vpass33.getChildren().addAll(seatno3, cbseatno3);


        // Baggage dropdown
        Label checkedbaggage3       = new Label("Checked baggage");
        ChoiceBox cbbaggage3        = new ChoiceBox();
        cbbaggage3.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg. - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");
        VBox vpass43                = new VBox(4);
        vpass43.getChildren().addAll(checkedbaggage3, cbbaggage3);
        HBox passengerDet3          = new HBox(20);
        passengerDet3.getChildren().addAll(vpass13, vpass23, vpass33, vpass43);
        /////////////////////////////////////////////////////////////////////////////

        /* =========================
                Passenger row 4
           ========================= */

        // Passenger name
        Label passengername4        = new Label("Passenger name:");
        TextField tfpassengername4  = new TextField();
        tfpassengername4.setPromptText("Passenger's name");
        tfpassengername4.setMaxWidth(178);
        VBox vpass14                = new VBox(4);
        vpass14.getChildren().addAll(passengername4, tfpassengername4);


        // Birth date picker
        Label birthdate4            = new Label("Birth date:");
        DatePicker dpbirthdate4     = new DatePicker();
        VBox vpass24                = new VBox(4);
        vpass24.getChildren().addAll(birthdate4, dpbirthdate4);


        // Seats number dropdown
        Label seatno4               = new Label("Seat no.");
        ChoiceBox cbseatno4         = new ChoiceBox();
        cbseatno4.setMaxWidth(40);
        cbseatno4.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType(), excludeSeats));
        VBox vpass34                = new VBox(4);
        vpass34.getChildren().addAll(seatno4, cbseatno4);


        // Baggage dropdown
        Label checkedbaggage4       = new Label("Checked baggage");
        ChoiceBox cbbaggage4        = new ChoiceBox();
        cbbaggage4.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg. - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");
        VBox vpass44                = new VBox(4);
        vpass44.getChildren().addAll(checkedbaggage4, cbbaggage4);
        HBox passengerDet4          = new HBox(20);
        passengerDet4.getChildren().addAll(vpass14, vpass24, vpass34, vpass44);
        /////////////////////////////////////////////////////////////////////////////

        /* Assinging CSS to all the labels,
           for as many passenger HBoxes as needed in the interface
         */

        passengername.getStyleClass().addAll("label");
        passengerDet2.getStyleClass().addAll("label");
        passengerDet3.getStyleClass().addAll("label");
        passengerDet4.getStyleClass().addAll("label");

        tfpassengername.getStyleClass().addAll("label");
        tfpassengername2.getStyleClass().addAll("label");
        tfpassengername3.getStyleClass().addAll("label");
        tfpassengername4.getStyleClass().addAll("label");

        birthdate.getStyleClass().add("label");
        birthdate2.getStyleClass().addAll("label");
        birthdate3.getStyleClass().addAll("label");
        birthdate4.getStyleClass().addAll("label");

        dpbirthdate.getStyleClass().add("label");
        dpbirthdate2.getStyleClass().addAll("label");
        dpbirthdate3.getStyleClass().addAll("label");
        dpbirthdate4.getStyleClass().addAll("label");

        seatno.getStyleClass().add("label");
        seatno2.getStyleClass().addAll("label");
        seatno3.getStyleClass().addAll("label");
        seatno4.getStyleClass().addAll("label");

        cbseatno.getStyleClass().add("label");
        cbseatno2.getStyleClass().addAll("label");
        cbseatno3.getStyleClass().addAll("label");
        cbseatno4.getStyleClass().addAll("label");

        checkedbaggage.getStyleClass().add("label");
        checkedbaggage2.getStyleClass().addAll("label");
        checkedbaggage3.getStyleClass().addAll("label");
        checkedbaggage4.getStyleClass().addAll("label");


        // Update the seats dropboxes here because declaration position issue.
        // Here all of them are declared.


        // First seats dropdown
        cbseatno.setOnAction(e -> {

            // Create an exclude list
            excludeSeats.clear();
            excludeSeats.add(Integer.parseInt(cbseatno.getValue().toString()));
            excludeSeats.addAll(FXCollections.observableArrayList(services.DataController.getReservedSeats()));

            // Clear list and add a new one excluding the selected ones
            cbseatno2.getItems().clear();
            cbseatno2.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType(), excludeSeats));

            System.out.println("Exclude: " + excludeSeats);
        });

        cbseatno2.setOnAction(e -> {
            // Clear all items
            //cbseatno2.getItems().clear();

            // Add the list again but ignore the value selected in the first combobox
            excludeSeats.clear();
            excludeSeats.add(Integer.parseInt(cbseatno.getValue().toString()));
            excludeSeats.add(Integer.parseInt(cbseatno2.getValue().toString()));
            excludeSeats.addAll(FXCollections.observableArrayList(services.DataController.getReservedSeats()));

            // Clear list and add a new one excluding the selected ones
            cbseatno3.getItems().clear();
            cbseatno3.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType(), excludeSeats));

            System.out.println("Exclude: " + excludeSeats);
        });

        cbseatno3.setOnAction(e -> {
            // Clear all items
            //cbseatno2.getItems().clear();

            // Add the list again but ignore the value selected in the first combobox
            excludeSeats.clear();
            excludeSeats.add(Integer.parseInt(cbseatno.getValue().toString()));
            excludeSeats.add(Integer.parseInt(cbseatno2.getValue().toString()));
            excludeSeats.add(Integer.parseInt(cbseatno3.getValue().toString()));
            excludeSeats.addAll(FXCollections.observableArrayList(services.DataController.getReservedSeats()));

            // Clear list and add a new one excluding the selected ones
            cbseatno4.getItems().clear();
            cbseatno4.setItems(data.getSeatsForClass(flight.getFlight_id(), searchInfo.getClassType(), excludeSeats));


            System.out.println("Exclude: " + excludeSeats);
        });

        // these are needed for the price down the scene, each of them has to be shown deppending on how many passengers were chosen
        Label aa = new Label("Pass. 1: ");
        Label firstpass = new Label("");
        Label bb = new Label("Pass. 2: ");
        Label secondpass = new Label("");
        Label cc = new Label("Pass. 3: ");
        Label thirdpass = new Label("");
        Label dd = new Label("Pass. 4: ");
        Label fourthpass = new Label("");

        VBox passengerDetVBox = new VBox(10);

        switch(searchInfo.getPassengers()) {
            case 1:
                price1 = data.getFlightPrice(flight.getFlight_id());
                passengerDetVBox.getChildren().addAll(passengerDet);
                choiceboxesHBox.getChildren().addAll(aa, firstpass);
                break;
            case 2:
                price1 = data.getFlightPrice(flight.getFlight_id());
                price2 = data.getFlightPrice(flight.getFlight_id());
                passengerDetVBox.getChildren().addAll(passengerDet, passengerDet2);
                choiceboxesHBox.getChildren().addAll(aa, firstpass, bb, secondpass);
                break;
            case 3:
                price1 = data.getFlightPrice(flight.getFlight_id());
                price2 = data.getFlightPrice(flight.getFlight_id());
                price3 = data.getFlightPrice(flight.getFlight_id());
                passengerDetVBox.getChildren().addAll(passengerDet, passengerDet2, passengerDet3);
                choiceboxesHBox.getChildren().addAll(aa, firstpass, bb, secondpass, cc, thirdpass);
                break;
            case 4:
                price1 = data.getFlightPrice(flight.getFlight_id());
                price2 = data.getFlightPrice(flight.getFlight_id());
                price3 = data.getFlightPrice(flight.getFlight_id());
                price4 = data.getFlightPrice(flight.getFlight_id());
                passengerDetVBox.getChildren().addAll(passengerDet, passengerDet2, passengerDet3, passengerDet4);
                choiceboxesHBox.getChildren().addAll(aa, firstpass, bb, secondpass, cc, thirdpass, dd, fourthpass);
                break;

            default:
                break;
        }

        Label passengerdetails = new Label("Passenger(s) details");
        passengerdetails.setAlignment(Pos.CENTER);
        passengerdetails.getStyleClass().add("selectedFlightSubtitle");

        VBox vbiggg             = new VBox(15);
        vbiggg.getChildren().addAll(wejustneed);

        Label Payment           = new Label("Payment info");
        Payment.getStyleClass().add("selectedFlightSubtitle");
        Label note1             = new Label("Note: (If you leave the fields empty you can still pay the reservation later,");
        Label note2             = new Label("but if you don't do it, the reservation will be canceled in 2 weeks from now)");

        VBox vpayment           = new VBox(6);
        vpayment.getChildren().addAll(Payment, note1, note2);

        // declaring the radio buttons
        // by placing them in a ToggleGroup I make sure only one of them can be selected at a time

        ToggleGroup group           = new ToggleGroup();

        RadioButton mastercard      = new RadioButton("MasterCard");
        mastercard.setToggleGroup(group);
        mastercard.setOnAction(event -> cardType = "mastercard");
        RadioButton visa            = new RadioButton("Visa");
        visa.setToggleGroup(group);
        visa.setOnAction(event -> cardType = "visa");
        RadioButton visa_electron   = new RadioButton("Visa Electron");
        visa_electron.setToggleGroup(group);
        visa_electron.setOnAction(event -> cardType = "visaelectron");
        RadioButton maestro         = new RadioButton("Maestro");
        maestro.setToggleGroup(group);
        maestro.setOnAction(event -> cardType = "maestro");


        HBox himages            = new HBox(30);
        himages.getChildren().addAll(mastercard, visa, visa_electron, maestro);

        Label holderName        = new Label("Card holder name:");
        holderName.getStyleClass().add("label");
        TextField holderField   = new TextField();
        holderField.getStyleClass().add("cardHolderInput");
        holderField.setPromptText("Name");
        VBox holder             = new VBox(10);
        holder.getChildren().addAll(holderName,holderField);

        Label cardNo            = new Label("Card no:");
        cardNo.getStyleClass().add("label");
        TextField noField       = new TextField();
        noField.getStyleClass().add("noInput");
        noField.setPromptText("card number");
        VBox number             = new VBox(10);
        number.getChildren().addAll(cardNo,noField);

        Label expirationDate    = new Label("Expiration date:");
        expirationDate.getStyleClass().add("label");
        expirationDate.setAlignment(Pos.TOP_CENTER);
        TextField expirationYear= new TextField();
        expirationYear.getStyleClass().add("expYearInput");
        expirationYear.setPromptText("YYYY");
        expirationYear.setMaxWidth(50);
        Label dash              = new Label("/");
        TextField expirationMounth = new TextField();
        expirationMounth.setPromptText("MM");
        expirationMounth.getStyleClass().add("expMoInput");
        expirationMounth.setMaxWidth(35);
        HBox fields             = new HBox(5);
        fields.getChildren().addAll(expirationMounth,dash,expirationYear);
        VBox expiration         = new VBox(10);
        expiration.getChildren().addAll(expirationDate,fields);

        // Book reservation
        bookreservation.setOnAction(event -> {

            ArrayList<Passenger> passengersList = new ArrayList<>();

            passengersList.add(new Passenger(0, getBaggageType(cbbaggage.getSelectionModel().getSelectedItem().toString()), formatBirthDate(dpbirthdate.getValue().toString()), tfpassengername.getText(), Integer.parseInt(cbseatno.getValue().toString())));
            System.out.println("Nr1" + searchInfo.getPassengers());
            if (searchInfo.getPassengers() >= 2) {
                System.out.println("Nr2" + searchInfo.getPassengers());
                passengersList.add(new Passenger(0, getBaggageType(cbbaggage2.getSelectionModel().getSelectedItem().toString()), formatBirthDate(dpbirthdate2.getValue().toString()), tfpassengername2.getText(), Integer.parseInt(cbseatno2.getValue().toString())));
            }
            if (searchInfo.getPassengers() >= 3) {
                System.out.println("Nr3" + searchInfo.getPassengers());
                passengersList.add(new Passenger(0, getBaggageType(cbbaggage3.getSelectionModel().getSelectedItem().toString()), formatBirthDate(dpbirthdate3.getValue().toString()), tfpassengername3.getText(), Integer.parseInt(cbseatno3.getValue().toString())));
            }
            if (searchInfo.getPassengers() >= 4) {
                System.out.println("Nr4" + searchInfo.getPassengers());
                passengersList.add(new Passenger(0, getBaggageType(cbbaggage4.getSelectionModel().getSelectedItem().toString()), formatBirthDate(dpbirthdate4.getValue().toString()), tfpassengername4.getText(), Integer.parseInt(cbseatno4.getValue().toString())));
            }
            String status;

            int loginid = services.components.checkLogin.getAccount_id();

            // Change this if we have data in payment shit!
            if (noField.getText().equals("") || expirationYear.getText().equals("") ||expirationMounth.getText().equals("") || holderField.getText().equals("")) {
                status = "booked";
            } else {

                if (ok != 1) {
                    if(data.checkPayment(checkLogin.getAccount_id())) {
                        String expirationD = String.format("%s/%s/28",expirationYear.getText(),expirationMounth.getText());
                        data.setPayment(loginid, cardType, Integer.parseInt(noField.getText()), expirationD, holderField.getText(), data.getPayment(loginid).getPayment_id());
                    } else {
                        String expirationD = String.format("%s/%s/28",expirationYear.getText(),expirationMounth.getText());
                        data.setPayment(loginid, cardType, Integer.parseInt(noField.getText()), expirationD, holderField.getText(), 0);
                    }
                }
                status = "confirmed";
            }

            // Call createReservation
            data.createReservation(status, flight.getFlight_id(), checkLogin.getAccount_id(), passengersList);

            searchResults results = new searchResults(searchInfo);
            results.start(primaryStage);
        });
        if(data.checkPayment(checkLogin.getAccount_id())) {
            System.err.println("We have shit");
             Payment payment = data.getPayment(checkLogin.getAccount_id());


            // mastercard, visa, visa_electron, maestro
            switch(payment.getCard_type()) {
                case "visa":
                    visa.setSelected(true);
                    break;
                case "mastercard":
                    mastercard.setSelected(true);
                    cardType = "mastercard";
                    break;
                case "visa_electron":
                    visa_electron.setSelected(true);
                    cardType = "visaelectron";
                    break;
                case "maestro":
                    maestro.setSelected(true);
                    cardType = "maestro";
                    break;
            }

            noField.setText(Integer.toString(payment.getCard_no()));
            expirationYear.setText(payment.getCard_expiration().substring(0, 4));
            expirationMounth.setText(payment.getCard_expiration().substring(5, 7));
            holderField.setText(payment.getCardHolder_name());
            ok = 1;

        }

        HBox hcard              = new HBox(15);
        hcard.getChildren().addAll(holder, number, expiration);

        Label pricel            = new Label("Price for "+searchInfo.getPassengers()+" people:");
        pricel.getStyleClass().add("label");

        HBox hloc               = new HBox(5);
        Label arrowlabel        = new Label("->");
        Label fromvalue1        = new Label(flight.getDeparture_loc().getCity());
        Label tovalue1          = new Label(flight.getArrival_loc().getCity());
        hloc.getChildren().addAll(fromvalue1, arrowlabel, tovalue1);
        Label pricevalue1       = new Label(data.getFlightPrice(flight.getFlight_id())*searchInfo.getPassengers()+" DKK");
        HBox hedge              = new HBox(35);
        hedge.getChildren().addAll(hloc, pricevalue1);

        ImageView line          = new ImageView(new Image("assets//images//dash.png"));
        line.setFitHeight(5);
        line.setFitWidth(400);

        Label totalprice        = new Label("TOTAL PRICE:");
        totalprice.getStyleClass().add("label");

        bigvalue.setText(price1+price2+price3+price4+"");

        // functionality for the first passenger's choice box for the baggage
        cbbaggage.setOnAction(e -> {
            price1 = data.getFlightPrice(flight.getFlight_id());
            bigvalue.setText(price1+price2+price3+price4+"");
            if (!(cbbaggage.getSelectionModel().getSelectedItem() == null)) {
                        if (cbbaggage.getSelectionModel().getSelectedItem().equals("None - 0 DKK")) {
                            price1+=0;
                            firstpass.setText("0 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        } else if (cbbaggage.getSelectionModel().getSelectedItem().equals("Baggage, Max 15 Kg. - 50 DKK")) {
                            price1+=50;
                            firstpass.setText("50 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        } else if (cbbaggage.getSelectionModel().getSelectedItem().equals("Baggage, Max 20 Kg - 90 DKK")) {
                            price1+=90;
                            firstpass.setText("90 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        }
                    }
                }
        );

        // functionality for the second passenger's choice box for the baggage
        cbbaggage2.setOnAction(e -> {
            price2 = data.getFlightPrice(flight.getFlight_id());
            bigvalue.setText(price1+price2+price3+price4+"");
            if (!(cbbaggage2.getSelectionModel().getSelectedItem() == null)) {
                        if (cbbaggage2.getSelectionModel().getSelectedItem().equals("None - 0 DKK")) {
                            price2+=0;
                            secondpass.setText("0 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        } else if (cbbaggage2.getSelectionModel().getSelectedItem().equals("Baggage, Max 15 Kg. - 50 DKK")) {
                            price2+=50;
                            secondpass.setText("50 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        } else if (cbbaggage2.getSelectionModel().getSelectedItem().equals("Baggage, Max 20 Kg - 90 DKK")) {
                            price2+=90;
                            secondpass.setText("90 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        }
                    }
                }
        );

        // functionality for the third passenger's choice box for the baggage
       cbbaggage3.setOnAction(e -> {
           price3 = data.getFlightPrice(flight.getFlight_id());
           bigvalue.setText(price1+price2+price3+price4+"");
           if (!(cbbaggage3.getSelectionModel().getSelectedItem() == null)) {
                        if (cbbaggage3.getSelectionModel().getSelectedItem().equals("None - 0 DKK")) {
                            price3+=0;
                            thirdpass.setText("0 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        } else if (cbbaggage3.getSelectionModel().getSelectedItem().equals("Baggage, Max 15 Kg. - 50 DKK")) {
                            price3+=50;
                            thirdpass.setText("50 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        } else if (cbbaggage3.getSelectionModel().getSelectedItem().equals("Baggage, Max 20 Kg - 90 DKK")) {
                            price3+=90;
                            thirdpass.setText("90 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        }
                    }
                }
        );

        // functionality for the fourth passenger's choice box for the baggage
        cbbaggage4.setOnAction(e -> {
            price4 = data.getFlightPrice(flight.getFlight_id());
            bigvalue.setText(price1+price2+price3+price4+"");
            if (!(cbbaggage4.getSelectionModel().getSelectedItem() == null)) {
                        if (cbbaggage4.getSelectionModel().getSelectedItem().equals("None - 0 DKK")) {
                            price4+=0;
                            fourthpass.setText("0 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        } else if (cbbaggage4.getSelectionModel().getSelectedItem().equals("Baggage, Max 15 Kg. - 50 DKK")) {
                            price4+=50;
                            fourthpass.setText("50 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        } else if (cbbaggage4.getSelectionModel().getSelectedItem().equals("Baggage, Max 20 Kg - 90 DKK")) {
                            price4+=90;
                            fourthpass.setText("90 kr.");
                            bigvalue.setText(price1+price2+price3+price4+"");
                        }
                    }
                }
        );

        HBox htot = new HBox(30);
        htot.getChildren().addAll(totalprice, bigvalue);

        VBox vforprice = new VBox(5);
        vforprice.getChildren().addAll(pricel, hedge , choiceboxesHBox, line, htot);

        HBox footer = new HBox();
        footer.getChildren().addAll(vforprice, hbuttons);

        vbig.getChildren().addAll(vbiggg, vfirst, passengerDetVBox, vpayment, himages, hcard, footer);

        Scene scene = new Scene(scrollPane, 950, 870);
        scrollPane.setPadding(new Insets(20,20,20,20));
        scrollPane.getStyleClass().addAll("SelectedFlightBlock");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static int getBaggageType(String baggageItem) {
        int selectedBaggage = 0;
        if (baggageItem.equals("None - 0 DKK")) {
            selectedBaggage = 0;
        } else if (baggageItem.equals("Baggage, Max 15 Kg. - 50 DKK")){
            selectedBaggage = 1;
        }else if (baggageItem.equals("Baggage, Max 20 Kg - 90 DKK")){
            selectedBaggage = 2;
        }

        return selectedBaggage;
    }

    public static Timestamp formatBirthDate(String birthDate) {
        Timestamp timeStampDate = null;
        // int id, int baggage, java.sql.Timestamp birth_date, String name, int seat_no

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormatted = formatter.parse(birthDate);
            timeStampDate = new Timestamp(dateFormatted.getTime());
        } catch(ParseException pe) {}

        return timeStampDate;
    }

}
