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
        super.updateItem(t, bln);
        if (bln) {
            setGraphic(null);
        } else {
            this.setGraphic(getFlightCell(t));
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
            if(checkLogin.isLoggedIn()) {
                SelectedFlight selectedFlightView = new SelectedFlight(flight, searchInfo);
                selectedFlightView.start(window);
            } else {
                Login login = new Login(false, true, flight, searchInfo);
                login.start(window);
            }
        });

        details.setOnAction(event -> {
            BookTicketPopUp popUp = new BookTicketPopUp(flight, searchInfo);
            popUp.start();
        });

        // Apply CSS
        selectFlight.getStyleClass().addAll("btn", "btn-small", "btn-info");

        HBox hBox2 = new HBox(20);
        hBox2.getChildren().addAll(flightVbox,flightBox);
        hBox2.setAlignment(Pos.CENTER);

        return hBox2;
    }
}
