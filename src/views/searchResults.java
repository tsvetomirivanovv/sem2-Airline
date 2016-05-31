package views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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

    public searchResults(searchInfo info) {
        searchInfo = info;
    }

    //List of flights
    ListView flights = new ListView();

    DataController data = new DataController();

    @Override
    public void start(Stage primaryStage)  {
        Label sidebarTitle = new Label("You are searching for:");
        Label departureLocLabel = new Label("Departure:");
        Label arrivalLocLabel = new Label("Arrival:");
        Label passengersLabel = new Label("For:");
        Label classTypeLabel = new Label("At: ");
        Label startDateLabel = new Label("From:");
        sidebarTitle.getStyleClass().addAll("h6", "bold");
        departureLocLabel.getStyleClass().addAll("bold", "p");
        arrivalLocLabel.getStyleClass().addAll("bold", "p");
        passengersLabel.getStyleClass().addAll("bold", "p");
        classTypeLabel.getStyleClass().addAll("bold", "p");
        startDateLabel.getStyleClass().addAll("bold", "p");


        VBox sidebarLabels = new VBox(10);
        sidebarLabels.getChildren().addAll(departureLocLabel, arrivalLocLabel, passengersLabel, classTypeLabel, startDateLabel);

        Label departureLocValue = new Label(searchInfo.getDeparture_loc());
        Label arrivalLocValue = new Label(searchInfo.getArrival_loc());
        Label passengersValue = new Label();
        Label classTypeValue = new Label(searchInfo.getClassType());
        Label startDateValue = new Label(searchInfo.getStart_date());

        // Check passengers number to validate plural/singular for Passenger word
        if (searchInfo.getPassengers() == 1) {
            passengersValue = new Label(searchInfo.getPassengers() + " Passenger");
        } else {
            passengersValue = new Label(searchInfo.getPassengers() + " Passengers");
        }

        departureLocValue.getStyleClass().addAll("p");
        arrivalLocValue.getStyleClass().addAll("p");
        passengersValue.getStyleClass().addAll("p");
        classTypeValue.getStyleClass().addAll("p");
        startDateValue.getStyleClass().addAll("p");

        // the vBox from the right side of the Sidebar (The values selected in the previous page)
        VBox sidebarValues = new VBox(10);
        sidebarValues.getChildren().addAll(departureLocValue, arrivalLocValue, passengersValue, classTypeValue, startDateValue);

        // Hbox where we put both the labels and the values
        HBox sidebarContent = new HBox(10);
        sidebarContent.getChildren().addAll(sidebarLabels, sidebarValues);

        Button back = new Button("Back");
        back.getStyleClass().addAll("btn", "btn-danger");

        //assinging css for the list view

        back.setOnAction(event -> {
            searchFlights flights1 = new searchFlights();
            flights1.start(primaryStage);
        });
        StackPane stackPane= new StackPane(back);
        stackPane.setAlignment(Pos.BOTTOM_LEFT);

        VBox sidebarWrapper = new VBox(10);
        sidebarWrapper.getChildren().addAll(sidebarTitle, sidebarContent, stackPane);
        sidebarWrapper.setAlignment(Pos.TOP_CENTER);
        sidebarWrapper.setPadding(new Insets(20,20,20,20));

        flights.setItems(data.searchFlights(searchInfo.getDeparture_loc(), searchInfo.getStart_date(), searchInfo.getArrival_loc(), searchInfo.getPassengers(), searchInfo.getClassType()));
        flights.setCellFactory(e -> new flightCell(primaryStage, searchInfo));

        BorderPane layout = new BorderPane();
        layout.setCenter(flights);
        layout.setLeft(sidebarWrapper);

        menu menu1 = new menu(); // CREATING THE MENU OBJECT
        layout.setTop(menu1.display(primaryStage));

        primaryStage.setTitle("Search Results");
        layout.getStylesheets().add("assets//styles//style.css");
        primaryStage.setScene(new Scene(layout, 1000, 600));
        primaryStage.show();
    }
}
