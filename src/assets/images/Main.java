import javafx.application.Application;
import javafx.stage.Stage;
import views.searchFlights;
import java.sql.SQLException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;
        searchFlights searchFlights = new searchFlights();
        searchFlights.start(window);
    }
}
