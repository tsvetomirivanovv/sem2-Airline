package views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Paradox on 5/24/2016.
 */
public class addFlight {


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
