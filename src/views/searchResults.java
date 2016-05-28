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
        ToggleGroup flightType = new ToggleGroup(); // Group the 2 radioboxes (One way / Return)

        Label departureLocLabel = new Label("Departure:");
        Label arrivalLocLabel = new Label("Arrival:");
        Label passengersLabel = new Label("For:");
        Label classTypeLabel = new Label("At: ");
        RadioButton oneWay = new RadioButton("One way");
        oneWay.setToggleGroup(flightType);
        oneWay.setDisable(true);
        oneWay.setSelected(!searchInfo.hasReturnDate());
        Label startDateLabel = new Label("From:");
        Label returnDateLabel = new Label("To:");

        VBox sidebarLabels = new VBox(20);
        sidebarLabels.getChildren().addAll(departureLocLabel, arrivalLocLabel, passengersLabel, classTypeLabel, oneWay, startDateLabel);

        // Add return date just if we selected one
        if (searchInfo.hasReturnDate()) {
            sidebarLabels.getChildren().add(returnDateLabel);
        }

        Label departureLocValue = new Label(searchInfo.getDeparture_loc());
        Label arrivalLocValue = new Label(searchInfo.getArrival_loc());
        Label passengersValue = new Label();

        // Check passengers number to validate plural/singular for Passenger word
        if (searchInfo.getPassengers() == 1) {
            passengersValue = new Label(searchInfo.getPassengers() + " Passenger");
        } else {
            passengersValue = new Label(searchInfo.getPassengers() + " Passengers");
        }

        Label classTypeValue = new Label(searchInfo.getClassType());
        RadioButton returnWay = new RadioButton("Return");
        oneWay.setToggleGroup(flightType);
        returnWay.setDisable(true);
        returnWay.setSelected(searchInfo.hasReturnDate());
        Label startDateValue = new Label(searchInfo.getStart_date());
        Label returnDateValue = new Label(searchInfo.getReturn_date());


        // the vBox from the right side of the Sidebar (The values selected in the previous page)
        VBox sidebarValues = new VBox(20);
        sidebarValues.getChildren().addAll(departureLocValue, arrivalLocValue, passengersValue, classTypeValue, returnWay, startDateValue);

        // Add return date just if we selected one
        if (searchInfo.hasReturnDate()) {
            sidebarValues.getChildren().add(returnDateValue);
        }


        // Hbox where we put both the labels and the values
        HBox sidebarContent = new HBox(20);
        sidebarContent.getChildren().addAll(sidebarLabels, sidebarValues);

        Button back = new Button("Back");

        back.setOnAction(event -> {
            searchFlights flights1 = new searchFlights();
            flights1.start(primaryStage);
        });
        StackPane stackPane= new StackPane(back);
        stackPane.setAlignment(Pos.BOTTOM_CENTER);

        VBox sidebarWrapper = new VBox(20);
        sidebarWrapper.getChildren().addAll(sidebarTitle, sidebarContent, stackPane);
        sidebarWrapper.setAlignment(Pos.TOP_CENTER);
        sidebarWrapper.setPadding(new Insets(10,10,10,10));

        if(searchInfo.hasReturnDate()) {
            flights.setItems(data.searchFlights(searchInfo.getDeparture_loc(), searchInfo.getStart_date(), searchInfo.getArrival_loc(), searchInfo.getReturn_date(), searchInfo.getPassengers(), searchInfo.getClassType()));
        } else {
            flights.setItems(data.searchFlights(searchInfo.getDeparture_loc(), searchInfo.getStart_date(), searchInfo.getArrival_loc(), "", searchInfo.getPassengers(), searchInfo.getClassType()));
        }
        flights.setCellFactory(e -> new flightCell(primaryStage, searchInfo));

        BorderPane layout = new BorderPane();
        layout.setCenter(flights);
        layout.setLeft(sidebarWrapper);

        menu menu1 = new menu(); // CREATING THE MENU OBJECT
        layout.setTop(menu1.display(primaryStage));

        primaryStage.setTitle("Search Results");
        primaryStage.setScene(new Scene(layout, 1000, 600));
        primaryStage.show();
    }
}
