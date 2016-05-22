package views;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class seeDetails extends Application {

    GridPane pane = new GridPane();
    GridPane Hbox1Pane = new GridPane();
    Label title,checkticket,returnfor,seatsLeft,totalprice,checkroutes,outboundDate;
    Label weekday,datenumber,month,year;
    Label flightplane,time,fromto,duration,arrives,price;

    RadioButton priceBoxEconomy,priceBoxFirstClass;
    HBox outboundBox,returnBox;

    @Override
    public void start(Stage primaryStage) throws Exception {

        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(0,10,0,20));

        title = new Label("Book your ticket");
        title.setAlignment(Pos.CENTER);
        pane.add(title,11,2);

        checkticket = new Label("Check ticket info");
        pane.add(checkticket,5,5);

        returnfor = new Label("Return for 1x passenger");
        pane.add(returnfor,5,6);

        seatsLeft = new Label("Seats left: less than "+"?");
        pane.add(seatsLeft,5,7);


        totalprice = new Label("Total price: ");
        pane.add(totalprice,13,6);

        priceBoxEconomy = new RadioButton();
        priceBoxEconomy.setText("kr.890.0 [Economy]");
        pane.add(priceBoxEconomy,14,6);

        priceBoxFirstClass = new RadioButton();
        priceBoxFirstClass.setText("kr.1590.0 [First class]");
        pane.add(priceBoxFirstClass,14,7);

        checkroutes = new Label("Check your routes");
        pane.add(checkroutes,5,10);

        Hbox1Pane = new GridPane(); // HBOX FOR THE OUTBOUND INFO

        outboundBox = new HBox();
        pane.add(outboundBox,5,12);

        outboundBox.getChildren().add(Hbox1Pane);


        outboundDate = new Label("OUTBOUND ");
        Hbox1Pane.add(outboundDate,1,1);

        weekday = new Label("- Sunday?//");
        Hbox1Pane.add(weekday,2,1);

        datenumber = new Label(" 15?//");
        Hbox1Pane.add(datenumber,3,1);

        month = new Label(" May?//");
        Hbox1Pane.add(month,4,1);

        year = new Label(" 2016?//");
        Hbox1Pane.add(year,5,1);

        flightplane =  new Label("Flight/Plane");
        Hbox1Pane.add(flightplane,1,2);

        time = new Label("Time");
        Hbox1Pane.add(time,3,2);

        fromto = new Label("From -> To");
        Hbox1Pane.add(fromto,6,2);

        duration = new Label("Duration");
        Hbox1Pane.add(duration,8,2);

        arrives = new Label("Arrives");
        Hbox1Pane.add(arrives,10,2);

        price = new Label("Price");
        Hbox1Pane.add(price,12,2);

        //flightplane,time,fromto,duration,arrives,price;
        //  weekday,datenumber,month,year;

        Scene scene = new Scene(pane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage planes");
        primaryStage.show();
    }
}
