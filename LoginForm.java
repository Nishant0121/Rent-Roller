import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class LoginForm extends JFrame implements ActionListener {

    // Define components
    private JLabel titleLabel, emailLabel, passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Define colors for dark theme
    private Color bgColor = new Color(30, 30, 30);
    private Color textColor = new Color(230, 230, 230);
    private Color accentColor = new Color(0, 107, 255);

    LoginForm() {
        // Set up the frame
        setTitle("Rent Roller Vehicle Rental Service");
        getContentPane().setBackground(bgColor);

        // Title Label
        titleLabel = new JLabel("Rent Roller Vehicle Rental Service");
        titleLabel.setForeground(accentColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Email Label and Field
        emailLabel = new JLabel("Email:");
        emailLabel.setForeground(textColor);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        emailField = new JTextField();
        styleTextField(emailField);

        // Password Label and Field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(textColor);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        passwordField = new JPasswordField();
        styleTextField(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBackground(accentColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(this);

        // Create main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(bgColor);
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Add components to main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        mainPanel.add(titleLabel, gbc);
        gbc.insets = new Insets(20, 0, 5, 0);
        mainPanel.add(emailLabel, gbc);
        gbc.insets = new Insets(0, 0, 15, 0);
        mainPanel.add(emailField, gbc);
        gbc.insets = new Insets(0, 0, 5, 0);
        mainPanel.add(passwordLabel, gbc);
        gbc.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(passwordField, gbc);
        mainPanel.add(loginButton, gbc);

        // Add main panel to frame
        add(mainPanel);

        // Frame settings
        setSize(400, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(new Color(45, 45, 45));
        textField.setForeground(textColor);
        textField.setCaretColor(textColor);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(60, 60, 60)),
                new EmptyBorder(5, 10, 5, 10)));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    // Action listener to handle button click
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            showErrorMessage("Please enter both email and password.");
        } else {
            // Add your authentication logic here
            if (email.equals("nishant0121@gmail.com") && password.equals("1234qwer")) {
                showSuccessMessage("Login Successful!");
                dispose();
                new MainMenu();
            } else {
                showErrorMessage("Invalid email or password.");
            }
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}