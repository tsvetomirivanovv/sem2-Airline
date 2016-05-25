package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Reservation;
import services.DataController;

public class manageReservations extends Application {
    Menu menu2 = new Menu("Manage reservations");
    TableColumn<Reservation,String> status, customerName;
    TableColumn<Reservation,Integer> reservationId, passengersNo;
    Button viewDetails, cancel, confirm;
    TableView<Reservation> tableView = new TableView<>();
    TextField search = new TextField();
    HBox buttons = new HBox(20);
    DataController data = new DataController();

    @Override
    public void start(Stage primaryStage) {
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        status = new TableColumn<>("Status");
        status.setMinWidth(50);
        status.setCellValueFactory(c -> c.getValue().statusProperty());
        status.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setOnEditCommit((TableColumn.CellEditEvent<Reservation,String> event) ->{
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setStatus(event.getNewValue());
        });
        reservationId = new TableColumn<>("Reservation Id");
        reservationId.setMinWidth(50);
        reservationId.setCellValueFactory(c -> c.getValue().reservation_idProperty().asObject());
        reservationId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        reservationId.setOnEditCommit((TableColumn.CellEditEvent<Reservation,Integer> event) ->{
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setReservation_id(event.getNewValue());
        });
        customerName = new TableColumn<>("Customer name");
        customerName.setMinWidth(50);
        customerName.setCellValueFactory(c -> c.getValue().customer_nameProperty());
        customerName.setCellFactory(TextFieldTableCell.forTableColumn());
        customerName.setOnEditCommit((TableColumn.CellEditEvent<Reservation,String> event) ->{
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setCustomer_name(event.getNewValue());
        });
        passengersNo = new TableColumn<>("Passenger No");
        passengersNo.setMinWidth(50);
        passengersNo.setCellValueFactory(c -> c.getValue().total_passengersProperty().asObject());
        passengersNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        passengersNo.setOnEditCommit((TableColumn.CellEditEvent<Reservation,Integer> event) ->{
            (event.getTableView().getItems().get(event.getTablePosition().getRow())).setTotal_passengers(event.getNewValue());
        });



        tableView.getColumns().addAll(customerName, passengersNo, reservationId, status);
        //tableView.setEditable(true);
        //tableView.setItems(data.getCustomerReservations(data.getCustomerId())); // we need the password and the email

        //buttons.getChildren().addAll(search, viewDetails, cancel, confirm);

        Button confirm = new Button("Confirm reservation ");
        Button refresh = new Button("Refresh");
        Button cancelButton  = new Button("Cancel reservation");

        confirm.setOnAction(event -> {
            int i = tableView.getSelectionModel().getSelectedItem().getCustomer_id();
            creditCardPopUp popup = new creditCardPopUp();
            popup.start(i);
        });

        refresh.setOnAction(event -> {
            tableView.setItems(data.getReservations());
        });

        cancelButton.setOnAction(event ->{
            int i = tableView.getSelectionModel().getSelectedItem().getCustomer_id();
            data.cancelReservation(i);
        });

        HBox hBox1 = new HBox(15);
        hBox1.getChildren().addAll(confirm,cancelButton,refresh);


        menu menu1 = new menu();
        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(tableView);
        layout.setBottom(hBox1);


        primaryStage.setTitle("Log in as Admin");
        primaryStage.show();
    }
}
