package views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Flight;

/**
 * Created by Andrei on 5/23/2016.
 */
public class BookTicketPopUp extends Application {

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
        h2.getChildren().addAll(outboundtitle, outbounds);

        //everything for the third horizontal box
        Label returntitle = new Label("RETURN - Thursday, 19 May 2016");
        TableView<Flight> returns = new TableView<Flight>();
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
