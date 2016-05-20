package views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class searchResults extends Application {

    //List of flights
     Button button221, button222;
     ImageView imageView1, imageView2;
    Label label22, label21111, label21112, label21121, label21122, label21221, label21222, label21211, label21212;
     VBox vBox21, vBox22, vBox2111, vBox2112, vBox2121, vBox2122;
     HBox hBox2, hBox211,hBox212;
     ObservableList<HBox> list = FXCollections.observableArrayList();
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
        box11.getChildren().addAll(label111,label112,empty,label113,label114,radio11,empty,label115,label116);

        label121 = new Label("Airport");
        label122 = new Label("Airport");
        label123 = new Label("1 passanger");
        check12 = new CheckBox("One way");
        radio12 = new RadioButton("Return");
        label124 = new Label("Date");
        label125 = new Label("Date");

        box12 = new VBox(20);
        box12.getChildren().addAll(label121,label122,empty,label123,check12,radio12,empty,label124,label125);

        hBox1 = new HBox(20);
        hBox1.getChildren().addAll(box11,box12);

        box1 = new VBox(20);
        box1.getChildren().addAll(label1,hBox1);

        //while (/*as long as there are flights left in the table*/) {
            //region Create a single flight box
            label21111 = new Label("Time");
            label21112 = new Label("Airport");
            vBox2111 = new VBox(20);
            vBox2111.getChildren().addAll(label21111,label21112);

            label21121 = new Label("Time");
            label21122 = new Label("Airport");
            vBox2112 = new VBox(20);
            vBox2112.getChildren().addAll(label21121,label21122);

            imageView1 = new ImageView();
            hBox211 = new HBox(20);
            hBox211.getChildren().addAll(vBox2111,imageView1,vBox2112);


            label21211 = new Label("Time");
            label21212 = new Label("Airport");
            vBox2121 = new VBox(20);
            vBox2121.getChildren().addAll(label21211,label21212);

            label21221 = new Label("Time");
            label21222 = new Label("Airport");
            vBox2122 = new VBox(20);
            vBox2122.getChildren().addAll(label21221,label21222);

            imageView2 = new ImageView();
            hBox212 = new HBox(20);
            hBox212.getChildren().addAll(vBox2121,imageView2,vBox2122);


            vBox21 = new VBox(20);
            vBox21.getChildren().addAll(hBox211,hBox212);


            button222 = new Button("See details");
            button221 = new Button("Select flight");
            vBox22 = new VBox(20);
            vBox22.getChildren().addAll(label22,button221,button222);


            hBox2 = new HBox(20);
            hBox2.getChildren().addAll(vBox21,vBox22);
            //endregion

            list.add(hBox2);
      //  }



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
