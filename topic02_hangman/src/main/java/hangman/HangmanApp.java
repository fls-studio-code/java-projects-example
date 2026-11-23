package hangman;

import javax.swing.SwingUtilities;

/**
 * Punctul de pornire al programului.
 */
public class HangmanApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HangmanFrame().setVisible(true));
    }
}
