package views.components;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import models.Reservation;
import views.reservationDetails;

public class reservationsDetailsCell {

    public Callback<TableColumn<Reservation, Reservation>, TableCell<Reservation, Reservation>> getCallback1(){

        Callback<TableColumn<Reservation, Reservation>, TableCell<Reservation, Reservation>> callback1 = new Callback<TableColumn<Reservation, Reservation>, TableCell<Reservation, Reservation>>() {
            @Override public TableCell<Reservation, Reservation> call(TableColumn<Reservation, Reservation> details) {
                return new TableCell<Reservation, Reservation>() {
                    final Button button = new Button(); {
                        button.setMinWidth(100);
                    }
                    @Override public void updateItem(final Reservation reservation, boolean empty) {
                        super.updateItem(reservation, empty);
                        if (reservation != null) {
                            button.setText("View");

                            setGraphic(button);
                            button.setOnAction(e -> {
                                reservationDetails seeDetails = new reservationDetails();
                                seeDetails.start(reservation);
                            });
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        };

        return callback1;
    }

    public Callback<TableColumn.CellDataFeatures<Reservation, Reservation>, ObservableValue<Reservation>> getCallback2() {
        Callback<TableColumn.CellDataFeatures<Reservation, Reservation>, ObservableValue<Reservation>> callback2 = new Callback<TableColumn.CellDataFeatures<Reservation, Reservation>, ObservableValue<Reservation>>() {
            @Override public ObservableValue<Reservation> call(TableColumn.CellDataFeatures<Reservation, Reservation> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
            }
        };

        return callback2;
    }
}
