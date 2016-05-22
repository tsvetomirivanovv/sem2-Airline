package views;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import services.OscaServices;


public class menu {

    public MenuBar display(Stage primaryStage){
        Menu filemenu = new Menu("File");
        Menu reservationsMenu = new Menu("Manage reservations");
        Menu planesMenus = new Menu("Manage Planes");
        Menu OSCA = new Menu("OSCA");

        //MENU ITEMS
        MenuItem login = new MenuItem("Log in as Admin");
        MenuItem loginCustomer = new MenuItem("Log in as Customer");
        MenuItem addPlane = new MenuItem("Add Plane");
        MenuItem manageReservations = new MenuItem("Manage Reservations");
        MenuItem OSCA1 = new MenuItem("OSCA");

        loginCustomer.setOnAction(event -> {
            loginAsCustomer login2 = new loginAsCustomer();
            login2.start();
        });

        login.setOnAction(e->{
            Login login1 = new Login();
            login1.start();
        });


        addPlane.setOnAction(e->{
            managePlanes managePlanes = new managePlanes();

            managePlanes.start(primaryStage);
        });

        manageReservations.setOnAction(event -> {
            views.manageReservations manageReservations1 = new manageReservations();
            manageReservations1.start(primaryStage);
        });

        OSCA1.setOnAction(event -> {
            OscaServices caos = new OscaServices();
            caos.FlightToCSV();
        });

        filemenu.getItems().addAll(login,loginCustomer);
        planesMenus.getItems().addAll(addPlane);
        reservationsMenu.getItems().addAll(manageReservations);
        OSCA.getItems().addAll(OSCA1);

        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filemenu,reservationsMenu,planesMenus,OSCA);
        return menuBar;

    }

}
