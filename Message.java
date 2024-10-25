import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Message {

    public static void main(String[] args) {
        try {
            // Accept input for phoneNumber and message
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter phone number (with country code): ");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter the message: ");
            String message = scanner.nextLine();

            // Set the URL to point to the localhost server at port 5000
            URL url = new URL("http://localhost:5000/api/send-sms");

            // Create a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Set headers for the request
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Enable writing output to this connection
            connection.setDoOutput(true);

            // JSON body containing the phone number and message
            String jsonInputString = String.format("{\"phoneNumber\": \"%s\", \"message\": \"%s\"}", phoneNumber,
                    message);

            // Write the JSON body to the connection's output stream
            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.writeBytes(jsonInputString);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("POST Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                // Read and print the response
                try (BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = bf.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                System.out.println("Message sent successfully!");
            } else {
                System.out.println("POST request failed. Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
