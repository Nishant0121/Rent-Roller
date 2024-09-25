import java.sql.*;

public class DB {
    static final String URL = "jdbc:mysql://127.0.0.1:3306/rentroller";
    static final String USER = "root";
    static final String PASSWORD = "1234Qwer";

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles;");
            while (rs.next()) {
                String regno = rs.getString("regno");
                String brand = rs.getString("brand");
                System.out.println(regno + " " + brand);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }

    }

}
