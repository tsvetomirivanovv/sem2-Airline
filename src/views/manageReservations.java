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
import views.components.errorAlert;
import views.components.reservationsDetailsCell;

public class manageReservations extends Application {
    TableColumn<Reservation,String> status, customerName;
    TableColumn<Reservation,Integer> reservationId, passengersNo;
    TableColumn<Reservation, Reservation> details;
    TableView<Reservation> tableView = new TableView<>();
    TextField search = new TextField();
    DataController data = new DataController();
    reservationsDetailsCell detailsCell = new reservationsDetailsCell();


    @Override
    public void start(Stage primaryStage) {
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);

        TextField searchField = new TextField();
        searchField.setPromptText("Search reservation");
        Button searchButton = new Button("Search");

        int loginid = services.components.checkLogin.getAccount_id();
        boolean checkadmin = services.components.checkLogin.isAdmin();

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

        details = new TableColumn<>("Details");
        details.setCellValueFactory(detailsCell.getCallback2());

        details.setCellFactory(detailsCell.getCallback1());



        tableView.getColumns().addAll(customerName, passengersNo, reservationId, status, details);
        tableView.setItems(data.getReservations(loginid,checkadmin,""));

        Button confirm = new Button("Confirm reservation ");
        Button refund  = new Button("Cancel reservation");
        Button back = new Button("Back to menu");

        confirm.setOnAction(event -> {
            if(tableView.getSelectionModel().getSelectedItem()==null){
                views.components.errorAlert alert = new errorAlert();
                alert.display(null,"Please select a row before!");
            }else {
                int i = tableView.getSelectionModel().getSelectedItem().getReservation_id();
                creditCardPopUp popup = new creditCardPopUp();
                popup.start(i, tableView, checkadmin);
            }
        });

        tableView.setOnMouseClicked(event -> {
            if ( tableView.getSelectionModel().getSelectedItem() != null)
                if(tableView.getSelectionModel().getSelectedItem().getStatus().equals("confirmed")){
                    confirm.setDisable(true);
                }else if (tableView.getSelectionModel().getSelectedItem().getStatus().equals("canceled")){
                    refund.setDisable(true);
                }
        });

        back.setOnAction(event -> {
            searchFlights search = new searchFlights();
            search.start(primaryStage);
        });

        refund.setOnAction( e -> {
            cancelReservation cancel = new cancelReservation();
            cancel.start(tableView.getSelectionModel().getSelectedItem(), primaryStage);
        });

        HBox hBox1 = new HBox(15);
        hBox1.getChildren().addAll(confirm, refund, back);

        if (checkadmin == true){
        hBox1.getChildren().add(search);
            hBox1.getChildren().add(searchButton);

            searchButton.setOnAction(event -> {
                String searchWord = search.getText();
                tableView.setItems(data.getReservations(loginid, checkadmin, searchWord));
            });
        }

        menu menu1 = new menu();


        layout.setTop(menu1.display(primaryStage));
        layout.setCenter(tableView);
        layout.setBottom(hBox1);
        primaryStage.setTitle("Manage reservations");
        primaryStage.show();
    }
}
