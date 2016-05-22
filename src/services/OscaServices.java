package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Airport;
import models.Flight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Caseru on 5/22/2016.
 */
public class OscaServices {

    DataController x = new DataController();
    public void FlightToCSV () {
        PrintStream out = null;
        try {
            out = new PrintStream(new File("Flights.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Flight> list = FXCollections.observableList(x.getFlights());
        for (int i=0; i<list.size(); i++) {
            out.print(list.get(i).getFlight_id()+", ");
            out.print(list.get(i).getPlane().getPlane_id()+", ");
            out.print(list.get(i).getDeparture_loc().getAirport_code()+", ");
            out.print(list.get(i).getArrival_loc().getAirport_code()+", ");
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(list.get(i).getDeparture_time());
            out.print(timeStamp+", ");
            String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(list.get(i).getArrival_time());
            out.print(timeStamp1);
            out.println();
        }

    }
}
