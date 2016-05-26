package views.components;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Plane;
import views.planeAvailability;

/**
 * Created by Caseru on 5/25/2016.
 */
public class availabilityCell extends TableCell<Plane, Plane> {
    Stage window;

    public availabilityCell(Stage primaryStage){
        window=primaryStage;
    }

    protected void updateItem(Plane t, boolean bln) {
        // don't omit this!!!
        super.updateItem(t, bln);
        if (bln) {
            setGraphic(null);
        } else {
            this.setGraphic(buttonCell(t));  // ***set the content of the graphic
        }
    }
    private Node buttonCell(Plane plane) {
        StackPane pane = new StackPane();

        Button cellButton = new Button("view");

        cellButton.setOnAction(event -> {
            planeAvailability ava= new planeAvailability(plane);
            ava.start();
        });
        pane.getChildren().addAll(cellButton);
        return pane;
    }
}
