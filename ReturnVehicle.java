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
    JTextField txtCustomerId, txtCustomerName, txtTotalRent, txtFine, txtTotalAmount, txtVehicleId, txtDamage;
    JComboBox<String> cmbmode_of_payment;
    JSpinner dateRent, dateReturn;
    JButton btnReturn, btnCancel, btnFind, btnCalculate; // Add btnCalculate

    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rentroller";
    private static final String USER = "root";
    private static final String PASS = "1234Qwer";

    // Fine rate per day (e.g., 50 units)
    private static int FINE_RATE_PER_DAY = 0;
    private static int days = 0;
    private static Date returnDate = null;

    private Color bgColor = new Color(30, 30, 30);
    private Color textColor = new Color(230, 230, 230);
    private Color accentColor = new Color(0, 107, 255);
    private Color inputColor = new Color(0, 0, 0);

    ReturnVehicle() {
        // Set up the frame
        setTitle("Rent Roller Vehicle Rental Service");
        setSize(700, 400);
        setLayout(null);
        getContentPane().setBackground(bgColor);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Title
        JLabel lblTitle = new JLabel("Rent Roller Vehicle Rental Service");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(100, 10, 400, 30);
        lblTitle.setForeground(textColor);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle);

        // Labels and Fields
        JLabel lblVehicleId = new JLabel("Vehicle Id");
        lblVehicleId.setBounds(50, 70, 100, 30);
        lblVehicleId.setForeground(textColor);
        add(lblVehicleId);

        txtVehicleId = new JTextField();
        txtCustomerId = new JTextField();
        txtCustomerName = new JTextField();
        txtTotalRent = new JTextField("");
        txtFine = new JTextField("0");
        txtTotalAmount = new JTextField("");

        txtVehicleId.setBounds(150, 70, 150, 30);
        txtVehicleId.setForeground(inputColor);
        add(txtVehicleId);

        JLabel lblCustomerId = new JLabel("Customer Id");
        lblCustomerId.setBounds(50, 110, 100, 30);
        lblCustomerId.setForeground(textColor);
        add(lblCustomerId);

        txtCustomerId.setBounds(150, 110, 150, 30);
        txtCustomerId.setForeground(inputColor);
        add(txtCustomerId);

        JLabel lblCustomerName = new JLabel("Customer Name");
        lblCustomerName.setBounds(50, 150, 100, 30);
        lblCustomerName.setForeground(textColor);
        add(lblCustomerName);

        txtCustomerName.setBounds(150, 150, 150, 30);
        txtCustomerName.setForeground(inputColor);
        add(txtCustomerName);

        JLabel lblRentDate = new JLabel("Rent Date");
        lblRentDate.setBounds(330, 70, 100, 30);
        lblRentDate.setForeground(textColor);
        add(lblRentDate);

        dateRent = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor rentEditor = new JSpinner.DateEditor(dateRent, "dd MMM yyyy");
        dateRent.setEditor(rentEditor);
        dateRent.setForeground(inputColor);
        dateRent.setBounds(430, 70, 150, 30);
        add(dateRent);

        JLabel lblReturnDate = new JLabel("Return Date");
        lblReturnDate.setBounds(330, 110, 100, 30);
        lblReturnDate.setForeground(textColor);
        add(lblReturnDate);

        dateReturn = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor returnEditor = new JSpinner.DateEditor(dateReturn, "dd MMM yyyy");
        dateReturn.setEditor(returnEditor);
        dateReturn.setForeground(inputColor);
        dateReturn.setValue(new Date()); // Default to today's date
        dateReturn.setBounds(430, 110, 150, 30);
        add(dateReturn);

        JLabel lblTotalRent = new JLabel("Total Rent");
        lblTotalRent.setBounds(50, 190, 100, 30);
        lblTotalRent.setForeground(textColor);
        add(lblTotalRent);

        txtTotalRent.setBounds(150, 190, 150, 30);
        txtTotalRent.setForeground(inputColor);
        add(txtTotalRent);

        JLabel lblFine = new JLabel("Fine");
        lblFine.setBounds(330, 150, 100, 30);
        lblFine.setForeground(textColor);
        add(lblFine);

        txtFine.setBounds(430, 150, 150, 30);
        txtFine.setForeground(inputColor);
        add(txtFine);

        JLabel lblmode_of_payment = new JLabel("Mode Of Payment");
        lblmode_of_payment.setBounds(330, 190, 120, 30);
        lblmode_of_payment.setForeground(textColor);
        add(lblmode_of_payment);

        cmbmode_of_payment = new JComboBox<>(new String[] { "Offline", "Online" });
        cmbmode_of_payment.setBounds(430, 190, 150, 30);
        cmbmode_of_payment.setForeground(inputColor);
        add(cmbmode_of_payment);

        // Add new damage field
        JLabel lblDamage = new JLabel("Damage Cost");
        lblDamage.setBounds(330, 230, 100, 30);
        lblDamage.setForeground(textColor);
        add(lblDamage);

        txtDamage = new JTextField("0");
        txtDamage.setBounds(430, 230, 150, 30);
        txtDamage.setForeground(inputColor);
        add(txtDamage);

        // Move Total Amount field
        JLabel lblTotalAmount = new JLabel("Total Amount");
        lblTotalAmount.setBounds(50, 230, 100, 30);
        lblTotalAmount.setForeground(textColor);
        add(lblTotalAmount);

        txtTotalAmount.setBounds(150, 230, 150, 30);
        txtTotalAmount.setForeground(textColor);
        add(txtTotalAmount);

        // Buttons
        btnFind = new JButton("Find Vehicle");
        btnFind.setBounds(370, 320, 120, 30);
        btnFind.setBackground(accentColor); // Light cyan
        btnFind.addActionListener(this);
        add(btnFind);

        btnReturn = new JButton("Return");
        btnReturn.setBounds(90, 320, 100, 30);
        btnReturn.setBackground(accentColor); // Light cyan
        btnReturn.addActionListener(this);
        add(btnReturn);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(230, 320, 100, 30);
        btnCancel.setBackground(accentColor); // Light cyan
        btnCancel.addActionListener(this);
        add(btnCancel);

        // Add Calculate button
        btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(510, 320, 100, 30);
        btnCalculate.setBackground(accentColor); // Light cyan
        btnCalculate.addActionListener(this);
        add(btnCalculate);

        setVisible(true);
    }

    int depositeAmount = 0;

    // Method to fetch vehicle details from the database using Vehicle ID
    private void findVehicleById(String vehicleId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn
                        .prepareStatement("SELECT * FROM rent WHERE vehicle_id = ?")) {
            pstmt.setString(1, vehicleId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Fill the form with the details from the database

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                Date rentDate = sdf.parse(rs.getString("rent_date"));
                dateRent.setValue(rentDate);
                returnDate = sdf.parse(rs.getString("return_date"));
                dateReturn.setValue(returnDate);

                days = (int) ((returnDate.getTime() - rentDate.getTime()) / (1000 * 60 * 60 * 24));

                txtCustomerId.setText(rs.getString("customer_id"));
                txtCustomerName.setText(rs.getString("customer_name"));
                txtTotalRent.setText(String.valueOf(Integer.parseInt(rs.getString("rentperday")) * days));
                FINE_RATE_PER_DAY = Integer.parseInt(rs.getString("rentperday"));
                depositeAmount = Integer.parseInt(rs.getString("deposit"));
                // Set Rent Date from the database

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
        } else if (e.getSource() == btnCalculate) {
            String vehicleId = txtVehicleId.getText();
            String customerId = txtCustomerId.getText();
            String customerName = txtCustomerName.getText();
            String totalRent = txtTotalRent.getText();
            String mode_of_payment = (String) cmbmode_of_payment.getSelectedItem();
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            String rentDateStr = sdf.format((Date) dateRent.getValue());
            Date rentDate = (Date) dateRent.getValue();
            Date expectedReturnDate = (Date) dateReturn.getValue();
            Date todayDate = new Date(); // Get today's date

            // Calculate delay in return date
            long differenceInMillis = todayDate.getTime() - expectedReturnDate.getTime();
            long delayDays = differenceInMillis / (1000 * 60 * 60 * 24); // Convert milliseconds to days

            // Calculate fine based on delay
            int fine = (delayDays > 0) ? (int) (delayDays * FINE_RATE_PER_DAY) : 0;
            txtFine.setText(String.valueOf(fine));

            // Get damage cost
            int damageCost = Integer.parseInt(txtDamage.getText());

            // Calculate total amount (Total Rent + Fine + Damage Cost)
            int totalAmount = Integer.parseInt(totalRent) + fine + damageCost;
            txtTotalAmount.setText(String.valueOf(totalAmount));

            // Update the message to include damage cost
            String message = String.format(
                    "Vehicle ID: %s\nCustomer ID: %s\nCustomer Name: %s\nRent Date: %s\nReturn Date: %s\n" +
                            "Total Rent: %s\nFine: %s\nDamage Cost: %s\nMode of Payment: %s\nTotal Amount: %d",
                    vehicleId, customerId, customerName, rentDateStr, sdf.format(expectedReturnDate), totalRent, fine,
                    damageCost, mode_of_payment, totalAmount);

            JOptionPane.showMessageDialog(this, message, "Rental Details", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == btnReturn) {
            String vehicleId = txtVehicleId.getText();
            String customerId = txtCustomerId.getText();
            String customerName = txtCustomerName.getText();
            String totalRent = txtTotalRent.getText();
            String mode_of_payment = (String) cmbmode_of_payment.getSelectedItem();
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            String rentDateStr = sdf.format((Date) dateRent.getValue());
            Date rentDate = (Date) dateRent.getValue();
            Date expectedReturnDate = (Date) dateReturn.getValue();
            Date todayDate = new Date(); // Get today's date

            // // Calculate delay in return date
            // long differenceInMillis = todayDate.getTime() - expectedReturnDate.getTime();
            // long delayDays = differenceInMillis / (1000 * 60 * 60 * 24); // Convert
            // milliseconds to days

            // // Calculate fine based on delay
            // int fine = (delayDays > 0) ? (int) (delayDays * FINE_RATE_PER_DAY) : 0;
            // txtFine.setText(String.valueOf(fine));

            // // Get damage cost
            // int damageCost = Integer.parseInt(txtDamage.getText());

            // // Calculate total amount (Total Rent + Fine + Damage Cost)
            // int totalAmount = Integer.parseInt(totalRent) + fine + damageCost;
            // txtTotalAmount.setText(String.valueOf(totalAmount));

            // // Update the message to include damage cost
            // String message = String.format(
            // "Vehicle ID: %s\nCustomer ID: %s\nCustomer Name: %s\nRent Date: %s\nReturn
            // Date: %s\n" +
            // "Total Rent: %s\nFine: %s\nDamage Cost: %s\nMode of Payment: %s\nTotal
            // Amount: %d",
            // vehicleId, customerId, customerName, rentDateStr,
            // sdf.format(expectedReturnDate), totalRent, fine,
            // damageCost, mode_of_payment, totalAmount);

            // Update the database to set the return date and other details
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt2 = conn.prepareStatement(
                        "INSERT INTO bill (amount, deposit, fine, mode_of_payement, customer_id, customer_name) VALUES (?, ?, ?, ?, ?, ?)");
                pstmt2.setInt(1, Integer.parseInt(txtTotalAmount.getText()));
                pstmt2.setInt(2, depositeAmount);
                pstmt2.setInt(3, Integer.parseInt(txtFine.getText()));
                pstmt2.setString(4, mode_of_payment);
                pstmt2.setString(5, customerId);
                pstmt2.setString(6, customerName);
                pstmt2.executeUpdate();

                PreparedStatement pstmt3 = conn.prepareStatement(
                        "DELETE FROM rent WHERE vehicle_id = ?");
                pstmt3.setString(1, vehicleId);
                pstmt3.executeUpdate();

                JOptionPane.showMessageDialog(this, "Vehicle returned successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == btnCancel) {
            dispose(); // Close the current form
            new MainMenu(); // Assuming you have a MainMenu class
        }
    }

    public static void main(String[] args) {
        new ReturnVehicle();
    }
}
