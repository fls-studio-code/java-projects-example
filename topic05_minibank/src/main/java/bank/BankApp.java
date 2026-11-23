package bank;

import javax.swing.SwingUtilities;

/**
 * Punctul de pornire al programului.
 */
public class BankApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankFrame().setVisible(true));
    }
}
