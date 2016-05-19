package views;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.Plane;
import services.DataController;

public class managePlanes extends Application{
   // DataController dataController = new DataController();

    public  Button addPlane, deletePlane,close;
    public TableView<Plane> mainPlanesTable = new TableView<>();
    public  ObservableList<Plane> planes = FXCollections.observableArrayList(
            new Plane(359,"Boing", 20, 1, 5, 5, 10)
    );
    @Override
    public void start(Stage primaryStage)  {




        //LOADING FORM DATABASE


        //tableView declaration and moving the ArrayList called <drinks> into the table

        mainPlanesTable.itemsProperty().setValue(planes);
        mainPlanesTable.setEditable(true);
        mainPlanesTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); // DESTRIBURE THE SIZE OF THE COLLUMS


        // SETTING THE COLLUMS AND ADDING THEM TO THE TABLEVIEW
        TableColumn<Plane, Integer> reg_num = new TableColumn<>("Registration Number");
        TableColumn<Plane, String> model = new TableColumn<>("Model");
        TableColumn<Plane, Integer> seats = new TableColumn<>("Total Seats");
        TableColumn<Plane, Integer> inUse = new TableColumn<>("In Use");
        TableColumn<Plane, Integer> businessSeats = new TableColumn<>("Business Seats");
        TableColumn<Plane, Integer> coachSeats = new TableColumn<>("Coach Seats");
        TableColumn<Plane, Integer> economicSeats = new TableColumn<>("Economim Seats");

        mainPlanesTable.getColumns().addAll(reg_num,model,seats,inUse,businessSeats,coachSeats,economicSeats);


        // ATTACHING ACTION LISTENERS (Displaying the objects into the tableview)

        reg_num.setCellValueFactory(e -> e.getValue().reg_noProperty().asObject());
        model.setCellValueFactory(e -> e.getValue().modelProperty());
        seats.setCellValueFactory(e -> e.getValue().seatsProperty().asObject());
        inUse.setCellValueFactory(e -> e.getValue().in_useProperty().asObject());
        businessSeats.setCellValueFactory(e -> e.getValue().businessSeatsProperty().asObject());
        coachSeats.setCellValueFactory(e -> e.getValue().coachSeatsProperty().asObject());
        economicSeats.setCellValueFactory(e -> e.getValue().economySeatsProperty().asObject());



        //!!!!!!ATTENTION!!!!!
        reg_num.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        model.setCellFactory(TextFieldTableCell.forTableColumn());
        seats.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        inUse.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        businessSeats.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        coachSeats.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        economicSeats.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        reg_num.setOnEditCommit((TableColumn.CellEditEvent<Plane, Integer> event) -> {
            ((Plane) event.getTableView().getItems().get(event.getTablePosition().getRow())).setReg_no(event.getNewValue());
        });

        model.setOnEditCommit((TableColumn.CellEditEvent<Plane, String> event) -> {
            ((Plane) event.getTableView().getItems().get(event.getTablePosition().getRow())).setModel(event.getNewValue());
        });

        seats.setOnEditCommit((TableColumn.CellEditEvent<Plane, Integer> event) -> {
            ((Plane) event.getTableView().getItems().get(event.getTablePosition().getRow())).setSeats(event.getNewValue());
        });

        inUse.setOnEditCommit((TableColumn.CellEditEvent<Plane, Integer> event) -> {
            ((Plane) event.getTableView().getItems().get(event.getTablePosition().getRow())).setIn_use(event.getNewValue());
        });

        businessSeats.setOnEditCommit((TableColumn.CellEditEvent<Plane, Integer> event) -> {
            ((Plane) event.getTableView().getItems().get(event.getTablePosition().getRow())).setBusinessSeats(event.getNewValue());
        });

        coachSeats.setOnEditCommit((TableColumn.CellEditEvent<Plane, Integer> event) -> {
            ((Plane) event.getTableView().getItems().get(event.getTablePosition().getRow())).setCoachSeats(event.getNewValue());
        });

        economicSeats.setOnEditCommit((TableColumn.CellEditEvent<Plane, Integer> event) -> {
            ((Plane) event.getTableView().getItems().get(event.getTablePosition().getRow())).setEconomySeats(event.getNewValue());
        });

        Button addButton = new Button("Create ");
        Button deleteButton = new Button("Delete ");

        deleteButton.setOnAction(event -> {

        });



        VBox hBox1 = new VBox();
        hBox1.getChildren().addAll(addButton,deleteButton);

        BorderPane layout = new BorderPane();
        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(mainPlanesTable);
        layout.setBottom(hBox1);


        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log in as Admin");
        primaryStage.show();

    }
}