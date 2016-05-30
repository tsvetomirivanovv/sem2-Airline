package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Airport;
import models.Flight;
import models.Plane;
import models.Reservation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Caseru on 5/22/2016.
 */
public class OscaServices {

    DataController x = new DataController();

    //region coma separated values
    public void FlightToCSV () {
        PrintStream out = null;
        try {
            out = new PrintStream(new File("Flights.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.println("//// flight_id, plane_id, departure_loc, arrival_loc, departure_time, arrival_time");

        ObservableList<Flight> list = FXCollections.observableList(x.getFlights());
        for (int i=0; i<list.size(); i++) {
            out.print(list.get(i).getFlight_id()+",");
            out.print(list.get(i).getPlane().getPlane_id()+",");
            out.print(list.get(i).getDeparture_loc().getAirport_code()+",");
            out.print(list.get(i).getArrival_loc().getAirport_code()+",");
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(list.get(i).getDeparture_time());
            out.print(timeStamp+",");
            String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(list.get(i).getArrival_time());
            out.print(timeStamp1);
            out.println();
        }
        System.out.println("Done! Flights to Flights.csv");
    }
    public void PlaneToCSV () {
        PrintStream out = null;
        try {
            out = new PrintStream(new File("Planes.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.println("//// plane_id, registration_no, model, businessSeats, coachSeats, economySeats, businessPrice, coachPrice");

        ObservableList<Plane> list = FXCollections.observableList(x.getPlanes());
        for (int i=0; i<list.size(); i++) {
            out.print(list.get(i).getPlane_id()+",");
            out.print(list.get(i).getReg_no()+",");
            out.print(list.get(i).getModel()+",");
            out.print(list.get(i).getBusinessSeats()+",");
            out.print(list.get(i).getCoachSeats()+",");
            out.print(list.get(i).getEconomySeats()+",");
            out.print(list.get(i).getBusinessPrice()+",");
            out.print(list.get(i).getCoachPrice());
            out.println();
        }
        System.out.println("Done! Planes to Planes.csv");
    }
    //endregion

    //region Binary fixed format
    public void ReservationsToBinary() throws IOException {
        PrintStream out = new PrintStream(new File("ReservationsBinary.txt"));
        byte[] bytes;

        for (Reservation one :x.getReservations(0,true,"")){
            bytes = createRecord(one.getReservation_id(),
                    one.getFlight().getFlight_id(),
                    one.getCustomer_id(), one.getStatus());

            out.write(bytes);
        }
    }

    private static byte[] createRecord(int reservationId, int flightId, int customerId, String status)
    {
        byte[] rawRecord = new byte[4 + 4 + 4 + 1 + 18*2];

        rawRecord[0] = (byte) (reservationId >> 24);
        rawRecord[1] = (byte) (reservationId >> 16);
        rawRecord[2] = (byte) (reservationId >> 8);
        rawRecord[3] = (byte) reservationId;

        rawRecord[4] = (byte) (flightId >> 24);
        rawRecord[5] = (byte) (flightId >> 16);
        rawRecord[6] = (byte) (flightId >> 8);
        rawRecord[7] = (byte) flightId;

        rawRecord[8] = (byte) (customerId >> 24);
        rawRecord[9] = (byte) (customerId >> 16);
        rawRecord[10] = (byte) (customerId >> 8);
        rawRecord[11] = (byte) customerId;

        rawRecord[12] = (byte) status.length();

        int j = 13;
        stringRecord(j,status,rawRecord);

        return rawRecord;
    }

    private static void stringRecord(int j, String z, byte[] byte1){
        for ( int i = 0;  i < z.length(); i++)
        {
            char ch = z.charAt(i);

            byte1[j] = (byte) (ch >> 8);
            j++;

            byte1[j] = (byte) ch;
            j++;
        }
    }
    //endregion
}
