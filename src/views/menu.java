package views;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import services.OscaServices;
import services.components.checkLogin;

import java.io.IOException;


public class menu {

    public MenuBar display(Stage primaryStage){

        // Call the checkLogin class

        Menu filemenu1 = new Menu("File");
        Menu filemenu2 = new Menu("File");
        Menu reservationsMenu = new Menu("Manage reservations");
        Menu planesMenus = new Menu("Manage Planes");
        Menu OSCA = new Menu("OSCA");
        Menu flightsMenu = new Menu("Manage flights");
        Menu helpMenu = new Menu("Help");

        //MENU ITEMS
        MenuItem login1 = new MenuItem("Log in as Admin");
        MenuItem login2 = new MenuItem("Log in as Customer");
        MenuItem logOut = new MenuItem("Log out");
        MenuItem addPlane = new MenuItem("Add Plane");
        MenuItem manageReservations = new MenuItem("Manage Reservations");
        MenuItem flights = new MenuItem("Export flights");
        MenuItem planes = new MenuItem("Export planes");
        MenuItem reservationsBinary = new MenuItem("Reservations to binary");
        MenuItem manageFlights = new MenuItem("Manage flights");
        MenuItem contactus = new MenuItem("Contact us");
        MenuItem faq = new MenuItem("FAQ");


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

        login1.setOnAction(event -> {
            Login loginAsAdmin = new Login(true);
            loginAsAdmin.start(primaryStage);
        });

        login2.setOnAction(event -> {
            Login loginAsCustomer = new Login(false);
            loginAsCustomer.start(primaryStage);
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
        reservationsBinary.setOnAction(event -> {
            OscaServices caos = new OscaServices();
            try {
                caos.ReservationsToBinary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        manageFlights.setOnAction(event -> {
            views.manageFlights manageFlights1 = new manageFlights();
            manageFlights1.start(primaryStage);
        });

        contactus.setOnAction(event -> {
            contactUS contactUS = new contactUS();
            contactUS.start();
        });

        faq.setOnAction(event -> {
            views.faq faq1 = new faq();
            faq1.start();
        });

        filemenu1.getItems().addAll(login1, login2);
        filemenu1.getStyleClass().add("menu-label");
        filemenu2.getItems().addAll(logOut);
        planesMenus.getItems().addAll(addPlane);
        reservationsMenu.getItems().addAll(manageReservations);
        OSCA.getItems().addAll(flights,planes,reservationsBinary);
        flightsMenu.getItems().addAll(manageFlights);
        helpMenu.getItems().addAll(contactus,faq);

        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("menu");
        if(!checkLogin.isLoggedIn()) {
            menuBar.getMenus().addAll(filemenu1,OSCA,helpMenu);
        }

        if(checkLogin.isLoggedIn()) {
            if (checkLogin.isAdmin()) {
                menuBar.getMenus().addAll(filemenu2, reservationsMenu, planesMenus, flightsMenu, OSCA,helpMenu);
            } else {
                menuBar.getMenus().addAll(filemenu2, reservationsMenu, OSCA,helpMenu);
            }
        }
        return menuBar;

    }

}
