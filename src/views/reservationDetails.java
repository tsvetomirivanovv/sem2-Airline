package views;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Interval;
import models.Passenger;
import models.Reservation;
import services.DataController;
import services.components.searchInfo;

import java.text.SimpleDateFormat;

public class reservationDetails {
    // Access Data controller
    DataController data = new DataController();

    public void start(Reservation reservation) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Label reservationDetailsTitle = new Label("Reservation details");
        reservationDetailsTitle.getStyleClass().addAll("bold", "h5");
        reservationDetailsTitle.setAlignment(Pos.CENTER);

        /* =========================
                Reservation Info
           ========================= */

        // Labels
        Label customerLabel         = new Label("Customer name: ");
        Label reservationIdLabel    = new Label("Reservation id: ");
        Label passengersNoLabel     = new Label("Passengers");
        Label totalPriceLabel       = new Label("Total price: ");
        Label statusLabel           = new Label("Status");

        // CSS for labels
        customerLabel.getStyleClass().addAll("bold", "p");
        reservationIdLabel.getStyleClass().addAll("bold", "p");
        passengersNoLabel.getStyleClass().addAll("bold", "p");
        totalPriceLabel.getStyleClass().addAll("bold", "p");
        statusLabel.getStyleClass().addAll("bold", "p");

        // Values
        Label customerValue         = new Label(reservation.getCustomer_name());
        Label reservationIdValue    = new Label(reservation.getReservation_id() + "");
        Label passengersNoValue     = new Label(reservation.getPassenger_list().size() + "");
        Label totalPriceValue       = new Label(reservation.getPrice() + " DKK");
        Label statusValue           = new Label(reservation.getStatus());

        // CSS for values
        customerValue.getStyleClass().addAll("p");
        reservationIdValue.getStyleClass().addAll("p");
        passengersNoValue.getStyleClass().addAll("p");
        totalPriceValue.getStyleClass().addAll("p");
        statusValue.getStyleClass().addAll("p");

        // Labels column
        VBox reservationLabels      = new VBox();
        reservationLabels.getChildren().addAll(customerLabel, reservationIdLabel, passengersNoLabel, totalPriceLabel, statusLabel);

        // Values column
        VBox reservationValues      = new VBox();
        reservationValues.getChildren().addAll(customerValue, reservationIdValue, passengersNoValue, totalPriceValue, statusValue);

        // Header section with Labels and Values for reservation info
        HBox reservationHeader      = new HBox();
        reservationHeader.getChildren().addAll(reservationLabels, reservationValues);



        /* =========================
                Passengers list
           ========================= */

        // Title
        Label passengersTitle          = new Label("Passengers");
        passengersTitle.getStyleClass().addAll("bold", "p");

        // Columns
        TableColumn<Passenger, String> passengerName        = new TableColumn<>("Name");
        TableColumn<Passenger, String> passengerBirthDate   = new TableColumn<>("Birth date");
        TableColumn<Passenger, Integer> passengerSeatNo     = new TableColumn<>("Seat no");
        TableColumn<Passenger, String> passengerBaggage     = new TableColumn<>("Baggage");

        // Fill with data
        passengerName.setCellValueFactory(e -> e.getValue().nameProperty());
        passengerBirthDate.setCellValueFactory(e -> {
            String birthDate = new SimpleDateFormat("dd/MM/yyyy").format(e.getValue().getBirth_date());
            ObservableValue<String> birthDateString = new ReadOnlyObjectWrapper<>(birthDate);

            return birthDateString;
        });
        passengerSeatNo.setCellValueFactory(e -> e.getValue().seat_noProperty().asObject());
        passengerBaggage.setCellValueFactory(e -> {
            String baggageType = "";

            switch(e.getValue().getBaggage()) {
                case 0:
                    baggageType = "none";
                    break;
                case 1:
                    baggageType = "small";
                    break;
                case 2:
                    baggageType = "large";
                    break;
            }

            ObservableValue<String> baggageTypeString = new ReadOnlyObjectWrapper<>(baggageType);

            return baggageTypeString;
        });

        // Table
        TableView<Passenger> passengersTable = new TableView<>();
        ObservableList<Passenger> passengersList = FXCollections.observableArrayList(reservation.getPassenger_list());
        passengersTable.setItems(passengersList);
        passengersTable.getColumns().addAll(passengerName, passengerBirthDate, passengerSeatNo, passengerBaggage);


