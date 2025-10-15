package com.exam.app;

import com.exam.ui.*;
import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] options = {"Register", "Login"};

            // Show a neat centered dialog for selection
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Welcome to the Online Examination System!\nPlease choose an action:",
                    "Online Exam System",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            // Launch the selected UI
            if (choice == 0) {
                new RegisterUI(); // Centered and fixed size (we’ll adjust inside RegisterUI)
            } else if (choice == 1) {
                new LoginUI();    // Same layout consistency
            } else {
                // If user closes the dialog without selecting
                JOptionPane.showMessageDialog(null, "Goodbye!");
                System.exit(0);
            }
        });
    }
}