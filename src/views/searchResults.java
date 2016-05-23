package views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.DataController;
import views.components.flightCell;

public class searchResults extends Application {

    //List of flights
    ListView flights = new ListView();

    // The filter
    Label empty = new Label();
     Label label1 = new Label("You are searching for:");
     VBox box1, box12, box11;
     HBox hBox1;
     Label label111, label112, label113, label114, label115, label116;
     Label label121, label122, label123, label124, label125;
     RadioButton radio11, radio12;
     CheckBox check12;

    DataController data = new DataController();

    @Override
    public void start(Stage primaryStage)  {

        label111 = new Label("Departure:");
        label112 = new Label("Arrival:");
        label113 = new Label("For:");
        label114 = new Label("Direct flight:");
        radio11 = new RadioButton("Return");
        label115 = new Label("From:");
        label116 = new Label("To:");

        box11 = new VBox(20);
        box11.getChildren().addAll(label111,label112,label113,label114,radio11,label115,label116);

        label121 = new Label("Airport");
        label122 = new Label("Airport");
        label123 = new Label("1 passanger");
        check12 = new CheckBox("One way");
        radio12 = new RadioButton("Return");
        label124 = new Label("Date");
        label125 = new Label("Date");

        box12 = new VBox(20);
        box12.getChildren().addAll(label121,label122,label123,check12,radio12,label124,label125);

        hBox1 = new HBox(20);
        hBox1.getChildren().addAll(box11,box12);

        box1 = new VBox(20);
        box1.getChildren().addAll(label1,hBox1);
        box1.setAlignment(Pos.CENTER);

        flights.setItems(data.getFlights());
        flights.setCellFactory(e -> new flightCell(primaryStage));

        BorderPane layout = new BorderPane();
        layout.setCenter(flights);
        layout.setLeft(box1);

        menu menu1 = new menu(); // CREATING THE MENU OBJECT
        layout.setTop(menu1.display(primaryStage));

        primaryStage.setTitle("Search Results");
        primaryStage.setScene(new Scene(layout,600,400));
        primaryStage.show();
    }
}
