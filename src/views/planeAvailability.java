package views;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Interval;
import services.DataController;

import java.security.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Caseru on 5/25/2016.
 */
public class planeAvailability {
    DataController data = new DataController();

    public void start(int id){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Button close = new Button("close");

        TableView<Interval> table = new TableView<>();
        table.setItems(data.getPlanes().get(id).getIn_use());

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
    }
}
