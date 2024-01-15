// Import necessary packages for GUI and database connection
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class JavaGUI {
    private JTextField idField, nameField, ageField, emailField;
    private DefaultTableModel tableModel;
    private JTable table;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JavaGUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Java GUI Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(new Color(40, 75, 99));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 15, 0)); // Add space at top and bottom

        // Add title text to the title panel
        JLabel titleLabel = new JLabel("Student Management System ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);

        // Center the title text
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.anchor = GridBagConstraints.CENTER;
        titlePanel.add(titleLabel, titleConstraints);

        // Add icon to the title panel after the title text
        ImageIcon titleIcon = new ImageIcon(getClass().getResource("/icons/title-icon.png"));
        Image resizedTitleIcon = titleIcon.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        ImageIcon finalTitleIcon = new ImageIcon(resizedTitleIcon);
        JLabel titleIconLabel = new JLabel(finalTitleIcon);

        // Set constraints for the icon
        GridBagConstraints iconConstraints = new GridBagConstraints();
        iconConstraints.gridx = 1;
        iconConstraints.gridy = 0;
        iconConstraints.anchor = GridBagConstraints.CENTER;
        titlePanel.add(titleIconLabel, iconConstraints);

        frame.add(titlePanel, BorderLayout.NORTH);

        // Left Panel with Manage Students Title, Input Fields and Buttons
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(217, 217, 217));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.anchor = GridBagConstraints.WEST;

        // Add Manage Students Title with icon
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns for the title
        gbc.anchor = GridBagConstraints.CENTER; // Center-align the title

        ImageIcon manageStudentsIcon = new ImageIcon(getClass().getResource("/icons/manage-icon.png"));
        Image manageStudentsImage = manageStudentsIcon.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        ImageIcon resizedManageStudentsIcon = new ImageIcon(manageStudentsImage);

        JLabel manageStudentsTitle = new JLabel("  Manage Students", resizedManageStudentsIcon, SwingConstants.LEFT);
        
        manageStudentsTitle.setBorder(BorderFactory.createEmptyBorder(2, 0, 14, 0)); // Add space at top and bottom
        
        manageStudentsTitle.setFont(new Font("Arial", Font.BOLD, 25));
        leftPanel.add(manageStudentsTitle, gbc);

        // Use a separate GridBagConstraints for the Student Info Text
        GridBagConstraints studentInfoGbc = new GridBagConstraints();
        studentInfoGbc.gridx = 0;
        studentInfoGbc.gridy = 1; // Adjust the row index accordingly
        studentInfoGbc.gridwidth = 2; // Span two columns for the text
        studentInfoGbc.anchor = GridBagConstraints.WEST; // left-align the text

        JLabel studentInfoLabel = new JLabel("   Student Info:");
        studentInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        leftPanel.add(studentInfoLabel, studentInfoGbc);

        // Reset gridwidth for subsequent components
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(new JLabel("ID:"), gbc);

        gbc.gridy++;
        leftPanel.add(new JLabel("Name:"), gbc);

        gbc.gridy++;
        leftPanel.add(new JLabel("Age:"), gbc);

        gbc.gridy++;
        leftPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        idField = new JTextField(21);
        idField.setPreferredSize(new Dimension(idField.getPreferredSize().width, 32)); // Increased height
        leftPanel.add(idField, gbc);

        gbc.gridy++;
        nameField = new JTextField(21);
        nameField.setPreferredSize(new Dimension(nameField.getPreferredSize().width, 32)); // Increased height
        leftPanel.add(nameField, gbc);

        gbc.gridy++;
        ageField = new JTextField(21);
        ageField.setPreferredSize(new Dimension(ageField.getPreferredSize().width, 32)); // Increased height
        leftPanel.add(ageField, gbc);

        gbc.gridy++;
        emailField = new JTextField(21);
        emailField.setPreferredSize(new Dimension(emailField.getPreferredSize().width, 32)); // Increased height
        leftPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 12, 18));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0)); // Add space at top and bottom
        buttonPanel.setBackground(new Color(217, 217, 217));

        // Create icon buttons using IconButton class
        IconButton updateButton = new IconButton(" Update", "/icons/update-icon.png", 30, 30);
        IconButton addButton = new IconButton(" Add", "/icons/add-icon.png", 26, 26);
        IconButton clearButton = new IconButton(" Clear", "/icons/clear-icon.png", 26, 26);
        IconButton deleteButton = new IconButton(" Delete", "/icons/delete-icon.png", 26, 26);

        // Add icon buttons to the button panel
        buttonPanel.add(updateButton);
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);

        // Set background color for the buttons
        updateButton.setBackground(new Color(53, 53, 53));
        addButton.setBackground(new Color(53, 53, 53));
        clearButton.setBackground(new Color(53, 53, 53));
        deleteButton.setBackground(new Color(53, 53, 53));

        // Set foreground color for the buttons
        updateButton.setForeground(Color.WHITE);
        addButton.setForeground(Color.WHITE);
        clearButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);

        // Increase the font size for the buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        updateButton.setFont(buttonFont);
        addButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);

        // Increase the width of the buttons
        Dimension buttonSize = new Dimension(140, 40);
        updateButton.setPreferredSize(buttonSize);
        addButton.setPreferredSize(buttonSize);
        clearButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);

        buttonPanel.add(updateButton);
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);

        leftPanel.add(buttonPanel, gbc);

        frame.add(leftPanel, BorderLayout.WEST);

        // Right Panel with Table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Email");

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = table.getTableHeader();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Customize font and background color for the table header
        Font headerFont = new Font("Arial", Font.PLAIN, 14);
        header.setFont(headerFont);
        header.setBackground(new Color(53, 53, 53));
        header.setForeground(Color.WHITE);

        frame.add(tablePanel, BorderLayout.CENTER);

        refreshTable();

        // Add action listeners for the buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInputFields()) {
                    addRecord();
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Set frame properties
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private Connection getConnection() throws SQLException {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/studmandatabase?useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "";
    
        // Explicitly load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    
        return DriverManager.getConnection(url, user, password);
    }

    private boolean validateInputFields() {
        // Validate if all input fields are filled
        return !idField.getText().isEmpty() && !nameField.getText().isEmpty() && !ageField.getText().isEmpty()
                && !emailField.getText().isEmpty();
    }

    private void addRecord() {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO studentinfo (id, name, age, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Set values for prepared statement
                statement.setString(1, idField.getText());
                statement.setString(2, nameField.getText());
                statement.setString(3, ageField.getText());
                statement.setString(4, emailField.getText());
                // Execute the query and get the number of affected rows
                int rowsAffected = statement.executeUpdate();
    
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Record added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    refreshTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add record.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String idToDelete = (String) table.getValueAt(selectedRow, 0);
            // Confirm the deletion with a dialog
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                try (Connection connection = getConnection()) {
                    String query = "DELETE FROM studentinfo WHERE id=?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        // Set the value for the prepared statement
                        statement.setString(1, idToDelete);
                        // Execute the query and get the number of affected rows
                        int rowsAffected = statement.executeUpdate();
    
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            refreshTable();
                            clearFields();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to delete record.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String idToUpdate = (String) table.getValueAt(selectedRow, 0);
            String nameToUpdate = (String) table.getValueAt(selectedRow, 1);
            String ageToUpdate = (String) table.getValueAt(selectedRow, 2);
            String emailToUpdate = (String) table.getValueAt(selectedRow, 3);
    
            // Create a new JFrame for the update window
            JFrame updateFrame = new JFrame("Update Record");
            updateFrame.setLayout(new BorderLayout());
            updateFrame.setSize(400, 200);
            updateFrame.setLocationRelativeTo(null);
    
            JPanel updatePanel = new JPanel(new GridLayout(4, 2, 10, 10));
            updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
            // Add labels and text fields for each attribute
            JTextField idFieldUpdate = new JTextField(idToUpdate);
            JTextField nameFieldUpdate = new JTextField(nameToUpdate);
            JTextField ageFieldUpdate = new JTextField(ageToUpdate);
            JTextField emailFieldUpdate = new JTextField(emailToUpdate);
    
            updatePanel.add(new JLabel("ID:"));
            updatePanel.add(idFieldUpdate);
            updatePanel.add(new JLabel("Name:"));
            updatePanel.add(nameFieldUpdate);
            updatePanel.add(new JLabel("Age:"));
            updatePanel.add(ageFieldUpdate);
            updatePanel.add(new JLabel("Email:"));
            updatePanel.add(emailFieldUpdate);
    
            JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Update the data in the table and database
                    updateDataInTable(idToUpdate, idFieldUpdate.getText(), nameFieldUpdate.getText(),
                            ageFieldUpdate.getText(), emailFieldUpdate.getText());
                    updateFrame.dispose(); // Close the update window
                }
            });
    
            updateFrame.add(updatePanel, BorderLayout.CENTER);
            updateFrame.add(confirmButton, BorderLayout.SOUTH);
    
            updateFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateDataInTable(String idToUpdate, String newId, String newName, String newAge, String newEmail) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE studentinfo SET id=?, name=?, age=?, email=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, newId);
                statement.setString(2, newName);
                statement.setString(3, newAge);
                statement.setString(4, newEmail);
                statement.setString(5, idToUpdate);
                int rowsAffected = statement.executeUpdate();
    
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Record updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    refreshTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update record.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0); // Clear existing rows
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM studentinfo";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String age = resultSet.getString("age");
                    String email = resultSet.getString("email");
    
                    // Check for null values and handle accordingly
                    if (id != null && name != null && age != null && email != null) {
                        tableModel.addRow(new String[]{id, name, age, email});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        emailField.setText("");
    }
}
