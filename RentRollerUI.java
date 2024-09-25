import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RentRollerUI {

        public static void main(String[] args) {
                JFrame frame = new JFrame("Rent Roller Vehicle Rental Service");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 400);

                // Panel for the form fields
                JPanel formPanel = new JPanel();
                GroupLayout layout = new GroupLayout(formPanel);
                formPanel.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                // Form fields
                JLabel customerNameLabel = new JLabel("Customer Name");
                JTextField customerNameField = new JTextField(15);

                JLabel mobileNumberLabel = new JLabel("Mobile Number");
                JTextField mobileNumberField = new JTextField(15);

                JLabel dlNoLabel = new JLabel("DL No");
                JTextField dlNoField = new JTextField(15);

                JLabel locationLabel = new JLabel("Location");
                JTextField locationField = new JTextField(15);

                JLabel idProofLabel = new JLabel("Id Proof");
                String[] idProofOptions = { "Aadhaar Number", "PAN Card", "Passport" };
                JComboBox<String> idProofDropdown = new JComboBox<>(idProofOptions);

                JLabel idProofNumberLabel = new JLabel("Id Proof Number");
                JTextField idProofNumberField = new JTextField(15);

                // Buttons for Add Customer and Cancel
                JButton addButton = new JButton("Add Customer");
                JButton cancelButton = new JButton("Cancel");

                // Table for customer details
                String[] columnNames = { "Customer ID", "Mobile Number", "Name", "Id Proof" };
                Object[][] data = {
                                { "RR0012", "9175729290", "Nishnat", "345836572932" },
                                { "RR0043", "9022764690", "Uday", "648249176345" },
                                { "RR0032", "9022764690", "Megha", "794628461528" }
                };

                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                JTable customerTable = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(customerTable);
                customerTable.setFillsViewportHeight(true);

                // Configure GroupLayout for the form panel
                layout.setHorizontalGroup(
                                layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(customerNameLabel)
                                                                .addComponent(mobileNumberLabel)
                                                                .addComponent(dlNoLabel)
                                                                .addComponent(locationLabel)
                                                                .addComponent(idProofLabel)
                                                                .addComponent(idProofNumberLabel))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(customerNameField)
                                                                .addComponent(mobileNumberField)
                                                                .addComponent(dlNoField)
                                                                .addComponent(locationField)
                                                                .addComponent(idProofDropdown)
                                                                .addComponent(idProofNumberField)
                                                                .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(addButton)
                                                                                .addComponent(cancelButton)))
                                                .addComponent(scrollPane) // Add the table on the right
                );

                layout.setVerticalGroup(
                                layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(customerNameLabel)
                                                                .addComponent(customerNameField))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(mobileNumberLabel)
                                                                .addComponent(mobileNumberField))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(dlNoLabel)
                                                                .addComponent(dlNoField))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(locationLabel)
                                                                .addComponent(locationField))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(idProofLabel)
                                                                .addComponent(idProofDropdown))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(idProofNumberLabel)
                                                                .addComponent(idProofNumberField))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(addButton)
                                                                .addComponent(cancelButton))
                                                .addComponent(scrollPane));

                // Add the form and table to the frame
                frame.add(formPanel);
                frame.setVisible(true);
                try {

                } catch (Exception e) {
                }
        }
}
