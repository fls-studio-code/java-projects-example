package calculator;

import javax.swing.SwingUtilities;

/**
 * Punctul de pornire al programului.
 */
public class CalculatorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorFrame().setVisible(true));
    }
}
