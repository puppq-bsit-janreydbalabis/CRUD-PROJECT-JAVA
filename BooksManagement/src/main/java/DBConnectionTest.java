import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Bookstore?useSSL=false";
        String username = "root";
        String password = "Andromeda_042004";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            con.close();
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}