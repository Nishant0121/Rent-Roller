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

    private Color bgColor = new Color(30, 30, 30);
    private Color textColor = new Color(230, 230, 230);
    private Color accentColor = new Color(0, 107, 255);
    private Color inputColor = new Color(0, 0, 0);

    RentVehicleForm() {
        // Set up the frame
        setTitle("Rent Roller Vehicle Rental Service");
        setSize(700, 600);
        setLocationRelativeTo(null); // Center the window on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(bgColor);

        // Create a content panel with null layout
        JPanel contentPanel = new JPanel(null);
        contentPanel.setBackground(bgColor);
        contentPanel.setPreferredSize(new Dimension(600, 400));

        // Add components to contentPanel
        // Title
        JLabel lblTitle = new JLabel("Rent Roller Vehicle Rental Service");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(100, 10, 400, 30);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setForeground(accentColor);
        contentPanel.add(lblTitle);

        // Labels and Fields
        JLabel lblVehicleId = new JLabel("Vehicle Id");
        lblVehicleId.setBounds(50, 70, 100, 30);
        lblVehicleId.setForeground(textColor);
        contentPanel.add(lblVehicleId);

        txtVehicleId = new JTextField();
        txtVehicleId.setBounds(150, 70, 150, 30);
        txtVehicleId.setForeground(inputColor);
        contentPanel.add(txtVehicleId);

        JLabel lblCustomerId = new JLabel("Customer Id");
        lblCustomerId.setBounds(50, 110, 100, 30);
        lblCustomerId.setForeground(textColor);
        contentPanel.add(lblCustomerId);

        txtCustomerId = new JTextField();
        txtCustomerId.setBounds(150, 110, 150, 30);
        txtCustomerId.setForeground(inputColor);
        contentPanel.add(txtCustomerId);

        JLabel lblCustomerName = new JLabel("Customer Name");
        lblCustomerName.setBounds(50, 150, 100, 30);
        lblCustomerName.setForeground(textColor);
        contentPanel.add(lblCustomerName);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(150, 150, 150, 30);
        txtCustomerName.setForeground(inputColor);
        contentPanel.add(txtCustomerName);

        JLabel lblRentPerDay = new JLabel("Rent (Per Day)");
        lblRentPerDay.setBounds(50, 190, 100, 30);
        lblRentPerDay.setForeground(textColor);
        contentPanel.add(lblRentPerDay);

        txtRentPerDay = new JTextField();
        txtRentPerDay.setBounds(150, 190, 150, 30);
        txtRentPerDay.setForeground(inputColor);
        contentPanel.add(txtRentPerDay);

        // New Location Field
        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(50, 230, 100, 30);
        lblLocation.setForeground(textColor);
        contentPanel.add(lblLocation);

        txtLocation = new JTextField();
        txtLocation.setBounds(150, 230, 150, 30);
        txtLocation.setForeground(inputColor);
        contentPanel.add(txtLocation);

        JLabel lblDateFrom = new JLabel("Date (From)");
        lblDateFrom.setBounds(330, 70, 100, 30);
        lblDateFrom.setForeground(textColor);
        contentPanel.add(lblDateFrom);

        dateFrom = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor fromEditor = new JSpinner.DateEditor(dateFrom, "dd MMM yyyy");
        dateFrom.setEditor(fromEditor);
        dateFrom.setValue(new Date()); // Default to today's date
        dateFrom.setBounds(430, 70, 150, 30);
        dateFrom.setForeground(inputColor);
        contentPanel.add(dateFrom);

        JLabel lblDateTo = new JLabel("Date (To)");
        lblDateTo.setBounds(330, 110, 100, 30);
        lblDateTo.setForeground(textColor);
        contentPanel.add(lblDateTo);

        dateTo = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor toEditor = new JSpinner.DateEditor(dateTo, "dd MMM yyyy");
        dateTo.setEditor(toEditor);
        dateTo.setValue(new Date()); // Default to today's date
        dateTo.setBounds(430, 110, 150, 30);
        dateTo.setForeground(inputColor);
        contentPanel.add(dateTo);

        JLabel lblDepositType = new JLabel("Deposit");
        lblDepositType.setBounds(330, 150, 100, 30);
        lblDepositType.setForeground(textColor);
        contentPanel.add(lblDepositType);

        cmbDepositType = new JComboBox<>(new String[] { "Offline", "Online" });
        cmbDepositType.setBounds(430, 150, 150, 30);
        cmbDepositType.setForeground(inputColor);
        contentPanel.add(cmbDepositType);

        JLabel lblDepositAmount = new JLabel("Deposit Amount");
        lblDepositAmount.setBounds(330, 190, 100, 30);
        lblDepositAmount.setForeground(textColor);
        contentPanel.add(lblDepositAmount);

        txtDepositAmount = new JTextField();
        txtDepositAmount.setBounds(430, 190, 150, 30);
        txtDepositAmount.setForeground(inputColor);
        contentPanel.add(txtDepositAmount);

        // Buttons
        btnFind = new JButton("Find");
        btnFind.setBounds(150, 320, 100, 40);
        btnFind.setFont(new Font("Arial", Font.BOLD, 20));
        btnFind.setBackground(accentColor);
        btnFind.setForeground(textColor);
        btnFind.addActionListener(this);
        contentPanel.add(btnFind);

        btnOk = new JButton("Rent");
        btnOk.setBounds(270, 320, 100, 40);
        btnOk.setFont(new Font("Arial", Font.BOLD, 20));
        btnOk.setBackground(accentColor);
        btnOk.setForeground(textColor);
        btnOk.addActionListener(this);
        contentPanel.add(btnOk);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(390, 320, 100, 40);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 20));
        btnCancel.setBackground(accentColor);
        btnCancel.setForeground(textColor);
        btnCancel.addActionListener(this);
        contentPanel.add(btnCancel);

        // Add contentPanel to mainPanel
        mainPanel.add(contentPanel);

        // Set mainPanel as the content pane
        setContentPane(mainPanel);

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
                        .prepareStatement("SELECT customerName FROM customer WHERE customer_id = ?")) {
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
