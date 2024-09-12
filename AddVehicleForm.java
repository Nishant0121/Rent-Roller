import java.awt.*;
import javax.swing.*;

public class AddVehicleForm {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rent Roller Vehicle Rental Service");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the frame

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Rent Roller Vehicle Rental Service", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 4, 10, 10));

        formPanel.add(new JLabel("Registration Number:"));
        JTextField regNumberField = new JTextField();
        formPanel.add(regNumberField);

        formPanel.add(new JLabel("No. of Seats:"));
        JTextField seatsField = new JTextField();
        formPanel.add(seatsField);

        formPanel.add(new JLabel("Type:"));
        JTextField typeField = new JTextField();
        formPanel.add(typeField);

        formPanel.add(new JLabel("Color:"));
        JTextField colorField = new JTextField();
        formPanel.add(colorField);

        formPanel.add(new JLabel("Model Number:"));
        JTextField modelNumberField = new JTextField();
        formPanel.add(modelNumberField);

        formPanel.add(new JLabel("Brand:"));
        JTextField brandField = new JTextField();
        formPanel.add(brandField);

        formPanel.add(new JLabel("Rent (Per Day):"));
        JTextField rentField = new JTextField();
        formPanel.add(rentField);

        formPanel.add(new JLabel("Deposit:"));
        JTextField depositField = new JTextField();
        formPanel.add(depositField);

        panel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addButton = new JButton("Add Vehicle");
        addButton.setBackground(new Color(0, 190, 218));
        addButton.setForeground(Color.WHITE);
        buttonPanel.add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(0, 190, 218));
        cancelButton.setForeground(Color.WHITE);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }
}
