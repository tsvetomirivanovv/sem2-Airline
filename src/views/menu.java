package views;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class menu {

    public MenuBar display(Stage primaryStage){
        Menu filemenu = new Menu("File");
        Menu reservationsMenu = new Menu("Manage reservations");
        Menu planesMenus = new Menu("Manage Planes");

        MenuItem login = new MenuItem("Login as Admin");
        login.setOnAction(e->{
            Login login1 = new Login();
            login1.start();
        });

        MenuItem addPlane = new MenuItem("Add Plane");
        addPlane.setOnAction(e->{
            managePlanes managePlanes = new managePlanes();

            managePlanes.start(primaryStage);
        });




        filemenu.getItems().addAll(login);
        planesMenus.getItems().addAll(addPlane);


        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filemenu,reservationsMenu,planesMenus);
        return menuBar;

    }

}
