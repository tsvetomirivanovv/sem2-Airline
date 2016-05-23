package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;

import java.sql.*;
import java.util.ArrayList;

public class DataController {
    static Connection conn = SQLConfig.connect();

    // getReservations function
    public ObservableList<Reservation> getReservations() {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        ArrayList<Passenger> passenger_list;

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM Reservations");

            if (rs != null)
                while (rs.next()) {

                    int reservation_id = rs.getInt("id");
                    int flight_id = rs.getInt("flight_id");
                    int customer_id = rs.getInt("customer_id");
                    String status = rs.getString("status");
                    String customer_name = getCustomerName(customer_id);

                    Flight flight = getFlight(flight_id);
                    passenger_list = getPassengers(reservation_id);
                    Double price = getReservationPrice(reservation_id);
                    int total_passengers = getPassengers(reservation_id).size();

                    Reservation reservation = new Reservation(flight, price, reservation_id, status, customer_id, passenger_list, total_passengers , customer_name);
                    reservations.add(reservation);
                }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }

        return reservations;
    }

    public static Flight getFlight(int flightId) {
        Flight flight = null;

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT f.id as flight_id, p.id as plane_id, p.reg_no as plane_number, model, firstclass_seats, coach_seats, economy_seats, firstclass_price, coach_price, departure_loc, arrival_loc, departure_time, arrival_time, flight_price FROM Flights f, Planes p WHERE f.plane_id = p.id AND f.id = '" + flightId + "'");

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
                    flight = new Flight(flight_id, plane, departure_loc, departure_time, arrival_loc, arrival_time, flight_price);
                }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }

        return flight;
    }

    // getPlanes function
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
                    Flight flight = new Flight(flight_id, plane, departure_loc, departure_time, arrival_loc, arrival_time, flight_price);

                    flights.add(flight);
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

    public static String getCustomerName(int customerId) {
        String name = "";

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT name FROM Customers WHERE account_id = '" + customerId + "'");

            if (rs != null) {
                while (rs.next()) {
                    name = rs.getString("name");
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

        return name;
    }

    public static ArrayList<Passenger> getPassengers(int reservationId) {
        ArrayList<Passenger> passengers_list = new ArrayList<>();

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT p.id as passenger_id, p.name, p.birth_date, p.seat_no, p.baggage FROM Reservations r, Reservation_passengers rp, Passengers p WHERE p.id = rp.passenger_id AND rp.reservation_id = '" + reservationId + "'");

            if (rs != null) {
                while (rs.next()) {
                    int passenger_id = rs.getInt("passenger_id");
                    String name = rs.getString("name");
                    Timestamp birth_date = rs.getTimestamp("birth_date");
                    int seat_no = rs.getInt("seat_no");
                    String baggage = rs.getString("baggage");

                    int baggageType = 0;

                    switch (baggage) {
                        case "none":
                            baggageType = 0;
                            break;
                        case "small":
                            baggageType = 1;
                            break;
                        case "large":
                            baggageType = 2;
                            break;
                    }

                    passengers_list.add(new Passenger(passenger_id, baggageType , birth_date, name, seat_no));
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

        return passengers_list;
    }

    public static Double getReservationPrice(int reservationId) {
        Double price = 0.0;

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT f.plane_id as plane_id, r.flight_id, p.seat_no, p.baggage FROM Reservations r, Flights f, Reservation_passengers rp, Passengers p WHERE r.flight_id = f.id AND p.id = rp.passenger_id AND rp.reservation_id = '" + reservationId + "' AND r.id = '"+ reservationId +"'");

            if (rs != null) {
                while (rs.next()) {
                    int seat_no = rs.getInt("seat_no");
                    int plane_id = rs.getInt("plane_id");
                    int flight_id = rs.getInt("flight_id");
                    String baggage = rs.getString("baggage");
                    Double baggagePrice = 0.0;

                    switch (baggage) {
                        case "none":
                            baggagePrice = 0.0;
                            break;
                        case "small":
                            baggagePrice = 50.0;
                            break;
                        case "large":
                            baggagePrice = 90.0;
                            break;
                    }

                    Double seat_price = getPriceForSeat(seat_no, plane_id);
                    Double flight_price = getFlightPrice(flight_id);
                    price = baggagePrice + seat_price + flight_price;
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

        return price;
    }

    public static Double getFlightPrice(int flightId) {
        Double price = 0.0;

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT flight_price FROM Flights WHERE id = '" + flightId + "'");

            if (rs != null) {
                while (rs.next()) {
                    price = rs.getDouble("flight_price");
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

        return price;
    }

    public static Double getPriceForSeat(int seatNo, int planeId) {
        Double price = 0.0;

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM Planes WHERE id = '" + planeId + "'");

            if (rs != null) {
                while (rs.next()) {
                    int firstclass_seats = rs.getInt("firstclass_seats");
                    int coach_seats = rs.getInt("coach_seats");
                    int economy_seats = rs.getInt("economy_seats");
                    Double firstclass_price = rs.getDouble("firstclass_price");
                    Double coach_price = rs.getDouble("coach_price");

                    if (seatNo <= firstclass_seats) {
                        price = firstclass_price;
                    }

                    if (seatNo > firstclass_seats && seatNo <= coach_seats) {
                        price = coach_price;
                    }
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

        return price;
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

    public static String confirmReservation(int id,String rStatus){
        String status = "";
        id = 0;
        try {
            Statement s = null;
            s = conn.createStatement();

          //  ResultSet rs = s.executeUpdate("INSERT INTO Reservations(status)"+ "VALUES('confirmed') WHERE customer_id  = '" + id + "' AND status = '"+ rStatus +"'");
            ResultSet rs = s.executeQuery("SELECT id, status FROM Reservations WHERE customer_id  = '" + id + "' AND status = '"+ rStatus +"'");
            String query = "UPDATE Reservations set status  = confirmed WHERE customer_id  = '" + id + "' AND status = '"+ rStatus +"'";
            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rStatus = rs.getString("status"));

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

        return rStatus;
    }
}
