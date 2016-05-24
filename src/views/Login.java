package views;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.DataController;

public class Login {
    DataController data = new DataController();

    public void start (Stage parentStage) {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);


        Label title = new Label("Log in as Admin");

        Label emailLabel = new Label("Email: ");
        emailLabel.setAlignment(Pos.CENTER_LEFT);
        emailLabel.setMaxWidth(220);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setAlignment(Pos.CENTER_LEFT);
        passwordLabel.setMaxWidth(220);

        TextField email = new TextField();
        email.setMaxWidth(220);

        PasswordField password = new PasswordField();
        password.setMaxWidth(220);

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(emailLabel,email);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox();
        vBox1.getChildren().addAll(passwordLabel,password);
        vBox1.setAlignment(Pos.CENTER);

        HBox ButtonHbox = new HBox(25);
        Button loginButton = new Button("Log In");
        Button closeButton = new Button("Close");
        ButtonHbox.setAlignment(Pos.CENTER);
        ButtonHbox.getChildren().addAll(loginButton,closeButton);

        loginButton.setOnAction(e -> {
           // Menu menu = new Menu();
           // menu.start(primaryStage);
            String userName;
            String userPassword;

            userName = email.getText();
            userPassword = password.getText();

            if (data.login(userName,userPassword)) {
                searchFlights searchFlights = new searchFlights();
                searchFlights.start(parentStage);
                primaryStage.close();
            } else {
                Alert granted = new Alert(Alert.AlertType.ERROR);
                granted.setTitle("Access Denied!");
                granted.setContentText("You need to enter a valid Email and Password");
                granted.setHeaderText(null);
                granted.show();
            }
        });



        closeButton.setOnAction(e -> {
            primaryStage.close();
        });


        VBox mainVbox =  new VBox(15);
        mainVbox.getChildren().addAll(title,vBox1,vBox2,ButtonHbox);
        mainVbox.setMaxWidth(240);
        mainVbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainVbox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log in as Admin");
        primaryStage.showAndWait();

    }

}

