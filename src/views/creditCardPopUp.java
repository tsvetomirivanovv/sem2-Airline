package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Payment;
import services.DataController;
import services.components.checkLogin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class creditCardPopUp {

    DataController data = new DataController();
    public static Payment payment = new Payment();

    public void start(int id, TableView table, boolean checkadmin){
        Stage window = new Stage();
        window.setTitle("Confirm reservation & Payment");

        int loginid = services.components.checkLogin.getAccount_id();

        TextField cardType = new TextField();
        cardType.setPromptText("card type");
        cardType.setMaxWidth(150);

        TextField cardNo = new TextField();
        cardNo.setPromptText("card number");
        cardNo.setMaxWidth(150);

        TextField cardExM = new TextField();
        cardExM.setPromptText("MM");
        cardExM.setMaxWidth(50);

        TextField cardExY = new TextField();
        cardExY.setPromptText("yyyy");
        cardExY.setMaxWidth(100);

        HBox cardEx = new HBox(20);
        cardEx.getChildren().addAll(cardExM,cardExY);
        cardEx.setAlignment(Pos.CENTER);

        TextField cardName = new TextField();
        cardName.setPromptText("card holder name");
        cardName.setMaxWidth(200);

        if(data.checkPayment(checkLogin.getAccount_id())) {
            System.err.println("We have shit");
            payment = data.getPayment(checkLogin.getAccount_id());
            data.getPayment(checkLogin.getAccount_id());
            cardType.setText(payment.getCard_type());
            cardNo.setText(Integer.toString(payment.getCard_no()));
            cardExY.setText(payment.getCard_expiration().substring(0, 4));
            cardExM.setText(payment.getCard_expiration().substring(5, 7));
            cardName.setText(payment.getCardHolder_name());
        }

        HBox h1 = new HBox(5);
        h1.setAlignment(Pos.BASELINE_CENTER);
        Label test = new Label();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(cardType, cardNo, cardEx, cardName, h1);
        layout.setAlignment(Pos.CENTER);

        Button close = new Button("Close");
        Button save = new Button("Save");

        h1.getChildren().addAll(save, close);

        close.setOnAction(e -> window.close());

        save.setOnAction(e -> {
            if (cardType.getText().equals("") || cardNo.getText().equals("") || cardExY.getText().equals("") ||cardExM.getText().equals("") || cardName.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error.");
                alert.setContentText("Please fill in all the required information!");
                alert.showAndWait();
            } else {

                if(data.checkPayment(checkLogin.getAccount_id())) {
                    String expiration = String.format("%s/%s/28",cardExY.getText(),cardExM.getText());
                    data.setPayment(loginid, cardType.getText(), Integer.parseInt(cardNo.getText()), expiration, cardName.getText(), payment.getPayment_id());
                    data.setReservationStatus(id);
                    if (table != null) {
                        table.setItems(data.getReservations(loginid,checkadmin,""));
                    }
                } else {
                    String expiration = String.format("%s/%s/28",cardExY.getText(),cardExM.getText());
                    data.setPayment(loginid, cardType.getText(), Integer.parseInt(cardNo.getText()), expiration, cardName.getText(), 0);
                    data.setReservationStatus(id);
                    if (table!=null) {
                        table.setItems(data.getReservations(loginid,checkadmin,""));
                    }
                }

                window.close();
            }
        });

        window.setScene(new Scene(layout, 600, 600));
        window.showAndWait();
    }

}
