package views;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.DataController;
import services.components.comboBoxAutocomplete;

import java.time.LocalDate;

public class searchFlights extends Application {

    static Label label1, label2, label3, label4;
    static TextField user;
    static PasswordField pass;
    static Button searchButton;
    static HBox h1,h2,h3,h4;
    static VBox v1,v2, v3 ,v4;
    static DatePicker datePicker1, datePicker2;
    static BorderPane layout;

    DataController data = new DataController();

    public void start (Stage primaryStage) {

        label1 = new Label("Leaving from:");
        label2 = new Label("Going to:");

        ComboBox comboBox1 = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        comboBox1.getItems().addAll(data.getAllAirports(""));
        comboBox2.getItems().addAll(data.getAllAirports(""));
        comboBox1.setPromptText("Search departure location");

        comboBox1.setOnAction( e -> {

            // Clear all items
            comboBox2.getItems().clear();

            // Add the list again but ignore the value selected in the first combobox
            comboBox2.getItems().addAll(data.getAllAirports((String)comboBox1.getValue()));
            System.err.println("Departure location is: " + comboBox1.getValue());
        });

        comboBox2.setPromptText("Search arrival location");

        comboBox2.setOnAction( e -> {
            System.err.println("Departure location is: " + comboBox2.getValue());
        });

        new comboBoxAutocomplete<String>(comboBox1);
        new comboBoxAutocomplete<String>(comboBox2);

        label3 = new Label("Departure Date:");
        label4 = new Label("Return Date:");

        datePicker1 = new DatePicker();
        datePicker1.setOnAction(e -> {
            LocalDate date = datePicker1.getValue();
            System.err.println("Selected date: " + date);
        });

        datePicker2 = new DatePicker();
        datePicker2.setOnAction( e -> {
            LocalDate date = datePicker2.getValue();
            System.err.println("Selected date: " + date);
        });

        searchButton = new Button("Search");
        searchButton.setId("b");

        searchButton.setOnAction(event -> {
            searchResults results = new searchResults();
            try {
                results.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        v1 = new VBox(7);
        v1.getChildren().addAll(label1, comboBox1);
        v1.setAlignment(Pos.CENTER);

        v2 = new VBox(7);
        v2.getChildren().addAll(label2, comboBox2);
        v2.setAlignment(Pos.CENTER);

        v3 = new VBox(7);
        v3.getChildren().addAll(label3, datePicker1);
        v3.setAlignment(Pos.CENTER);

        v4 = new VBox(7);
        v4.getChildren().addAll(label4, datePicker2);
        v4.setAlignment(Pos.CENTER);


        h1 = new HBox(10);
        h1.getChildren().addAll(v1, v2);
        h1.setAlignment(Pos.CENTER);
        h1.setPadding(new Insets(20, 20, 20, 20));
        h1.setSpacing(20);

        h2 = new HBox(10);
        h2.getChildren().addAll(v3, v4);
        h2.setAlignment(Pos.CENTER);
        h2.setPadding(new Insets(20, 20, 20, 20));
        h2.setSpacing(20);

        v3 = new VBox(50);
        v3.getChildren().addAll(h1, h2, searchButton);
        v3.setAlignment(Pos.CENTER);


        layout = new BorderPane();

        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));



        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(v3);


        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search flights");
        primaryStage.show();

    }

}

