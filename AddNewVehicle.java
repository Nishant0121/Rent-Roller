import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class AddNewVehicle extends JFrame implements ActionListener {

    // Define components
    JTextField regNumberField, typeField, modelNumberField, rentField, seatsField, colorField, brandField, depositField;
    JButton addVehicleButton, cancelButton;

    AddNewVehicle() {
        // Set up the frame
        JFrame f = new JFrame("Add New Vehicle - Rent Roller Vehicle Rental Service");

        // Title Label
        JLabel title = new JLabel("Rent Roller Vehicle Rental Service");
        title.setBounds(50, 20, 400, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);

        // Subtitle for "Add New Vehicle"
        JLabel subtitle = new JLabel("ADD NEW VEHICLE");
        subtitle.setBounds(160, 60, 200, 30);
        subtitle.setFont(new Font("Arial", Font.BOLD, 14));

        // Labels and Text Fields
        JLabel regNumberLabel = new JLabel("Registration number");
        regNumberLabel.setBounds(30, 100, 150, 30);
        regNumberField = new JTextField();
        regNumberField.setBounds(180, 100, 150, 30);

        JLabel seatsLabel = new JLabel("No. of Seats");
        seatsLabel.setBounds(350, 100, 150, 30);
        seatsField = new JTextField();
        seatsField.setBounds(450, 100, 150, 30);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setBounds(30, 150, 150, 30);
        typeField = new JTextField();
        typeField.setBounds(180, 150, 150, 30);

        JLabel colorLabel = new JLabel("Color");
        colorLabel.setBounds(350, 150, 150, 30);
        colorField = new JTextField();
        colorField.setBounds(450, 150, 150, 30);

        JLabel modelNumberLabel = new JLabel("Model Number");
        modelNumberLabel.setBounds(30, 200, 150, 30);
        modelNumberField = new JTextField();
        modelNumberField.setBounds(180, 200, 150, 30);

        JLabel brandLabel = new JLabel("Brand");
        brandLabel.setBounds(350, 200, 150, 30);
        brandField = new JTextField();
        brandField.setBounds(450, 200, 150, 30);

        JLabel rentLabel = new JLabel("Rent (Per Day)");
        rentLabel.setBounds(30, 250, 150, 30);
        rentField = new JTextField();
        rentField.setBounds(180, 250, 150, 30);

        JLabel depositLabel = new JLabel("Deposit");
        depositLabel.setBounds(350, 250, 150, 30);
        depositField = new JTextField();
        depositField.setBounds(450, 250, 150, 30);

        // Buttons
        addVehicleButton = new JButton("Add Vehicle");
        addVehicleButton.setBounds(180, 300, 150, 40);
        addVehicleButton.setBackground(new Color(0, 200, 255));
        addVehicleButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(350, 300, 150, 40);
        cancelButton.setBackground(new Color(0, 200, 255));
        cancelButton.addActionListener(this);

        // Add components to the frame
        f.add(title);
        f.add(subtitle);
        f.add(regNumberLabel);
        f.add(regNumberField);
        f.add(seatsLabel);
        f.add(seatsField);
        f.add(typeLabel);
        f.add(typeField);
        f.add(colorLabel);
        f.add(colorField);
        f.add(modelNumberLabel);
        f.add(modelNumberField);
        f.add(brandLabel);
        f.add(brandField);
        f.add(rentLabel);
        f.add(rentField);
        f.add(depositLabel);
        f.add(depositField);
        f.add(addVehicleButton);
        f.add(cancelButton);

        // Frame settings
        f.setSize(650, 400);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Action listener for button events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            JOptionPane.showMessageDialog(this, "Action Cancelled");
            System.exit(0); // Close the window on cancel
        } else if (e.getSource() == addVehicleButton) {
            // Here you can handle the logic for adding a vehicle
            JOptionPane.showMessageDialog(this, "Vehicle added successfully!");
        }
    }

    public static void main(String[] args) {
        new AddNewVehicle();
    }
}
