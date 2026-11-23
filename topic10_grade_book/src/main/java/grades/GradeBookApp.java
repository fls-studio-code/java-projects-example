package grades;

import javax.swing.SwingUtilities;

/**
 * Punctul de pornire al programului.
 */
public class GradeBookApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradeBookFrame().setVisible(true));
    }
}
