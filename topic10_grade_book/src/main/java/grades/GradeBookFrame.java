package grades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Fereastra catalogului: introducere notă, tabel, media și butoane.
 */
public class GradeBookFrame extends JFrame {
    private static final String FILE_NAME = "note.txt";

    private final GradeBook book = new GradeBook();

    private final JTextField subjectField = new JTextField(12);
    private final JTextField valueField = new JTextField(4);
    private final DefaultTableModel tableModel =
            new DefaultTableModel(new String[] {"Nr", "Materia", "Nota"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
    private final JTable table = new JTable(tableModel);
    private final JLabel averageLabel = new JLabel(" ");
    private final JLabel extraLabel = new JLabel(" ");

    public GradeBookFrame() {
        setTitle("Catalog de note");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // sus: introducerea notei
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Adaugă");
        top.add(new JLabel("Materia:"));
        top.add(subjectField);
        top.add(new JLabel("Nota (1–10):"));
        top.add(valueField);
        top.add(addButton);

        // dreapta: butoane
        JPanel side = new JPanel(new GridLayout(6, 1, 5, 5));
        side.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JButton deleteButton = new JButton("Șterge nota");
        JButton searchButton = new JButton("Caută materia");
        JButton reportButton = new JButton("Raport");
        JButton saveButton = new JButton("Salvează");
        JButton loadButton = new JButton("Încarcă");
        side.add(deleteButton);
        side.add(searchButton);
        side.add(reportButton);
        side.add(saveButton);
        side.add(loadButton);

        // jos: media
        JPanel bottom = new JPanel(new GridLayout(2, 1));
        bottom.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        averageLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        bottom.add(averageLabel);
        bottom.add(extraLabel);

        table.setRowHeight(24);
        table.getColumnModel().getColumn(0).setMaxWidth(40);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(side, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);

        addButton.addActionListener(e -> onAdd());
        valueField.addActionListener(e -> onAdd());
        deleteButton.addActionListener(e -> onDelete());
        searchButton.addActionListener(e -> onSearch());
        reportButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this, book.getReport(), "Raport", JOptionPane.INFORMATION_MESSAGE));
        saveButton.addActionListener(e -> onSave());
        loadButton.addActionListener(e -> onLoad());

        // câteva note de start, ca să nu fie gol la demo
        book.addGrade("Matematica", 8);
        book.addGrade("Informatica", 10);
        book.addGrade("Româna", 7);
        refresh();
    }

    private void onAdd() {
        int value;
        try {
            value = Integer.parseInt(valueField.getText().trim());
        } catch (NumberFormatException ex) {
            showError("Nota trebuie să fie un număr întreg.");
            return;
        }
        try {
            book.addGrade(subjectField.getText(), value);
            subjectField.setText("");
            valueField.setText("");
            subjectField.requestFocus();
            refresh();
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    private void onDelete() {
        int row = table.getSelectedRow();
        if (row < 0) {
            showError("Selectează o notă din tabel.");
            return;
        }
        book.removeGrade(row);
        refresh();
    }

    private void onSearch() {
        String text = JOptionPane.showInputDialog(this, "Ce materie cauți?");
        if (text == null || text.trim().isEmpty()) {
            return;
        }
        ArrayList<Grade> found = book.searchBySubject(text);
        if (found.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu sunt note la: " + text);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Grade g : found) {
            sb.append(g).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Rezultatul căutării", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onSave() {
        try {
            book.saveToFile(FILE_NAME);
            JOptionPane.showMessageDialog(this, "Salvat în " + FILE_NAME);
        } catch (IOException ex) {
            showError("Nu am putut salva: " + ex.getMessage());
        }
    }

    private void onLoad() {
        try {
            book.loadFromFile(FILE_NAME);
            refresh();
        } catch (IOException ex) {
            showError("Nu am găsit fișierul " + FILE_NAME + ". Salvează mai întâi.");
        }
    }

    private void refresh() {
        tableModel.setRowCount(0);
        int nr = 1;
        for (Grade g : book.getGrades()) {
            tableModel.addRow(new Object[] {nr, g.getSubject(), g.getValue()});
            nr++;
        }
        if (book.isEmpty()) {
            averageLabel.setText("Media: –");
            extraLabel.setText("Adaugă prima notă.");
        } else {
            averageLabel.setText("Media: " + book.getAverageText() + "   →   "
                    + (book.isPassed() ? "PROMOVAT" : "NEPROMOVAT"));
            extraLabel.setText("Cea mai bună: " + book.getBest() + "   |   Cea mai slabă: " + book.getWorst());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Eroare", JOptionPane.ERROR_MESSAGE);
    }
}
