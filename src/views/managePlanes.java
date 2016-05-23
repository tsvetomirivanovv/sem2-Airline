package views;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Plane;
import services.DataController;

public class managePlanes extends Application{
    DataController data = new DataController();

    public  Button addPlane, deletePlane,close;
    public TableView<Plane> mainPlanesTable = new TableView<>();

    @Override
    public void start(Stage primaryStage)  {

        // SETTING THE COLLUMS AND ADDING THEM TO THE TABLEVIEW
        TableColumn<Plane, String> reg_num = new TableColumn<>("Registration Number");
        TableColumn<Plane, String> model = new TableColumn<>("Model");
        TableColumn<Plane, Integer> seats = new TableColumn<>("Total Seats");
        //TableColumn<Plane, Integer> availability = new TableColumn<>("See availability");


        // ATTACHING ACTION LISTENERS (Displaying the objects into the tableview)

        reg_num.setCellValueFactory(e -> e.getValue().reg_noProperty());
        model.setCellValueFactory(e -> e.getValue().modelProperty());
        seats.setCellValueFactory(e -> e.getValue().seatsProperty().asObject());

        reg_num.setCellFactory(TextFieldTableCell.forTableColumn());
        model.setCellFactory(TextFieldTableCell.forTableColumn());
        seats.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        reg_num.setOnEditCommit((TableColumn.CellEditEvent<Plane, String> event) -> {
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setReg_no(event.getNewValue());
        });

        model.setOnEditCommit((TableColumn.CellEditEvent<Plane, String> event) -> {
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setModel(event.getNewValue());
        });

        seats.setOnEditCommit((TableColumn.CellEditEvent<Plane, Integer> event) -> {
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setSeats(event.getNewValue());
        });

        // TO DO: We need that fucking button in here (See availability).

        Button addButton = new Button("Create ");
        Button deleteButton = new Button("Delete ");

        addButton.setOnAction(event -> {
            addPlane addPlane = new addPlane();
            addPlane.start();
        });

        deleteButton.setOnAction(event -> {

        });

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(addButton,deleteButton);

        mainPlanesTable.setItems(data.getPlanes());
        mainPlanesTable.getColumns().addAll(reg_num,model,seats);
        mainPlanesTable.setEditable(true);
        mainPlanesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // DESTRIBURE THE SIZE OF THE COLLUMS

        BorderPane layout = new BorderPane();
        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(mainPlanesTable);
        layout.setBottom(vBox1);

        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage planes");
        primaryStage.show();
    }
}
