package views;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import services.components.searchInfo;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Flight;
import services.DataController;

import java.text.SimpleDateFormat;

public class BookTicketPopUp {

    DataController data = new DataController();
    searchInfo searchInfo = new searchInfo();
    Flight flight;

    public BookTicketPopUp(Flight flightItem, searchInfo srchInfo) {
        searchInfo = srchInfo;
        flight = flightItem;
    }

    public void start() {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        // this is the big VBox, that will contain everything
        VBox layout = new VBox(20);

        HBox h1 = new HBox(5); // for the check ticket info
        VBox h2 = new VBox(5); // for the OUTBOUND tableview
        VBox h3 = new VBox(5); // for the RETURN tableview
        HBox h4 = new HBox(5); // for the Select Flight and Close Buttons

        //everything for the first horizontal box
        Label title = new Label("Book your ticket");
        Label checkroutes = new Label("Check your routes");

        Label checkticket = new Label("Check ticket info");
        String wayAndPassangers;

        if(searchInfo.hasReturnDate()) {
            wayAndPassangers = "Return for ";
        } else {
            wayAndPassangers = "One way for ";
        }

        if(searchInfo.getPassengers() == 1) {
            wayAndPassangers += searchInfo.getPassengers() + " Passenger";
        } else {
            wayAndPassangers += searchInfo.getPassengers() + " Passengers";
        }

        Label returnfor = new Label(wayAndPassangers);
        String totalPrice = "";

        totalPrice = "Total price: kr. " + (searchInfo.getPassengers() * (flight.getFlight_price() + data.getClassPrice(flight.getFlight_id(), searchInfo.getClassType()))) + " (" + searchInfo.getClassType() + ")";

        Label total = new Label(totalPrice);

        VBox small = new VBox(3);
        small.getChildren().addAll(checkticket, returnfor, total);

        h1.getChildren().addAll(small);

        //everything for the first flight details box
        Label outboundtitle = new Label("OUTBOUND - " + searchInfo.getStart_date());

        VBox flightPlaneBox = new VBox();
        Label flightPlaneLabel = new Label("Flight/Plane");
        Label flightPlaneValue = new Label(flight.getFlight_id() + "\n" + flight.getPlane().getReg_no());
        flightPlaneBox.getChildren().addAll(flightPlaneLabel, flightPlaneValue);

        VBox timeBox = new VBox();
        Label timeLabel = new Label("Time");
        String dep_time = new SimpleDateFormat("HH:mm:ss").format(flight.getDeparture_time());
        String arr_time = new SimpleDateFormat("HH:mm:ss").format(flight.getArrival_time());
        Label timeValue = new Label(dep_time + "\n" + arr_time);
        timeBox.getChildren().addAll(timeLabel, timeValue);

        VBox locationBox = new VBox();
        Label locationLabel = new Label("Location");
        Label locationValue = new Label(flight.getDeparture_loc().getCity() + "(" + flight.getDeparture_loc().getAirport_code() + ")" + "\n" + flight.getArrival_loc().getCity() + "(" + flight.getArrival_loc().getAirport_code() + ")");
        locationBox.getChildren().addAll(locationLabel, locationValue);

        VBox durationBox = new VBox();
        Label durationLabel = new Label("Duration");
        String dep_datetime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(flight.getDeparture_time());
        String arr_datetime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(flight.getArrival_time());
        Label durationValue = new Label(data.getFlightDuration(dep_datetime, arr_datetime));
        durationBox.getChildren().addAll(durationLabel, durationValue);

        VBox arrivesBox = new VBox();
        Label arrivesLabel = new Label("Arrives");
        String arr_date = new SimpleDateFormat("EEE, MMM d, yyyy").format(flight.getArrival_time());
        Label arrivesValue = new Label(arr_date);
        arrivesBox.getChildren().addAll(arrivesLabel, arrivesValue);

        VBox priceBox = new VBox();
        Label priceLabel = new Label("Price");
        Label priceValue = new Label((flight.getFlight_price() + data.getClassPrice(flight.getFlight_id(), searchInfo.getClassType()) )  + " DKK");
        priceBox.getChildren().addAll(priceLabel, priceValue);

        HBox firstFlightDetails = new HBox();
        firstFlightDetails.getChildren().addAll(flightPlaneBox, timeBox, locationBox, durationBox, arrivesBox, priceBox);

        layout.getChildren().addAll(title, h1, checkroutes, firstFlightDetails, h4);
        layout.setAlignment(Pos.TOP_CENTER);

        //everything for the third horizontal box
        //Label returntitle = new Label("RETURN - Thursday, 19 May 2016");

        //everything for the fourth table
        Button selectflight = new Button("Select flight");
        selectflight.setOnAction(e-> {
            SelectedFlight SelectedFlight = new SelectedFlight(flight, searchInfo);
            SelectedFlight.start(primaryStage);
        });
        Button close = new Button("Close");
        close.setOnAction(event -> primaryStage.close());
        h4.getChildren().addAll(selectflight, close);
        h4.setAlignment(Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout, 745, 520);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book ticket");
        primaryStage.showAndWait();

    }

}
