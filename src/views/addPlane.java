package views;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Plane;
import services.DataController;

public class addPlane  {

 DataController data = new DataController();

    public void start() {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Label title = new Label("Create a new Plane");

        Label regNumLabel = new Label("Plane Registration Number ");
        regNumLabel.setAlignment(Pos.CENTER_LEFT);
        regNumLabel.setMaxWidth(220);

        Label modelLabel = new Label("Model of the plane");
        modelLabel.setAlignment(Pos.CENTER_LEFT);
        modelLabel.setMaxWidth(220);

        Label seatsLabel = new Label("Number of the seats");
        seatsLabel.setAlignment(Pos.CENTER_LEFT);
        seatsLabel.setMaxWidth(220);

        Label businessSeatLabel = new Label("How many Business class seats");
        businessSeatLabel.setAlignment(Pos.CENTER_LEFT);
        businessSeatLabel.setMaxWidth(220);

        Label coachSeatLabel = new Label("How many Coach class seats");
        coachSeatLabel.setAlignment(Pos.CENTER_LEFT);
        coachSeatLabel.setMaxWidth(220);

        Label economicSeatLabel = new Label("How many Economy class seats");
        economicSeatLabel.setAlignment(Pos.CENTER_LEFT);
        economicSeatLabel.setMaxWidth(220);


        TextField regNum = new TextField();
        regNum.setMaxWidth(220);

        TextField model = new TextField();
        model.setMaxWidth(220);

        TextField seats = new TextField();
        seats.setMaxWidth(220);

        TextField businessSeat = new TextField();
        businessSeat.setMaxWidth(220);

        TextField coachSeat = new TextField();
        coachSeat.setMaxWidth(220);

        TextField economicSeat = new TextField();
        economicSeat.setMaxWidth(220);

        TextField businessPrice = new TextField();
        businessPrice.setMaxWidth(220);
        businessPrice.setPromptText("The price for business class");

        TextField coachPrice = new TextField();
        coachPrice.setMaxWidth(220);
        coachPrice.setPromptText("The price for business class");

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(regNumLabel,regNum);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(modelLabel,model);
        vBox2.setAlignment(Pos.CENTER);

        VBox vBox3 = new VBox();
        vBox3.getChildren().addAll(seatsLabel,seats);
        vBox3.setAlignment(Pos.CENTER);

        VBox vBox4 = new VBox();
        vBox4.getChildren().addAll(businessSeatLabel,businessSeat);
        vBox4.setAlignment(Pos.CENTER);

        VBox vBox5 = new VBox();
        vBox5.getChildren().addAll(coachSeatLabel,coachSeat);
        vBox5.setAlignment(Pos.CENTER);

        VBox vBox6 = new VBox();
        vBox6.getChildren().addAll(economicSeatLabel,economicSeat);
        vBox6.setAlignment(Pos.CENTER);



        HBox ButtonHbox = new HBox(25);

        Button addPlane = new Button("Add Plane");

        addPlane.setOnAction(event -> {
            Plane plane = new Plane(regNum.getText(),model.getText(),
                Integer.parseInt(seats.getText()),Integer.parseInt(businessSeat.getText()),
                Integer.parseInt(coachSeat.getText()),Integer.parseInt(economicSeat.getText()),
                Double.parseDouble(businessPrice.getText()),Double.parseDouble(coachPrice.getText()));
            data.addPlane(plane);

        });

        Button close = new Button("Close");

        close.setOnAction(event -> primaryStage.close());

        ButtonHbox.setAlignment(Pos.CENTER);
        ButtonHbox.getChildren().addAll(addPlane,close);

        VBox mainVbox =  new VBox(15);
        mainVbox.getChildren().addAll(title,vBox1,vBox2,vBox3,vBox4,vBox5,vBox6,businessPrice,coachPrice,ButtonHbox);
        mainVbox.setMaxWidth(240);
        mainVbox.setAlignment(Pos.CENTER);

        // BorderPane pane = new BorderPane();
       // pane.setCenter(mainVbox);
       // pane.setTop(title);
        Scene scene = new Scene(mainVbox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }
}


