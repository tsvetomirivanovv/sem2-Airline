package views;


import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import services.components.checkLogin;
import views.components.errorAlert;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class manageFlights extends Application{

    DataController data = new DataController();

    TableView<Flight> mainFlightsTable = new TableView<>();
    TableColumn<Flight,Integer> flightNo;
    TableColumn<Flight,String> planeNo, departureLoc, arrivalLoc;
    TableColumn<Flight,String> departureTime, arrivalTime;

    Button addFlight, deleteFlights,updateFlight,back;

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

        departureLoc.setCellValueFactory((cellData -> {
            String name = (cellData.getValue().getDeparture_loc().cityProperty().getValue()+" ("+cellData.getValue().getDeparture_loc().airport_codeProperty().getValue()+") ");
            ObservableValue<String> deploc = new ReadOnlyObjectWrapper<>(name);

            return deploc;
        }));

        arrivalLoc.setCellValueFactory((cellData -> {
            String name = (cellData.getValue().getArrival_loc().cityProperty().getValue()+" ("+cellData.getValue().getArrival_loc().airport_codeProperty().getValue()+") ");
            ObservableValue<String> arrloc = new ReadOnlyObjectWrapper<>(name);

            return arrloc;
        }));

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
        addFlight.getStyleClass().addAll("btn","btn-info");
        // deleteFlights = new Button("Delete Flight");
        updateFlight = new Button("Update Flight");
        updateFlight.getStyleClass().addAll("btn","btn-info");
        back = new Button("Back to first page");
        back.getStyleClass().addAll("btn","btn-danger");

        back.setOnAction(event -> {
            searchFlights flights = new searchFlights();
            flights.start(primaryStage);
        });

        mainFlightsTable.setOnMouseClicked(e -> {
            tempFlight = mainFlightsTable.getSelectionModel().getSelectedItem();
        });

        updateFlight.setOnAction(event -> {
            if(mainFlightsTable.getSelectionModel().getSelectedItem()==null){
                views.components.errorAlert alert = new errorAlert();
                alert.display(null,"Please select a row!");
            }else {
                views.updateFlight updateFlight = new updateFlight(tempFlight);
                updateFlight.start();
            }
        });

        addFlight.setOnAction(event -> {
            views.addFlight addFlight = new addFlight();
            addFlight.start();
            mainFlightsTable.setItems(data.getFlights());
        });
        
        HBox hBox1 = new HBox(15);
        hBox1.getChildren().addAll(addFlight,updateFlight,back);
        hBox1.setPadding(new Insets(10,10,10,10));

        BorderPane layout = new BorderPane();
        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(mainFlightsTable);
        layout.setBottom(hBox1);


        Scene scene = new Scene(layout, 1000, 600);
        String css = this.getClass().getResource("/assets/styles/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage flights");
        primaryStage.show();
    }
}
