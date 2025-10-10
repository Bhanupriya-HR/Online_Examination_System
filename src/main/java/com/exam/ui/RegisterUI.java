package com.exam.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;

    public RegisterUI() {
        setTitle("Register");
        setSize(5, 3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        submitButton = new JButton("Register");
        add(submitButton);

        submitButton.addActionListener(e -> registerUser());

        setVisible(true);
    }

    public void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection con = com.exam.db.DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            dispose(); // close the register window
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new RegisterUI();
    }
}