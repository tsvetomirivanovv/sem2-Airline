package views.components;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Flight;
import services.components.checkLogin;
import services.components.searchInfo;
import views.BookTicketPopUp;
import views.Login;
import views.SelectedFlight;
import java.text.SimpleDateFormat;

public class flightCell extends ListCell<Flight> {
    Stage window;
    searchInfo searchInfo = new searchInfo();


    public flightCell(Stage primaryStage, searchInfo srchInfo) {
        searchInfo = srchInfo;
        window  = primaryStage;
    }

    @Override
    protected void updateItem(Flight t, boolean bln) {
        // don't omit this!!!
        super.updateItem(t, bln);
        if (bln) {
            setGraphic(null);
        } else {
            this.setGraphic(getFlightCell(t));  // ***set the content of the graphic
        }
    }
    private Node getFlightCell(Flight flight) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd \n HH:mm:ss").format(flight.getDeparture_time());
        Label departureTime = new Label(timeStamp);
        Label departureLocation = new Label(flight.getDeparture_loc().getAirport_code());
        VBox departureVbox = new VBox(20);
        departureVbox.getChildren().addAll(departureTime,departureLocation);

        String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd \n HH:mm:ss").format(flight.getArrival_time());
        Label arrivalTime = new Label(timeStamp1);
        Label arrivalLocation = new Label(flight.getArrival_loc().getAirport_code());
        VBox arrivalVbox = new VBox(20);
        arrivalVbox.getChildren().addAll(arrivalTime,arrivalLocation);

        ImageView imageView1 = new ImageView(new Image("assets/images/arrow.png", 40, 40, false, false));
        HBox flightHbox = new HBox(20);
        flightHbox.getChildren().addAll(departureVbox,imageView1,arrivalVbox);
        flightHbox.setAlignment(Pos.CENTER);


//        Label label21211 = new Label("Time");
//        Label label21212 = new Label(flight.getArrival_loc().getAirport_code());
//        VBox vBox2121 = new VBox(20);
//        vBox2121.getChildren().addAll(label21211,label21212);
//
//        Label label21221 = new Label("Time");
//        Label label21222 = new Label(flight.getDeparture_loc().getAirport_code());
//        VBox vBox2122 = new VBox(20);
//        vBox2122.getChildren().addAll(label21221,label21222);
//
//        ImageView imageView2 = new ImageView();
//        HBox hBox212 = new HBox(20);
//        hBox212.getChildren().addAll(vBox2121,imageView2,vBox2122);


        VBox flightVbox = new VBox(20);
        flightVbox.getChildren().addAll(flightHbox);


        Hyperlink details = new Hyperlink("See details");
        Button selectFlight = new Button("Select flight");
        VBox flightBox = new VBox(20);
        String sprice = "" + flight.getFlight_price();
        Label price = new Label(sprice + "DKK"); // will be changed to flight.getPrice
        flightBox.getChildren().addAll(price, selectFlight, details);
        flightBox.setAlignment(Pos.CENTER);

        selectFlight.setOnAction(e -> {
            SelectedFlight selectedFlightView = new SelectedFlight(flight, searchInfo);
            selectedFlightView.start(window);
        });

        details.setOnAction(event -> {
            BookTicketPopUp popUp = new BookTicketPopUp(flight, searchInfo);
            popUp.start();
        });

        HBox hBox2 = new HBox(20);
        hBox2.getChildren().addAll(flightVbox,flightBox);
        hBox2.setAlignment(Pos.CENTER);

        return hBox2;
    }
}
