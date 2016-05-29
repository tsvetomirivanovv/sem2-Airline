package views;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Flight;
import services.DataController;
import services.components.searchInfo;
import views.components.errorAlert;

public class createAccount  {

    DataController data = new DataController();
    Flight flight = new Flight();
    searchInfo searchInfo = new searchInfo();
    boolean redirect = false;


    public createAccount(Flight flightItem, searchInfo searchInfoItem, boolean redirectItem) {
        flight = flightItem;
        searchInfo = searchInfoItem;
        redirect = redirectItem;
    }

    public createAccount() {}

    public void start(Stage parentStage) {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Label title                     = new Label("Register");

        Label fullName                  = new Label("Full name: ");
        fullName.setAlignment(Pos.CENTER_LEFT);
        fullName.setMaxWidth(220);

        Label address                   = new Label("Address: ");
        address.setAlignment(Pos.CENTER_LEFT);
        address.setMaxWidth(220);

        Label townCity                  = new Label("Town/City: ");
        townCity.setAlignment(Pos.CENTER_LEFT);
        townCity.setMaxWidth(220);

        Label country                   = new Label("Country: ");
        townCity.setAlignment(Pos.CENTER_LEFT);
        townCity.setMaxWidth(220);

        Label mobilephone               = new Label("Mobile phone: ");
        townCity.setAlignment(Pos.CENTER_LEFT);
        townCity.setMaxWidth(220);

        Label email                     = new Label("Email: ");
        townCity.setAlignment(Pos.CENTER_LEFT);
        townCity.setMaxWidth(220);

        Label confirmemail              = new Label("Confirm email: ");
        townCity.setAlignment(Pos.CENTER_LEFT);
        townCity.setMaxWidth(220);

        Label password                  = new Label("Password: ");
        townCity.setAlignment(Pos.CENTER_LEFT);
        townCity.setMaxWidth(220);

        Label confirmpassword           = new Label("Confirm password: ");
        townCity.setAlignment(Pos.CENTER_LEFT);
        townCity.setMaxWidth(220);

        TextField nametext              = new TextField();
        nametext.setMaxWidth(220);

        TextField addresstext           = new TextField();
        addresstext.setMaxWidth(220);

        TextField towncitytext          = new TextField();
        towncitytext.setMaxWidth(220);

        TextField mobiletext            = new TextField();
        mobiletext.setMaxWidth(220);

        TextField emailtext             = new TextField();
        emailtext.setMaxWidth(220);

        TextField confirmemailtext= new TextField();
        confirmemailtext.setMaxWidth(220);

        TextField passwordtext          = new TextField();
        passwordtext.setMaxWidth(220);

        TextField confirmpasswordtext   = new TextField();
        confirmpasswordtext.setMaxWidth(220);


        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(fullName,nametext);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(address,addresstext);
        vBox2.setAlignment(Pos.CENTER);

        VBox vBox3 = new VBox();
        vBox3.getChildren().addAll(townCity,towncitytext);
        vBox3.setAlignment(Pos.CENTER);

        VBox vBox5 = new VBox();
        vBox5.getChildren().addAll(mobilephone,mobiletext);
        vBox5.setAlignment(Pos.CENTER);

        VBox vBox6 = new VBox();
        vBox6.getChildren().addAll(email, emailtext);
        vBox6.setAlignment(Pos.CENTER);

        VBox vBox7 = new VBox();
        vBox7.getChildren().addAll(confirmemail,confirmemailtext);
        vBox7.setAlignment(Pos.CENTER);

        VBox vBox8 = new VBox();
        vBox8.getChildren().addAll(password, passwordtext);
        vBox8.setAlignment(Pos.CENTER);

        VBox vBox9 = new VBox();
        vBox9.getChildren().addAll(confirmpassword, confirmpasswordtext);
        vBox9.setAlignment(Pos.CENTER);


        HBox ButtonHbox = new HBox(25);

        Button register = new Button("Register");
        register.setOnAction(e -> {
            if (nametext.getText().equals("") || addresstext.getText().equals("") || towncitytext.getText().equals("") || mobiletext.getText().equals("") || emailtext.getText().equals("") || confirmemailtext.getText().equals("") || passwordtext.getText().equals("") || confirmpasswordtext.getText().equals("")) {

                errorAlert error = new errorAlert();
                error.display("Error", "Please fill all the required fields");

            } else {

                if(!emailtext.getText().equals(confirmemailtext.getText())) {
                    errorAlert error = new errorAlert();
                    error.display("Error", "Email address doesn't match");

                } else if(!passwordtext.getText().equals(confirmpasswordtext.getText())) {
                    errorAlert error = new errorAlert();
                    error.display("Error", "Password doesn't match");
                } else {
                    if(redirect) {
                        data.registerAccount(nametext.getText(), addresstext.getText(), towncitytext.getText(), mobiletext.getText(), emailtext.getText(), passwordtext.getText());
                        SelectedFlight selectedFlight = new SelectedFlight(flight, searchInfo);
                        selectedFlight.start(parentStage);
                        primaryStage.close();
                    } else {
                        data.registerAccount(nametext.getText(), addresstext.getText(), towncitytext.getText(), mobiletext.getText(), emailtext.getText(), passwordtext.getText());
                        searchFlights searchFlights = new searchFlights();
                        searchFlights.start(parentStage);
                        primaryStage.close();
                    }
                }

            }

        });

        Button close = new Button("Close");

        close.setOnAction(event -> {
            primaryStage.close();
        });

        ButtonHbox.setAlignment(Pos.CENTER);
        ButtonHbox.getChildren().addAll(register, close);

        VBox mainVbox =  new VBox(15);
        mainVbox.getChildren().addAll(title,vBox1,vBox2,vBox3,vBox5,vBox6,vBox7,vBox8,vBox9,ButtonHbox);
        mainVbox.setMaxWidth(240);
        mainVbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainVbox, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();

    }
}
