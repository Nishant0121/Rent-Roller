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

public class RentVehicleForm extends JFrame implements ActionListener {

    // Define form components
    JTextField txtVehicleId, txtCustomerId, txtCustomerName, txtRentPerDay, txtDepositAmount, txtLocation;
    JComboBox<String> cmbDepositType;
    JSpinner dateFrom, dateTo;
    JButton btnOk, btnCancel, btnFind;

    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rentroller";
    private static final String USER = "root";
    private static final String PASS = "1234Qwer";

    RentVehicleForm() {
        // Set up the frame
        setTitle("Rent Roller Vehicle Rental Service");
        setSize(700, 450); // Increased height for the new field
        setLayout(null);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Title
        JLabel lblTitle = new JLabel("Rent Roller Vehicle Rental Service");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(100, 10, 400, 30);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle);

        // Labels and Fields
        JLabel lblVehicleId = new JLabel("Vehicle Id");
        lblVehicleId.setBounds(50, 70, 100, 30);
        add(lblVehicleId);

        txtVehicleId = new JTextField();
        txtVehicleId.setBounds(150, 70, 150, 30);
        add(txtVehicleId);

        JLabel lblCustomerId = new JLabel("Customer Id");
        lblCustomerId.setBounds(50, 110, 100, 30);
        add(lblCustomerId);

        txtCustomerId = new JTextField();
        txtCustomerId.setBounds(150, 110, 150, 30);
        add(txtCustomerId);

        JLabel lblCustomerName = new JLabel("Customer Name");
        lblCustomerName.setBounds(50, 150, 100, 30);
        add(lblCustomerName);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(150, 150, 150, 30);
        add(txtCustomerName);

        JLabel lblRentPerDay = new JLabel("Rent (Per Day)");
        lblRentPerDay.setBounds(50, 190, 100, 30);
        add(lblRentPerDay);

        txtRentPerDay = new JTextField();
        txtRentPerDay.setBounds(150, 190, 150, 30);
        add(txtRentPerDay);

        // New Location Field
        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(50, 230, 100, 30);
        add(lblLocation);

        txtLocation = new JTextField();
        txtLocation.setBounds(150, 230, 150, 30);
        add(txtLocation);

        JLabel lblDateFrom = new JLabel("Date (From)");
        lblDateFrom.setBounds(330, 70, 100, 30);
        add(lblDateFrom);

        dateFrom = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor fromEditor = new JSpinner.DateEditor(dateFrom, "dd MMM yyyy");
        dateFrom.setEditor(fromEditor);
        dateFrom.setValue(new Date()); // Default to today's date
        dateFrom.setBounds(430, 70, 150, 30);
        add(dateFrom);

        JLabel lblDateTo = new JLabel("Date (To)");
        lblDateTo.setBounds(330, 110, 100, 30);
        add(lblDateTo);

        dateTo = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor toEditor = new JSpinner.DateEditor(dateTo, "dd MMM yyyy");
        dateTo.setEditor(toEditor);
        dateTo.setValue(new Date()); // Default to today's date
        dateTo.setBounds(430, 110, 150, 30);
        add(dateTo);

        JLabel lblDepositType = new JLabel("Deposit");
        lblDepositType.setBounds(330, 150, 100, 30);
        add(lblDepositType);

        cmbDepositType = new JComboBox<>(new String[] { "Offline", "Online" });
        cmbDepositType.setBounds(430, 150, 150, 30);
        add(cmbDepositType);

        JLabel lblDepositAmount = new JLabel("Deposit Amount");
        lblDepositAmount.setBounds(330, 190, 100, 30);
        add(lblDepositAmount);

        txtDepositAmount = new JTextField();
        txtDepositAmount.setBounds(430, 190, 150, 30);
        add(txtDepositAmount);

        // Buttons
        btnFind = new JButton("Find");
        btnFind.setBounds(150, 290, 100, 30);
        btnFind.setBackground(new Color(0, 200, 255)); // Light cyan
        btnFind.addActionListener(this);
        add(btnFind);

        btnOk = new JButton("Ok");
        btnOk.setBounds(270, 290, 100, 30);
        btnOk.setBackground(new Color(0, 200, 255)); // Light cyan
        btnOk.addActionListener(this);
        add(btnOk);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(390, 290, 100, 30);
        btnCancel.setBackground(new Color(0, 200, 255)); // Light cyan
        btnCancel.addActionListener(this);
        add(btnCancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFind) {
            String vehicleId = txtVehicleId.getText();
            String customerId = txtCustomerId.getText();

            // Fetch data from the database
            String customerName = findCustomerName(customerId);
            String rentPerDay = findRentPerDay(vehicleId);
            String depositAmount = findDepositAmount(vehicleId);

            // Fill fields with retrieved data
            txtCustomerName.setText(customerName);
            txtRentPerDay.setText(rentPerDay);
            txtDepositAmount.setText(depositAmount);
        } else if (e.getSource() == btnOk) {
            String vehicleId = txtVehicleId.getText();
            String customerId = txtCustomerId.getText();
            String customerName = txtCustomerName.getText();
            String rentPerDay = txtRentPerDay.getText();
            String depositAmount = txtDepositAmount.getText();
            String depositType = (String) cmbDepositType.getSelectedItem();
            String location = txtLocation.getText(); // Get location input
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            String dateFromstr = sdf.format((Date) dateFrom.getValue());
            String dateToStr = sdf.format((Date) dateTo.getValue());

            // Print the form data
            String message = String.format(
                    "Vehicle ID: %s\nCustomer ID: %s\nCustomer Name: %s\nRent Per Day: %s\n" +
                            "Deposit Amount: %s\nDeposit Type: %s\nLocation: %s\nDate From: %s\nDate To: %s",
                    vehicleId, customerId, customerName, rentPerDay, depositAmount, depositType, location, dateFromstr,
                    dateToStr);
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement pstmt = conn.prepareStatement(
                            "INSERT INTO rent (vehicle_id, customer_id, customer_name, rent_date, return_date, location, rentperday, deposit,deposit_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                pstmt.setString(1, vehicleId);
                pstmt.setString(2, customerId);
                pstmt.setString(3, customerName);
                pstmt.setString(4, dateFromstr);
                pstmt.setString(5, dateToStr);
                pstmt.setString(6, location);
                pstmt.setString(7, rentPerDay);
                pstmt.setString(8, depositAmount);
                pstmt.setString(9, depositType);
                // Insert location

                pstmt.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, message, "Rental Details", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == btnCancel) {
            dispose();
            new MainMenu();
        }
    }

    private String findCustomerName(String customerId) {
        String customerName = "Not Found";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn
                        .prepareStatement("SELECT customerName FROM customer WHERE idProofNumber = ?")) {
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customerName = rs.getString("customerName");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customerName;
    }

    private String findRentPerDay(String vehicleId) {
        String rentPerDay = "0";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement("SELECT rent FROM vehicles WHERE vehicle_id = ?")) {
            pstmt.setString(1, vehicleId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                rentPerDay = rs.getString("rent");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rentPerDay;
    }

    private String findDepositAmount(String vehicleId) {
        String depositAmount = "0";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement("SELECT deposit FROM vehicles WHERE vehicle_id = ?")) {
            pstmt.setString(1, vehicleId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                depositAmount = rs.getString("deposit");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return depositAmount;
    }

    public static void main(String[] args) {
        new RentVehicleForm();
    }
}
