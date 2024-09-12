import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginForm extends JFrame implements ActionListener {

    // Define components
    JLabel l1, l2, l3;
    JTextField tx1;
    JPasswordField tx2;
    JButton loginButton;

    LoginForm() {
        // Set up the frame
        JFrame f = new JFrame("Rent Roller Vehicle Rental Service");

        // Title Label
        l1 = new JLabel("Rent Roller Vehicle Rental Service");
        l1.setBounds(60, 20, 300, 30);
        l1.setFont(new Font("Arial", Font.BOLD, 18));

        // Email Label
        l2 = new JLabel("Email:");
        l2.setBounds(50, 80, 100, 30);
        l2.setFont(new Font("Arial", Font.PLAIN, 14));

        // Email Input Field
        tx1 = new JTextField("Input Email");
        tx1.setBounds(150, 80, 150, 30);

        // Password Label
        l3 = new JLabel("Password:");
        l3.setBounds(50, 130, 100, 30);
        l3.setFont(new Font("Arial", Font.PLAIN, 14));

        // Password Input Field
        tx2 = new JPasswordField("Input Password");
        tx2.setBounds(150, 130, 150, 30);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 180, 100, 30);
        loginButton.setBackground(Color.CYAN);
        loginButton.addActionListener(this);

        // Add components to frame
        f.add(l1);
        f.add(l2);
        f.add(tx1);
        f.add(l3);
        f.add(tx2);
        f.add(loginButton);

        // Frame settings
        f.setSize(400, 300);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Action listener to handle button click
    public void actionPerformed(ActionEvent e) {
        String email = tx1.getText();
        String password = new String(tx2.getPassword());

        // Simple validation (add your own logic here)
        if (email.equals("Input Email") || password.equals("Input Password")) {
            JOptionPane.showMessageDialog(this, "Please enter valid credentials");
        } else {
            JOptionPane.showMessageDialog(this, "Login Successful!");
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
