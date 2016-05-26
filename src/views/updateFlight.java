package views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.DataPropertyDescriptor;
import models.Airport;
import models.Flight;
import models.Plane;
import services.DataController;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paradox on 5/24/2016.
 */
public class updateFlight {


    public ComboBox depBox;
    public ComboBox arrbox;
    public ComboBox planeBox;
    public TextField depTime,arrTime;
    public VBox mainVbox,componentBox;
    public HBox buttonBox;
    public Label depboxLabel,arrboxLabel,depTimeLable,arrTimeLabel,planeboxLabel;
    public Button addFlight,close;
    String depCode;
    Flight flight = new Flight();

    public updateFlight(Flight tempFlight) {
        flight = tempFlight;
    }

    manageFlights manageFlights = new manageFlights();
    DataController data = new DataController();

    public void start(){
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        depBox = new ComboBox();
        depBox.setMinWidth(240);
        depBox.setPromptText("Select departure location");


        arrbox = new ComboBox();
        arrbox.setMinWidth(240);
        arrbox.setPromptText("Select arrival location");


        depBox.getItems().addAll(data.getAllAirports(""));
        String depDefValue = flight.getDeparture_loc().getCity() + " (" + flight.getDeparture_loc().getAirport_code() + ")";
        depBox.setValue(depDefValue);


        String arrDefValue = flight.getArrival_loc().getCity() + " (" + flight.getArrival_loc().getAirport_code() + ")";
        arrbox.getItems().addAll(flight.getDeparture_loc().getCity() + " (" + flight.getDeparture_loc().getAirport_code() + ")");
        arrbox.setValue(arrDefValue);

        depBox.setOnAction(event -> {
            if(depBox.getValue().toString().equals(arrbox.getValue().toString())) {
                System.err.println("Same shit" + arrbox.getValue());
                arrbox.setValue(null);
            }
            arrbox.getItems().clear();
            arrbox.getItems().addAll(data.getAllAirports((String)depBox.getValue()));

            depCode = new String(data.codeCUT(depBox.getSelectionModel().getSelectedItem().toString()));
            System.out.println(depCode);
        });

        planeBox = new ComboBox();
        planeBox.setMinWidth(240);
        planeBox.setItems(data.getAllPlanes());
        String initPlane = flight.getPlane().getReg_no() + " (" + flight.getPlane().getModel() + ")";
        planeBox.setValue(initPlane);

        depTime = new TextField();
        depTime.setMaxWidth(240);
        String depDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(flight.getDeparture_time());
        depTime.setText(depDatetime);

        arrTime = new TextField();
        arrTime.setMaxWidth(240);
        String arrDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(flight.getDeparture_time());
        arrTime.setText(arrDatetime);

        depboxLabel = new Label("Departure location");
        arrboxLabel = new Label("Arrival location");
        depTimeLable = new Label("Departure time");
        arrTimeLabel = new Label("Arrival time");
        planeboxLabel = new Label("Plane");

        addFlight = new Button("Update flight");
        close = new Button("Close");

        addFlight.setOnAction(event -> {

            if (depBox.getSelectionModel().isEmpty() || arrbox.getSelectionModel().isEmpty() || planeBox.getSelectionModel().isEmpty() || depTime.getText().isEmpty() || arrTime.getText().isEmpty()) {

                Alert granted = new Alert(Alert.AlertType.ERROR);
                granted.setTitle("Creating Denied!");
                granted.setContentText("Please fill all the required fields before creating a new flight");
                granted.setHeaderText(null);
                granted.show();

            }else {

                try {
                    int id = flight.getFlight_id();


                    data.updateFlight(id,depCode);
                    primaryStage.close();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("da");
                }
            }

        });

        close.setOnAction(event -> {

            primaryStage.close();
        });

        componentBox = new VBox(5);
        componentBox.getChildren().addAll(depboxLabel,depBox,arrboxLabel,arrbox,depTimeLable,depTime,arrTimeLabel,arrTime,planeboxLabel,planeBox);
        componentBox.setAlignment(Pos.CENTER);

        buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(addFlight,close);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);

        mainVbox = new VBox(20);
        mainVbox.getChildren().addAll(componentBox,buttonBox);
        mainVbox.setAlignment(Pos.CENTER);



        Scene scene = new Scene(mainVbox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage flights");
        primaryStage.showAndWait();
    }
}
