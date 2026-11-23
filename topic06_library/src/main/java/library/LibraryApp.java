package library;

import javax.swing.SwingUtilities;

/**
 * Punctul de pornire al programului.
 */
public class LibraryApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryFrame().setVisible(true));
    }
}
