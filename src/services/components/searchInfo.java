package services.components;

public class searchInfo {

    private String departure_loc;
    private String arrival_loc;
    private String start_date; // Start date
    private String return_date; // Return date (It might be null if the reservation is one way)
    private int passengers;
    private String classType;

    public searchInfo(String departure, String arrival, String date1, String date2, int passengers_no, String class_type) {
        this.departure_loc = departure;
        this.arrival_loc = arrival;
        this.start_date = date1;
        this.return_date = date2;
        this.passengers = passengers_no;
        this.classType = class_type;
    }

    public searchInfo() {
        this.departure_loc = "";
        this.arrival_loc = "";
        this.start_date = "";
        this.return_date = "";
        this.passengers = 0;
        this.classType = "";
    }

    public String getDeparture_loc() {
        return departure_loc;
    }

    public void setDeparture_loc(String departure_loc) {
        this.departure_loc = departure_loc;
    }

    public String getArrival_loc() {
        return arrival_loc;
    }

    public void setArrival_loc(String arrival_loc) {
        this.arrival_loc = arrival_loc;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public boolean hasReturnDate() {
        if(getReturn_date() == "") {
            return false;
        } else {
            return true;
        }
    }
}
