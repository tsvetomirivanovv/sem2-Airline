package views;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class faq {
    public void start(){
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(20);
        pane.setPadding(new Insets(0,10,0,10));

        TitledPane pane1 = new TitledPane();
        pane1.setText("Can't create a plane?");
        pane1.setContent(new Label("Please make sure all of the required fields for information are filled prior to clicking the create button!"));
        pane.add(pane1,0,1);

        TitledPane pane2 = new TitledPane();
        pane2.setText("Can't find a flight for the selected date?");
        pane2.setContent(new Label("There is probably no scheduled flight for that date.\nPlease contact your local airline company."));
        pane.add(pane2,0,3);

        TitledPane pane3 = new TitledPane();
        pane3.setText("Can't confirm a reservation?");
        pane3.setContent(new Label("Make sure that the credit card you enter is one of the 4 supported brands."));
        pane.add(pane3,0,6);

        TitledPane pane4 = new TitledPane();
        pane4.setText("For more information, please see the documentation of the software.");
        pane.add(pane4,0,8);

        Scene scene = new Scene(pane,700,400);
        scene.getStylesheets().add("assets//styles//style.css");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }
}
