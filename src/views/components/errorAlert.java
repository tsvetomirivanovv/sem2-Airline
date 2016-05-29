package views.components;

import javafx.scene.control.Alert;

public class errorAlert {

    public void display(String title, String message) {
        Alert granted = new Alert(Alert.AlertType.ERROR);
        granted.setTitle(title);
        granted.setContentText(message);
        granted.setHeaderText(null);
        granted.show();
    }
}
