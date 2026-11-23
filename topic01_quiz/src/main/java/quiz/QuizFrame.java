package quiz;

import javax.swing.*;
import java.awt.*;

/**
 * Fereastra jocului: întrebarea sus, 3 butoane cu variante, scorul jos.
 */
public class QuizFrame extends JFrame {
    private final QuestionBank bank = new QuestionBank();
    private QuizGame game;

    private final JLabel numberLabel = new JLabel(" ");
    private final JLabel questionLabel = new JLabel(" ");
    private final JButton[] optionButtons = new JButton[3];
    private final JLabel feedbackLabel = new JLabel(" ");
    private final JLabel scoreLabel = new JLabel(" ");

    public QuizFrame() {
        setTitle("Quiz / Victorină");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // partea de sus: numărul și textul întrebării
        JPanel top = new JPanel(new GridLayout(2, 1));
        numberLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        top.add(numberLabel);
        top.add(questionLabel);
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // mijloc: 3 butoane cu variante
        JPanel center = new JPanel(new GridLayout(3, 1, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        String[] letters = {"a", "b", "c"};
        for (int i = 0; i < 3; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 16));
            final int index = i; // index folosit în ascultător
            optionButtons[i].addActionListener(e -> onAnswer(index));
            optionButtons[i].setActionCommand(letters[i]);
            center.add(optionButtons[i]);
        }

        // jos: mesaj corect / greșit și scorul
        JPanel bottom = new JPanel(new GridLayout(2, 1));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        feedbackLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        bottom.add(feedbackLabel);
        bottom.add(scoreLabel);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    // alegem categoria și pornim un joc nou
    public void startNewGame() {
        String category = (String) JOptionPane.showInputDialog(this, "Alege categoria:", "Quiz",
                JOptionPane.QUESTION_MESSAGE, null, bank.getCategories(), QuestionBank.ALL);
        if (category == null) {
            category = QuestionBank.ALL; // dacă se apasă Cancel
        }
        game = new QuizGame(bank.getQuestions(category), true);
        feedbackLabel.setText(" ");
        showQuestion();
    }

    private void showQuestion() {
        Question q = game.getCurrentQuestion();
        numberLabel.setText("Întrebarea " + game.getQuestionNumber() + " din " + game.getTotal()
                + "   (" + q.getCategory() + ")");
        // <html> ca textul lung să treacă pe rândul următor
        questionLabel.setText("<html>" + q.getText() + "</html>");
        String[] letters = {"a) ", "b) ", "c) "};
        for (int i = 0; i < 3; i++) {
            optionButtons[i].setText(letters[i] + q.getOption(i));
        }
        scoreLabel.setText("Scor: " + game.getScore() + "   |   Record: " + QuizGame.getBestScore());
    }

    private void onAnswer(int index) {
        Question q = game.getCurrentQuestion();
        boolean correct = game.answer(index);
        if (correct) {
            feedbackLabel.setForeground(new Color(0, 130, 0));
            feedbackLabel.setText("Corect!");
        } else {
            feedbackLabel.setForeground(Color.RED);
            feedbackLabel.setText("Greșit! Răspuns corect: " + q.getCorrectText());
        }

        if (game.isFinished()) {
            scoreLabel.setText("Scor: " + game.getScore() + "   |   Record: " + QuizGame.getBestScore());
            int choice = JOptionPane.showConfirmDialog(this,
                    "Scor final: " + game.getScore() + " din " + game.getTotal()
                            + "\nRecord: " + QuizGame.getBestScore()
                            + "\n\nMai joci o dată?",
                    "Sfârșit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                startNewGame();
            } else {
                dispose();
            }
        } else {
            showQuestion();
        }
    }
}
