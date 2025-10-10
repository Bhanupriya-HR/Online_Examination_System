//package com.exam.ui;
//import com.exam.db.DBConnection;
//import java.sql.*;
//import java.util.Scanner;
//public class LoginUI {
//    public static boolean loginUser() {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter username: ");
//        String username = sc.nextLine();
//        System.out.print("Enter password: ");
//        String password = sc.nextLine();
//        try (Connection con = DBConnection.getConnection()) {
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                System.out.println("Login successful!");
//                return true;
//            } else {
//                System.out.println("Invalid credentials.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
package com.exam.ui;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.exam.db.DBConnection;

public class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginUI() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(e -> loginUser());

        setVisible(true);
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose();
                new ExamUI(username);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}
