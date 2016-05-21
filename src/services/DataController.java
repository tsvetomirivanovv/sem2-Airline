package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Airport;
import models.Flight;

import java.sql.*;

public class DataController {
    static Connection conn = SQLConfig.connect();

    public static void getFlights() {
        ObservableList<Flight> employees = FXCollections.observableArrayList();
        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT f.id as flight_id, p.reg_no as plane_number, model, departure_loc, arrival_loc, departure_time, arrival_time, flight_price FROM Flights f, Planes p WHERE f.plane_id = p.id");

            if (rs != null)
                while (rs.next()) {
                    int flight_id = rs.getInt("flight_id");
                    String plane_number = rs.getString("plane_number");
                    String model = rs.getString("model");
                    String departure_code = rs.getString("departure_loc");
                    String arrival_code = rs.getString("arrival_loc");
                    Timestamp departure_time = rs.getTimestamp("departure_time");
                    Timestamp arrival_time = rs.getTimestamp("arrival_time");
                    Double flight_price = rs.getDouble("flight_price");
                    Airport departure_loc = getAirport(departure_code);
                    Airport arrival_loc = getAirport(arrival_code);

                    System.err.println(flight_id + " " + plane_number + " " + model + " " + departure_loc.getAirport_code() + " " +arrival_loc.getAirport_code() + " " + departure_time + " " + arrival_time + " " + flight_price);
                }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }
    }

    public static Airport getAirport(String airportCode) {
        Airport airport = null;

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT a.airport_code, a.name as airport_name, a.city as airport_city FROM Airports a WHERE a.airport_code = '" + airportCode + "'");

            if (rs != null) {
                while (rs.next()) {
                    String airport_code = rs.getString("airport_code");
                    String airport_name = rs.getString("airport_name");
                    String airport_city = rs.getString("airport_city");

                    airport = new Airport(airport_code, airport_name, airport_city);
                }
            }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }

        return airport;
    }


}
