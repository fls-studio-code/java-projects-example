package battleship;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Fereastra: stânga = tabla ta (vezi navele), dreapta = tabla calculatorului (dai click).
 */
public class BattleshipFrame extends JFrame {
    private BattleshipGame game;
    private final JButton[][] playerButtons = new JButton[Board.SIZE][Board.SIZE];
    private final JButton[][] enemyButtons = new JButton[Board.SIZE][Board.SIZE];
    private final JLabel statusLabel = new JLabel(" ");
    private final JLabel infoLabel = new JLabel(" ");
    private final JCheckBox showShipsBox = new JCheckBox("Arată navele (demo)");

    public BattleshipFrame() {
        setTitle("Bătălia navală / Battleship");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel boards = new JPanel(new GridLayout(1, 2, 20, 0));
        boards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boards.add(createGrid(playerButtons, "Tabla ta", false));
        boards.add(createGrid(enemyButtons, "Tabla calculatorului (trage aici)", true));

        JPanel bottom = new JPanel(new GridLayout(2, 1));
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        JPanel buttons = new JPanel(new FlowLayout());
        JButton newGameButton = new JButton("Joc nou");
        newGameButton.addActionListener(e -> startNewGame());
        showShipsBox.addActionListener(e -> refresh());
        buttons.add(infoLabel);
        buttons.add(showShipsBox);
        buttons.add(newGameButton);
        bottom.add(statusLabel);
        bottom.add(buttons);

        add(boards, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        startNewGame();
    }

    // creează un panou cu 5x5 butoane și coordonate A–E / 1–5
    private JPanel createGrid(JButton[][] buttons, String title, boolean clickable) {
        JPanel grid = new JPanel(new GridLayout(Board.SIZE + 1, Board.SIZE + 1, 2, 2));
        grid.setBorder(BorderFactory.createTitledBorder(title));
        grid.add(new JLabel(""));
        for (int c = 0; c < Board.SIZE; c++) {
            grid.add(new JLabel(String.valueOf(c + 1), SwingConstants.CENTER));
        }
        for (int r = 0; r < Board.SIZE; r++) {
            grid.add(new JLabel(String.valueOf((char) ('A' + r)), SwingConstants.CENTER));
            for (int c = 0; c < Board.SIZE; c++) {
                JButton b = new JButton();
                b.setFont(new Font("SansSerif", Font.BOLD, 18));
                b.setFocusPainted(false);
                if (clickable) {
                    final int row = r;
                    final int col = c;
                    b.addActionListener(e -> onPlayerShot(row, col));
                }
                buttons[r][c] = b;
                grid.add(b);
            }
        }
        return grid;
    }

    private void startNewGame() {
        game = new BattleshipGame(new Random());
        statusLabel.setText("Găsește cele " + BattleshipGame.SHIPS + " nave ale calculatorului!");
        refresh();
    }

    private void onPlayerShot(int row, int col) {
        if (game.isOver()) {
            return;
        }
        ShotResult result = game.playerShoots(row, col);
        String coord = "" + (char) ('A' + row) + (col + 1);
        if (result == ShotResult.ALREADY) {
            statusLabel.setText("Ai tras deja în " + coord + ". Alege altă celulă.");
            return;
        }
        String text = (result == ShotResult.HIT) ? "Tu: " + coord + " – LOVIT!" : "Tu: " + coord + " – ratat.";

        // calculatorul trage înapoi, dacă jocul nu s-a terminat
        if (!game.isOver()) {
            int[] shot = game.computerShoots();
            char cell = game.getPlayerBoard().getCell(shot[0], shot[1]);
            String c2 = "" + (char) ('A' + shot[0]) + (shot[1] + 1);
            text += "   Calculatorul: " + c2 + (cell == Board.HIT ? " – te-a lovit!" : " – a ratat.");
        }
        statusLabel.setText(text);
        refresh();

        if (game.isOver()) {
            showEnd();
        }
    }

    private void showEnd() {
        String message;
        if (game.playerWon()) {
            message = "Ai câștigat! Ai găsit toate navele.";
        } else if (game.computerWon()) {
            message = "Calculatorul a câștigat! Ți-a scufundat navele.";
        } else {
            message = "S-au terminat loviturile. Ai pierdut!";
        }
        showShipsBox.setSelected(true);
        refresh();
        int choice = JOptionPane.showConfirmDialog(this, message + "\n\nMai joci?", "Sfârșit",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            showShipsBox.setSelected(false);
            startNewGame();
        }
    }

    // redesenăm ambele table după starea din logică
    private void refresh() {
        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                paintCell(playerButtons[r][c], game.getPlayerBoard().getCell(r, c), true);
                paintCell(enemyButtons[r][c], game.getComputerBoard().getCell(r, c), showShipsBox.isSelected());
            }
        }
        infoLabel.setText("Lovituri rămase: " + game.getShotsLeft()
                + "   |   Nave inamice: " + game.getComputerBoard().shipsLeft()
                + "   |   Navele tale: " + game.getPlayerBoard().shipsLeft());
    }

    private void paintCell(JButton b, char cell, boolean showShips) {
        if (cell == Board.HIT) {
            b.setText("X");
            b.setBackground(Color.RED);
        } else if (cell == Board.MISS) {
            b.setText("o");
            b.setBackground(Color.LIGHT_GRAY);
        } else if (cell == Board.SHIP && showShips) {
            b.setText("S");
            b.setBackground(new Color(120, 200, 120));
        } else {
            b.setText("");
            b.setBackground(new Color(150, 200, 255));
        }
        b.setOpaque(true);
    }
}
