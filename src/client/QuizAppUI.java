package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuizAppUI extends JFrame {
    private JLabel questionLabel;
    private JButton[] answerButtons;
    private JLabel scoreLabel;

    public QuizAppUI(ActionListener answerListener) {
        setTitle("Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Pioneer Quiz");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        scoreLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(scoreLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Question Area
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 245, 245));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        questionLabel = new JLabel("Connecting to server...", SwingConstants.CENTER);
        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        questionLabel.setForeground(new Color(50, 50, 50));
        centerPanel.add(questionLabel, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);

        // Answers Area
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonsPanel.setBackground(new Color(245, 245, 245));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));

        answerButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            JButton btn = new JButton("Option " + (i + 1));
            btn.setFont(new Font("SansSerif", Font.PLAIN, 16));
            btn.setBackground(Color.WHITE);
            btn.setForeground(new Color(50, 50, 50));
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.addActionListener(answerListener);
            btn.setActionCommand(String.valueOf(i));
            answerButtons[i] = btn;
            buttonsPanel.add(btn);
        }

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void updateQuestion(String question, String[] options) {
        SwingUtilities.invokeLater(() -> {
            questionLabel.setText("<html><div style='text-align: center;'>" + question + "</div></html>");
            for (int i = 0; i < 4; i++) {
                if (i < options.length) {
                    answerButtons[i].setText(options[i]);
                    answerButtons[i].setVisible(true);
                    answerButtons[i].setEnabled(true);
                } else {
                    answerButtons[i].setVisible(false);
                }
            }
        });
    }

    public void updateScore(int score) {
        SwingUtilities.invokeLater(() -> scoreLabel.setText("Score: " + score));
    }

    public void disableButtons() {
        for (JButton btn : answerButtons) {
            btn.setEnabled(false);
        }
    }
}
