package hangman;

import javax.swing.*;
import java.awt.*;

/**
 * Desenează spânzurătoarea: cu fiecare greșeală apare o parte nouă.
 */
public class GallowsPanel extends JPanel {
    private int mistakes = 0;

    public GallowsPanel() {
        setPreferredSize(new Dimension(220, 260));
        setBackground(Color.WHITE);
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
        repaint(); // redesenăm panoul
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.DARK_GRAY);

        // stâlpul (mereu desenat)
        g2.drawLine(20, 240, 140, 240);
        g2.drawLine(50, 240, 50, 20);
        g2.drawLine(50, 20, 150, 20);
        g2.drawLine(150, 20, 150, 50);

        g2.setColor(Color.RED);
        if (mistakes >= 1) g2.drawOval(130, 50, 40, 40);    // capul
        if (mistakes >= 2) g2.drawLine(150, 90, 150, 160);  // corpul
        if (mistakes >= 3) g2.drawLine(150, 105, 120, 135); // mâna stângă
        if (mistakes >= 4) g2.drawLine(150, 105, 180, 135); // mâna dreaptă
        if (mistakes >= 5) g2.drawLine(150, 160, 125, 205); // piciorul stâng
        if (mistakes >= 6) g2.drawLine(150, 160, 175, 205); // piciorul drept
    }
}
