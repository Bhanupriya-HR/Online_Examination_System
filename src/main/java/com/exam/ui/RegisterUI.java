package com.exam.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;

    public RegisterUI() {
        // ==== Window setup ====
        setTitle("Register");
        setSize(800, 500);               // Medium, consistent size
        setLocationRelativeTo(null);     // Centers window on screen
        setResizable(false);             // Prevents maximizing/resizing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ==== Layout setup ====
        JPanel panel = new JPanel(new GridBagLayout()); // Centered content
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);        // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ==== Components ====
        JLabel titleLabel = new JLabel("Register for Online Exam", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(20);
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // Submit Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        submitButton = new JButton("Register");
        panel.add(submitButton, gbc);

        // ==== Add listener ====
        submitButton.addActionListener(e -> registerUser());

        // ==== Final setup ====
        add(panel);
        setVisible(true);
    }

    public void registerUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try (Connection con = com.exam.db.DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username, password) VALUES (?, ?)"
            );
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "User registered successfully!");
            dispose(); // close the register window
            new LoginUI(); // open login window automatically after success
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterUI::new);
    }
}