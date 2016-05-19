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
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Reservation;

/**
 * Created by Caseru on 5/18/2016.
 */
public class manageReservations extends Application {
    ObservableList<Reservation> list = FXCollections.observableArrayList(); Menu menu2 = new Menu("Manage reservations");
    TableColumn<Reservation,String> status;
    TableColumn<Reservation,Integer> reservationId, customerId, passengerNo;
    Button viewDetails, cancel, confirm;
    TableView<Reservation> tableView = new TableView<>();
    TextField search = new TextField();
    HBox buttons = new HBox(20);
    @Override
    public void start(Stage primaryStage) throws Exception {

        status = new TableColumn<>("Status");
        status.setMinWidth(50);
        status.setCellValueFactory(c -> c.getValue().statusProperty());
        status.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setOnEditCommit((TableColumn.CellEditEvent<Reservation,String> event) ->{
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setStatus(event.getNewValue());
        });
        reservationId = new TableColumn<>("Reservation Id");
        reservationId.setMinWidth(50);
        reservationId.setCellValueFactory(c -> c.getValue().reservationIdProperty().asObject());
        reservationId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        reservationId.setOnEditCommit((TableColumn.CellEditEvent<Reservation,Integer> event) ->{
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setReservationId(event.getNewValue());
        });
        customerId = new TableColumn<>("Customer Id");
        customerId.setMinWidth(50);
        customerId.setCellValueFactory(c -> c.getValue().customer_idProperty().asObject());
        customerId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        customerId.setOnEditCommit((TableColumn.CellEditEvent<Reservation,Integer> event) ->{
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setCustomer_id(event.getNewValue());
        });
        passengerNo = new TableColumn<>("Passenger No");
        passengerNo.setMinWidth(50);

        tableView.getColumns().addAll(customerId, passengerNo, reservationId, status);
        tableView.setEditable(true);
        tableView.setItems(list);

        //buttons.getChildren().addAll(search, viewDetails, cancel, confirm);

        VBox hBox1 = new VBox();


        BorderPane layout = new BorderPane();
        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(tableView);
        layout.setBottom(hBox1);


        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log in as Admin");
        primaryStage.show();
    }
}
