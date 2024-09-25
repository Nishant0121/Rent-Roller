import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GroceryApp extends JFrame implements ActionListener {
    JLabel headerLabel, welcomeLabel, usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JLabel forgotPasswordLabel;

    // Constructor to set up the login frame
    GroceryApp() {
        setTitle("Grocefy - Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        headerLabel = new JLabel("Grocefy", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(headerLabel, constraints);

        welcomeLabel = new JLabel("Welcome to Grocefy, your one-stop grocery store!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        constraints.gridy = 1;
        add(welcomeLabel, constraints);

        usernameLabel = new JLabel("Username: ");
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        add(usernameLabel, constraints);

        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.EAST;
        passwordLabel = new JLabel("Password: ");
        add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        add(passwordField, constraints);

        loginButton = new JButton("Login");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        add(loginButton, constraints);

        forgotPasswordLabel = new JLabel("<html><a href=''>Forgot Password?</a></html>", JLabel.CENTER);
        constraints.gridy = 5;
        add(forgotPasswordLabel, constraints);

        loginButton.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new GroceryMainPage(); // Open the main page
            dispose(); // Close login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!");
        }
    }

    public static void main(String[] args) {
        new GroceryApp(); // Start the login page
    }
}

// New main page class
class GroceryMainPage extends JFrame {

    public GroceryMainPage() {
        setTitle("Grocery App - Main Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700); // Set size closer to your screenshot
        setLayout(new BorderLayout());

        // Top panel for buttons and customer info
        JPanel topPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel for Add Customer and Get Customer buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        JButton addCustomerBtn = new JButton("ADD CUSTOMER");
        JButton getCustomerBtn = new JButton("GET CUSTOMER");
        buttonPanel.add(addCustomerBtn);
        buttonPanel.add(getCustomerBtn);
        topPanel.add(buttonPanel);

        // Panel for customer name and id
        JPanel customerInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField customerNameField = createPlaceholderTextField("Customer Name");
        JTextField customerIdField = createPlaceholderTextField("Customer ID");
        customerInfoPanel.add(customerNameField);
        customerInfoPanel.add(customerIdField);
        topPanel.add(customerInfoPanel);

        // Logout button aligned to the right
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        JButton logoutBtn = new JButton("LOGOUT");
        logoutPanel.add(logoutBtn);
        topPanel.add(logoutPanel);

        // Add top panel to frame
        add(topPanel, BorderLayout.NORTH);

        // Middle panel for product table
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(BorderFactory.createEmptyBorder(5, 13, 5, 13));

        // Table to display products
        String[] columnNames = { "Product Name", "Quantity", "Price" };
        Object[][] data = {
                { "Apple", "1 KG", "XX.XX rs" },
                { "Banana", "1 DOZEN", "XX.XX rs" },
                { "Tomato", "2 KG", "XX.XX rs" },
                { "Onion", "3 KG", "XX.XX rs" },
                { "Watermelon", "1", "XX.XX rs" },
                { "Carrot", "1 KG", "XX.XX rs" },
                { "Cabbage", "1.5 KG", "XX.XX rs" }
        };

        JTable productTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(productTable);
        middlePanel.add(scrollPane, BorderLayout.CENTER);

        // Input panel for product search
        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField productSearchField = createPlaceholderTextField("Enter product ID/name");
        JTextField quantityField = createPlaceholderTextField("Quantity");
        JButton addButton = new JButton("ADD");
        inputPanel.add(productSearchField);
        inputPanel.add(quantityField);
        inputPanel.add(addButton);

        middlePanel.add(inputPanel, BorderLayout.NORTH);

        // Add middle panel to frame
        add(middlePanel, BorderLayout.CENTER);

        // Bottom panel for total, edit, and next button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel totalLabel = new JLabel("TOTAL: 00 rs");
        JButton nextButton = new JButton("NEXT");
        JButton editButton = new JButton("EDIT"); // Added Edit button
        bottomPanel.add(editButton); // Add the Edit button below the table
        bottomPanel.add(totalLabel);
        bottomPanel.add(nextButton);

        // Add bottom panel to frame
        add(bottomPanel, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
    }

    private JTextField createPlaceholderTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.setForeground(Color.GRAY); // Set placeholder color

        // Add a focus listener to handle focus events
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });

        return textField;
    }
}