package views;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import services.OscaServices;
import services.components.checkLogin;


public class menu {

    public MenuBar display(Stage primaryStage){

        // Call the checkLogin class

        Menu filemenu1 = new Menu("File");
        Menu filemenu2 = new Menu("File");
        Menu reservationsMenu = new Menu("Manage reservations");
        Menu planesMenus = new Menu("Manage Planes");
        Menu OSCA = new Menu("OSCA");
        Menu flightsMenu = new Menu("Manage flights");


        //MENU ITEMS
        MenuItem login = new MenuItem("Log in");
        MenuItem logOut = new MenuItem("Log out");
        MenuItem addPlane = new MenuItem("Add Plane");
        MenuItem manageReservations = new MenuItem("Manage Reservations");
        MenuItem flights = new MenuItem("Export flights");
        MenuItem planes = new MenuItem("Export planes");
        MenuItem manageFlights = new MenuItem("Manage flights");

        logOut.setOnAction(event -> {
            checkLogin.logOut();
            searchFlights searchFlights = new searchFlights();
            searchFlights.start(primaryStage);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("You have logged out!");
            alert.setContentText("You are now back in the guest screen !");
            alert.showAndWait();
        });

        login.setOnAction(event -> {
            Login login2 = new Login();
            login2.start(primaryStage);
        });

        addPlane.setOnAction(e->{
            managePlanes managePlanes = new managePlanes();

            managePlanes.start(primaryStage);
        });

        manageReservations.setOnAction(event -> {
            views.manageReservations manageReservations1 = new manageReservations();
            manageReservations1.start(primaryStage);
        });

        flights.setOnAction(event -> {
            OscaServices caos = new OscaServices();
            caos.FlightToCSV();
        });
        planes.setOnAction(event -> {
            OscaServices caos = new OscaServices();
            caos.PlaneToCSV();
        });

        manageFlights.setOnAction(event -> {
            views.manageFlights manageFlights1 = new manageFlights();
            manageFlights1.start(primaryStage);
        });

        filemenu1.getItems().addAll(login);
        filemenu2.getItems().addAll(logOut);
        planesMenus.getItems().addAll(addPlane);
        reservationsMenu.getItems().addAll(manageReservations);
        OSCA.getItems().addAll(flights,planes);
        flightsMenu.getItems().addAll(manageFlights);
        //Main menu bar
        MenuBar menuBar = new MenuBar();
        if(!checkLogin.isLoggedIn()) {
            menuBar.getMenus().addAll(filemenu1,OSCA);
        }

        if(checkLogin.isLoggedIn()) {
            if (checkLogin.isAdmin()) {
                menuBar.getMenus().addAll(filemenu2, reservationsMenu, planesMenus, flightsMenu, OSCA);
            } else {
                menuBar.getMenus().addAll(filemenu2, reservationsMenu, OSCA);
            }

        }
        return menuBar;

    }

}
