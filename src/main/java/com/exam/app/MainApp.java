//package com.exam.app;
//import com.exam.ui.*;
//import java.util.Scanner;
//public class MainApp {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("==== ONLINE EXAM SYSTEM ====");
//        System.out.println("1. Register");
//        System.out.println("2. Login");
//        System.out.print("Choose option: ");
//        int choice = sc.nextInt();
//        sc.nextLine();
////        switch (choice) {
////            case 1 -> RegisterUI.registerUser();
////            case 2 -> {
////                if (LoginUI.loginUser()) ExamUI.startExam();
////            }
////            default -> System.out.println("Invalid option!");
//        }
//    }
package com.exam.app;

//import com.exam.ui.RegisterUI;
import com.exam.ui.*;
import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        // Option dialog to choose start window
        SwingUtilities.invokeLater(() -> {
            String[] options = {"Register", "Login"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Choose an action:",
                    "Online Exam System",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            JFrame frame = (choice == 0) ? new RegisterUI() : new LoginUI();

//            if(choice == 0) {
//                new RegisterUI();
//            } else if(choice == 1) {
//                new LoginUI();
//            }
        });
    }
}
