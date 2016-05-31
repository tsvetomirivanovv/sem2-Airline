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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;
import services.DataController;
import java.text.SimpleDateFormat;

public class cancelReservation {
    DataController data = new DataController();

    public void start(Reservation reservation, Stage parentStage) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        // Close button
        Button closeBut = new Button("Close");
        StackPane close = new StackPane(closeBut);
        close.setAlignment(Pos.CENTER_RIGHT);
        closeBut.setOnAction( e -> window.close());

        /* =========================
           Refund  - Passengers list
           ========================= */

        // Title
        Label passengersTitle                       = new Label("Passengers");

        // Columns
        TableColumn<Refund, String> passengerName   = new TableColumn<>("Passenger name");
        TableColumn<Refund, String> classType       = new TableColumn<>("Class type");
        TableColumn<Refund, Integer> percentange    = new TableColumn<>("Refund percentage (%)");
        TableColumn<Refund, Double> amount          = new TableColumn<>("Refund amount (DKK)");

        // Fill with data
        passengerName.setCellValueFactory(e -> e.getValue().passenger_nameProperty());
        classType.setCellValueFactory(e -> e.getValue().class_typeProperty());
        percentange.setCellValueFactory(e -> e.getValue().refund_percentageProperty().asObject());
        amount.setCellValueFactory(e -> e.getValue().refund_amountProperty().asObject());

        // Table
        TableView<Refund> passengersTable = new TableView<>();
        passengersTable.setItems(data.getRefund(reservation.getReservation_id()));
        passengersTable.getColumns().addAll(passengerName, classType, percentange, amount);

        // Refund note
        Label refundNote = new Label("The refund percentage is 0% for an economy ticket if the flight is in less than 2 weeks, otherwise you will get 70%");

        VBox reservationPassengers = new VBox();
        reservationPassengers.getChildren().addAll(passengersTitle, passengersTable, refundNote);

        Button confirm = new Button("Confirm");
        confirm.setOnAction(e -> {
            data.cancelReservation(reservation.getReservation_id());
            manageReservations manageReservations = new manageReservations();
            manageReservations.start(parentStage);
            window.close();
        });

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(passengersTitle, passengersTable, refundNote, confirm, close);
        layout.getStylesheets().add("assets//styles//style.css");

        window.setScene(new Scene(layout, 650, 400));
        window.showAndWait();
    }
}
