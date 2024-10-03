import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class ReturnVehicle extends JFrame implements ActionListener {

    // Define form components
    JTextField txtCustomerId, txtCustomerName, txtTotalRent, txtFine, txtTotalAmount, txtVehicleId;
    JComboBox<String> cmbModeOfPayment;
    JSpinner dateRent, dateReturn;
    JButton btnReturn, btnCancel, btnFind;

    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rentroller";
    private static final String USER = "root";
    private static final String PASS = "1234Qwer";

    ReturnVehicle() {
        // Set up the frame
        setTitle("Rent Roller Vehicle Rental Service");
        setSize(700, 400);
        setLayout(null);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Title
        JLabel lblTitle = new JLabel("Rent Roller Vehicle Rental Service");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(100, 10, 400, 30);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle);

        // Labels and Fields
        JLabel lblVehicleId = new JLabel("Vehicle Id");
        lblVehicleId.setBounds(50, 70, 100, 30);
        add(lblVehicleId);

        txtVehicleId = new JTextField();
        txtCustomerId = new JTextField();
        txtCustomerName = new JTextField();
        txtTotalRent = new JTextField("2500");
        txtFine = new JTextField("0");
        txtTotalAmount = new JTextField("2500");

        txtVehicleId.setBounds(150, 70, 150, 30);
        add(txtVehicleId);

        JLabel lblCustomerId = new JLabel("Customer Id");
        lblCustomerId.setBounds(50, 110, 100, 30);
        add(lblCustomerId);

        txtCustomerId.setBounds(150, 110, 150, 30);
        add(txtCustomerId);

        JLabel lblCustomerName = new JLabel("Customer Name");
        lblCustomerName.setBounds(50, 150, 100, 30);
        add(lblCustomerName);

        txtCustomerName.setBounds(150, 150, 150, 30);
        add(txtCustomerName);

        JLabel lblRentDate = new JLabel("Rent Date");
        lblRentDate.setBounds(330, 70, 100, 30);
        add(lblRentDate);

        dateRent = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor rentEditor = new JSpinner.DateEditor(dateRent, "dd MMM yyyy");
        dateRent.setEditor(rentEditor);
        dateRent.setBounds(430, 70, 150, 30);
        add(dateRent);

        JLabel lblReturnDate = new JLabel("Return Date");
        lblReturnDate.setBounds(330, 110, 100, 30);
        add(lblReturnDate);

        dateReturn = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor returnEditor = new JSpinner.DateEditor(dateReturn, "dd MMM yyyy");
        dateReturn.setEditor(returnEditor);
        dateReturn.setValue(new Date()); // Default to today's date
        dateReturn.setBounds(430, 110, 150, 30);
        add(dateReturn);

        JLabel lblTotalRent = new JLabel("Total Rent");
        lblTotalRent.setBounds(50, 190, 100, 30);
        add(lblTotalRent);

        txtTotalRent.setBounds(150, 190, 150, 30);
        add(txtTotalRent);

        JLabel lblFine = new JLabel("Fine");
        lblFine.setBounds(330, 150, 100, 30);
        add(lblFine);

        txtFine.setBounds(430, 150, 150, 30);
        add(txtFine);

        JLabel lblModeOfPayment = new JLabel("Mode Of Payment");
        lblModeOfPayment.setBounds(330, 190, 120, 30);
        add(lblModeOfPayment);

        cmbModeOfPayment = new JComboBox<>(new String[] { "Offline", "Online" });
        cmbModeOfPayment.setBounds(430, 190, 150, 30);
        add(cmbModeOfPayment);

        JLabel lblTotalAmount = new JLabel("Total Amount");
        lblTotalAmount.setBounds(50, 230, 100, 30);
        add(lblTotalAmount);

        txtTotalAmount.setBounds(150, 230, 150, 30);
        add(txtTotalAmount);

        // Buttons
        btnFind = new JButton("Find Vehicle");
        btnFind.setBounds(320, 70, 120, 30);
        btnFind.setBackground(new Color(0, 200, 255)); // Light cyan
        btnFind.addActionListener(this);
        add(btnFind);

        btnReturn = new JButton("Return");
        btnReturn.setBounds(180, 290, 100, 30);
        btnReturn.setBackground(new Color(0, 200, 255)); // Light cyan
        btnReturn.addActionListener(this);
        add(btnReturn);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(320, 290, 100, 30);
        btnCancel.setBackground(new Color(0, 200, 255)); // Light cyan
        btnCancel.addActionListener(this);
        add(btnCancel);

        setVisible(true);
    }

    // Method to fetch vehicle details from the database using Vehicle ID
    private void findVehicleById(String vehicleId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn
                        .prepareStatement("SELECT * FROM rent WHERE vehicle_id = ?")) {
            pstmt.setString(1, vehicleId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Fill the form with the details from the database
                txtCustomerId.setText(rs.getString("customer_id"));
                txtCustomerName.setText(rs.getString("customer_name"));
                txtTotalRent.setText(rs.getString("total_rent"));

                // Set Rent Date from the database
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                Date rentDate = sdf.parse(rs.getString("rent_date"));
                dateRent.setValue(rentDate);
            } else {
                JOptionPane.showMessageDialog(this, "No active rental found for Vehicle ID: " + vehicleId, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching vehicle details", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFind) {
            // Find the vehicle by its ID
            String vehicleId = txtVehicleId.getText();
            findVehicleById(vehicleId);
        } else if (e.getSource() == btnReturn) {
            String vehicleId = txtVehicleId.getText();
            String customerId = txtCustomerId.getText();
            String customerName = txtCustomerName.getText();
            String totalRent = txtTotalRent.getText();
            String fine = txtFine.getText();
            String modeOfPayment = (String) cmbModeOfPayment.getSelectedItem();
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            String rentDateStr = sdf.format((Date) dateRent.getValue());
            String returnDateStr = sdf.format((Date) dateReturn.getValue());

            // Calculate total amount (Total Rent + Fine)
            int totalAmount = Integer.parseInt(totalRent) + Integer.parseInt(fine);
            txtTotalAmount.setText(String.valueOf(totalAmount));

            // Print the form data
            String message = String.format(
                    "Vehicle ID: %s\nCustomer ID: %s\nCustomer Name: %s\nRent Date: %s\nReturn Date: %s\n" +
                            "Total Rent: %s\nFine: %s\nMode of Payment: %s\nTotal Amount: %d",
                    vehicleId, customerId, customerName, rentDateStr, returnDateStr, totalRent, fine, modeOfPayment,
                    totalAmount);

            // Update the database to set the return date and other details
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement pstmt = conn.prepareStatement(
                            "UPDATE rent SET return_date = ?, fine = ?, payment_mode = ?, total_amount = ? WHERE vehicle_id = ? AND return_date IS NULL")) {
                pstmt.setString(1, returnDateStr);
                pstmt.setString(2, fine);
                pstmt.setString(3, modeOfPayment);
                pstmt.setInt(4, totalAmount);
                pstmt.setString(5, vehicleId);
                pstmt.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, message, "Rental Details", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == btnCancel) {
            dispose(); // Close the current form
            new MainMenu(); // Assuming you have a MainMenu class
        }
    }

    public static void main(String[] args) {
        new ReturnVehicle();
    }
}
