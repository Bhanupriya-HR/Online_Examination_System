////package com.exam.ui;
////import com.exam.db.DBConnection;
////import com.exam.model.Question;
////import java.sql.*;
////import java.util.*;
////public class ExamUI {
////    public static void startExam() {
////        List<Question> questions = new ArrayList<>();
////        try (Connection con = DBConnection.getConnection()) {
////            Statement st = con.createStatement();
////            ResultSet rs = st.executeQuery("SELECT * FROM questions");
////            while (rs.next()) {
////                questions.add(new Question(rs.getInt("id"), rs.getString("question_text"), rs.getString("option_a"),
////                        rs.getString("option_b"), rs.getString("option_c"), rs.getString("option_d"), rs.getString("correct_answer")));
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        int score = 0;
////        Scanner sc = new Scanner(System.in);
////        for (Question q : questions) {
////            System.out.println("\nQ" + q.getId() + ": " + q.getQuestionText());
////            System.out.println("A. " + q.getOptionA());
////            System.out.println("B. " + q.getOptionB());
////            System.out.println("C. " + q.getOptionC());
////            System.out.println("D. " + q.getOptionD());
////            System.out.print("Your answer: ");
////            String ans = sc.nextLine();
////            if (ans.equalsIgnoreCase(q.getCorrectAnswer())) score++;
////        }
////        System.out.println("Exam completed!   Your score: \" + score + \"/\" + questions.size()");
////    }
////}
//package com.exam.ui;
//import javax.swing.*;
//import java.awt.*;
//import java.sql.*;
//import java.util.*;
//import com.exam.db.DBConnection;
//import com.exam.model.Question;
//
//public class ExamUI extends JFrame {
//    private java.util.List<Question> questions = new ArrayList<>();
//    private int currentIndex = 0;
//    private int score = 0;
//    private JLabel questionLabel;
//    private JRadioButton optionA, optionB, optionC, optionD;
//    private ButtonGroup group;
//    private JButton nextButton;
//    private String username;
//
//    public ExamUI(String username) {
//        this.username = username;
//        setTitle("Exam - " + username);
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(6,1));
//
//        questionLabel = new JLabel();
//        add(questionLabel);
//
//        optionA = new JRadioButton();
//        optionB = new JRadioButton();
//        optionC = new JRadioButton();
//        optionD = new JRadioButton();
//        group = new ButtonGroup();
//        group.add(optionA); group.add(optionB); group.add(optionC); group.add(optionD);
//        add(optionA); add(optionB); add(optionC); add(optionD);
//
//        nextButton = new JButton("Next");
//        add(nextButton);
//        nextButton.addActionListener(e -> nextQuestion());
//
//        loadQuestions();
//        displayQuestion();
//        setVisible(true);
//    }
//
//    private void loadQuestions() {
//        try (Connection con = DBConnection.getConnection()) {
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM questions");
//            while (rs.next()) {
//                questions.add(new Question(rs.getInt("id"), rs.getString("question_text"),
//                        rs.getString("option_a"), rs.getString("option_b"), rs.getString("option_c"),
//                        rs.getString("option_d"), rs.getString("correct_answer")));
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error loading questions: " + e.getMessage());
//        }
//    }
//
//    private void displayQuestion() {
//        if(currentIndex < questions.size()) {
//            Question q = questions.get(currentIndex);
//            questionLabel.setText((currentIndex+1) + ". " + q.getQuestionText());
//            optionA.setText("A. " + q.getOptionA());
//            optionB.setText("B. " + q.getOptionB());
//            optionC.setText("C. " + q.getOptionC());
//            optionD.setText("D. " + q.getOptionD());
//            group.clearSelection();
//        }
//    }
//
//    private void nextQuestion() {
//        if(currentIndex < questions.size()) {
//            Question q = questions.get(currentIndex);
//            String selected = null;
//            if(optionA.isSelected()) selected = "A";
//            else if(optionB.isSelected()) selected = "B";
//            else if(optionC.isSelected()) selected = "C";
//            else if(optionD.isSelected()) selected = "D";
//
//            if(selected != null && selected.equalsIgnoreCase(q.getCorrectAnswer())) score++;
//            currentIndex++;
//            if(currentIndex < questions.size()) displayQuestion();
//            else showResult();
//        } else showResult();
//    }
//
//    private void showResult() {
//        JOptionPane.showMessageDialog(this, "Exam completed! Score: " + score + "/" + questions.size());
//        dispose();
//        new LoginUI(); // go back to login after exam
//    }
//}

package com.exam.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import com.exam.db.DBConnection;
import com.exam.model.Question;

public class ExamUI extends JFrame {
    private java.util.List<Question> questions = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;
    private JLabel questionLabel;
    private JRadioButton optionA, optionB, optionC, optionD;
    private ButtonGroup group;
    private JButton nextButton;
    private String username;

    public ExamUI(String username) {
        this.username = username;

        // ==== Window setup ====
        setTitle("Exam - " + username);
        setSize(800, 500);            // consistent with other UIs
        setLocationRelativeTo(null);  // center on screen
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ==== Layout ====
        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        JPanel optionsPanel = new JPanel(new GridLayout(4,1,5,5));
        JPanel bottomPanel = new JPanel();

        questionLabel = new JLabel("Question will appear here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        optionA = new JRadioButton();
        optionB = new JRadioButton();
        optionC = new JRadioButton();
        optionD = new JRadioButton();

        group = new ButtonGroup();
        group.add(optionA); group.add(optionB); group.add(optionC); group.add(optionD);

        optionsPanel.add(optionA);
        optionsPanel.add(optionB);
        optionsPanel.add(optionC);
        optionsPanel.add(optionD);

        nextButton = new JButton("Next");
        bottomPanel.add(nextButton);

        mainPanel.add(questionLabel, BorderLayout.NORTH);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // ==== Listeners ====
        nextButton.addActionListener(e -> nextQuestion());

        loadQuestions();
        displayQuestion();

        setVisible(true);
    }

    private void loadQuestions() {
        try (Connection con = DBConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM questions");
            while (rs.next()) {
                questions.add(new Question(
                        rs.getInt("id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_answer")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading questions: " + e.getMessage());
        }
    }

    private void displayQuestion() {
        if(currentIndex < questions.size()) {
            Question q = questions.get(currentIndex);
            questionLabel.setText((currentIndex+1) + ". " + q.getQuestionText());
            optionA.setText("A. " + q.getOptionA());
            optionB.setText("B. " + q.getOptionB());
            optionC.setText("C. " + q.getOptionC());
            optionD.setText("D. " + q.getOptionD());
            group.clearSelection();
        }
    }

    private void nextQuestion() {
        if(currentIndex < questions.size()) {
            Question q = questions.get(currentIndex);
            String selected = null;
            if(optionA.isSelected()) selected = "A";
            else if(optionB.isSelected()) selected = "B";
            else if(optionC.isSelected()) selected = "C";
            else if(optionD.isSelected()) selected = "D";

            if(selected != null && selected.equalsIgnoreCase(q.getCorrectAnswer())) score++;
            currentIndex++;

            if(currentIndex < questions.size()) displayQuestion();
            else showResult();
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Exam completed! Score: " + score + "/" + questions.size());
        saveScore(); // Save to database
        dispose();
        new LoginUI(); // go back to login
    }

    private void saveScore() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO results(username, score, total_questions) VALUES (?, ?, ?)"
            );
            ps.setString(1, username);
            ps.setInt(2, score);
            ps.setInt(3, questions.size());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving score: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExamUI("TestUser"));
    }
}