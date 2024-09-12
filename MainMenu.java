import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class MainMenu extends JFrame implements ActionListener {

    // Define buttons
    JButton btnRentVehicle, btnAddVehicle, btnUpdateDetails, btnViewAllVehicle, btnAddCustomers,
            btnViewRentedVehicles, btnReturnVehicle, btnLogout;

    MainMenu() {
        // Set up the frame
        JFrame f = new JFrame("Rent Roller Vehicle Rental Service");

        // Title Label
        JLabel title = new JLabel("Rent Roller Vehicle Rental Service");
        title.setBounds(60, 20, 400, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(JLabel.CENTER);

        // Button setup
        btnRentVehicle = new JButton("Rent A Vehicle");
        btnAddVehicle = new JButton("Add New Vehicle");
        btnUpdateDetails = new JButton("Update Details");
        btnViewAllVehicle = new JButton("View All Vehicle");
        btnAddCustomers = new JButton("Add Customers");
        btnViewRentedVehicles = new JButton("View Rented Vehicles");
        btnReturnVehicle = new JButton("Return Vehicle");
        btnLogout = new JButton("Logout");

        // Set bounds for the buttons
        btnRentVehicle.setBounds(150, 70, 150, 30);
        btnAddVehicle.setBounds(150, 110, 150, 30);
        btnUpdateDetails.setBounds(150, 150, 150, 30);
        btnViewAllVehicle.setBounds(150, 190, 150, 30);
        btnAddCustomers.setBounds(150, 230, 150, 30);
        btnViewRentedVehicles.setBounds(150, 270, 150, 30);
        btnReturnVehicle.setBounds(150, 310, 150, 30);
        btnLogout.setBounds(150, 350, 150, 30);

        // Set button colors (matching the screenshot)
        Color buttonColor = new Color(0, 200, 255); // Light cyan color
        btnRentVehicle.setBackground(buttonColor);
        btnAddVehicle.setBackground(buttonColor);
        btnUpdateDetails.setBackground(buttonColor);
        btnViewAllVehicle.setBackground(buttonColor);
        btnAddCustomers.setBackground(buttonColor);
        btnViewRentedVehicles.setBackground(buttonColor);
        btnReturnVehicle.setBackground(buttonColor);
        btnLogout.setBackground(buttonColor);

        // Add action listeners for all buttons
        btnRentVehicle.addActionListener(this);
        btnAddVehicle.addActionListener(this);
        btnUpdateDetails.addActionListener(this);
        btnViewAllVehicle.addActionListener(this);
        btnAddCustomers.addActionListener(this);
        btnViewRentedVehicles.addActionListener(this);
        btnReturnVehicle.addActionListener(this);
        btnLogout.addActionListener(this);

        // Add components to frame
        f.add(title);
        f.add(btnRentVehicle);
        f.add(btnAddVehicle);
        f.add(btnUpdateDetails);
        f.add(btnViewAllVehicle);
        f.add(btnAddCustomers);
        f.add(btnViewRentedVehicles);
        f.add(btnReturnVehicle);
        f.add(btnLogout);

        // Frame settings
        f.setSize(450, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Action listener for button events
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Logout")) {
            JOptionPane.showMessageDialog(this, "Logged out successfully!");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, command + " button clicked.");
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
