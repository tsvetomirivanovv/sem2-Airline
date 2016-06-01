package views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.DataController;
import services.components.searchInfo;
import views.components.flightCell;

public class searchResults extends Application {

    searchInfo searchInfo = new searchInfo();
    ListView flights = new ListView();
    DataController data = new DataController();

    public searchResults(searchInfo info) {
        searchInfo = info;
    }

    @Override
    public void start(Stage primaryStage)  {

        // ------------------------
        // Left - Sidebar
        // ------------------------

        // Labels
        Label sidebarTitle = new Label("You are searching for:");
        Label departureLocLabel = new Label("Departure:");
        Label arrivalLocLabel = new Label("Arrival:");
        Label passengersLabel = new Label("For:");
        Label classTypeLabel = new Label("At: ");
        Label startDateLabel = new Label("From:");

        // Apply the css to labels
        sidebarTitle.getStyleClass().addAll("h6", "bold");
        departureLocLabel.getStyleClass().addAll("bold", "p");
        arrivalLocLabel.getStyleClass().addAll("bold", "p");
        passengersLabel.getStyleClass().addAll("bold", "p");
        classTypeLabel.getStyleClass().addAll("bold", "p");
        startDateLabel.getStyleClass().addAll("bold", "p");

        // Labels box
        VBox sidebarLabels = new VBox(10);
        sidebarLabels.getChildren().addAll(departureLocLabel, arrivalLocLabel, passengersLabel, classTypeLabel, startDateLabel);

        // Values
        Label departureLocValue = new Label(searchInfo.getDeparture_loc());
        Label arrivalLocValue = new Label(searchInfo.getArrival_loc());
        Label passengersValue = new Label();
        Label classTypeValue = new Label(searchInfo.getClassType());
        Label startDateValue = new Label(searchInfo.getStart_date());

        // Apply css to values
        departureLocValue.getStyleClass().addAll("p");
        arrivalLocValue.getStyleClass().addAll("p");
        passengersValue.getStyleClass().addAll("p");
        classTypeValue.getStyleClass().addAll("p");
        startDateValue.getStyleClass().addAll("p");

        // Check passengers number to validate plural/singular for Passenger word
        if (searchInfo.getPassengers() == 1) {
            passengersValue = new Label(searchInfo.getPassengers() + " Passenger");
        } else {
            passengersValue = new Label(searchInfo.getPassengers() + " Passengers");
        }

        // the vBox from the right side of the Sidebar (The values selected in the previous page)
        VBox sidebarValues = new VBox(10);
        sidebarValues.getChildren().addAll(departureLocValue, arrivalLocValue, passengersValue, classTypeValue, startDateValue);

        // Hbox where we put both the labels and the values
        HBox sidebarContent = new HBox(10);
        sidebarContent.getChildren().addAll(sidebarLabels, sidebarValues);


        // Back button
        Button back = new Button("Back");
        back.getStyleClass().addAll("btn", "btn-danger");

        back.setOnAction(event -> {
            searchFlights flights1 = new searchFlights();
            flights1.start(primaryStage);
        });

        StackPane stackPane= new StackPane(back);
        stackPane.setAlignment(Pos.BOTTOM_LEFT);

        // Sidebar wrapper box
        VBox sidebarWrapper = new VBox(10);
        sidebarWrapper.getChildren().addAll(sidebarTitle, sidebarContent, stackPane);
        sidebarWrapper.setAlignment(Pos.TOP_CENTER);
        sidebarWrapper.setPadding(new Insets(20,20,20,20));

        // Fetch flights result from DataController based on the what we choose on the first view
        flights.setItems(data.searchFlights(searchInfo.getDeparture_loc(), searchInfo.getStart_date(), searchInfo.getArrival_loc(), searchInfo.getPassengers(), searchInfo.getClassType()));
        flights.setCellFactory(e -> new flightCell(primaryStage, searchInfo));

        BorderPane layout = new BorderPane();

        // Add flights result tables
        layout.setCenter(flights);
        layout.setLeft(sidebarWrapper);

        // Import menu bar
        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));

        primaryStage.setTitle("Search Results");
        String css = this.getClass().getResource("/assets/styles/style.css").toExternalForm();
        layout.getStylesheets().add(css);
        primaryStage.setScene(new Scene(layout, 1000, 600));
        primaryStage.show();
    }
}
