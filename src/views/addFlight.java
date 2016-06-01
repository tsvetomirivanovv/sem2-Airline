package views;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Airport;
import services.DataController;
import views.components.errorAlert;

import java.util.ArrayList;

public class addFlight {

    public  DataController data = new DataController();
    public ComboBox depBox;
    public ComboBox arrbox;
    public ComboBox planeBox;
    public TextField depTime,arrTime;
    public VBox mainVbox,componentBox;
    public HBox buttonBox;
    public Label depboxLabel,arrboxLabel,depTimeLable,arrTimeLabel,planeboxLabel;
    public Button addFlight,close;


    public void start(){
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        ObservableList<String> emptyPlanes = FXCollections.observableArrayList();
        emptyPlanes.add("Please select departure and arrive date");


        depBox = new ComboBox();
        depBox.setMinWidth(240);
        depBox.getStyleClass().addAll("dropdown", "dropdown-small");

        arrbox = new ComboBox();
        arrbox.setMinWidth(240);
        arrbox.getStyleClass().addAll("dropdown", "dropdown-small");

        depBox.getItems().addAll(data.getAllAirports(""));
        depBox.setPromptText("Select departure location");
        arrbox.getItems().addAll(data.getAllAirports(""));
        arrbox.setPromptText("Select arrival location");

        depBox.setOnAction(event -> {
            arrbox.getItems().clear();
            arrbox.getItems().addAll(data.getAllAirports((String)depBox.getValue()));
        });


        planeBox = new ComboBox();
        planeBox.getStyleClass().addAll("dropdown", "dropdown-small");
        planeBox.setPromptText("Select plane");
        planeBox.setMinWidth(240);

        depTime = new TextField();
        depTime.setPromptText("dd/mm/yyy hh:mm:ss");
        depTime.setMaxWidth(240);

        arrTime = new TextField();
        arrTime.setPromptText("dd/mm/yyy hh:mm:ss");
        arrTime.setMaxWidth(240);

        depboxLabel = new Label("Departure location");
        arrboxLabel = new Label("Arrival location");
        depTimeLable = new Label("Departure time");
        arrTimeLabel = new Label("Arrival time");
        planeboxLabel = new Label("Plane");

        Label priceLabel = new Label("Price");
        priceLabel.setMaxWidth(240);
        TextField priceValue = new TextField();
        priceValue.setPromptText("eg: 1200.00");
        priceValue.setMaxWidth(240);

        addFlight = new Button("Add flight");
        addFlight.getStyleClass().addAll("btn","btn-info");
        close = new Button("Close");
        close.getStyleClass().addAll("btn","btn-danger");
        close.setOnAction(e -> primaryStage.close() );




        addFlight.setOnAction(event -> {

            System.err.println(depBox.getValue());
            System.err.println(arrbox.getValue());
            System.err.println(planeBox.getValue());
            System.err.println(depTime.getText());
            System.err.println(arrTime.getText());

            if (depBox.getValue() == null || arrbox.getValue() == null || planeBox.getValue() == null || depTime.getText().equals("") || arrTime.getText().equals("") || priceValue.getText().equals("")) {

                views.components.errorAlert alert = new errorAlert();
                alert.display(null,"Please fill all the required fields!");

            } else {
                //data.updateFlight(flight.getFlight_id(), depBox.getValue().toString(), arrbox.getValue().toString(), depTime.getText(), arrTime.getText(), planeBox.getValue().toString());
                //primaryStage.close();
                data.addFlight(depBox.getValue().toString(), arrbox.getValue().toString(), planeBox.getValue().toString(), depTime.getText(), arrTime.getText(), priceValue.getText());
                System.err.println("Saved!");
            }

        });

        planeBox.setOnMouseClicked(e -> {
            planeBox.getItems().clear();
            planeBox.getItems().addAll(data.getAvailablePlanes(depTime.getText(), arrTime.getText()));
        });

        componentBox = new VBox(5);
        componentBox.getChildren().addAll(depboxLabel, depBox, arrboxLabel, arrbox, depTimeLable, depTime, arrTimeLabel, arrTime, planeboxLabel, planeBox, priceLabel, priceValue);
        componentBox.setAlignment(Pos.CENTER);

        buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(addFlight,close);
        buttonBox.setPadding(new Insets(10,10,10,10));
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);

        mainVbox = new VBox(20);
        mainVbox.getChildren().addAll(componentBox,buttonBox);
        mainVbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainVbox, 600, 600);
        String css = this.getClass().getResource("/assets/styles/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage flights");
        primaryStage.show();
    }
}
