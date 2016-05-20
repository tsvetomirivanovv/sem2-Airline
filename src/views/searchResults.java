package views;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class searchResults extends Application {

    static Label label1, label2, label3, label4;
    static TextField user;
    static PasswordField pass;
    static Button searchButton;
    static HBox h1,h2,h3,h4;
    static VBox v1,v2, v3 ,v4;
    static DatePicker datePicker1, datePicker2;
    static BorderPane layout;

    public void start (Stage primaryStage) throws Exception {

        v1 = new VBox();
        v2 = new VBox();


        layout = new BorderPane();

        menu menu1 = new menu(); // CREATING THE MENU OBJECT

        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(v3);


        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search flights");
        primaryStage.show();

    }

}

