
  /* 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
*/
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationFrame extends JFrame {
    private JLabel nameLabel, usernameLabel, passwordLabel, confirmPasswordLabel, contactLabel, emailLabel, presentAddressLabel, permanentAddressLabel, districtLabel;
    private JTextField nameField, usernameField, contactField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JTextArea presentAddressArea, permanentAddressArea;
    private JComboBox<String> districtComboBox;
    private JButton submitButton, backButton, closeButton;

    private final String[] districts = {
            "Bagerhat", "Bandarban", "Barguna", "Barishal", "Bhola", "Bogra", "Brahmanbaria", "Chandpur", "Chattogram", "Chuadanga",
            "Cox's Bazar", "Cumilla", "Dhaka", "Dinajpur", "Faridpur", "Feni", "Gaibandha", "Gazipur", "Gopalganj", "Habiganj",
            "Jamalpur", "Jashore", "Jhalokati", "Jhenaidah", "Joypurhat", "Khagrachari", "Khulna", "Kishoreganj", "Kurigram",
            "Kushtia", "Lakshmipur", "Lalmonirhat", "Madaripur", "Magura", "Manikganj", "Meherpur", "Moulvibazar", "Munshiganj",
            "Mymensingh", "Naogaon", "Narail", "Narayanganj", "Narsingdi", "Natore", "Netrokona", "Nilphamari", "Noakhali",
            "Pabna", "Panchagarh", "Patuakhali", "Pirojpur", "Rajbari", "Rajshahi", "Rangamati", "Rangpur", "Satkhira", "Shariatpur",
            "Sherpur", "Sirajganj", "Sunamganj", "Sylhet", "Tangail", "Thakurgaon"
    };

    private static final String URL = "jdbc:mysql://localhost:3306/hostelaccomodation1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static final String INSERT_QUERY = "INSERT INTO hostel_registration (full_name, username, password, contact_number, email_address, present_address, permanent_address, district) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public RegistrationFrame() {
        setTitle("User Registration - Hostel Accommodation");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("username.png").getImage()); 

 
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closeButton = new JButton("X");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        topPanel.add(closeButton);
        add(topPanel, BorderLayout.NORTH);

        // Main content panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(50, 30, 50, 70)); // Add padding
        mainPanel.setLayout(new GridLayout(15, 2, 10, 10));

        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 18); // Increase font size for labels
        nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(labelFont);
        nameField = new JTextField();

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        usernameField = new JTextField();

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordField = new JPasswordField();

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordField = new JPasswordField();

        contactLabel = new JLabel("Contact Number:");
        contactLabel.setFont(labelFont);
        contactField = new JTextField();

        emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(labelFont);
        emailField = new JTextField();

        presentAddressLabel = new JLabel("Present Address:");
        presentAddressLabel.setFont(labelFont);
        presentAddressArea = new JTextArea();
        presentAddressArea.setRows(2);
        presentAddressArea.setMargin(new Insets(5, 10, 5, 5));

        permanentAddressLabel = new JLabel("Permanent Address:");
        permanentAddressLabel.setFont(labelFont);
        permanentAddressArea = new JTextArea();
        permanentAddressArea.setRows(2);
        permanentAddressArea.setMargin(new Insets(5, 10, 5, 5));

        districtLabel = new JLabel("Home District:");
        districtLabel.setFont(labelFont);
        districtComboBox = new JComboBox<>(districts);

        submitButton = new JButton("Submit");
        submitButton.setFont(labelFont); // Increase font size for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertRegistrationData();
            }
        });
        
        backButton = new JButton("Back"); // Adding Back button
        backButton.setFont(labelFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new registrationframe01().setVisible(true);
                
            }
        });
        

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(confirmPasswordLabel);
        mainPanel.add(confirmPasswordField);
        mainPanel.add(contactLabel);
        mainPanel.add(contactField);
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(presentAddressLabel);
        mainPanel.add(new JScrollPane(presentAddressArea));
        mainPanel.add(permanentAddressLabel);
        mainPanel.add(new JScrollPane(permanentAddressArea));
        mainPanel.add(districtLabel);
        mainPanel.add(districtComboBox);
        mainPanel.add(backButton); // Add the Back button here
        mainPanel.add(submitButton);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void insertRegistrationData() {
        String fullName = nameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String contactNumber = contactField.getText();
        String emailAddress = emailField.getText();
        String presentAddress = presentAddressArea.getText();
        String permanentAddress = permanentAddressArea.getText();
        String district = (String) districtComboBox.getSelectedItem();

        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || contactNumber.isEmpty() || emailAddress.isEmpty() || presentAddress.isEmpty() || permanentAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, contactNumber);
            preparedStatement.setString(5, emailAddress);
            preparedStatement.setString(6, presentAddress);
            preparedStatement.setString(7, permanentAddress);
            preparedStatement.setString(8, district);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        contactField.setText("");
        emailField.setText("");
        presentAddressArea.setText("");
        permanentAddressArea.setText("");
        districtComboBox.setSelectedIndex(0);
    }

    private void goBack() {
        // Implement your logic to navigate back
        // For example, you could close this frame and open the previous one
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationFrame::new);
    }
}
