package hangman;

import javax.swing.*;
import java.awt.*;

/**
 * Fereastra jocului Spânzurătoarea.
 */
public class HangmanFrame extends JFrame {
    private final WordBank bank = new WordBank();
    private HangmanGame game;

    private final GallowsPanel gallowsPanel = new GallowsPanel();
    private final JLabel maskLabel = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel livesLabel = new JLabel(" ");
    private final JLabel usedLabel = new JLabel(" ");
    private final JLabel messageLabel = new JLabel(" ");
    private final JTextField letterField = new JTextField(3);
    private final JButton guessButton = new JButton("Ghicește");
    private final JComboBox<String> categoryBox = new JComboBox<>(bank.getCategories());
    private final JComboBox<String> levelBox = new JComboBox<>(bank.getLevels());

    public HangmanFrame() {
        setTitle("Spânzurătoarea / Hangman");
        setSize(650, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // sus: categoria, nivelul, joc nou
        JPanel top = new JPanel(new FlowLayout());
        JButton newGameButton = new JButton("Joc nou");
        top.add(new JLabel("Categoria:"));
        top.add(categoryBox);
        top.add(new JLabel("Nivel:"));
        top.add(levelBox);
        top.add(newGameButton);

        // dreapta: masca, viețile, literele, câmpul de introducere
        JPanel right = new JPanel(new GridLayout(5, 1, 5, 5));
        right.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        maskLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        JPanel inputPanel = new JPanel(new FlowLayout());
        letterField.setFont(new Font("SansSerif", Font.BOLD, 20));
        inputPanel.add(new JLabel("Litera:"));
        inputPanel.add(letterField);
        inputPanel.add(guessButton);
        right.add(maskLabel);
        right.add(livesLabel);
        right.add(usedLabel);
        right.add(inputPanel);
        right.add(messageLabel);

        add(top, BorderLayout.NORTH);
        add(gallowsPanel, BorderLayout.WEST);
        add(right, BorderLayout.CENTER);

        // butoane: click sau Enter în câmp
        guessButton.addActionListener(e -> onGuess());
        letterField.addActionListener(e -> onGuess());
        newGameButton.addActionListener(e -> startNewGame());

        startNewGame();
    }

    private void startNewGame() {
        String category = (String) categoryBox.getSelectedItem();
        boolean hard = levelBox.getSelectedIndex() == 1;
        game = new HangmanGame(bank.randomWord(category, hard));
        letterField.setEnabled(true);
        guessButton.setEnabled(true);
        messageLabel.setText("Scrie o literă și apasă Ghicește.");
        refresh();
        letterField.requestFocus();
    }

    private void onGuess() {
        String text = letterField.getText().trim();
        letterField.setText("");
        if (text.length() != 1) {
            messageLabel.setText("Scrie exact o literă!");
            return;
        }
        GuessResult result = game.guess(text.charAt(0));
        switch (result) {
            case CORRECT -> messageLabel.setText("Bravo, litera există!");
            case WRONG -> messageLabel.setText("Nu este. Ai pierdut o viață.");
            case ALREADY_USED -> messageLabel.setText("Ai mai spus litera asta.");
            case INVALID -> messageLabel.setText("Asta nu e o literă.");
        }
        refresh();

        if (game.isOver()) {
            letterField.setEnabled(false);
            guessButton.setEnabled(false);
            String text2 = game.isWon()
                    ? "Ai câștigat! Cuvântul: " + game.getWord()
                    : "Ai pierdut! Cuvântul era: " + game.getWord();
            int choice = JOptionPane.showConfirmDialog(this, text2 + "\n\nMai joci?",
                    "Sfârșit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                startNewGame();
            }
        }
    }

    // actualizăm tot ce se vede pe ecran
    private void refresh() {
        maskLabel.setText(game.getMask());
        livesLabel.setText("Vieți: " + game.getLives() + " / " + HangmanGame.MAX_LIVES);
        usedLabel.setText("Litere folosite: " + game.getUsedLettersText());
        gallowsPanel.setMistakes(game.getMistakes());
    }
}
