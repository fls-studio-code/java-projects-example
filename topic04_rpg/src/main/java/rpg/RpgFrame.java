package rpg;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Fereastra: povestea în mijloc, starea eroului sus, două butoane de alegere jos.
 */
public class RpgFrame extends JFrame {
    private RpgGame game;

    private final JTextArea storyArea = new JTextArea();
    private final JProgressBar hpBar = new JProgressBar(0, Hero.MAX_HP);
    private final JLabel weaponLabel = new JLabel(" ");
    private final JLabel inventoryLabel = new JLabel(" ");
    private final JButton choice1Button = new JButton();
    private final JButton choice2Button = new JButton();

    public RpgFrame() {
        setTitle("Aventură text (RPG)");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // sus: HP, arma, inventarul
        JPanel status = new JPanel(new GridLayout(1, 3, 10, 0));
        status.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        hpBar.setStringPainted(true);
        status.add(hpBar);
        status.add(weaponLabel);
        status.add(inventoryLabel);

        // mijloc: textul poveștii
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        storyArea.setMargin(new Insets(10, 10, 10, 10));

        // jos: cele două alegeri
        JPanel choices = new JPanel(new GridLayout(1, 2, 10, 0));
        choices.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        choice1Button.setFont(new Font("SansSerif", Font.BOLD, 15));
        choice2Button.setFont(new Font("SansSerif", Font.BOLD, 15));
        choices.add(choice1Button);
        choices.add(choice2Button);

        choice1Button.addActionListener(e -> onChoice(1));
        choice2Button.addActionListener(e -> onChoice(2));

        add(status, BorderLayout.NORTH);
        add(new JScrollPane(storyArea), BorderLayout.CENTER);
        add(choices, BorderLayout.SOUTH);
    }

    public void startNewGame() {
        String name = JOptionPane.showInputDialog(this, "Cum se numește eroul tău?", "Erou");
        if (name == null || name.trim().isEmpty()) {
            name = "Eroul"; // nume implicit
        }
        game = new RpgGame(name.trim(), new Random());
        refresh();
    }

    private void onChoice(int option) {
        if (game.isOver()) {
            // în final: 1 = joc nou, 2 = ieșire
            if (option == 1) {
                startNewGame();
            } else {
                dispose();
            }
            return;
        }
        game.choose(option);
        refresh();
    }

    private void refresh() {
        Hero hero = game.getHero();
        String text = "";
        if (!game.getLastEvent().isEmpty()) {
            text = game.getLastEvent() + "\n\n";
        }
        storyArea.setText(text + game.getSceneText());
        choice1Button.setText("1) " + game.getChoice1());
        choice2Button.setText("2) " + game.getChoice2());
        hpBar.setValue(hero.getHp());
        hpBar.setString("HP: " + hero.getHp() + " / " + Hero.MAX_HP);
        weaponLabel.setText("Armă: " + hero.getWeapon().getTitle());
        inventoryLabel.setText("Inventar: " + (hero.getInventory().isEmpty() ? "gol" : hero.getInventory()));
    }
}
