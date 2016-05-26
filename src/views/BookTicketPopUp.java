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

    DataController controller = new DataController();
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

        layout.getChildren().addAll(title, h1, checkroutes, h2, h3, h4);

        layout.setAlignment(Pos.TOP_CENTER);

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

        switch(searchInfo.getClassType()) {
            case "Economy class":
                totalPrice = "Total price: kr. " + (searchInfo.getPassengers() * flight.getFlight_price()) + " (" + searchInfo.getClassType() + ")";
                break;
            case "Coach class":
                totalPrice = "Total price: kr. " + (searchInfo.getPassengers() * (flight.getFlight_price()+flight.getPlane().getCoachPrice())) + " (" + searchInfo.getClassType() + ")";
                break;
            case "First class":
                totalPrice = "Total price: kr. " + (searchInfo.getPassengers() * (flight.getFlight_price()+flight.getPlane().getBusinessPrice())) + " (" + searchInfo.getClassType() + ")";
                break;
        }

        Label total = new Label(totalPrice);

        VBox small = new VBox(3);
        small.getChildren().addAll(checkticket, returnfor, total);

        h1.getChildren().addAll(small);

        //everything for the second horizontal box
        Label outboundtitle = new Label("OUTBOUND - " + searchInfo.getStart_date());

        VBox flightPlaneBox = new VBox();
        Label flightPlaneLabel = new Label("Flight/Plane");
        Label flightPlaneValue = new Label(flight.getFlight_id() + "\n" + flight.getPlane().getReg_no());
        flightPlaneBox.getChildren().addAll(flightPlaneLabel, flightPlaneValue);

        VBox timeBox = new VBox();
        Label timeLabel = new Label("Time");
        Label timeValue = new Label(flight.getArrival_time().getHours() + ":" + flight.getArrival_time().getMinutes());
        timeBox.getChildren().addAll(flightPlaneLabel, flightPlaneValue);

        System.err.println(flight.getArrival_time().toString());



        //everything for the third horizontal box
        //Label returntitle = new Label("RETURN - Thursday, 19 May 2016");


        //everything for the fourth table
        Button selectflight = new Button("Select flight");
        Button close = new Button("Close");
        h4.getChildren().addAll(selectflight, close);
        h4.setAlignment(Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout, 745, 520);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book ticket");
        primaryStage.showAndWait();

    }

}
