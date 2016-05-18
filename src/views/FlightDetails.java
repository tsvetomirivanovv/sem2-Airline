package views;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Airport;
import models.Flight;
import models.Plane;

import java.util.Date;

/**
 * Created by Andrei on 5/17/2016.
 */
public class FlightDetails extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("  Selected flight");

        //GridPane with 10px padding around the edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Date d1 = new Date(2015-12-12);
        Date d2 = new Date(2016-01-02);

        Plane p1 = new Plane(222, "Boeing 837", 650, 1, 200, 200, 250);
        Plane p2 = new Plane(539, "Boeing 777", 550, 0, 100, 100, 350);
        Plane p3 = new Plane(345, "Boeing 455", 300, 0, 50, 50, 200);
        Plane p4 = new Plane(175, "Boeing 399", 700, 1, 200, 200, 300);

        Airport a1 = new Airport("Baneasa","Bucuresti", 233);
        Airport a2 = new Airport("Otopeni","Otopeni", 355);
        Airport a3 = new Airport("Henri Coanda", "Bucuresti", 500);

        ObservableList<Flight> flights_list = FXCollections.observableArrayList(
                new Flight(p1, a1, d1, a2, d2),
                new Flight(p2, a3, d2, a1, d1),
                new Flight(p3, a2, d1, a3, d2),
                new Flight(p4, a2, d2, a1, d1)
        );

        Label titleLabel = new Label("We just need a few more details. Who is travelling?");
        Label flightdetail = new Label("Flight details");

        grid.add(titleLabel, 1, 1);
        grid.add(flightdetail, 1, 3);
        // first table
        Label outbound = new Label("OUTBOUND - Sunday, 15 May 2016");
        TableView<Flight> flights1 = new TableView<>();

        flights1.itemsProperty().setValue(flights_list);

        TableColumn<Flight, String> column1 = new TableColumn<>("Flight/Plane");
        TableColumn<Flight, String> column2 = new TableColumn<>("Date & Time");
        TableColumn<Flight, String> column3 = new TableColumn<>("From -> To");
        TableColumn<Flight, String> column4 = new TableColumn<>("Duration");
        TableColumn<Flight, Date> column5 = new TableColumn<>("Arrival");
        TableColumn<Flight, Integer> column6 = new TableColumn<>("Price");

        flights1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //column1.setCellValueFactory(e -> e.getValue().getPlane().modelProperty());
        //column2.setCellValueFactory(e -> e.getValue().toString()):
        //column3.setCellValueFactory(e -> e.getValue().getDeparture_loc());

        flights1.getColumns().addAll(column1, column2, column3, column4, column5, column6);

        //second table
        Label returner = new Label("RETURN - Thursday, 19 May 2016");
        TableView<Flight> flights2 = new TableView<>();

        flights2.itemsProperty().setValue(flights_list);

        TableColumn<Flight, String> columne1 = new TableColumn<>("Flight/Plane");
        TableColumn<Flight, String> columne2 = new TableColumn<>("Date & Time");
        TableColumn<Flight, String> columne3 = new TableColumn<>("From -> To");
        TableColumn<Flight, String> columne4 = new TableColumn<>("Duration");
        TableColumn<Flight, Date> columne5 = new TableColumn<>("Arrival");
        TableColumn<Flight, Integer> columne6 = new TableColumn<>("Price");

        flights2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        flights2.getColumns().addAll(columne1, columne2, columne3, columne4, columne5, columne6);

        grid.add(outbound, 1, 4);
        grid.add(flights1, 1, 5);
        grid.add(returner, 1, 6);
        grid.add(flights2, 1, 7);

        Label detail = new Label("Passenger(s) details");
        grid.add(detail, 1, 8);

        Label name = new Label("Passenger name");
        TextField tfname = new TextField();
        Label birth = new Label("Birth date");
        DatePicker dpbirth = new DatePicker();
        Label seat = new Label("Seat");
        TextField tfseat = new TextField();
        Label luggage = new Label("Checked luggage");
        ChoiceBox cbluggage = new ChoiceBox();
        cbluggage.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");

        VBox v1 = new VBox(2);
        v1.getChildren().addAll(name, tfname);

        VBox v2 = new VBox(2);
        v2.getChildren().addAll(birth, dpbirth);

        VBox v3 = new VBox(2);
        v3.getChildren().addAll(seat, tfseat);

        VBox v4 = new VBox(2);
        v4.getChildren().addAll(luggage, cbluggage);

        HBox h1 = new HBox(10);
        h1.getChildren().addAll(v1, v2, v3, v4);

        // should be able to add one more hbox
        HBox h2 = new HBox(10);
        h2.getChildren().addAll(v1, v2, v3, v4);

        grid.add(h1, 1, 9);

        Label info = new Label("Payment info");
        Label infor = new Label("Note: If you leave the fields empty you can still pay the reservation later,");
        Label inform = new Label("but if you don't do it the reservation will be canceled in 2 weeks from now");

        grid.add(h2, 1, 11);
        grid.add(info, 1, 12);
        grid.add(infor, 1, 13);
        grid.add(inform, 1, 14);




        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();



    }

}
