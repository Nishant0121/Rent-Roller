import java.sql.*;
import java.net.*;
import java.io.*;

public class DB {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        try {
            String body = "{\"phoneNumber\": \"+919511692910\", \"message\": \"Hello\"}";
            URL url = new URL("http://localhost:5000/api/send-sms");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(body);
            }

            try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = bf.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the API", e);
        }

    }

}
