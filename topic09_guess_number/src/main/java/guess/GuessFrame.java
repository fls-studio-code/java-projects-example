package guess;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Fereastra: câmp pentru număr, buton, indicii și contorul de încercări.
 */
public class GuessFrame extends JFrame {
    private final Random random = new Random();
    private GuessGame game;

    private final JComboBox<Difficulty> levelBox = new JComboBox<>(Difficulty.values());
    private final JTextField numberField = new JTextField(6);
    private final JButton guessButton = new JButton("Verifică");
    private final JLabel rangeLabel = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel hintLabel = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel attemptsLabel = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel recordLabel = new JLabel(" ", SwingConstants.CENTER);

    public GuessFrame() {
        setTitle("Ghicește numărul");
        setSize(520, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new FlowLayout());
        JButton newGameButton = new JButton("Joc nou");
        top.add(new JLabel("Nivel:"));
        top.add(levelBox);
        top.add(newGameButton);

        JPanel center = new JPanel(new GridLayout(5, 1, 5, 5));
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        hintLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        numberField.setFont(new Font("SansSerif", Font.BOLD, 20));
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(numberField);
        inputPanel.add(guessButton);
        center.add(rangeLabel);
        center.add(inputPanel);
        center.add(hintLabel);
        center.add(attemptsLabel);
        center.add(recordLabel);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        guessButton.addActionListener(e -> onGuess());
        numberField.addActionListener(e -> onGuess()); // Enter = Verifică
        newGameButton.addActionListener(e -> startNewGame());

        startNewGame();
    }

    private void startNewGame() {
        Difficulty level = (Difficulty) levelBox.getSelectedItem();
        game = new GuessGame(level, random);
        rangeLabel.setText("Am ales un număr de la 1 la " + level.getMax() + ". Ghicește-l!");
        hintLabel.setText(" ");
        numberField.setText("");
        numberField.setEnabled(true);
        guessButton.setEnabled(true);
        refresh();
        numberField.requestFocus();
    }

    private void onGuess() {
        int number;
        try {
            number = Integer.parseInt(numberField.getText().trim());
        } catch (NumberFormatException ex) {
            hintLabel.setForeground(Color.RED);
            hintLabel.setText("Scrie un număr întreg!");
            return;
        }
        numberField.setText("");

        GuessResult result = game.guess(number);
        hintLabel.setForeground(Color.BLACK);
        switch (result) {
            case OUT_OF_RANGE:
                hintLabel.setForeground(Color.RED);
                hintLabel.setText("Numărul trebuie să fie între 1 și " + game.getDifficulty().getMax());
                break;
            case TOO_LOW:
                hintLabel.setText(number + " este prea mic.  " + game.getHint(number));
                break;
            case TOO_HIGH:
                hintLabel.setText(number + " este prea mare.  " + game.getHint(number));
                break;
            case CORRECT:
                hintLabel.setForeground(new Color(0, 130, 0));
                hintLabel.setText("Corect! Numărul era " + number);
                break;
        }
        refresh();

        if (game.isOver()) {
            numberField.setEnabled(false);
            guessButton.setEnabled(false);
            String message = (result == GuessResult.CORRECT)
                    ? "Bravo! Ai ghicit din " + game.getAttempts() + " încercări."
                    : "Ai terminat încercările. Numărul era " + game.getSecret() + ".";
            int choice = JOptionPane.showConfirmDialog(this, message + "\n\nMai joci o dată?",
                    "Sfârșit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                startNewGame();
            }
        }
    }

    private void refresh() {
        attemptsLabel.setText("Încercări: " + game.getAttempts() + "   |   Rămase: " + game.getAttemptsLeft());
        int best = GuessGame.getBestScore();
        recordLabel.setText("Record: " + (best == 0 ? "încă nu există" : best + " încercări"));
    }
}
