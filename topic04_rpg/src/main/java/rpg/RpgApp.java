package rpg;

import javax.swing.SwingUtilities;

/**
 * Punctul de pornire al programului.
 */
public class RpgApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RpgFrame frame = new RpgFrame();
            frame.setVisible(true);
            frame.startNewGame();
        });
    }
}
