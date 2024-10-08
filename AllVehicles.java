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

    private Color bgColor = new Color(30, 30, 30);
    private Color textColor = new Color(230, 230, 230);
    private Color accentColor = new Color(0, 150, 136);
    private Color inputColor = new Color(255, 255, 255);

    public AllVehicles() {
        // Set up the frame
        setTitle("Rent Roller Vehicle Rental Service");
        setSize(900, 500); // Adjust size to accommodate both form and table
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(bgColor);
        setLocationRelativeTo(null);

        // Left Panel (Form)
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints formConstraints = new GridBagConstraints();
        formConstraints.insets = new Insets(10, 10, 10, 10);
        formPanel.setBackground(bgColor);
        formConstraints.fill = GridBagConstraints.HORIZONTAL;

        // Title label
        JLabel titleLabel = new JLabel("Rent Roller Vehicle Rental Service", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(textColor);
        formConstraints.gridx = 0;
        formConstraints.gridy = 0;
        formConstraints.gridwidth = 2;
        formPanel.add(titleLabel, formConstraints);

        // Subtitle label
        JLabel subtitleLabel = new JLabel("Select Vehicle", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(textColor);
        formConstraints.gridy = 1;
        formPanel.add(subtitleLabel, formConstraints);

        // Reset constraints for the form fields
        formConstraints.gridwidth = 1;

        // Type of Vehicles Label and Dropdown
        JLabel typeLabel = new JLabel("Type Of Vehicles");
        formConstraints.gridx = 0;
        formConstraints.gridy = 2;
        typeLabel.setForeground(textColor);
        formPanel.add(typeLabel, formConstraints);

        String[] vehicleTypes = { "2 Wheeler", "4 Wheeler", "Heavy Vehicles" };
        typeDropdown = new JComboBox<>(vehicleTypes);
        typeDropdown.setForeground(inputColor);
        typeDropdown.setBackground(bgColor);
        formConstraints.gridx = 1;
        formPanel.add(typeDropdown, formConstraints);

        // Brand Label and Dropdown
        JLabel brandLabel = new JLabel("Brand");
        brandLabel.setForeground(textColor);
        formConstraints.gridx = 0;
        formConstraints.gridy = 3;
        formPanel.add(brandLabel, formConstraints);

        String[] vehicleBrands = { "Maruti", "Toyota", "Honda", "Ford" };
        brandDropdown = new JComboBox<>(vehicleBrands);
        formConstraints.gridx = 1;
        brandDropdown.setForeground(inputColor);
        brandDropdown.setBackground(bgColor);
        formPanel.add(brandDropdown, formConstraints);

        // Number of Seats Label and Dropdown
        JLabel seatsLabel = new JLabel("Number Of Seats");
        seatsLabel.setForeground(textColor);
        formConstraints.gridx = 0;
        formConstraints.gridy = 4;
        formPanel.add(seatsLabel, formConstraints);

        String[] seatOptions = { "2", "4", "7", "10" };
        seatsDropdown = new JComboBox<>(seatOptions);
        seatsDropdown.setForeground(inputColor);
        seatsDropdown.setBackground(bgColor);
        formConstraints.gridx = 1;
        formPanel.add(seatsDropdown, formConstraints);

        // Color Label and Dropdown
        JLabel colorLabel = new JLabel("Colour");
        formConstraints.gridx = 0;
        formConstraints.gridy = 5;
        colorLabel.setForeground(textColor);
        formPanel.add(colorLabel, formConstraints);

        String[] colors = { "Red", "White", "Blue", "Black", "Silver" };
        colorDropdown = new JComboBox<>(colors);
        colorDropdown.setForeground(inputColor);
        colorDropdown.setBackground(bgColor);
        formConstraints.gridx = 1;
        formPanel.add(colorDropdown, formConstraints);

        Color buttonColor = accentColor;

        // Close and Find buttons
        closeButton = new JButton("Close");
        closeButton.setBackground(buttonColor);
        closeButton.setForeground(textColor);
        closeButton.setPreferredSize(new Dimension(100, 40));
        formConstraints.gridx = 0;
        formConstraints.gridy = 6;
        formConstraints.gridwidth = 1;
        formConstraints.anchor = GridBagConstraints.WEST;
        formPanel.add(closeButton, formConstraints);

        findButton = new JButton("Find");
        findButton.setBackground(buttonColor);
        findButton.setForeground(textColor);
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
        vehicleTable.setRowHeight(30);
        vehicleTable.setBackground(bgColor);
        vehicleTable.setForeground(textColor);
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

            String query = "SELECT * FROM vehicles v "
                    + "LEFT JOIN rent r ON v.vehicle_id = r.vehicle_id "
                    + "WHERE r.vehicle_id IS NULL "
                    + "AND v.type = '" + selectedVehicleType + "' "
                    + "AND v.no_of_seats = '" + selectedSeats + "' "
                    + "AND v.brand = '" + selectedBrand + "' "
                    + "AND v.color = '" + selectedColor + "';";

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
