import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AllVehicles extends JFrame implements ActionListener {
    JComboBox<String> typeDropdown, brandDropdown, seatsDropdown, colorDropdown;
    JButton findButton, closeButton;
    JTable vehicleTable;
    DefaultTableModel tableModel;

    public AllVehicles() {
        // Set up the frame
        setTitle("Rent Roller Vehicle Rental Service");
        setSize(900, 500); // Adjust size to accommodate both form and table
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Left Panel (Form)
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints formConstraints = new GridBagConstraints();
        formConstraints.insets = new Insets(10, 10, 10, 10);
        formConstraints.fill = GridBagConstraints.HORIZONTAL;

        // Title label
        JLabel titleLabel = new JLabel("Rent Roller Vehicle Rental Service", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        formConstraints.gridx = 0;
        formConstraints.gridy = 0;
        formConstraints.gridwidth = 2;
        formPanel.add(titleLabel, formConstraints);

        // Subtitle label
        JLabel subtitleLabel = new JLabel("Select Vehicle", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        formConstraints.gridy = 1;
        formPanel.add(subtitleLabel, formConstraints);

        // Reset constraints for the form fields
        formConstraints.gridwidth = 1;

        // Type of Vehicles Label and Dropdown
        JLabel typeLabel = new JLabel("Type Of Vehicles");
        formConstraints.gridx = 0;
        formConstraints.gridy = 2;
        formPanel.add(typeLabel, formConstraints);

        String[] vehicleTypes = { "2 Wheeler", "4 Wheeler", "Heavy Vehicles" };
        typeDropdown = new JComboBox<>(vehicleTypes);
        formConstraints.gridx = 1;
        formPanel.add(typeDropdown, formConstraints);

        // Brand Label and Dropdown
        JLabel brandLabel = new JLabel("Brand");
        formConstraints.gridx = 0;
        formConstraints.gridy = 3;
        formPanel.add(brandLabel, formConstraints);

        String[] vehicleBrands = { "Maruti", "Toyota", "Honda", "Ford" };
        brandDropdown = new JComboBox<>(vehicleBrands);
        formConstraints.gridx = 1;
        formPanel.add(brandDropdown, formConstraints);

        // Number of Seats Label and Dropdown
        JLabel seatsLabel = new JLabel("Number Of Seats");
        formConstraints.gridx = 0;
        formConstraints.gridy = 4;
        formPanel.add(seatsLabel, formConstraints);

        String[] seatOptions = { "2", "4", "7", "10" };
        seatsDropdown = new JComboBox<>(seatOptions);
        formConstraints.gridx = 1;
        formPanel.add(seatsDropdown, formConstraints);

        // Color Label and Dropdown
        JLabel colorLabel = new JLabel("Colour");
        formConstraints.gridx = 0;
        formConstraints.gridy = 5;
        formPanel.add(colorLabel, formConstraints);

        String[] colors = { "Red", "White", "Blue", "Black", "Silver" };
        colorDropdown = new JComboBox<>(colors);
        colorDropdown.setPreferredSize(new Dimension(100, 30));
        formConstraints.gridx = 1;
        formPanel.add(colorDropdown, formConstraints);

        Color buttonColor = new Color(0, 200, 255);

        // Close and Find buttons
        closeButton = new JButton("Close");
        closeButton.setBackground(buttonColor);
        closeButton.setPreferredSize(new Dimension(100, 40));
        formConstraints.gridx = 0;
        formConstraints.gridy = 6;
        formConstraints.gridwidth = 1;
        formConstraints.anchor = GridBagConstraints.WEST;
        formPanel.add(closeButton, formConstraints);

        findButton = new JButton("Find");
        findButton.setBackground(buttonColor);
        findButton.setPreferredSize(new Dimension(100, 40));
        formConstraints.gridx = 1;
        formConstraints.anchor = GridBagConstraints.EAST;
        formPanel.add(findButton, formConstraints);

        // Add ActionListener to the buttons
        findButton.addActionListener(this);
        closeButton.addActionListener(this);

        // Right Panel (Table)
        String[] columnNames = { "Vehicle ID", "Brand", "Type", "Color", "Seats" };
        tableModel = new DefaultTableModel(columnNames, 0); // Set column headers and 0 rows initially
        vehicleTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(vehicleTable);
        tableScrollPane.setPreferredSize(new Dimension(400, 400)); // Adjust as needed

        // Split pane to divide formPanel (left) and tableScrollPane (right)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tableScrollPane);
        splitPane.setResizeWeight(0.5); // Balance between the two panels
        splitPane.setDividerLocation(450); // Adjust divider location if necessary

        // Add split pane to the frame
        add(splitPane);

        // Set the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            // Close the window when the Close button is clicked
            dispose();
            new MainMenu();
        } else if (e.getSource() == findButton) {
            // Logic for the Find button
            String selectedVehicleType = (String) typeDropdown.getSelectedItem();
            String selectedBrand = (String) brandDropdown.getSelectedItem();
            String selectedSeats = (String) seatsDropdown.getSelectedItem();
            String selectedColor = (String) colorDropdown.getSelectedItem();

            String query = "SELECT * FROM vehicles WHERE type = '" + selectedVehicleType + "' AND no_of_seats = '"
                    + selectedSeats + "' AND brand='" + selectedBrand + "';";

            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rentroller", "root",
                        "1234Qwer");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                // Clear previous data
                tableModel.setRowCount(0);

                // Fetch data and add rows to the table model
                while (rs.next()) {
                    String vehicle_id = rs.getString("vehicle_id");
                    String brand = rs.getString("brand");
                    String type = rs.getString("type");
                    String color = rs.getString("color");
                    String seats = rs.getString("no_of_seats");
                    tableModel.addRow(new Object[] { vehicle_id, brand, type, color, seats });
                }

                rs.close();
                st.close();
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AllVehicles();
    }
}
