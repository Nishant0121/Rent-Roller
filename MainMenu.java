import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

class MainMenu extends JFrame implements ActionListener {

    // Define buttons
    JButton btnRentVehicle, btnAddVehicle, btnUpdateDetails, btnViewAllVehicle, btnAddCustomers,
            btnViewRentedVehicles, btnReturnVehicle, btnLogout;

    // Define colors for dark theme
    private Color bgColor = new Color(30, 30, 30);
    private Color textColor = new Color(230, 230, 230);
    private Color accentColor = new Color(0, 150, 136);

    MainMenu() {
        // Set up the frame
        setTitle("Rent Roller Vehicle Rental Service");
        setSize(550, 650); // Increased frame size
        setLayout(new GridBagLayout());
        getContentPane().setBackground(bgColor);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Title Label
        JLabel title = new JLabel("Rent Roller Vehicle Rental Service");
        title.setForeground(accentColor);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);

        // Button setup
        btnRentVehicle = createStyledButton("Rent A Vehicle");
        btnAddVehicle = createStyledButton("Add New Vehicle");
        btnUpdateDetails = createStyledButton("Update Details");
        btnViewAllVehicle = createStyledButton("View All Vehicles");
        btnAddCustomers = createStyledButton("Add Customers");
        btnViewRentedVehicles = createStyledButton("View Rented Vehicles");
        btnReturnVehicle = createStyledButton("Return Vehicle");
        btnLogout = createStyledButton("Logout");

        // Create main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(bgColor);
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Add components to main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        mainPanel.add(title, gbc);
        gbc.insets = new Insets(20, 0, 10, 0);
        mainPanel.add(btnRentVehicle, gbc);
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(btnAddVehicle, gbc);
        mainPanel.add(btnUpdateDetails, gbc);
        mainPanel.add(btnViewAllVehicle, gbc);
        mainPanel.add(btnAddCustomers, gbc);
        mainPanel.add(btnViewRentedVehicles, gbc);
        mainPanel.add(btnReturnVehicle, gbc);
        gbc.insets = new Insets(20, 0, 10, 0);
        mainPanel.add(btnLogout, gbc);

        // Add main panel to frame
        add(mainPanel);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(accentColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Increased font size
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 24, 12, 24)); // Increased padding
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        return button;
    }

    // Action listener for button events
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Perform specific actions based on button clicked
        switch (command) {
            case "Rent A Vehicle":
                dispose();
                new RentVehicleForm();
                break;

            case "Add New Vehicle":
                dispose();
                new AddNewVehicle();
                break;

            case "Update Details":
                dispose();
                new UpdateVehicle();
                break;

            case "View All Vehicles":
                dispose();
                new AllVehicles();
                break;

            case "Add Customers":
                dispose();
                new AddCustomer();
                break;

            case "View Rented Vehicles":
                dispose();
                new ViewRented();
                break;

            case "Return Vehicle":
                dispose();
                new ReturnVehicle();
                break;

            case "Logout":
                JOptionPane.showMessageDialog(this, "Logging out...");
                dispose();
                new LoginForm(); // Open the login form again
                break;

            default:
                JOptionPane.showMessageDialog(this, "Unknown command: " + command);
                break;
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
