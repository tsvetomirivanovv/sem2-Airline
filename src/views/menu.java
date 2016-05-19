package views;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class menu {

    public MenuBar display(Stage primaryStage){
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Manage reservations");
        Menu menu3 = new Menu("Manage Planes");

        MenuItem login = new MenuItem("Login as Admin");
        login.setOnAction(e->{
            Login login1 = new Login();
            login1.start();
        });

        MenuItem AddPlane = new MenuItem("Add Plane");
        AddPlane.setOnAction(e->{
            managePlanes managePlanes = new managePlanes();

            managePlanes.start(primaryStage);
        });




        menu1.getItems().addAll(login);



        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        return menuBar;

    }

}
