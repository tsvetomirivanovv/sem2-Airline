package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Airport;
import models.Flight;
import models.Interval;
import models.Plane;

import java.sql.*;
import java.util.ArrayList;

public class DataController {
    static Connection conn = SQLConfig.connect();

    public ObservableList<Plane> getPlanes() {
        ObservableList<Plane> planes = FXCollections.observableArrayList();

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM Planes p");

            if (rs != null)
                while (rs.next()) {

                    //Get plane info
                    int plane_id = rs.getInt("id");
                    String plane_number = rs.getString("reg_no");
                    String model = rs.getString("model");
                    int firstclass_seats = rs.getInt("firstclass_seats");
                    int coach_seats = rs.getInt("coach_seats");
                    int economy_seats = rs.getInt("economy_seats");
                    Double firstclass_price = rs.getDouble("firstclass_price");
                    Double coach_price = rs.getDouble("coach_price");
                    int totalSeats = firstclass_seats + coach_seats + economy_seats;

                    // Get intervals for each plane:
                    ArrayList<Interval> intervals = getIntervals(plane_id);

                    //Create plane object:
                    Plane plane = new Plane(plane_id, plane_number, model, totalSeats, intervals, firstclass_seats, coach_seats, economy_seats, firstclass_price, coach_price);

                    planes.add(plane);
                }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }

        return planes;
    }

    public static ArrayList<Interval> getIntervals(int planeId) {
        ArrayList<Interval> intervals = new ArrayList<>();

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT f.departure_time, f.arrival_time FROM Planes p, Flights f WHERE f.plane_id = '" + planeId + "'");

            if (rs != null) {
                while (rs.next()) {
                    java.sql.Timestamp departure_time = rs.getTimestamp("departure_time");
                    java.sql.Timestamp arrival_time = rs.getTimestamp("arrival_time");

                    Interval interval = new Interval(departure_time, arrival_time);
                    intervals.add(interval);


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

        return intervals;
    }


    public static ObservableList<Flight> getFlights() {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT f.id as flight_id, p.id as plane_id, p.reg_no as plane_number, model, firstclass_seats, coach_seats, economy_seats, firstclass_price, coach_price, departure_loc, arrival_loc, departure_time, arrival_time, flight_price FROM Flights f, Planes p WHERE f.plane_id = p.id");

            if (rs != null)
                while (rs.next()) {
                    int flight_id = rs.getInt("flight_id");
                    String departure_code = rs.getString("departure_loc");
                    String arrival_code = rs.getString("arrival_loc");
                    Timestamp departure_time = rs.getTimestamp("departure_time");
                    Timestamp arrival_time = rs.getTimestamp("arrival_time");
                    Double flight_price = rs.getDouble("flight_price");

                    Airport departure_loc = getAirport(departure_code);
                    Airport arrival_loc = getAirport(arrival_code);

                    //Get plane info
                    int plane_id = rs.getInt("plane_id");
                    String plane_number = rs.getString("plane_number");
                    String model = rs.getString("model");
                    int firstclass_seats = rs.getInt("firstclass_seats");
                    int coach_seats = rs.getInt("coach_seats");
                    int economy_seats = rs.getInt("economy_seats");
                    Double firstclass_price = rs.getDouble("firstclass_price");
                    Double coach_price = rs.getDouble("coach_price");
                    int totalSeats = firstclass_seats + coach_seats + economy_seats;

                    //Create plane object:
                    Plane plane = new Plane(plane_id, plane_number, model, totalSeats, firstclass_seats, coach_seats, economy_seats, firstclass_price, coach_price);

                    // Create flight object
                    Flight flight = new Flight(flight_id, plane, departure_loc, departure_time, arrival_loc, arrival_time);

                    flights.add(flight);

                    //System.err.println(flight_id + " " + plane_number + " " + model + " " + departure_loc.getAirport_code() + " " +arrival_loc.getAirport_code() + " " + departure_time + " " + arrival_time + " " + flight_price);
                }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }

        return flights;
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
