package battleship;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Punctul de pornire al programului.
 */
public class BattleshipApp {
    public static void main(String[] args) {
        // aspect "Metal": pe Mac altfel culorile butoanelor nu se văd
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Nu am putut schimba aspectul: " + e.getMessage());
        }
        SwingUtilities.invokeLater(() -> new BattleshipFrame().setVisible(true));
    }
}
