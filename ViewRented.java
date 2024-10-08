import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewRented extends JFrame implements ActionListener {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rentroller";
    private static final String USER = "root";
    private static final String PASS = "1234Qwer";

    private JTable rentedVehiclesTable;
    private JButton closeButton;

    private Color inputColor = new Color(0, 0, 0);
    private Color textColor = new Color(230, 230, 230);
    private Color accentColor = new Color(0, 107, 255);
    private Color bgColor = new Color(30, 30, 30);

    ViewRented() {
        setTitle("Rented Vehicles");
        setSize(800, 400);
        getContentPane().setBackground(bgColor);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Create the table model with column names
        String[] columnNames = { "Vehicle ID", "Customer Name", "Mobile Number", "Location", "From", "To" };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Create the JTable with the model
        rentedVehiclesTable = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(rentedVehiclesTable);

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);

        // Create and add the close button
        closeButton = new JButton("Close");
        closeButton.setBackground(accentColor);
        closeButton.setForeground(textColor);
        closeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            dispose(); // Close the current window
            new MainMenu(); // Open the main menu (assuming you have a MainMenu class)
        }
    }

    public static void main(String[] args) {
        new ViewRented();
    }
}
