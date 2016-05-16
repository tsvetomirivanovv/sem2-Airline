import services.SQLConfig;

public class Main {

    public static void main(String[] args) {
        SQLConfig conn = new SQLConfig();
        conn.connect();


        System.out.println("Hello World!");
    }
}
