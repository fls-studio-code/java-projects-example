package guess;

import javax.swing.SwingUtilities;

/**
 * Punctul de pornire al programului.
 */
public class GuessApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessFrame().setVisible(true));
    }
}
