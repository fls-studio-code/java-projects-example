package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Fereastra bibliotecii: tabel cu cărți, căutare și butoane de acțiune.
 */
public class LibraryFrame extends JFrame {
    private final Library library = new Library();
    private ArrayList<Book> shown = new ArrayList<>(); // cărțile afișate acum în tabel

    private final DefaultTableModel tableModel =
            new DefaultTableModel(new String[] {"Nr", "Titlu", "Autor", "Tip", "Status"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // tabelul doar se citește
                }
            };
    private final JTable table = new JTable(tableModel);
    private final JTextField searchField = new JTextField(15);
    private final JLabel infoLabel = new JLabel(" ");

    public LibraryFrame() {
        setTitle("Bibliotecă");
        setSize(750, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // sus: căutarea
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton searchButton = new JButton("Caută");
        JButton showAllButton = new JButton("Arată tot");
        top.add(new JLabel("Titlu:"));
        top.add(searchField);
        top.add(searchButton);
        top.add(showAllButton);

        // jos: acțiunile
        JPanel bottom = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Adaugă");
        JButton lendButton = new JButton("Împrumută");
        JButton returnButton = new JButton("Returnează");
        JButton sortButton = new JButton("Sortează A–Z");
        buttons.add(addButton);
        buttons.add(lendButton);
        buttons.add(returnButton);
        buttons.add(sortButton);
        bottom.add(buttons, BorderLayout.CENTER);
        bottom.add(infoLabel, BorderLayout.SOUTH);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        table.setRowHeight(24);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setMaxWidth(40);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> onSearch());
        searchField.addActionListener(e -> onSearch());
        showAllButton.addActionListener(e -> showBooks(library.getAll()));
        addButton.addActionListener(e -> onAdd());
        lendButton.addActionListener(e -> onLend());
        returnButton.addActionListener(e -> onReturn());
        sortButton.addActionListener(e -> {
            library.sortByTitle();
            showBooks(library.getAll());
        });

        showBooks(library.getAll());
    }

    // umplem tabelul cu lista dată
    private void showBooks(ArrayList<Book> list) {
        shown = new ArrayList<>(list);
        tableModel.setRowCount(0);
        int nr = 1;
        for (Book b : shown) {
            tableModel.addRow(new Object[] {nr, b.getTitle(), b.getAuthor(), b.getType(), b.getStatusText()});
            nr++;
        }
        infoLabel.setText("Afișate: " + shown.size() + "   |   Total: " + library.getAll().size()
                + "   |   Disponibile: " + library.countAvailable());
    }

    private void onSearch() {
        String text = searchField.getText();
        if (text.trim().isEmpty()) {
            showBooks(library.getAll());
            return;
        }
        ArrayList<Book> found = library.searchByTitle(text);
        if (found.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu am găsit nimic pentru: " + text);
        }
        showBooks(found);
    }

    private void onAdd() {
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JComboBox<String> typeBox = new JComboBox<>(new String[] {"Carte", "Revistă"});
        Object[] form = {"Titlu:", titleField, "Autor:", authorField, "Tip:", typeBox};
        int ok = JOptionPane.showConfirmDialog(this, form, "Carte nouă", JOptionPane.OK_CANCEL_OPTION);
        if (ok != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            library.addBook(titleField.getText(), authorField.getText(), (String) typeBox.getSelectedItem());
            showBooks(library.getAll());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    // cartea selectată în tabel sau null
    private Book getSelectedBook() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selectează mai întâi o carte din tabel.");
            return null;
        }
        return shown.get(row);
    }

    private void onLend() {
        Book b = getSelectedBook();
        if (b == null) {
            return;
        }
        if (library.lend(b)) {
            JOptionPane.showMessageDialog(this, "Ai împrumutat: " + b.getTitle());
        } else {
            JOptionPane.showMessageDialog(this, "Cartea este deja împrumutată.");
        }
        showBooks(shown);
    }

    private void onReturn() {
        Book b = getSelectedBook();
        if (b == null) {
            return;
        }
        if (library.giveBack(b)) {
            JOptionPane.showMessageDialog(this, "Mulțumim! Ai returnat: " + b.getTitle());
        } else {
            JOptionPane.showMessageDialog(this, "Cartea nu era împrumutată.");
        }
        showBooks(shown);
    }
}
