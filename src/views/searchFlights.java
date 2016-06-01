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

//    static HBox h1,h2,h3,h4;
//    static VBox v1, v2, v3, v4, v5, v6;
//    static BorderPane layout;

    DataController data = new DataController();

    public void start (Stage primaryStage) {


        // ------------------------
        // Departure and arrival content
        // ------------------------
        Label departureLocLabel = new Label("Leaving from:");
        Label arrivalLocLabel = new Label("Going to:");

        ComboBox departure = new ComboBox();
        ComboBox arrival = new ComboBox();

        // Fetch data from Data base into comboboxes
        departure.getItems().addAll(data.getAllAirports(""));
        arrival.getItems().addAll(data.getAllAirports(""));
        departure.setPromptText("Search departure location");

        // Make sure that we exclude the selected location on the first combobox
        // from the second one.
        departure.setOnAction( e -> {
            // Clear all items
            arrival.getItems().clear();

            // Add the list again but ignore the value selected in the first combobox
            arrival.getItems().addAll(data.getAllAirports((String)departure.getValue()));
        });

        arrival.setPromptText("Search arrival location");

        // Custom search class. When we select a combobox we can start typing
        // And get the search results in realtime.
        new comboBoxAutocomplete<String>(departure);
        new comboBoxAutocomplete<String>(arrival);

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

        // ------------------------
        // Date picker and search btn content
        // ------------------------


        // Labels
        Label departureTimeLabel = new Label("Departure Date:");
        Label emptyLabel = new Label(" ");

        // Elements
        DatePicker datePicker1 = new DatePicker();
        datePicker1.setPromptText("mm/dd/yyyy");
        datePicker1.getStyleClass().add("datePicker1");
        datePicker1.setOnAction(e -> {
            LocalDate date = datePicker1.getValue();
        });

        Button searchButton = new Button("Search");
        searchButton.getStyleClass().addAll("btn", "btn-info");

        // Search button - Switch the scene where you can see all the results
        searchButton.setOnAction(event -> {
            if(departure.getValue() == null || arrival.getValue() == null || datePicker1.getValue() == null || passengers.getValue() == null || classType.getValue() == null) {
                views.components.errorAlert alert = new errorAlert();
                alert.display(null, "Please fill in all the required fields!");
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

        // Departure dropdown
        VBox departureBox       = new VBox(7);
        departureBox.getChildren().addAll(departureLocLabel, departure);
        departureBox.setAlignment(Pos.CENTER);


        // Arrival dropdown
        VBox arrivalBox         = new VBox(7);
        arrivalBox.getChildren().addAll(arrivalLocLabel, arrival);
        arrivalBox.setAlignment(Pos.CENTER);


        // Search button + empty label to keep consistency in design
        VBox searchBox          = new VBox(7);
        searchBox.getChildren().addAll(emptyLabel, searchButton);
        searchBox.setAlignment(Pos.CENTER);


        // Date picker
        VBox departureTimeBox   = new VBox(7);
        departureTimeBox.getChildren().addAll(departureTimeLabel, datePicker1);
        departureTimeBox.setAlignment(Pos.CENTER);


        // Passengers no
        VBox passengersBox      = new VBox(7);
        passengersBox.getChildren().addAll(passengersLabel, passengers);
        passengersBox.setAlignment(Pos.CENTER);


        // Class type
        VBox classTypeBox       = new VBox(7);
        classTypeBox.getChildren().addAll(classLabel, classType);
        classTypeBox.setAlignment(Pos.CENTER);


        // Departure and arrival dropdowns row
        HBox locationBox        = new HBox(10);
        locationBox.getChildren().addAll(departureBox, arrivalBox);
        locationBox.setAlignment(Pos.CENTER);
        locationBox.setPadding(new Insets(20, 20, 20, 20));
        locationBox.setSpacing(20);


        // Passengers and class type row
        HBox passengersClassTypeBox = new HBox(10);
        passengersClassTypeBox.getChildren().addAll(passengersBox, classTypeBox);
        passengersClassTypeBox.setAlignment(Pos.CENTER);
        passengersClassTypeBox.setPadding(new Insets(20, 20, 20, 20));
        passengersClassTypeBox.setSpacing(20);


        // Date picker and search button row
        HBox timeBox            = new HBox(10);
        timeBox.getChildren().addAll(departureTimeBox, searchBox);
        timeBox.setAlignment(Pos.CENTER);
        timeBox.setPadding(new Insets(20, 20, 20, 20));
        timeBox.setSpacing(20);


        // The wrapper for all content
        VBox contentWrapper     = new VBox(20);
        contentWrapper.getChildren().addAll(locationBox, passengersClassTypeBox, timeBox);
        contentWrapper.setAlignment(Pos.CENTER);

        //  ----- Apply css ----- //
        // CSS for elements
        departure.getStyleClass().add("dropdown");
        arrival.getStyleClass().add("dropdown");
        passengers.getStyleClass().add("dropdown");
        classType.getStyleClass().add("dropdown");
        datePicker1.getStyleClass().add("datepicker");
        searchButton.getStyleClass().add("btn-search");

        // CSS for labels
        departureTimeLabel.getStyleClass().addAll("bold", "indexTitles");
        departureLocLabel.getStyleClass().addAll("bold", "indexTitles");
        arrivalLocLabel.getStyleClass().addAll("bold", "indexTitles");
        classLabel.getStyleClass().addAll("bold", "indexTitles");
        passengersLabel.getStyleClass().addAll("bold", "indexTitles");


        BorderPane layout       = new BorderPane();

        // Generate menu bar
        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));

        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(contentWrapper);


        Scene scene = new Scene(layout, 1000, 600);
        String css = this.getClass().getResource("/assets/styles/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search flights");
        primaryStage.show();

    }

}

