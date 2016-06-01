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
import services.components.searchInfo;
import views.components.errorAlert;

import java.time.LocalDate;

public class searchFlights extends Application {

    static HBox h1,h2,h3,h4;
    static VBox v1, v2, v3, v4, v5, v6;
    static BorderPane layout;

    DataController data = new DataController();

    public void start (Stage primaryStage) {

        Label emptyLabel = new Label(" ");
        Label departureLocLabel = new Label("Leaving from:");
        Label arrivalLocLabel = new Label("Going to:");

        ComboBox departure = new ComboBox();
        ComboBox arrival = new ComboBox();
        departure.getItems().addAll(data.getAllAirports(""));
        arrival.getItems().addAll(data.getAllAirports(""));
        departure.setPromptText("Search departure location");

        departure.setOnAction( e -> {

            // Clear all items
            arrival.getItems().clear();

            // Add the list again but ignore the value selected in the first combobox
            arrival.getItems().addAll(data.getAllAirports((String)departure.getValue()));
        });

        arrival.setPromptText("Search arrival location");

        new comboBoxAutocomplete<String>(departure);
        new comboBoxAutocomplete<String>(arrival);

        Label departureTimeLabel = new Label("Departure Date:");

        DatePicker datePicker1 = new DatePicker();
        datePicker1.setPromptText("mm/dd/yyyy");
        datePicker1.getStyleClass().add("datePicker1");
        datePicker1.setOnAction(e -> {
            LocalDate date = datePicker1.getValue();
        });

        // ------------------------
        // Class type & Passengers number
        // ------------------------

        Label passengersLabel = new Label("Passengers:");
        Label classLabel = new Label("Class type");

        ComboBox passengers = new ComboBox();
        ComboBox classType = new ComboBox();

        passengers.setPromptText("How many passengers?");
        passengers.getItems().addAll(
                "1 Passenger",
                "2 Passengers",
                "3 Passengers",
                "4 Passengers"
        );
        classType.setPromptText("Select the class type");
        classType.getItems().addAll(
                "Economy class",
                "Coach class",
                "First class"
        );

        Button searchButton = new Button("Search");
        searchButton.getStyleClass().addAll("btn", "btn-info");

        // Search button - Switch the scene where you can see all the results
        searchButton.setOnAction(event -> {

            if(departure.getValue() == null || arrival.getValue() == null || datePicker1.getValue() == null || passengers.getValue() == null || classType.getValue() == null) {
                views.components.errorAlert alert = new errorAlert();
                alert.display(null,"Please fill in all the required fields!");
            } else {
                String date1 = datePicker1.getValue().toString();
                int passengerNo = 0;
                searchInfo info = new searchInfo();

                switch ((String)passengers.getValue()) {
                    case "1 Passenger":
                        passengerNo = 1;
                        break;
                    case "2 Passengers":
                        passengerNo = 2;
                        break;
                    case "3 Passengers":
                        passengerNo = 3;
                        break;
                    case "4 Passengers":
                        passengerNo = 4;
                        break;
                }

                info = new searchInfo((String)departure.getValue(), (String)arrival.getValue(), date1, passengerNo, (String) classType.getValue());

                searchResults results = new searchResults(info);
                results.start(primaryStage);
            }
        });

        v1 = new VBox(7);
        v1.getChildren().addAll(departureLocLabel, departure);
        v1.setAlignment(Pos.CENTER);

        v2 = new VBox(7);
        v2.getChildren().addAll(arrivalLocLabel, arrival);
        v2.setAlignment(Pos.CENTER);

        v4 = new VBox(7);
        v4.getChildren().addAll(emptyLabel, searchButton);
        v4.setAlignment(Pos.CENTER);

        v3 = new VBox(7);
        v3.getChildren().addAll(departureTimeLabel, datePicker1);
        v3.setAlignment(Pos.CENTER);

        v5 = new VBox(7);
        v5.getChildren().addAll(passengersLabel, passengers);
        v5.setAlignment(Pos.CENTER);

        v6 = new VBox(7);
        v6.getChildren().addAll(classLabel, classType);
        v6.setAlignment(Pos.CENTER);


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

        h3 = new HBox(10);
        h3.getChildren().addAll(v5, v6);
        h3.setAlignment(Pos.CENTER);
        h3.setPadding(new Insets(20, 20, 20, 20));
        h3.setSpacing(20);

        v3 = new VBox(20);
        v3.getChildren().addAll(h1, h3, h2);
        v3.setAlignment(Pos.CENTER);

        //  ----- Apply css ----- //
        departure.getStyleClass().add("dropdown");
        arrival.getStyleClass().add("dropdown");
        passengers.getStyleClass().add("dropdown");
        classType.getStyleClass().add("dropdown");
        datePicker1.getStyleClass().add("datepicker");
        searchButton.getStyleClass().add("btn-search");

        departureTimeLabel.getStyleClass().addAll("bold", "indexTitles");
        departureLocLabel.getStyleClass().addAll("bold", "indexTitles");
        arrivalLocLabel.getStyleClass().addAll("bold", "indexTitles");
        classLabel.getStyleClass().addAll("bold", "indexTitles");
        passengersLabel.getStyleClass().addAll("bold", "indexTitles");


        layout = new BorderPane();

        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));

        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(v3);


        Scene scene = new Scene(layout, 1000, 600);
        String css = this.getClass().getResource("/assets/styles/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search flights");
        primaryStage.show();

    }

}

