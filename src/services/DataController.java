package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import models.*;
import services.components.checkLogin;

import java.sql.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataController {
    static Connection conn = SQLConfig.connect();

    // getReservations function
    public ObservableList<Reservation> getReservations(int customerid,boolean checklogin,String search) {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        ArrayList<Passenger> passenger_list;
        String query = "";
        if (checklogin == false){
            query = ("SELECT id,flight_id,customer_id,status FROM Reservations WHERE  customer_id = '" + customerid + "'");
        } else if (checklogin == true){
            query = ("SELECT * FROM Reservations r, Customers c WHERE r.customer_id = c.account_id AND c.name like '%" + search + "%' ");
        }

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery(query);

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
                    int total_passengers = passenger_list.size();

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
    public ObservableList<Reservation> getCustomerReservations(int id) {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        ArrayList<Passenger> passenger_list;

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM Reservations WHERE customer_id = '"+id+"'");

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
                    ObservableList<Interval> intervals = getIntervals(plane_id);

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

    // getAvailablePlanes function
    public static ObservableList<String> getAvailablePlanes(String from, String to) {
        ObservableList<String> planes = FXCollections.observableArrayList();
        java.util.Date date1 = null;
        java.util.Date date2 = null;
        String depDate = "";
        String arrDate = "";

        try {
            if(!from.equals("") || !to.equals("")) {
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                date1 = formatter.parse(from);
                depDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);
                date2 = formatter.parse(to);
                arrDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2);
            }
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }


        try {
            Statement s = null;
            s = conn.createStatement();

            if(!from.equals("") || !to.equals("")) {
                ResultSet rs = s.executeQuery("select reg_no, model from planes where id not in (select distinct(p.id) from planes p, flights f where f.plane_id = p.id and ( (f.departure_time <= STR_TO_DATE('" + depDate + "', '%Y-%m-%d %H:%i:%s') and f.arrival_time >= STR_TO_DATE('" + depDate + "', '%Y-%m-%d %H:%i:%s') ) or (f.departure_time <= STR_TO_DATE('" + arrDate + "', '%Y-%m-%d %H:%i:%s') and f.arrival_time >= STR_TO_DATE('" + arrDate + "', '%Y-%m-%d %H:%i:%s')) ))");


                if (rs != null)
                    while (rs.next()) {
                        String plane_reg = rs.getString("reg_no");
                        String model = rs.getString("model");
                        String planeResult = plane_reg + " (" + model + ")";
                        planes.add(planeResult);
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

        if(from.equals("") || to.equals("")) {
            planes.add("Select departure & arrival times");
        }

        return planes;
    }


    public static void addFlight(String depLoc, String arrLoc, String planeItem, String depTime, String arrTime, String price) {
        int planeId = getPlaneId(planeItem);
        String depCode = codeCUT(depLoc);
        String arrCode = codeCUT(arrLoc);
        Double priceValue = Double.parseDouble(price);

        try {
            Statement s = null;
            s = conn.createStatement();

            String query = "INSERT INTO Flights (plane_id, departure_loc, arrival_loc, departure_time, arrival_time, flight_price) VALUES (" + planeId + ", '" + depCode + "', '" + arrCode + "', DATE_FORMAT(STR_TO_DATE('" + depTime + "' , '%d/%m/%Y %H:%i:%s'), '%Y-%m-%d %H:%i:%s'), DATE_FORMAT(STR_TO_DATE('" + arrTime + "' , '%d/%m/%Y %H:%i:%s'), '%Y-%m-%d %H:%i:%s'), " + priceValue + ")";
            s.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }
    }

    public static ObservableList<Interval> getIntervals(int planeId) {
        ObservableList<Interval> intervals = FXCollections.observableArrayList();

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

            ResultSet rs = s.executeQuery("SELECT p.id as passenger_id, p.name, p.birth_date, p.seat_no, p.baggage FROM Reservations r, Reservation_passengers rp, Passengers p WHERE p.id = rp.passenger_id AND rp.reservation_id = "+ reservationId +" GROUP BY p.id");

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

                    airport = new Airport(airport_name, airport_city, airport_code);
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

    public static void addPlane(String getReg_no, String getModel, int getBusinessSeats, int getCoachSeats, int getEconomySeats, double getBusinessPrice, double getCoachPrice){
        try {
            Statement s = null;
            s = conn.createStatement();


            String query = "INSERT INTO `Planes` (`reg_no`,`model`,`firstclass_seats`,`coach_seats`,`economy_seats`,`firstclass_price`,`coach_price`) " +
                    "VALUES ('"+getReg_no+"','"+getModel+"',"+getBusinessSeats+","+getCoachSeats+","+getEconomySeats+","+getBusinessPrice+","+getCoachPrice+");";
            {
                s.executeUpdate(query);
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

   public static String setReservationStatus(int id) {
       String name = "";

       try {
           Statement s = null;
           s = conn.createStatement();


           String query = "UPDATE Reservations set status  = 'confirmed' WHERE id  = '" + id + "'";
           {
               s.executeUpdate(query);
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

    public static String cancelReservation(int id) {
        String name = "";

        try {
            Statement s = null;
            s = conn.createStatement();


            // ResultSet rs = s.executeQuery("SELECT status FROM Reservations WHERE customer_id = '" + customerId + "'");
            String query = "UPDATE reservation_passengers rp, passengers p, reservations r " +
                    "SET r.status = 'canceled', p.seat_no = -1 " +
                    "WHERE rp.reservation_id = "+id+" AND rp.passenger_id = p.id AND r.id = rp.reservation_id;";
            {
                s.executeUpdate(query);
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



    public static Boolean login(String email, String password) {
        checkLogin checkLogin = new checkLogin();
        Boolean loggedIn = false;
        int id = -1;
        int role = -1;
        String account_email = "";
        String account_password = "";


        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * from Accounts where email = '" + email + "' and password='" + password + "'");
            if (rs != null) {
                while (rs.next()) {
                    id = rs.getInt("id");
                    role = rs.getInt("role");
                    account_email = rs.getString("email");
                    account_password = rs.getString("password");
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

        if(id == -1) {
            loggedIn = false;
        } else {
            loggedIn = true;
            checkLogin.setLoggedIn(true);
            System.out.println("Logged in");
            checkLogin.setAccount_id(id);
            checkLogin.setAccount_email(account_email);

            if (role == 1) {
                checkLogin.setAdmin(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText("You have logged in as admin!");
                alert.setContentText("You can now manage planes, flights and reservations.");
                alert.showAndWait();
            } else if (role == 0) {
                checkLogin.setAdmin(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText("You have logged in as a customer!");
                alert.setContentText("You can now search a flight and book a reservation.");
                alert.showAndWait();
            }
        }

        return loggedIn;
    }

    public static int getCustomerId(String email, String password) {
        checkLogin checkLogin = new checkLogin();
        Boolean loggedIn = false;
        int id = -1;
        int role = -1;
        String account_email = "";
        String account_password = "";


        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT * from Accounts where email = '" + email + "' and password='" + password + "'");
            if (rs != null) {
                while (rs.next()) {
                    id = rs.getInt("id");
                    role = rs.getInt("role");
                    account_email = rs.getString("email");
                    account_password = rs.getString("password");
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

        if(id == -1) {
            loggedIn = false;
        } else {
            loggedIn = true;
            checkLogin.setLoggedIn(true);
            System.out.println("Logged in");
            checkLogin.setAccount_id(id);
            checkLogin.setAccount_email(account_email);

            if (role == 1) {
                checkLogin.setAdmin(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText("You have logged in as admin!");
                alert.setContentText("You can now manage planes, flights and reservations.");
                alert.showAndWait();
            } else if (role == 0) {
                checkLogin.setAdmin(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText("You have logged in as a customer!");
                alert.setContentText("You can now search a flight and book a reservation.");
                alert.showAndWait();
            }
        }

        return id;
    }

    public static ObservableList<Airport> getAirports() {
        ObservableList<Airport> airports = FXCollections.observableArrayList();
        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT a.airport_code as airport_code, a.name as airport_name, a.city as airport_city FROM Airports a");

            if (rs != null)
                while (rs.next()) {
                    String airport_code = rs.getString("airport_code");
                    String airport_name = rs.getString("airport_name");
                    String airport_city = rs.getString("airport_city");

                    Airport airport = new Airport(airport_name, airport_code, airport_city);
                    airports.add(airport);
                }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }

        return airports;
    }

    public static ObservableList<String> getAllAirports(String exclude) {
        ObservableList<String> airports = FXCollections.observableArrayList();
        try {
            Statement s = null;
            s = conn.createStatement();
            ResultSet rs;

            if(exclude.length() > 0) {
                exclude = codeCUT(exclude);

                rs = s.executeQuery("SELECT airport_code, city as airport_city FROM Airports WHERE airport_code != '" + exclude + "'");
            } else {
                rs = s.executeQuery("SELECT airport_code, city as airport_city FROM Airports");
            }

            if (rs != null)
                while (rs.next()) {
                    String airport_code = rs.getString("airport_code");
                    String airport_city = rs.getString("airport_city");

                    String airportResult = airport_city + " (" + airport_code + ")";
                    airports.add(airportResult);
                }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }

        return airports;
    }

    public static ObservableList<String> getAllPlanes() {
        ObservableList<String> planes = FXCollections.observableArrayList();
        try {
            Statement s = null;
            s = conn.createStatement();
            ResultSet rs;


                rs = s.executeQuery("SELECT id, reg_no, model  FROM planes");


            if (rs != null)
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String plane_reg = rs.getString("reg_no");
                    String model = rs.getString("model");

                    String planeResult = plane_reg + " (" + model + ")";
                    planes.add(planeResult);
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

    public static ObservableList<Flight> searchFlights(String departure, String date1, String arrival, String date2, int passengers, String classType) {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        departure  = codeCUT(departure);
        arrival  = codeCUT(arrival);

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery("SELECT f.id as flight_id, p.id as plane_id, p.reg_no as plane_number, model, firstclass_seats, coach_seats, economy_seats, firstclass_price, coach_price, departure_loc, arrival_loc, departure_time, arrival_time, flight_price FROM Flights f, Planes p WHERE f.plane_id = p.id AND departure_time like '" + date1 + "%' AND departure_loc = '" + departure + "' AND arrival_loc = '" + arrival + "'");

            if (rs != null)
                while (rs.next()) {
                    int flight_id = rs.getInt("flight_id");
                    String departure_code = rs.getString("departure_loc");
                    String arrival_code = rs.getString("arrival_loc");
                    Timestamp departure_time = rs.getTimestamp("departure_time");
                    Timestamp arrival_time = rs.getTimestamp("arrival_time");
                    Double flight_price = rs.getDouble("flight_price");


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

                    Airport departure_loc = getAirport(departure_code);
                    Airport arrival_loc = getAirport(arrival_code);

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

    public static void updateFlight(int flightId, String depLoc, String arrLoc, String depDate, String ArrDate, String airplane){
        int planeId = getPlaneId(airplane);
        System.err.println("planeId: " + planeId);
        System.err.println("flight_id: " + flightId);
        System.err.println("depLoc: " + codeCUT(depLoc));
        System.err.println("arrLoc: " + codeCUT(arrLoc));
        System.err.println("depDate: " + depDate);
        System.err.println("ArrDate: " + ArrDate);
        System.err.println("airplane: " + airplane);

        try {
            Statement s = null;
            s = conn.createStatement();

            System.out.println("UPDATE Flights SET (plane_id, departure_loc, arrival_loc, departure_time, arrival_time) VALUES ("+ planeId +", '"+codeCUT(depLoc) +"', '"+codeCUT(arrLoc)+"', '"+ depDate +"', '"+ ArrDate +"') WHERE id = " + flightId);

            s.executeUpdate("UPDATE Flights SET plane_id = "+ planeId + ", departure_loc = '"+codeCUT(depLoc) +"',  arrival_loc = '"+codeCUT(arrLoc)+"', departure_time = '"+ depDate +"', arrival_time = '"+ ArrDate +"' WHERE id = " + flightId);
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }
    }

    public static int getPlaneId(String regNo) {
        int planeId = 0;
        regNo = regNo.substring(0, 6);
        System.err.println(regNo);

        try {
            Statement s = null;
            s = conn.createStatement();
            ResultSet rs;


            rs = s.executeQuery("SELECT id FROM Planes WHERE reg_no = '" + regNo + "'");


            if (rs != null)
                while (rs.next()) {
                    planeId = rs.getInt("id");
                }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){}
        }

        return planeId;
    }

    public static String codeCUT(String fullString){
        return  fullString.substring(fullString.length() - 4, fullString.length() - 1);
    }

    public static Double getClassPrice(int flight_id, String classType) {
        Double price = 0.0;
        String query = "";

        switch(classType) {
            case "Economy class":
                price = 0.0;
                break;
            case "Coach class":
                query = "SELECT p.coach_price FROM Planes p, Flights f WHERE f.plane_id = p.id AND f.id = " + flight_id;
                break;
            case "First class":
                query = "SELECT p.firstclass_price FROM Planes p, Flights f WHERE f.plane_id = p.id AND f.id = " + flight_id;
                break;
        }

        try {
            Statement s = null;
            s = conn.createStatement();

            ResultSet rs = s.executeQuery(query);

            if (rs != null)
                while (rs.next()) {

                    switch(classType) {
                        case "Economy class":
                            price = 0.0;
                            break;
                        case "Coach class":
                            price = rs.getDouble("coach_price");
                            break;
                        case "First class":
                            price = rs.getDouble("firstclass_price");
                            break;
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

    public static String getFlightDuration(String date1, String date2) {
        String duration = "";

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        java.util.Date d1 = null;
        java.util.Date d2 = null;

        try {
            d1 = format.parse(date1);
            d2 = format.parse(date2);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            //long diffDays = diff / (24 * 60 * 60 * 1000);

            if(diffHours > 0) {
                duration = diffHours + "h " + diffMinutes + "m";
            } else {
                duration = diffMinutes + "m";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return duration;
    }

    public static ObservableList<Integer> getSeatsForClass(int flight_id, String classe) {
        ObservableList<Integer> integers = FXCollections.observableArrayList();
        Flight flighter = getFlight(flight_id);
        int seats = 0;

        if (classe.equals("coach")) {
            seats=(flighter.getPlane().getCoachSeats());
        }
        else if (classe.equals("economy")) {
            seats=(flighter.getPlane().getEconomySeats());
        }
        else if (classe.equals("business")) {
            seats=(flighter.getPlane().getBusinessSeats());
        }

        for (int i=1; i <= seats; i++) {
            integers.add(i);
        }

        return integers;
    }

    public static void setPayment(String cardType,int cardNo,String cardEXP,String cardName){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        java.util.Date d1 = null;

        try {
            d1= format.parse(cardEXP);
            System.out.println(d1);
            Statement s = null;
            s = conn.createStatement();
            System.out.println(d1);

            String query = "INSERT INTO Payments (card_type,card_no,card_expiration,cardholder_name) " +
                    "VALUES ('"+cardType+"','"+cardNo+"','"+cardEXP+"','"+cardName+"');";
            {
                s.executeUpdate(query);
            }
        } catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                conn.close();
            }
            catch(SQLException sql){}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}