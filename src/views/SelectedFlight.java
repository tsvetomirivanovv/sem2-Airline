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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.*;
import services.components.searchInfo;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class SelectedFlight extends Application {
    Flight flight = new Flight();
    searchInfo searchInfo = new searchInfo();

    public SelectedFlight(Flight flightItem, searchInfo searchInfoItem) {
        flight = flightItem;
        searchInfo = searchInfoItem;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("   Selected flight");

        Plane p1 = new Plane(1, "222", "Boeing 837", 650, 200, 200, 250, 25, 25);
        Airport a1 = new Airport("Baneasa","Bucuresti", "BNS");
        Airport a2 = new Airport("Otopeni","Otopeni", "OTP");
        Flight fl = new Flight(1, p1, a1, Timestamp.valueOf("2000-01-01 00:05:00"), a2, Timestamp.valueOf("2000-01-01 00:08:00"), 800.0);

        Label titleLabel = new Label("We just need a few more details. Who is travelling?");
        Label flightdetail = new Label("Flight details");

        HBox layout = new HBox(2);

        VBox vbig = new VBox(5);
        VBox vflights = new VBox(20);

        layout.getChildren().addAll(vbig, vflights);

        //setting the contect for the second vbox

        VBox firstflight = new VBox(5); // the vbox with outbound flight

        Label outbound = new Label("OUTBOUND");
        Label flight = new Label("Flight ID");
        Label date = new Label("Date&Time");
        Label fromto = new Label("From->To");
        Label duration = new Label("Duration");
        Label price = new Label("Price");

        Label reg_no = new Label(""+fl.getPlane().getReg_no());

        Label datevalue1 = new Label(""+fl.getDeparture_time());
        Label datevalue2 = new Label(""+fl.getArrival_time());
        VBox vdates = new VBox(1);
        vdates.getChildren().addAll(datevalue1, datevalue2);

        Label fromvalue = new Label(""+fl.getDeparture_loc().getName());
        Label tovalue = new Label(""+fl.getArrival_loc().getName());
        VBox vlocations = new VBox(1);
        vlocations.getChildren().addAll(fromvalue, tovalue);

        Label durationvalue = new Label("3 hr");

        Label pricevalue = new Label(fl.getFlight_price()+" DKK");

        VBox v1 = new VBox(5);
        v1.getChildren().addAll(flight, reg_no);

        VBox v2 = new VBox(5);
        v2.getChildren().addAll(date, vdates);

        VBox v3 = new VBox(5);
        v3.getChildren().addAll(fromto, vlocations);

        VBox v4 = new VBox(5);
        v4.getChildren().addAll(duration, durationvalue);

        VBox v5 = new VBox(5);
        v5.getChildren().addAll(price, pricevalue);

        HBox houtboundflight = new HBox(16);
        houtboundflight.getChildren().addAll(v1, v2, v3, v4, v5);

        VBox vfirst = new VBox(8);
        vfirst.getChildren().addAll(outbound, houtboundflight);

        Label returner = new Label("RETURN");

        Plane p2 = new Plane(2, "539", "Boeing 777", 550, 100, 100, 350, 25, 25);
        Flight fl2 = new Flight(2, p2, a2, Timestamp.valueOf("2000-03-01 00:12:35"),a1,Timestamp.valueOf("2000-03-01 00:14:00"), 825.5 );

        Label reg_no2 = new Label(""+fl2.getPlane().getReg_no());

        Label datevalue3 = new Label(""+fl2.getDeparture_time());
        Label datevalue4 = new Label(""+fl2.getArrival_time());
        VBox vdates2 = new VBox(1);
        vdates2.getChildren().addAll(datevalue3, datevalue4);

        Label fromvalue2 = new Label(""+fl2.getDeparture_loc().getName());
        Label tovalue2 = new Label(""+fl2.getArrival_loc().getName());
        VBox vlocations2 = new VBox(1);
        vlocations2.getChildren().addAll(fromvalue2, tovalue2);

        Label durationvalue2 = new Label("2hr25min");

        Label pricevalue2 = new Label(fl2.getFlight_price()+" DKK");

        Label flight2 = new Label("Flight ID");
        Label date2 = new Label("Date&Time");
        Label fromto2 = new Label("From->To");
        Label duration2 = new Label("Duration");
        Label price2 = new Label("Price");

        VBox vb1 = new VBox(5);
        vb1.getChildren().addAll(flight2, reg_no2);

        VBox vb2 = new VBox(5);
        vb2.getChildren().addAll(date2, vdates2);

        VBox vb3 = new VBox(5);
        vb3.getChildren().addAll(fromto2, vlocations2);

        VBox vb4 = new VBox(5);
        vb4.getChildren().addAll(duration2, durationvalue2);

        VBox vb5 = new VBox(5);
        vb5.getChildren().addAll(price2, pricevalue2);

        HBox houtboundflight2 = new HBox(16);
        houtboundflight2.getChildren().addAll(vb1, vb2, vb3, vb4, vb5);

        VBox vsecond = new VBox(8);
        vsecond.getChildren().addAll(returner, houtboundflight2);



        vflights.getChildren().addAll(vfirst, vsecond);





        Scene scene = new Scene(layout, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