        VBox reservationPassengers = new VBox();
        reservationPassengers.getChildren().addAll(passengersTitle, passengersTable);

        /* =========================
                Flights list
           ========================= */

        Label flightsTitle      = new Label("Flights");

        //everything for the first flight details box
        String dep_date         = new SimpleDateFormat("EEE, MMM d, yyyy").format(reservation.getFlight().getDeparture_time());
        Label outboundtitle     = new Label("Landing date - " + dep_date);

        VBox flightPlaneBox     = new VBox(5);
        Label flightPlaneLabel  = new Label("Flight/Plane");
        Label flightPlaneValue  = new Label(reservation.getFlight().getFlight_id() + "\n" + reservation.getFlight().getPlane().getReg_no());
        flightPlaneBox.getChildren().addAll(flightPlaneLabel, flightPlaneValue);

        VBox timeBox            = new VBox(5);
        Label timeLabel         = new Label("Time");
        String dep_time         = new SimpleDateFormat("HH:mm:ss").format(reservation.getFlight().getDeparture_time());
        String arr_time         = new SimpleDateFormat("HH:mm:ss").format(reservation.getFlight().getArrival_time());
        Label timeValue         = new Label(dep_time + "\n" + arr_time);
        timeBox.getChildren().addAll(timeLabel, timeValue);

        VBox locationBox        = new VBox(5);
        Label locationLabel     = new Label("Location");
        Label locationValue     = new Label(reservation.getFlight().getDeparture_loc().getCity() + "(" + reservation.getFlight().getDeparture_loc().getAirport_code() + ")" + "\n" + reservation.getFlight().getArrival_loc().getCity() + "(" + reservation.getFlight().getArrival_loc().getAirport_code() + ")");
        locationBox.getChildren().addAll(locationLabel, locationValue);

        VBox durationBox        = new VBox(5);
        Label durationLabel     = new Label("Duration");
        String dep_datetime     = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(reservation.getFlight().getDeparture_time());
        String arr_datetime     = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(reservation.getFlight().getArrival_time());
        Label durationValue     = new Label(data.getFlightDuration(dep_datetime, arr_datetime));
        durationBox.getChildren().addAll(durationLabel, durationValue);

        VBox arrivesBox         = new VBox(5);
        Label arrivesLabel      = new Label("Arrives");
        String arr_date         = new SimpleDateFormat("EEE, MMM d, yyyy").format(reservation.getFlight().getArrival_time());
        Label arrivesValue      = new Label(arr_date);
        arrivesBox.getChildren().addAll(arrivesLabel, arrivesValue);

        HBox firstFlightDetails = new HBox(10);
        firstFlightDetails.getChildren().addAll(flightPlaneBox, timeBox, locationBox, durationBox, arrivesBox);

        VBox firstFlightWrapper = new VBox();
        firstFlightWrapper.getChildren().addAll(outboundtitle, firstFlightDetails);

        // CSS for headers & VAlues
        flightPlaneLabel.getStyleClass().addAll("bold", "p");
        timeLabel.getStyleClass().addAll("bold", "p");
        locationLabel.getStyleClass().addAll("bold", "p");
        durationLabel.getStyleClass().addAll("bold", "p");
        arrivesLabel.getStyleClass().addAll("bold", "p");

        flightPlaneValue.getStyleClass().addAll("p");
        timeValue.getStyleClass().addAll("p");
        locationValue.getStyleClass().addAll("p");
        durationValue.getStyleClass().addAll("p");
        arrivesValue.getStyleClass().addAll("p");


        // Close button
        Button close                = new Button("close");
        close.getStyleClass().addAll("btn", "btn-danger");
        close.setAlignment(Pos.CENTER_RIGHT);
        close.setOnAction( e -> window.close());


        // Main layout
        VBox layout                 = new VBox(20);
        layout.getChildren().addAll(reservationDetailsTitle, reservationHeader, reservationPassengers, firstFlightWrapper, close);
        layout.setPadding(new Insets(20, 20, 20, 20));
        String css = this.getClass().getResource("/assets/styles/style.css").toExternalForm();
        layout.getStylesheets().add(css);

        window.setScene(new Scene(layout, 600, 600));
        window.setTitle("Reservation details");
        window.showAndWait();
    }
}
