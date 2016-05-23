package services.components;

public class checkLogin {
    private static boolean loggedIn = false;
    private static boolean isAdmin = false;

    public checkLogin(Boolean loggedIn, Boolean isAdmin) {
        this.loggedIn = loggedIn;
        this.isAdmin = isAdmin;
    }

    public checkLogin() {
        this.loggedIn = false;
        this.isAdmin = false;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean logged_in) {
        loggedIn = logged_in;
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
