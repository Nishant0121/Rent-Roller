import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class LoginForm extends JFrame implements ActionListener {

    // Define components
    JLabel l1, l2, l3;
    JTextField tx1;
    JPasswordField tx2;
    JButton loginButton;

    LoginForm() {
        // Set up the frame (no need to create a new JFrame, just use 'this')
        setTitle("Rent Roller Vehicle Rental Service");

        // Title Label
        l1 = new JLabel("Rent Roller Vehicle Rental Service");
        l1.setBounds(60, 20, 300, 30);
        l1.setFont(new Font("Arial", Font.BOLD, 18));

        // Email Label
        l2 = new JLabel("Email:");
        l2.setBounds(50, 80, 100, 30);
        l2.setFont(new Font("Arial", Font.PLAIN, 14));

        // Email Input Field
        tx1 = new JTextField("");
        tx1.setBounds(150, 80, 150, 30);

        // Password Label
        l3 = new JLabel("Password:");
        l3.setBounds(50, 130, 100, 30);
        l3.setFont(new Font("Arial", Font.PLAIN, 14));

        // Password Input Field
        tx2 = new JPasswordField("");
        tx2.setBounds(150, 130, 150, 30);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 180, 100, 30);
        loginButton.setBackground(Color.CYAN);
        loginButton.addActionListener(this);

        // Add components to frame
        add(l1);
        add(l2);
        add(tx1);
        add(l3);
        add(tx2);
        add(loginButton);

        // Frame settings
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Action listener to handle button click
    public void actionPerformed(ActionEvent e) {
        String email = tx1.getText();
        String password = new String(tx2.getPassword());

        // Simple validation (add your own logic here)
        if (email.equals("Input Email") || password.equals("Input Password")) {
            JOptionPane.showMessageDialog(this, "Please enter valid credentials");
        } else {
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Login Failed. Please enter valid credentials!");
            } else {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                // Close the current login window
                dispose();
                new MainMenu();
            }

        }

    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
