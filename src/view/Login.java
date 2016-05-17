package view;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

    static Label title, username, password;
    static TextField user;
    static PasswordField pass;
    static Button loginButton;
    static HBox h1,h2,h3,h4;
    static VBox v1,v2;

    public void start (Stage primaryStage) {

        title = new Label("Admin Login");
        title.setId("title");

        username = new Label("Username :");
        password = new Label("Password: ");

        username.setId("username");
        password.setId("password");

        user = new TextField();


        pass = new PasswordField();

        loginButton = new Button("Login");
        loginButton.setId("b");

        h1 = new HBox(7);
        h1.getChildren().addAll(username, user);
        h1.setAlignment(Pos.CENTER);

        h2 = new HBox(7);
        h2.getChildren().addAll(password, pass);
        h2.setAlignment(Pos.CENTER);

        h4 = new HBox();
        h4.getChildren().add(title);
        h4.setAlignment(Pos.TOP_CENTER);

        h3 = new HBox();
        h3.getChildren().add(loginButton);
        h3.setAlignment(Pos.CENTER);

        v1 = new VBox(10);
        v1.getChildren().addAll(h1, h2, h3);
        v1.setAlignment(Pos.CENTER);
        v1.setPadding(new Insets(20, 20, 20, 20));
        v1.setSpacing(20);

        v2 = new VBox(50);
        v2.getChildren().addAll(title, v1);
        v2.setAlignment(Pos.CENTER);

       // loginButton.setOnAction(e -> {
         //   Menu menu = new Menu();
           // menu.start(primaryStage);
        //});

        Scene scene = new Scene(v2, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Login");
        primaryStage.show();

    }

}

