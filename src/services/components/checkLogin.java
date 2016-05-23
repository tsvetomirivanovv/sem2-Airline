package services.components;

public class checkLogin {
    private static boolean loggedIn = false;
    private static boolean isAdmin = false;
    private static int account_id = -1;
    private static String account_email = "";

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
        checkLogin.loggedIn = logged_in;
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setAdmin(boolean admin) {
        checkLogin.isAdmin = admin;
    }

    public static String getAccount_email() {
        return account_email;
    }

    public static void setAccount_email(String account_email) {
        checkLogin.account_email = account_email;
    }

    public static int getAccount_id() {
        return account_id;
    }

    public static void setAccount_id(int account_id) {
        checkLogin.account_id = account_id;
    }

    public static void logOut() {
        setAccount_email("");
        setAccount_id(-1);
        setLoggedIn(false);
        setAdmin(false);
    }
}
