package views;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import models.Airport;
import services.DataController;

import java.util.ArrayList;

/**
 * Created by Paradox on 5/24/2016.
 */
public class addFlight {

    public  DataController controller = new DataController();
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

        depBox = new ComboBox();
        depBox.setMinWidth(240);

        arrbox = new ComboBox();
        arrbox.setMinWidth(240);

        depBox.getItems().addAll(controller.getAllAirports(""));
        arrbox.getItems().addAll(controller.getAllAirports(""));

        depBox.setOnAction(event -> {
            arrbox.getItems().clear();
            arrbox.getItems().addAll(controller.getAllAirports((String)depBox.getValue()));
        });


        planeBox = new ComboBox();
        planeBox.setMinWidth(240);

        depTime = new TextField();
        depTime.setMaxWidth(240);

        arrTime = new TextField();
        arrTime.setMaxWidth(240);

        depboxLabel = new Label("Departure location");
        arrboxLabel = new Label("Arrival location");
        depTimeLable = new Label("Departure time");
        arrTimeLabel = new Label("Arrival time");
        planeboxLabel = new Label("Plane");

        addFlight = new Button("Add flight");
        close = new Button("Close");




        addFlight.setOnAction(event -> {
            String test = depBox.getSelectionModel().getSelectedItem().toString();

            System.out.println(test);
                        if (depBox.getSelectionModel().isEmpty() || arrbox.getSelectionModel().isEmpty() || planeBox.getSelectionModel().isEmpty() || depTime.getText().isEmpty() || arrTime.getText().isEmpty()) {

                            Alert granted = new Alert(Alert.AlertType.ERROR);
                            granted.setTitle("Creating Denied!");
                            granted.setContentText("Please fill all the required fields before creating a new flight");
                            granted.setHeaderText(null);
                            granted.show();
                        }

                });

            close.setOnAction(event -> {

            primaryStage.close();
        });

        componentBox = new VBox(5);
        componentBox.getChildren().addAll(depboxLabel,depBox,arrboxLabel,arrbox,planeboxLabel,planeBox,depTimeLable,depTime,arrTimeLabel,arrTime);
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
        primaryStage.show();
    }
}
