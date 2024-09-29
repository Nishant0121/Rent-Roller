import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddCustomer extends JFrame implements ActionListener {

    // Declare instance variables for the input fields and table
    private JTextField nameField, mobileField, dlField, locationField, idProofNumberField;
    private JComboBox<String> idProofDropdown;
    private JTable vehicleTable;
    private DefaultTableModel tableModel;
    private Connection con;

    JButton addButton = new JButton("Add Costumer");
    JButton cancelButton = new JButton("Cancel");

    AddCustomer() {
        // Set up the main frame
        setTitle("Rent Roller Vehicle Rental Service");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        // Create the main panel and set the layout to GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Title label
        JLabel titleLabel = new JLabel("Rent Roller Vehicle Rental Service");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, constraints);

        // Subtitle label
        JLabel subtitleLabel = new JLabel("Customer Details");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(subtitleLabel, constraints);

        // Reset constraints for form fields
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;

        // Customer Name Label and TextField
        JLabel nameLabel = new JLabel("Customer Name");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(nameLabel, constraints);

        nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(200, 30));
        constraints.gridx = 1;
        panel.add(nameField, constraints);

        // Mobile Number Label and TextField
        JLabel mobileLabel = new JLabel("Mobile Number");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(mobileLabel, constraints);

        mobileField = new JTextField(20);
        mobileField.setPreferredSize(new Dimension(200, 30));
        constraints.gridx = 1;
        panel.add(mobileField, constraints);

        // DL No Label and TextField
        JLabel dlLabel = new JLabel("DL No");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(dlLabel, constraints);

        dlField = new JTextField(20);
        dlField.setPreferredSize(new Dimension(200, 30));
        constraints.gridx = 1;
        panel.add(dlField, constraints);

        // Location Label and TextField
        JLabel locationLabel = new JLabel("Location");
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(locationLabel, constraints);

        locationField = new JTextField(20);
        locationField.setPreferredSize(new Dimension(200, 30));
        constraints.gridx = 1;
        panel.add(locationField, constraints);

        // ID Proof Label and Dropdown
        JLabel idProofLabel = new JLabel("Id Proof");
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(idProofLabel, constraints);

        String[] idProofOptions = { "Aadhaar Number", "PAN Card", "Passport" };
        idProofDropdown = new JComboBox<>(idProofOptions);
        idProofDropdown.setPreferredSize(new Dimension(200, 30));
        constraints.gridx = 1;
        panel.add(idProofDropdown, constraints);

        // ID Proof Number Label and TextField
        JLabel idProofNumberLabel = new JLabel("Id Proof Number");
        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(idProofNumberLabel, constraints);

        idProofNumberField = new JTextField(20);
        idProofNumberField.setPreferredSize(new Dimension(200, 30));
        constraints.gridx = 1;
        panel.add(idProofNumberField, constraints);

        // Buttons - Add Costumer and Cancel
        Color buttonColor = new Color(0, 200, 255);

        cancelButton.setBackground(buttonColor);
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        cancelButton.setPreferredSize(new Dimension(100, 40));
        cancelButton.addActionListener(this);
        panel.add(cancelButton, constraints);

        addButton.setBackground(buttonColor);
        constraints.gridx = 1;
        addButton.setPreferredSize(new Dimension(100, 40));
        constraints.anchor = GridBagConstraints.CENTER;
        addButton.addActionListener(this);
        panel.add(addButton, constraints);

        // Add the panel to a scroll pane and add to frame
        JScrollPane inputScrollPane = new JScrollPane(panel);
        inputScrollPane.setPreferredSize(new Dimension(450, 350));

        // Create the table model and the table
        String[] columnNames = { "ID", "Name", "Mobile Number", "DL No", "Location" };
        tableModel = new DefaultTableModel(columnNames, 0);
        vehicleTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(vehicleTable);
        tableScrollPane.setPreferredSize(new Dimension(350, 350));

        // Add the input form and table side by side using a split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputScrollPane, tableScrollPane);
        add(splitPane);

        // Show the frame
        setVisible(true);

        // Load vehicle data
        loadVehicleData();
    }

    // Load vehicle data from the database into the table
    private void loadVehicleData() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rentroller", "root", "1234Qwer");
            String query = "SELECT * FROM customer";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            // Clear any previous data
            tableModel.setRowCount(0);

            // Add rows to the table
            while (rs.next()) {
                String idProofNumber = rs.getString("idProofNumber");
                String customerName = rs.getString("customerName");
                String mobileNumber = rs.getString("mobileNumber");
                String dlNo = rs.getString("dlNo");
                String location = rs.getString("location");
                tableModel.addRow(new Object[] { idProofNumber, customerName, mobileNumber, dlNo, location });
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create and display the form and table
        new AddCustomer();
    }

    public void actionPerformed(ActionEvent e) {
        // Handle form submission
        String customerName = nameField.getText();
        String mobileNumber = mobileField.getText();
        String dlNo = dlField.getText();
        String location = locationField.getText();
        String idProof = (String) idProofDropdown.getSelectedItem();
        String idProofNumber = idProofNumberField.getText();

        // Add logic to store customer details into the database or perform other
        if (e.getSource() == addButton) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rentroller", "root",
                        "1234Qwer");
                String query = "insert into customer values ('" + customerName + "','" + mobileNumber + "','" + dlNo
                        + "','"
                        + location + "','" + idProof + "','" + idProofNumber + "')";
                System.out.println("Query: " + query);
                Statement st = con.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Customer added successfully");

                loadVehicleData();
            } catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + exception.getMessage());
            }
        } else if (e.getSource() == cancelButton) {
            dispose();
            new MainMenu();
        }
    }
}
