import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewRented extends JFrame {
    private JTable rentedVehiclesTable;
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rentroller";
    private static final String USER = "root";
    private static final String PASS = "1234Qwer";

    public ViewRented() {
        setTitle("Rented Vehicles");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the table model with column names
        String[] columnNames = { "Vehicle ID", "Customer Name", "Mobile Number", "Location", "From", "To" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Create the JTable with the model
        rentedVehiclesTable = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(rentedVehiclesTable);

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);

        // Populate the table with data from SQL Server
        fetchRentedVehiclesData(model);
    }

    private void fetchRentedVehiclesData(DefaultTableModel model) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt
                        .executeQuery("SELECT * from rental_details_view;")) {

            while (rs.next()) {
                String vehicleId = rs.getString("vehicle_id");
                String name = rs.getString("customerName");
                String mobileNumber = rs.getString("mobileNumber");
                String location = rs.getString("location");
                String from = rs.getString("rent_date");
                String to = rs.getString("return_date");

                model.addRow(new Object[] { vehicleId, name, mobileNumber, location, from, to });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewRented viewRented = new ViewRented();
            viewRented.setVisible(true);
        });
    }
}
