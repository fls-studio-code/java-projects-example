package rps;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Fereastra jocului Piatră-Foarfecă-Hârtie.
 */
public class RpsFrame extends JFrame {
    private final RpsGame game = new RpsGame(new Random());

    private final JButton[] moveButtons = new JButton[Move.values().length];
    private final JLabel roundLabel = new JLabel("Alege un gest!", SwingConstants.CENTER);
    private final JLabel scoreLabel = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel statsLabel = new JLabel(" ", SwingConstants.CENTER);
    private final JCheckBox extendedBox = new JCheckBox("Varianta cu Șopârlă și Spock");

    public RpsFrame() {
        setTitle("Piatră, foarfecă, hârtie");
        setSize(700, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // sus: varianta jocului
        JPanel top = new JPanel(new FlowLayout());
        JLabel rule = new JLabel("Joc până la " + RpsGame.WINS_NEEDED + " victorii.  ");
        top.add(rule);
        top.add(extendedBox);

        // mijloc: butoanele cu gesturi
        JPanel movesPanel = new JPanel(new GridLayout(1, Move.values().length, 10, 0));
        movesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (Move m : Move.values()) {
            JButton b = new JButton(m.getTitle());
            b.setFont(new Font("SansSerif", Font.BOLD, 16));
            b.addActionListener(e -> onMove(m));
            moveButtons[m.ordinal()] = b;
            movesPanel.add(b);
        }

        // jos: rezultatul rundei, scorul, statistica, butoane
        JPanel bottom = new JPanel(new GridLayout(4, 1, 5, 5));
        bottom.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        roundLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JPanel buttons = new JPanel(new FlowLayout());
        JButton newGameButton = new JButton("Joc nou");
        JButton finishButton = new JButton("Termină și arată scorul");
        buttons.add(newGameButton);
        buttons.add(finishButton);
        bottom.add(roundLabel);
        bottom.add(scoreLabel);
        bottom.add(statsLabel);
        bottom.add(buttons);

        add(top, BorderLayout.NORTH);
        add(movesPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        extendedBox.addActionListener(e -> {
            game.setExtended(extendedBox.isSelected());
            newGame();
        });
        newGameButton.addActionListener(e -> newGame());
        finishButton.addActionListener(e -> showFinalScore());

        newGame();
    }

    private void newGame() {
        game.reset();
        // butoanele Șopârlă și Spock apar doar în varianta extinsă
        moveButtons[Move.LIZARD.ordinal()].setVisible(game.isExtended());
        moveButtons[Move.SPOCK.ordinal()].setVisible(game.isExtended());
        roundLabel.setText("Alege un gest!");
        refresh();
    }

    private void onMove(Move player) {
        Move computer = game.randomComputerMove();
        RoundResult result = game.playRound(player, computer);
        String text = "Tu: " + player.getTitle() + "  –  Calculatorul: " + computer.getTitle() + "  →  ";
        if (result == RoundResult.PLAYER) {
            text += "Ai câștigat runda!";
        } else if (result == RoundResult.COMPUTER) {
            text += "Calculatorul câștigă runda.";
        } else {
            text += "Egal.";
        }
        roundLabel.setText(text);
        refresh();

        if (game.isOver()) {
            showFinalScore();
        }
    }

    private void showFinalScore() {
        String winner;
        if (game.getPlayerWins() > game.getComputerWins()) {
            winner = "Ai câștigat jocul!";
        } else if (game.getPlayerWins() < game.getComputerWins()) {
            winner = "Calculatorul a câștigat jocul.";
        } else {
            winner = "Egalitate.";
        }
        int choice = JOptionPane.showConfirmDialog(this,
                winner + "\n\nRunde: " + game.getRounds()
                        + "\nTu: " + game.getPlayerWins()
                        + "   Calculatorul: " + game.getComputerWins()
                        + "   Egal: " + game.getDraws()
                        + "\n\nMai joci?",
                "Scor final", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            newGame();
        } else {
            dispose();
        }
    }

    private void refresh() {
        scoreLabel.setText("Tu: " + game.getPlayerWins() + "   Calculatorul: " + game.getComputerWins()
                + "   Egal: " + game.getDraws() + "   (runda " + game.getRounds() + ")");
        statsLabel.setText("Statistica gesturilor:  " + game.getStatsText());
    }
}
