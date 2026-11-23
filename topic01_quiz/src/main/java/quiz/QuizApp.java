package quiz;

import javax.swing.SwingUtilities;

/**
 * Punctul de pornire al programului.
 */
public class QuizApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizFrame frame = new QuizFrame();
            frame.setVisible(true);
            frame.startNewGame();
        });
    }
}
