package views;


import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import models.Plane;
import services.DataController;
import views.components.availabilityCell;

public class managePlanes extends Application{
    DataController data = new DataController();
    availabilityCell avb = new availabilityCell();

    public TableView<Plane> mainPlanesTable = new TableView<>();

    @Override
    public void start(Stage primaryStage)  {

        // SETTING THE COLLUMS AND ADDING THEM TO THE TABLEVIEW
        TableColumn<Plane, String> reg_num = new TableColumn<>("Registration Number");
        TableColumn<Plane, String> model = new TableColumn<>("Model");
        TableColumn<Plane, Integer> seats = new TableColumn<>("Total Seats");
        TableColumn <Plane, Plane> availability = new TableColumn<>("Details");

        // ATTACHING ACTION LISTENERS (Displaying the objects into the tableview)

        reg_num.setCellValueFactory(e -> e.getValue().reg_noProperty());
        model.setCellValueFactory(e -> e.getValue().modelProperty());
        seats.setCellValueFactory(e -> e.getValue().seatsProperty().asObject());
        availability.setCellValueFactory(avb.getCallback2());

        availability.setCellFactory(avb.getCallback1());

        Button addButton = new Button("Add plane ");
        addButton.getStyleClass().addAll("btn", "btn-info");
        Button deleteButton = new Button("Delete ");
        deleteButton.getStyleClass().addAll("btn", "btn-danger");
        Button back = new Button("Back to menu ");
        back.getStyleClass().addAll("btn", "btn-danger");

        addButton.setOnAction(event -> {
            addPlane addPlane = new addPlane();
            addPlane.start(mainPlanesTable);
        });

        deleteButton.setOnAction(event -> {

        });

        back.setOnAction(event -> {
            searchFlights search = new searchFlights();
            search.start(primaryStage);
        });

        HBox vBox1 = new HBox(20);
        vBox1.getChildren().addAll(addButton,back);
        vBox1.setPadding(new Insets(10,10,10,10));

        mainPlanesTable.setItems(data.getPlanes());
        mainPlanesTable.getColumns().addAll(reg_num,model,seats,availability);
        mainPlanesTable.setEditable(true);
        mainPlanesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // DESTRIBURE THE SIZE OF THE COLLUMS

        BorderPane layout = new BorderPane();
        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(mainPlanesTable);
        layout.setBottom(vBox1);

        Scene scene = new Scene(layout, 1000, 600);
        String css = this.getClass().getResource("/assets/styles/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage planes");
        primaryStage.show();
    }
}
