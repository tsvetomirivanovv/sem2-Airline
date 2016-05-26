package views.components;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import models.Plane;
import views.planeAvailability;

public class availabilityCell {

    public Callback<TableColumn<Plane, Plane>, TableCell<Plane, Plane>> getCallback1(){

        Callback<TableColumn<Plane, Plane>, TableCell<Plane, Plane>> callback1 = new Callback<TableColumn<Plane, Plane>, TableCell<Plane, Plane>>() {
            @Override public TableCell<Plane, Plane> call(TableColumn<Plane, Plane> availability) {
                return new TableCell<Plane, Plane>() {
                    final Button button = new Button(); {
                        button.setMinWidth(100);
                    }
                    @Override public void updateItem(final Plane plane, boolean empty) {
                        super.updateItem(plane, empty);
                        if (plane != null) {
                            button.setText("View");

                            setGraphic(button);
                            button.setOnAction(e -> {
                                planeAvailability ddd = new planeAvailability();
                                ddd.start(plane);
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


    public Callback<TableColumn.CellDataFeatures<Plane, Plane>, ObservableValue<Plane>> getCallback2() {
        Callback<TableColumn.CellDataFeatures<Plane, Plane>, ObservableValue<Plane>> callback2 = new Callback<TableColumn.CellDataFeatures<Plane, Plane>, ObservableValue<Plane>>() {
            @Override public ObservableValue<Plane> call(TableColumn.CellDataFeatures<Plane, Plane> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
            }
        };

        return callback2;
    }



}
