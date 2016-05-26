package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

        VBox layout = new VBox(10);
        layout.getChildren().addAll(cardType, cardNo, cardEx, cardName, h1);
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


               // data.setPayment(cardType.getText(),Integer.parseInt(cardNo.getText()),cardEx.getText(),cardName.getText());
               // data.setReservationStatus(id);
                //window.close();
            }
        });

        window.setScene(new Scene(layout, 345, 689));
        window.showAndWait();
    }

}
