package views;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

        Button close = new Button("close");

        close.setOnAction(event -> {
            window.close();
        });

        TableView<Interval> table = new TableView<>();
        table.setItems(pl.getIn_use());

        TableColumn<Interval, String> departure = new TableColumn<>();
        departure.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getDepartureTime());
            ObservableValue<String> deptime = new ReadOnlyObjectWrapper<>(timeStamp);

            return deptime;
        });
        TableColumn<Interval, String> arrival = new TableColumn<>();
        arrival.setCellValueFactory(cellData -> {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getArrivalTime());
            ObservableValue<String> artime = new ReadOnlyObjectWrapper<>(timeStamp);

            return artime;
        });

        table.getColumns().addAll(departure,arrival);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(table,close);

        window.setScene(new Scene(layout,600,400));
        window.showAndWait();
    }
}
