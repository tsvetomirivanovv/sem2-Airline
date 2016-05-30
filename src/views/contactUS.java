package views;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class contactUS {

    public void start(){
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("If you find bug in the program, please contact us on the following address: \n" +
                "Alekistevej 231 2720 Vanlose\n" +
                "tsvetomirivanovv@gmail.com\n" +
                "+45 91 61 64 78");

        alert.show();


       GridPane layout = new GridPane();
        //layout.getChildren().addAll(alert);
        layout.setAlignment(Pos.CENTER);


    }
}
