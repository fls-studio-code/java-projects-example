package bank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Fereastra bancomatului. Are două ecrane (CardLayout): logare și meniu.
 */
public class BankFrame extends JFrame {
    private final Bank bank = new Bank();
    private Account current; // contul logat

    private final CardLayout cards = new CardLayout();
    private final JPanel root = new JPanel(cards);

    // ecranul de logare
    private final JComboBox<Account> accountBox = new JComboBox<>();
    private final JPasswordField pinField = new JPasswordField(6);
    private final JLabel loginMessage = new JLabel(" ");

    // ecranul meniului
    private final JLabel welcomeLabel = new JLabel(" ");
    private final JLabel balanceLabel = new JLabel(" ");
    private final JTextField amountField = new JTextField(8);
    private final DefaultListModel<String> historyModel = new DefaultListModel<>();

    public BankFrame() {
        setTitle("Bancomat / Mini-bancă");
        setSize(620, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        for (Account a : bank.getAccounts()) {
            accountBox.addItem(a);
        }
        root.add(createLoginPanel(), "login");
        root.add(createMenuPanel(), "menu");
        add(root);
        cards.show(root, "login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.gridx = 0;
        g.gridy = 0;
        JLabel title = new JLabel("Introdu cardul și PIN-ul");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.gridwidth = 2;
        panel.add(title, g);

        g.gridwidth = 1;
        g.gridy = 1;
        panel.add(new JLabel("Cont:"), g);
        g.gridx = 1;
        panel.add(accountBox, g);

        g.gridx = 0;
        g.gridy = 2;
        panel.add(new JLabel("PIN:"), g);
        g.gridx = 1;
        panel.add(pinField, g);

        JButton loginButton = new JButton("Intră");
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 2;
        panel.add(loginButton, g);
        g.gridy = 4;
        panel.add(loginMessage, g);

        loginButton.addActionListener(e -> onLogin());
        pinField.addActionListener(e -> onLogin());
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel top = new JPanel(new GridLayout(2, 1));
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        balanceLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        top.add(welcomeLabel);
        top.add(balanceLabel);

        JPanel buttons = new JPanel(new GridLayout(7, 1, 5, 5));
        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        amountPanel.add(new JLabel("Suma:"));
        amountPanel.add(amountField);
        JButton depositButton = new JButton("Depune");
        JButton withdrawButton = new JButton("Retrage");
        JButton transferButton = new JButton("Transfer");
        JButton balanceButton = new JButton("Sold");
        JButton pinButton = new JButton("Schimbă PIN");
        JButton exitButton = new JButton("Ieșire");
        buttons.add(amountPanel);
        buttons.add(balanceButton);
        buttons.add(depositButton);
        buttons.add(withdrawButton);
        buttons.add(transferButton);
        buttons.add(pinButton);
        buttons.add(exitButton);

        JList<String> historyList = new JList<>(historyModel);
        JScrollPane historyScroll = new JScrollPane(historyList);
        historyScroll.setBorder(BorderFactory.createTitledBorder("Istoricul operațiilor"));

        panel.add(top, BorderLayout.NORTH);
        panel.add(buttons, BorderLayout.WEST);
        panel.add(historyScroll, BorderLayout.CENTER);

        balanceButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Sold: " + current.getBalance() + " lei"));
        depositButton.addActionListener(e -> onDeposit());
        withdrawButton.addActionListener(e -> onWithdraw());
        transferButton.addActionListener(e -> onTransfer());
        pinButton.addActionListener(e -> onChangePin());
        exitButton.addActionListener(e -> onLogout());
        return panel;
    }

    private void onLogin() {
        Account account = (Account) accountBox.getSelectedItem();
        String pin = new String(pinField.getPassword());
        pinField.setText("");
        if (account.isBlocked()) {
            loginMessage.setText("Contul " + account.getOwner() + " este blocat!");
            return;
        }
        if (account.checkPin(pin)) {
            current = account;
            loginMessage.setText(" ");
            refresh();
            cards.show(root, "menu");
        } else if (account.isBlocked()) {
            loginMessage.setText("PIN greșit de 3 ori. Contul este blocat!");
        } else {
            loginMessage.setText("PIN greșit. Încercări rămase: " + account.getAttemptsLeft());
        }
    }

    // citim suma din câmp; întoarce -1 dacă nu e un număr
    private double readAmount() {
        try {
            return Double.parseDouble(amountField.getText().trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Scrie o sumă corectă, de exemplu 100.",
                    "Eroare", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private void onDeposit() {
        double amount = readAmount();
        if (amount < 0) {
            return;
        }
        try {
            current.deposit(amount);
            refresh();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onWithdraw() {
        double amount = readAmount();
        if (amount < 0) {
            return;
        }
        try {
            current.withdraw(amount);
            refresh();
        } catch (InsufficientFundsException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onTransfer() {
        double amount = readAmount();
        if (amount < 0) {
            return;
        }
        // alegem destinatarul din lista celorlalte conturi
        ArrayList<Account> others = new ArrayList<>();
        for (Account a : bank.getAccounts()) {
            if (a != current) {
                others.add(a);
            }
        }
        Account target = (Account) JOptionPane.showInputDialog(this, "Către cine?", "Transfer",
                JOptionPane.QUESTION_MESSAGE, null, others.toArray(), others.get(0));
        if (target == null) {
            return;
        }
        try {
            current.transferTo(target, amount);
            refresh();
        } catch (InsufficientFundsException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onChangePin() {
        String oldPin = JOptionPane.showInputDialog(this, "PIN-ul vechi:");
        if (oldPin == null) {
            return;
        }
        String newPin = JOptionPane.showInputDialog(this, "PIN-ul nou (4 cifre):");
        if (newPin == null) {
            return;
        }
        try {
            current.changePin(oldPin, newPin);
            refresh();
            JOptionPane.showMessageDialog(this, "PIN schimbat cu succes.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onLogout() {
        current = null;
        amountField.setText("");
        cards.show(root, "login");
    }

    private void refresh() {
        welcomeLabel.setText("Bună, " + current.getOwner() + "!");
        balanceLabel.setText("Sold: " + current.getBalance() + " lei");
        historyModel.clear();
        for (String line : current.getHistory()) {
            historyModel.addElement(line);
        }
    }
}
