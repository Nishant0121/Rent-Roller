import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Login extends javax.swing.JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public Login() {
        initComponents();
        try {
            loadUserData(); // Fetch user data from database
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Driver not found: " + e.getMessage());
        }
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        login_button = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(102, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("Login");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel3.setText("Password");

        name.setFont(new java.awt.Font("Segoe UI", 0, 18));
        name.setName("email");
        name.addActionListener(evt -> nameActionPerformed(evt));

        login_button.setBackground(new java.awt.Color(153, 255, 255));
        login_button.setText("Login");
        login_button.addActionListener(evt -> login_buttonActionPerformed(evt));

        // Table setup
        tableModel = new DefaultTableModel(new String[] { "ID", "Name", "Email" }, 0);
        table = new JTable(tableModel);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(249, 249, 249)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(227, 227, 227)
                                                .addComponent(login_button, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(150, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(login_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(100, Short.MAX_VALUE)));

        pack();
    }

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {
        // Action for name field
    }

    private void login_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        // You can handle login logic here
    }

    private void loadUserData() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/rentroller?useSSL=false";
        String username = "root";
        String password = "1234Qwer";
        String query = "SELECT id, name, email FROM user";

        try (Connection con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                tableModel.addRow(new Object[] { id, name, email });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data from the database: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton login_button;
    private javax.swing.JTextField name;
    private javax.swing.JPasswordField password;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}
