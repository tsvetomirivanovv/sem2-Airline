package views;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

    static Label title, username, password;
    static TextField user;
    static PasswordField pass;
    static Button loginButton, closeButton;

    static HBox h1,h2,h3,h4;
    static VBox v1,v2;


    public void start (Stage primaryStage) {

        title = new Label("Admin Login");
        title.setId("title");

        username = new Label("Email :");
        password = new Label("Password: ");

        username.setId("username");
        password.setId("password");

        user = new TextField(); // Username
        pass = new PasswordField(); // Password

        loginButton = new Button("Login");
        loginButton.setId("b");

        closeButton = new Button("Close");
        closeButton.setId("c");

        h1 = new HBox(25);
        h1.getChildren().addAll(username, user);
        h1.setAlignment(Pos.CENTER);

        h2 = new HBox(7);
        h2.getChildren().addAll(password, pass);
        h2.setAlignment(Pos.CENTER);

        h4 = new HBox();
        h4.getChildren().add(title);
        h4.setAlignment(Pos.TOP_CENTER);


        h3 = new HBox(10);
        h3.getChildren().addAll(loginButton,closeButton);
        h3.setAlignment(Pos.CENTER);

        v1 = new VBox(10);
        v1.getChildren().addAll(h1, h2, h3);
        v1.setAlignment(Pos.CENTER);
        v1.setPadding(new Insets(20, 20, 20, 20));

        v1.setSpacing(20);

        loginButton.setOnAction(e -> {
           // Menu menu = new Menu();
           // menu.start(primaryStage);
        });



        closeButton.setOnAction(e -> {
            primaryStage.close();
        });

        Scene scene = new Scene(v1, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log in as Admin");
        primaryStage.show();

    }

}

