package views;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Interval;
import models.Plane;
import services.DataController;
import java.text.SimpleDateFormat;

public class planeAvailability {
    DataController data = new DataController();

    public void start(Plane pl) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Button closeBut = new Button("close");
        closeBut.getStyleClass().addAll("btn","btn-danger");
        StackPane close = new StackPane(closeBut);
        close.setAlignment(Pos.CENTER_RIGHT);
        closeBut.setOnAction(event -> {
            window.close();
        });

        TableView<Interval> table = new TableView<>();
        table.setItems(pl.getIn_use());

        TableColumn<Interval, String> departure = new TableColumn<>("Departure");
        departure.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getDepartureTime());
            ObservableValue<String> deptime = new ReadOnlyObjectWrapper<>(timeStamp);

            return deptime;
        });
        TableColumn<Interval, String> arrival = new TableColumn<>("Arrival");
        arrival.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getArrivalTime());
            ObservableValue<String> artime = new ReadOnlyObjectWrapper<>(timeStamp);

            return artime;
        });

        table.getColumns().addAll(departure,arrival);

        Label regNo = new Label("Registration Number: " + pl.getReg_no());
        Label Model = new Label("Model: " + pl.getModel());
        Label seatsBus = new Label("Business seats: "+pl.getBusinessSeats());
        Label seatsCo = new Label("Coach seats: "+pl.getCoachSeats());
        Label seatsEco = new Label("Economy seats: "+pl.getEconomySeats());

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(regNo,Model,seatsBus,seatsCo,seatsEco,table,close);

        window.setScene(new Scene(layout,600,400));
        layout.getStylesheets().add("assets//styles//style.css");
        window.showAndWait();
    }
}
