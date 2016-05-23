package views;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Flight;
import services.DataController;

import java.text.SimpleDateFormat;

/**
 * Created by Andrei on 5/23/2016.
 */
public class BookTicketPopUp extends Application {

    DataController controller = new DataController();

    public void start(Stage primaryStage) {
        //Stage primaryStage = new Stage();
        //primaryStage.initModality(Modality.APPLICATION_MODAL);

        // this is the big VBox, that will contain everything
        VBox layout = new VBox(20);

        HBox h1 = new HBox(5); // for the check ticket info
        VBox h2 = new VBox(5); // for the OUTBOUND tableview
        VBox h3 = new VBox(5); // for the RETURN tableview
        HBox h4 = new HBox(5); // for the Select Flight and Close Buttons

        //everything for the first horizontal box
        Label title = new Label("Book your ticket");
        Label checkroutes = new Label("Check your routes");

        layout.getChildren().addAll(title, h1, checkroutes, h2, h3, h4);

        layout.setAlignment(Pos.TOP_CENTER);

        Label checkticket = new Label("Check ticket info");
        Label returnfor = new Label("Return for 1x Passenger");
        Label seatsleft = new Label("Seats left: ");
        Label lessthan = new Label("less than 5");

        Label total = new Label("Total price: ");

        RadioButton economybut = new RadioButton("kr. 890.0 (Economy)");
        RadioButton firstclassbut = new RadioButton("kr. 1590.9 (First class)");

        HBox seatsH = new HBox(2);
        seatsH.getChildren().addAll(seatsleft, lessthan);

        VBox small = new VBox(3);
        small.getChildren().addAll(checkticket, returnfor, seatsH);

        VBox radiobox = new VBox(5);
        radiobox.getChildren().addAll(economybut, firstclassbut);
        //layout.setAlignment(radiobox, Pos.BOTTOM_RIGHT);

        h1.getChildren().addAll(small, total, radiobox);

        //everything for the second horizontal box
        Label outboundtitle = new Label("OUTBOUND - Sunday, 15 May 2016");
        TableView<Flight> outbounds = new TableView<Flight>();

        outbounds.setItems(controller.getFlights());

        TableColumn<Flight, String> columne1 = new TableColumn<>("Flight/Plane");
        columne1.setCellValueFactory(cellData -> cellData.getValue().getPlane().reg_noProperty());

        TableColumn<Flight, String> columne2 = new TableColumn<>("Date & Time");
        columne2.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getDeparture_time());
            ObservableValue<String> dd = new ReadOnlyObjectWrapper<>(timeStamp);

            return dd;
        });

        TableColumn<Flight, String> columne3 = new TableColumn<>("From");
        columne3.setCellValueFactory(cellData -> cellData.getValue().getDeparture_loc().nameProperty());

        TableColumn<Flight, String> columne4 = new TableColumn<>("-> To");
        columne4.setCellValueFactory(cellData -> cellData.getValue().getArrival_loc().nameProperty());

        TableColumn<Flight, String> columne5 = new TableColumn<>("Duration");

        TableColumn<Flight, String> columne6 = new TableColumn<>("Arrival");
        columne6.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getArrival_time());
            ObservableValue<String> dd = new ReadOnlyObjectWrapper<>(timeStamp);

            return dd;
        });

        outbounds.getColumns().addAll(columne1,columne2,columne3,columne4,columne5,columne6);

        h2.getChildren().addAll(outboundtitle, outbounds);

        //everything for the third horizontal box
        Label returntitle = new Label("RETURN - Thursday, 19 May 2016");
        TableView<Flight> returns = new TableView<Flight>();

        returns.setItems(controller.getFlights());

        TableColumn<Flight, String> column1 = new TableColumn<>("Flight/Plane");
        column1.setCellValueFactory(cellData -> cellData.getValue().getPlane().reg_noProperty());

        TableColumn<Flight, String> column2 = new TableColumn<>("Date & Time");
        column2.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getDeparture_time());
            ObservableValue<String> dd = new ReadOnlyObjectWrapper<>(timeStamp);

            return dd;
        });

        TableColumn<Flight, String> column3 = new TableColumn<>("From");
        column3.setCellValueFactory(cellData -> cellData.getValue().getDeparture_loc().nameProperty());

        TableColumn<Flight, String> column4 = new TableColumn<>("-> To");
        column4.setCellValueFactory(cellData -> cellData.getValue().getArrival_loc().nameProperty());

        TableColumn<Flight, String> column5 = new TableColumn<>("Duration");

        TableColumn<Flight, String> column6 = new TableColumn<>("Arrival");
        column6.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getArrival_time());
            ObservableValue<String> dd = new ReadOnlyObjectWrapper<>(timeStamp);

            return dd;
        });

        returns.getColumns().addAll(column1,column2,column3,column4,column5,column6);

        h3.getChildren().addAll(returntitle, returns);

        //everything for the fourth table
        Button selectflight = new Button("Select flight");
        Button close = new Button("Close");
        h4.getChildren().addAll(selectflight, close);
        h4.setAlignment(Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book ticket");
        primaryStage.show();

    }

}
