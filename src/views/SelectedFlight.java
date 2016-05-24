package views;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.*;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class SelectedFlight extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("  Selected flight");

        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        final
        //GridPane with 10px padding around the edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Plane p1 = new Plane(1, "222", "Boeing 837", 650, 200, 200, 250, 25, 25);
        Plane p2 = new Plane(2, "539", "Boeing 777", 550, 100, 100, 350, 25, 25);
        Plane p3 = new Plane(3, "345", "Boeing 455", 300, 50, 50, 200, 25, 25);
        Plane p4 = new Plane(4, "175", "Boeing 399", 700, 200, 200, 300, 25, 25);

        Airport a1 = new Airport("Baneasa","Bucuresti", "BNS");
        Airport a2 = new Airport("Otopeni","Otopeni", "OTP");
        Airport a3 = new Airport("Henri Coanda", "Bucuresti", "HNC");

        ObservableList<Flight> flights_list = FXCollections.observableArrayList(
                new Flight(1, p1, a1, Timestamp.valueOf("2000-01-01 00:00:00"), a2, Timestamp.valueOf("2000-01-01 00:00:00"), 800.0),
                new Flight(1, p1, a1, Timestamp.valueOf("2000-01-01 00:00:00"), a2, Timestamp.valueOf("2000-01-01 00:00:00"), 900.6),
                new Flight(1, p1, a1, Timestamp.valueOf("2000-01-01 00:00:00"), a2, Timestamp.valueOf("2000-01-01 00:00:00"), 900.0),
                new Flight(1, p1, a1, Timestamp.valueOf("2000-01-01 00:00:00"), a2, Timestamp.valueOf("2000-01-01 00:00:00"), 768.8),
                new Flight(1, p1, a1, Timestamp.valueOf("2000-01-01 00:00:00"), a2, Timestamp.valueOf("2000-01-01 00:00:00"), 868.0)
        );

        Label titleLabel = new Label("We just need a few more details. Who is travelling?");
        Label flightdetail = new Label("Flight details");

        grid.add(titleLabel, 1, 1);
        grid.add(flightdetail, 1, 3);
        // first table
        Label outbound = new Label("OUTBOUND - Sunday, 15 May 2016");
        TableView<Flight> flights1 = new TableView<>();

        flights1.itemsProperty().setValue(flights_list);

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

        flights1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        flights1.getColumns().addAll(column1, column2, column3, column4, column5, column6);

        //second table
        Label returner = new Label("RETURN - Thursday, 19 May 2016");
        TableView<Flight> flights2 = new TableView<>();

        flights2.itemsProperty().setValue(flights_list);

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

        flights2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        flights2.getColumns().addAll(columne1, columne2, columne3, columne4, columne5, columne6);

        grid.add(outbound, 1, 4);
        grid.add(flights1, 1, 5);
        grid.add(returner, 1, 6);
        grid.add(flights2, 1, 7);

        Label detail = new Label("Passenger(s) details");
        grid.add(detail, 1, 8);

        Label name = new Label("Passenger name");
        TextField tfname = new TextField();

        Label birth = new Label("Birth date");
        DatePicker dpbirth = new DatePicker();

        Label seat = new Label("Seat");
        TextField tfseat = new TextField();

        Label luggage = new Label("Checked luggage");
        ChoiceBox cbluggage = new ChoiceBox();
        cbluggage.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");

        Label detail1 = new Label("Passenger(s) details");
        grid.add(detail1, 1, 8);

        Label name1 = new Label("Passenger name");
        TextField tfname1 = new TextField();

        Label birth1 = new Label("Birth date");
        DatePicker dpbirth1 = new DatePicker();

        Label seat1 = new Label("Seat");
        TextField tfseat1 = new TextField();

        Label luggage1 = new Label("Checked luggage");
        ChoiceBox cbluggage1 = new ChoiceBox();
        cbluggage1.getItems().addAll("None - 0 DKK", "Baggage, Max 15 Kg - 50 DKK", "Baggage, Max 20 Kg - 90 DKK");

        VBox v11 = new VBox(2);
        v11.getChildren().addAll(name1, tfname1);

        VBox v12 = new VBox(2);
        v12.getChildren().addAll(birth1, dpbirth1);

        VBox v13 = new VBox(2);
        v13.getChildren().addAll(seat1, tfseat1);

        VBox v14 = new VBox(2);
        v14.getChildren().addAll(luggage1, cbluggage1);

        VBox v21 = new VBox(2);
        v21.getChildren().addAll(name, tfname);

        VBox v22 = new VBox(2);
        v22.getChildren().addAll(birth, dpbirth);

        VBox v23 = new VBox(2);
        v23.getChildren().addAll(seat, tfseat);

        VBox v24 = new VBox(2);
        v24.getChildren().addAll(luggage, cbluggage);

        HBox h1 = new HBox(10);
        h1.getChildren().addAll(v11, v12, v13, v14);

        // should be able to add one more hbox
        HBox h2 = new HBox(10);
        h2.getChildren().addAll(v21, v22, v23, v24);

        VBox vBox= new VBox(20);
        vBox.getChildren().addAll(h1,h2);
        grid.add(vBox, 1, 9);


        Label info = new Label("Payment info");
        Label infor = new Label("Note: If you leave the fields empty you can still pay the reservation later,");
        Label inform = new Label("but if you don't do it the reservation will be canceled in 2 weeks from now");

        grid.add(info, 1, 12);
        grid.add(infor, 1, 13);
        grid.add(inform, 1, 14);

        ImageView mastercard = new ImageView(new Image("assets/images/mastercard.png", 80, 80, false, false));
        ImageView maestro = new ImageView(new Image("assets/images/Maestro.png", 80, 80, false, false));
        ImageView visa = new ImageView(new Image("assets/images/visa.jpg", 80, 80, false, false));
        ImageView visa_electron = new ImageView(new Image("assets/images/visa-electron.png", 80, 80, false, false));

        Button b1 = new Button();
        b1.setId("b1");
        Button b2 = new Button();
        b2.setId("b2");
        Button b3 = new Button();
        b3.setId("b3");
        Button b4 = new Button();
        b4.setId("b4");

       /* b4.setMaxWidth(1024);
        b4.setMaxHeight(640);

        b3.setMaxWidth(240);
        b3.setMaxHeight(139);

        b2.setMaxWidth(640);
        b2.setMaxHeight(381);

        b1.setMaxWidth(1456);
        b1.setMaxHeight(1033);

        b1.setGraphic(mastercard);
        b1.setBorder(null);

        b2.setGraphic(maestro);
        b2.setBorder(null);

        b3.setGraphic(visa);
        b3.setBorder(null);

        b4.setGraphic(visa_electron);
        b4.setBorder(null);*/

        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(b1,b2,b3,b4);

        grid.add(hbox, 1, 15);

        Label cardholder = new Label("Card holder name:");
        TextField tfcardholder = new TextField();
        Label cardno = new Label("Card No:");
        TextField tfcardno = new TextField();

        Label expiration = new Label("Expiration Date");
        TextField mm = new TextField();
        mm.setPromptText("MM");
        Label slash = new Label("  /  ");
        TextField yyyy = new TextField();
        yyyy.setPromptText("YYYY");
        Label security = new Label("Security code:");
        TextField tfsecurity = new TextField();
        tfsecurity.setPromptText("CVV");

        tfcardholder.setMaxWidth(80);
        tfcardno.setMaxWidth(60);
        mm.setMaxWidth(60);
        yyyy.setMaxWidth(60);
        tfsecurity.setMaxWidth(80);

        VBox v1 = new VBox(3);
        v1.getChildren().addAll(cardholder, tfcardholder);
        grid.add(v1, 1, 16);

        VBox v2 = new VBox(3);
        v2.getChildren().addAll(cardno, tfcardno);
        grid.add(v2, 2, 16);

        HBox he = new HBox(1);
        he.getChildren().addAll(mm, slash, yyyy);

        VBox v3 = new VBox(3);
        v3.getChildren().addAll(expiration, he);
        grid.add(v3, 3, 16);

        VBox v4 = new VBox(3);
        v4.getChildren().addAll(security, tfsecurity);
        grid.add(v4, 4, 16);

        Label price = new Label("Price");
        Label OTP = new Label("OTP - > BUD");
        Label no1 = new Label("1 X 445 DKK");
        Label BUD = new Label("BUD - > OTP");
        Label no2 = new Label("1 X 445 DKK");
        Label baggage = new Label("Baggage, Max 15 Kg:");
        Label no3 = new Label("1 X 50 DKK");
        ImageView dash = new ImageView(new Image("assets/images/dash.png", 80, 80, false, false));
        dash.setScaleY(1);
        dash.setScaleX(1);
        dash.setScaleZ(1);
        Label TotalP = new Label("TOTAL PRICE:  ");
        Label no4 = new Label("840 DKK");

        grid.add(price, 1, 17);

        HBox hbo = new HBox(20);
        hbo.getChildren().addAll(OTP, no1);
        grid.add(hbo, 1, 18);

        HBox hbo2 = new HBox(20);
        hbo2.getChildren().addAll(BUD, no2);
        grid.add(hbo2, 1, 19);

        HBox hbo3 = new HBox(20);
        hbo3.getChildren().addAll(baggage, no3);
        grid.add(hbo3, 1, 20);

        HBox hbo4 = new HBox(20);
        hbo4.getChildren().addAll(TotalP, no4);
        VBox vbo = new VBox(3);
        vbo.getChildren().addAll(dash, hbo4);
        // this does not appear in the grid pane, for some reason
        grid.add(vbo, 1, 21);

        Button Book = new Button("Book reservation");
        Button close = new Button("Close");

        HBox hbut = new HBox(5);
        hbut.getChildren().addAll(Book, close);
        //same thing as the vbox above, does not appear
        grid.add(hbut, 2, 22);
        grid.getStylesheets().add("assets/styles/style.css");




        //Inheriting the css and setting the background images in style.css for the buttons does not work
        //grid.getStylesheets().addAll("assets/styles/style.css");
        //it expects some kind of an URL, instead of the image's path

        Scene scene = new Scene(grid, 700,700);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
