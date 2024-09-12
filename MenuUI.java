import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuUI extends JFrame {

    public MenuUI() {
        setTitle("Rent Roller Vehicle Rental Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Rent Roller Vehicle Rental Service", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100)); // Padding around the buttons

        Dimension buttonSize = new Dimension(200, 40); // Set the width and height for the buttons

        JButton rentVehicleButton = new JButton("Rent A Vehicle");
        rentVehicleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rentVehicleButton.setPreferredSize(buttonSize);
        rentVehicleButton.setMaximumSize(buttonSize);
        rentVehicleButton.setBackground(new Color(0, 190, 218));
        rentVehicleButton.setForeground(Color.WHITE);
        rentVehicleButton.setFocusPainted(false);
        buttonPanel.add(rentVehicleButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton addVehicleButton = new JButton("Add New Vehicle");
        addVehicleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addVehicleButton.setPreferredSize(buttonSize);
        addVehicleButton.setMaximumSize(buttonSize);
        addVehicleButton.setBackground(new Color(0, 190, 218));
        addVehicleButton.setForeground(Color.WHITE);
        addVehicleButton.setFocusPainted(false);
        buttonPanel.add(addVehicleButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton updateDetailsButton = new JButton("Update Details");
        updateDetailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateDetailsButton.setPreferredSize(buttonSize);
        updateDetailsButton.setMaximumSize(buttonSize);
        updateDetailsButton.setBackground(new Color(0, 190, 218));
        updateDetailsButton.setForeground(Color.WHITE);
        updateDetailsButton.setFocusPainted(false);
        buttonPanel.add(updateDetailsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton viewAllVehiclesButton = new JButton("View All Vehicles");
        viewAllVehiclesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewAllVehiclesButton.setPreferredSize(buttonSize);
        viewAllVehiclesButton.setMaximumSize(buttonSize);
        viewAllVehiclesButton.setBackground(new Color(0, 190, 218));
        viewAllVehiclesButton.setForeground(Color.WHITE);
        viewAllVehiclesButton.setFocusPainted(false);
        buttonPanel.add(viewAllVehiclesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton addCustomersButton = new JButton("Add Customers");
        addCustomersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCustomersButton.setPreferredSize(buttonSize);
        addCustomersButton.setMaximumSize(buttonSize);
        addCustomersButton.setBackground(new Color(0, 190, 218));
        addCustomersButton.setForeground(Color.WHITE);
        addCustomersButton.setFocusPainted(false);
        buttonPanel.add(addCustomersButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton viewRentedVehiclesButton = new JButton("View Rented Vehicles");
        viewRentedVehiclesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewRentedVehiclesButton.setPreferredSize(buttonSize);
        viewRentedVehiclesButton.setMaximumSize(buttonSize);
        viewRentedVehiclesButton.setBackground(new Color(0, 190, 218));
        viewRentedVehiclesButton.setForeground(Color.WHITE);
        viewRentedVehiclesButton.setFocusPainted(false);
        buttonPanel.add(viewRentedVehiclesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton returnVehicleButton = new JButton("Return Vehicle");
        returnVehicleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnVehicleButton.setPreferredSize(buttonSize);
        returnVehicleButton.setMaximumSize(buttonSize);
        returnVehicleButton.setBackground(new Color(0, 190, 218));
        returnVehicleButton.setForeground(Color.WHITE);
        returnVehicleButton.setFocusPainted(false);
        buttonPanel.add(returnVehicleButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setPreferredSize(buttonSize);
        logoutButton.setMaximumSize(buttonSize);
        logoutButton.setBackground(new Color(0, 190, 218));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        buttonPanel.add(logoutButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(buttonPanel, BorderLayout.CENTER);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose(); // Close the login window
                new LoginUI();

            }
        });

        add(panel);

        setVisible(true); // Make the frame visible
    }
}
