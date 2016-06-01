import javafx.application.Application;
import javafx.stage.Stage;
import views.searchFlights;
import services.components.checkLogin;


public class Main extends Application {

    // Global object where we store information about loginStatus.
    public static checkLogin checkLogin = new checkLogin();

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
