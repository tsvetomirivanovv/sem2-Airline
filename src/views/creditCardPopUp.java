package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Payment;
import services.DataController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Caseru on 5/25/2016.
 */
public class creditCardPopUp {

    DataController data = new DataController();

    public void start(int id){
        Stage window = new Stage();
        window.setTitle("   Confirm reservation & Payment");

        int loginid = services.components.checkLogin.getAccount_id();


        Button visa = new Button();
        ImageView visaIcon = new ImageView(new Image("assets\\images\\visa.jpg"));
        visaIcon.setFitWidth(50);
        visaIcon.setFitHeight(50);
        visa.setMaxHeight(50);
        visa.setMaxWidth(50);
        visa.setGraphic(visaIcon);

        Button visaelectron = new Button();
        ImageView visaelectronIcon = new ImageView(new Image("assets\\images\\visa-electron.png"));
        visaelectronIcon.setFitWidth(50);
        visaelectronIcon.setFitHeight(50);
        visaelectron.setMaxHeight(50);
        visaelectron.setMaxWidth(50);
        visaelectron.setGraphic(visaelectronIcon);

        Button mastercard = new Button();
        ImageView mastercardIcon = new ImageView(new Image("assets\\images\\mastercard.png"));
        mastercardIcon.setFitWidth(50);
        mastercardIcon.setFitHeight(50);
        mastercard.setMaxHeight(50);
        mastercard.setMaxWidth(50);
        mastercard.setGraphic(mastercardIcon);

        Button maestro = new Button();
        ImageView maestroIcon = new ImageView(new Image("assets\\images\\Maestro.png"));
        maestroIcon.setFitWidth(50);
        maestroIcon.setFitHeight(50);
        maestro.setMaxHeight(50);
        maestro.setMaxWidth(50);
        maestro.setGraphic(maestroIcon);

        TextField cardType = new TextField();
        cardType.setPromptText("card type");
        cardType.setMaxWidth(150);

        TextField cardNo = new TextField();
        cardNo.setPromptText("card number");
        cardNo.setMaxWidth(150);

        TextField cardEx = new TextField();
        cardEx.setPromptText("card expiration date");
        cardEx.setMaxWidth(200);

        TextField cardName = new TextField();
        cardName.setPromptText("card holder name");
        cardName.setMaxWidth(200);

        HBox h1 = new HBox(5);
        h1.setAlignment(Pos.BASELINE_CENTER);
        Label test = new Label();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(cardType, cardNo, cardEx, cardName, h1,visa,visaelectron,mastercard,maestro,test);
        layout.setAlignment(Pos.CENTER);

        Button close = new Button("Close");
        Button save = new Button("Save");

        h1.getChildren().addAll(save, close);

        close.setOnAction(e -> window.close());

        save.setOnAction(e -> {
            if (cardType.getText().equals("") || cardNo.getText().equals("") || cardEx.getText().equals("") || cardName.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error.");
                alert.setContentText("Please fill in all the required information!");
                alert.showAndWait();
            }
            else {
                Payment aux = new Payment(cardType.getText(), Integer.parseInt(cardNo.getText()), cardEx.getText(), cardName.getText());


                data.setPayment(cardType.getText(),Integer.parseInt(cardNo.getText()),cardEx.getText(),cardName.getText());
                data.setReservationStatus(id);
                window.close();
            }
        });

        visa.setOnAction(event -> {
       // test.setText(data.getPayment(loginid,loginid,visa.getText()));

        });

        window.setScene(new Scene(layout, 600, 600));
        window.showAndWait();
    }

}
