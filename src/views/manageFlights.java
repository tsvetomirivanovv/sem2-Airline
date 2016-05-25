package views;


import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Flight;
import models.Plane;
import services.DataController;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class manageFlights extends Application{

    DataController data = new DataController();

    TableView<Flight> mainFlightsTable = new TableView<>();
    TableColumn<Flight,Integer> flightNo;
    TableColumn<Flight,String> planeNo, departureLoc, arrivalLoc;
    TableColumn<Flight,String> departureTime, arrivalTime;

    Button addFlight, deleteFlights,updateFlight,close;

    Flight tempFlight = new Flight();


    @Override
    public void start(Stage primaryStage)  {

        mainFlightsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        flightNo = new TableColumn<>("Flight No.");
        planeNo = new TableColumn<>("Plane No.");
        departureLoc = new TableColumn<>("Departure loc.");
        arrivalLoc = new TableColumn<>("Arrival loc.");
        departureTime = new TableColumn<>("Departure time");
        arrivalTime = new TableColumn<>("Arrival time");

        flightNo.setCellValueFactory(e -> e.getValue().flight_idProperty().asObject());
        planeNo.setCellValueFactory(e -> e.getValue().getPlane().reg_noProperty());
        departureLoc.setCellValueFactory(e-> e.getValue().getDeparture_loc().nameProperty());
        arrivalLoc.setCellValueFactory(e-> e.getValue().getArrival_loc().nameProperty());
        departureTime.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getDeparture_time());
            ObservableValue<String> deptime = new ReadOnlyObjectWrapper<>(timeStamp);

            return deptime;
        });
        arrivalTime.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getArrival_time());
            ObservableValue<String> artime = new ReadOnlyObjectWrapper<>(timeStamp);

            return artime;
        });





        mainFlightsTable.getColumns().addAll(flightNo,planeNo,departureLoc,departureTime,arrivalLoc,arrivalTime);

        mainFlightsTable.setItems(DataController.getFlights());

        addFlight = new Button("Add Flight");
       // deleteFlights = new Button("Delete Flight");
        updateFlight = new Button("Update Flight");
        close = new Button("Close");

        mainFlightsTable.setOnMouseClicked(e -> {
            tempFlight = mainFlightsTable.getSelectionModel().getSelectedItem();
        });

        updateFlight.setOnAction(event -> {
            views.updateFlight updateFlight = new updateFlight(tempFlight);
            updateFlight.start();
        });

        addFlight.setOnAction(event -> {
            views.addFlight addFlight = new addFlight();
            addFlight.start();
        });

        HBox hBox1 = new HBox(15);
        hBox1.getChildren().addAll(addFlight,updateFlight,close);


        BorderPane layout = new BorderPane();
        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(mainFlightsTable);
        layout.setBottom(hBox1);


        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage planes");
        primaryStage.show();
    }
}
