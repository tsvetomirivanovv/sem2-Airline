package views;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Flight;
import services.DataController;
import services.components.checkLogin;
import services.components.searchInfo;
import views.components.errorAlert;

public class Login {
    DataController data = new DataController();
    boolean isAdmin = false;
    Flight flight = new Flight();
    searchInfo searchInfo = new searchInfo();
    boolean redirect = false;


    public Login(boolean admin, boolean redirectItem, Flight flightItem, searchInfo searchInfoItem) {
        redirect = redirectItem;
        flight = flightItem;
        searchInfo = searchInfoItem;
        isAdmin = admin;
    }

    public Login(boolean admin) {
        isAdmin = admin;
    }

    public void start (Stage parentStage) {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        Label title;

        if (isAdmin) {
            title = new Label("Log in as admin");
        } else {
            title = new Label("Log in as customer");
        }

        //adding css to the title label
        title.getStyleClass().add("titles");

        Label emailLabel = new Label("Email: ");
        emailLabel.setAlignment(Pos.CENTER_LEFT);
        emailLabel.setMaxWidth(220);
        //adding the css to the labels, it is going to be the same for the emailLabel and the PasswordLabel
        emailLabel.getStyleClass().add("labels");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setAlignment(Pos.CENTER_LEFT);
        passwordLabel.setMaxWidth(220);
        passwordLabel.getStyleClass().add("labels");

        TextField email = new TextField();
        email.setMaxWidth(220);

        PasswordField password = new PasswordField();
        password.setMaxWidth(220);

        VBox vBox1 = new VBox(10);
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

        //assigning css to the buttons
        loginButton.getStyleClass().addAll("btn", "btn-info");
        closeButton.getStyleClass().addAll("btn", "btn-danger");

        HBox createAccount = new HBox(15);
        createAccount.setAlignment(Pos.CENTER);
        TextFlow flow = new TextFlow();
        Text text = new Text("Don't have an account yet?");
        Hyperlink hyperlink = new Hyperlink("Click here!");

        flow.getChildren().addAll(text,hyperlink);
        createAccount.getChildren().addAll(flow);

        hyperlink.setOnAction(event -> {
            if(redirect) {
                views.createAccount createAccount1 = new createAccount(flight, searchInfo, true);
                createAccount1.start(primaryStage);
                primaryStage.close();
            } else {
                views.createAccount createAccount2 = new createAccount();
                createAccount2.start(primaryStage);
                primaryStage.close();
            }
        });

        loginButton.setOnAction(e -> {
            onAc(primaryStage,parentStage,email,password);
        });

        closeButton.setOnAction(e -> {
            primaryStage.close();
        });


        VBox mainVbox =  new VBox(15);
        mainVbox.getStylesheets().add("assets//styles//style.css");
        mainVbox.getChildren().addAll(title,vBox1,vBox2,ButtonHbox);
        if(!isAdmin) {
            mainVbox.getChildren().add(createAccount);
        }
        mainVbox.setMaxWidth(240);
        mainVbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainVbox, 400, 400);
        scene.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.ENTER){
                onAc(primaryStage,parentStage,email,password);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log in");
        primaryStage.showAndWait();

    }
    public void onAc(Stage primaryStage, Stage parentStage, TextField email, PasswordField password){
        String userName;
        String userPassword;

        userName = email.getText();
        userPassword = password.getText();

        if (data.login(userName, userPassword)) {

            if (checkLogin.isAdmin()) {
//                checkLogin.setAdmin(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText("You have logged in as admin!");
                alert.setContentText("You can now manage planes, flights and reservations.");
                alert.showAndWait();
                System.err.println("ADMIN IS: "+checkLogin.isAdmin());
            } else {
//                checkLogin.setAdmin(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText("You have logged in as a customer!");
                alert.setContentText("You can now search a flight and book a reservation.");
                alert.showAndWait();
                System.err.println("ADMIN IS: "+checkLogin.isAdmin());
            }

            if(redirect) {
                SelectedFlight SelectedFlight = new SelectedFlight(flight, searchInfo);
                SelectedFlight.start(parentStage);
                primaryStage.close();
            } else {
                searchFlights searchFlights = new searchFlights();
                searchFlights.start(parentStage);
                primaryStage.close();
            }
        } else {
            views.components.errorAlert alert = new errorAlert();
            alert.display(null,"You need to enter a valid username and password!");
        }
    }
}
