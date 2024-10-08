import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

class UpdateVehicle extends JFrame implements ActionListener {

    // Define components
    JTextField regNumberField, typeField, modelNumberField, rentField, seatsField, colorField, brandField, depositField;
    JButton updateVehicleButton, cancelButton, findVehicleButton;

    private Color inputColor = new Color(0, 0, 0);
    private Color textColor = new Color(230, 230, 230);
    private Color accentColor = new Color(0, 150, 136);
    private Color bgColor = new Color(30, 30, 30);

    UpdateVehicle() {
        // Set up the frame
        setTitle("Update Vehicle - Rent Roller Vehicle Rental Service");
        getContentPane().setBackground(bgColor);

        // Title Label
        JLabel title = new JLabel("Rent Roller Vehicle Rental Service");
        title.setForeground(textColor);
        title.setBounds(50, 20, 400, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);

        // Subtitle for "Add New Vehicle"
        JLabel subtitle = new JLabel("UPDATE VEHICLE");
        subtitle.setForeground(textColor);
        subtitle.setBounds(160, 60, 200, 30);
        subtitle.setFont(new Font("Arial", Font.BOLD, 14));

        // Labels and Text Fields
        JLabel regNumberLabel = new JLabel("Registration number");
        regNumberLabel.setForeground(textColor);
        regNumberLabel.setBounds(30, 100, 150, 30);
        regNumberField = new JTextField();
        regNumberField.setBounds(180, 100, 150, 30);

        JLabel seatsLabel = new JLabel("No. of Seats");
        seatsLabel.setForeground(textColor);
        seatsLabel.setBounds(350, 100, 150, 30);
        seatsField = new JTextField();
        seatsField.setBounds(450, 100, 150, 30);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setForeground(textColor);
        typeLabel.setBounds(30, 150, 150, 30);
        typeField = new JTextField();
        typeField.setBounds(180, 150, 150, 30);

        JLabel colorLabel = new JLabel("Color");
        colorLabel.setForeground(textColor);
        colorLabel.setBounds(350, 150, 150, 30);
        colorField = new JTextField();
        colorField.setBounds(450, 150, 150, 30);

        JLabel modelNumberLabel = new JLabel("Model Number");
        modelNumberLabel.setForeground(textColor);
        modelNumberLabel.setBounds(30, 200, 150, 30);
        modelNumberField = new JTextField();
        modelNumberField.setBounds(180, 200, 150, 30);

        JLabel brandLabel = new JLabel("Brand");
        brandLabel.setForeground(textColor);
        brandLabel.setBounds(350, 200, 150, 30);
        brandField = new JTextField();
        brandField.setBounds(450, 200, 150, 30);

        JLabel rentLabel = new JLabel("Rent (Per Day)");
        rentLabel.setForeground(textColor);
        rentLabel.setBounds(30, 250, 150, 30);
        rentField = new JTextField();
        rentField.setBounds(180, 250, 150, 30);

        JLabel depositLabel = new JLabel("Deposit");
        depositLabel.setForeground(textColor);
        depositLabel.setBounds(350, 250, 150, 30);
        depositField = new JTextField();
        depositField.setBounds(450, 250, 150, 30);

        // Buttons
        updateVehicleButton = new JButton("Update Vehicle");
        updateVehicleButton.setForeground(textColor);
        updateVehicleButton.setBackground(accentColor);
        updateVehicleButton.setBounds(100, 300, 150, 40);
        updateVehicleButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setForeground(textColor);
        cancelButton.setBackground(accentColor);
        cancelButton.setBounds(270, 300, 150, 40);
        cancelButton.addActionListener(this);

        findVehicleButton = new JButton("Find");
        findVehicleButton.setForeground(textColor);
        findVehicleButton.setBackground(accentColor);
        findVehicleButton.setBounds(440, 300, 150, 40);
        findVehicleButton.addActionListener(this);

        // Add components to the frame
        add(title);
        add(subtitle);
        add(regNumberLabel);
        add(regNumberField);
        add(seatsLabel);
        add(seatsField);
        add(typeLabel);
        add(typeField);
        add(colorLabel);
        add(colorField);
        add(modelNumberLabel);
        add(modelNumberField);
        add(brandLabel);
        add(brandField);
        add(rentLabel);
        add(rentField);
        add(depositLabel);
        add(depositField);
        add(findVehicleButton);
        add(updateVehicleButton);
        add(cancelButton);

        // Frame settings
        setSize(650, 400);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Action listener for button events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            dispose();
            new MainMenu(); // Close the window on cancel
        } else if (e.getSource() == updateVehicleButton) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rentroller", "root",
                        "1234Qwer");
                Statement st = con.createStatement();
                String query = "UPDATE vehicles SET no_of_seats = " + seatsField.getText() + ", type = '"
                        + typeField.getText() + "', color = '" + colorField.getText() + "', modelno = '"
                        + modelNumberField.getText() + "', brand = '" + brandField.getText() + "', rent = '"
                        + rentField.getText() + "', deposit = '" + depositField.getText() + "' WHERE vehicle_id = '"
                        + regNumberField.getText() + "'";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Vehicle updated successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == findVehicleButton) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rentroller", "root",
                        "1234Qwer");
                Statement st = con.createStatement();
                String query = "SELECT * FROM vehicles WHERE vehicle_id = '" + regNumberField.getText() + "'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    regNumberField.setText(rs.getString("vehicle_id"));
                    seatsField.setText(rs.getString("no_of_seats"));
                    typeField.setText(rs.getString("type"));
                    colorField.setText(rs.getString("color"));
                    modelNumberField.setText(rs.getString("modelno"));
                    brandField.setText(rs.getString("brand"));
                    rentField.setText(rs.getString("rent"));
                    depositField.setText(rs.getString("deposit"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // JOptionPane.showMessageDialog(this, "Vehicle found successfully!");
        }
    }

    public static void main(String[] args) {
        new UpdateVehicle();
    }
}
