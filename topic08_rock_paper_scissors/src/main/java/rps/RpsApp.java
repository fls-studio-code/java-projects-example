package rps;

import javax.swing.SwingUtilities;

/**
 * Punctul de pornire al programului.
 */
public class RpsApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RpsFrame().setVisible(true));
    }
}
