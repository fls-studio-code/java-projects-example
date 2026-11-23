package calculator;

import javax.swing.*;
import java.awt.*;

/**
 * Fereastra cu două file: Calculator și Convertor. Istoricul e în dreapta.
 */
public class CalculatorFrame extends JFrame {
    private final Calculator calculator = new Calculator();
    private final Converter converter = new Converter();

    private final JTextField firstField = new JTextField(8);
    private final JTextField secondField = new JTextField(8);
    private final JLabel calcResult = new JLabel("Rezultat: ");

    private final JTextField convertField = new JTextField(8);
    private final JComboBox<String> modeBox = new JComboBox<>(Converter.MODES);
    private final JLabel convertResult = new JLabel("Rezultat: ");

    private final DefaultListModel<String> historyModel = new DefaultListModel<>();

    public CalculatorFrame() {
        setTitle("Calculator / Convertor");
        setSize(700, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Calculator", createCalculatorPanel());
        tabs.addTab("Convertor", createConverterPanel());

        // istoricul operațiilor
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder("Istoric"));
        historyPanel.setPreferredSize(new Dimension(230, 0));
        historyPanel.add(new JScrollPane(new JList<>(historyModel)), BorderLayout.CENTER);
        JButton clearButton = new JButton("Șterge istoricul");
        clearButton.addActionListener(e -> historyModel.clear());
        historyPanel.add(clearButton, BorderLayout.SOUTH);

        add(tabs, BorderLayout.CENTER);
        add(historyPanel, BorderLayout.EAST);
    }

    private JPanel createCalculatorPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputs = new JPanel(new FlowLayout());
        inputs.add(new JLabel("a ="));
        inputs.add(firstField);
        inputs.add(new JLabel("b ="));
        inputs.add(secondField);

        JPanel ops = new JPanel(new GridLayout(1, 4, 8, 0));
        String[] symbols = {"+", "-", "*", "/"};
        for (String s : symbols) {
            JButton b = new JButton(s);
            b.setFont(new Font("SansSerif", Font.BOLD, 22));
            b.addActionListener(e -> onCalculate(s));
            ops.add(b);
        }

        calcResult.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(inputs);
        panel.add(ops);
        panel.add(calcResult);
        panel.add(new JLabel("Scrie două numere și apasă o operație."));
        return panel;
    }

    private JPanel createConverterPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputs = new JPanel(new FlowLayout());
        inputs.add(new JLabel("Valoare:"));
        inputs.add(convertField);
        inputs.add(modeBox);

        JButton convertButton = new JButton("Convertește");
        convertButton.addActionListener(e -> onConvert());
        convertField.addActionListener(e -> onConvert());

        convertResult.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(inputs);
        panel.add(convertButton);
        panel.add(convertResult);
        panel.add(new JLabel("Curs fix: 1 EUR = " + Converter.EUR_RATE + " lei"));
        return panel;
    }

    private void onCalculate(String operation) {
        try {
            double a = Calculator.parseNumber(firstField.getText());
            double b = Calculator.parseNumber(secondField.getText());
            double result = calculator.calculate(a, operation, b);
            String line = Calculator.format(a) + " " + operation + " " + Calculator.format(b)
                    + " = " + Calculator.format(result);
            calcResult.setText("Rezultat: " + Calculator.format(result));
            historyModel.addElement(line);
        } catch (NumberFormatException ex) {
            showError("Scrie numere corecte (de exemplu 12 sau 3.5).");
        } catch (ArithmeticException ex) {
            showError(ex.getMessage());
        }
    }

    private void onConvert() {
        try {
            double value = Calculator.parseNumber(convertField.getText());
            int mode = modeBox.getSelectedIndex();
            double result = converter.convert(mode, value);
            String line = Calculator.format(value) + " " + Converter.MODES[mode] + " = " + Calculator.format(result);
            convertResult.setText("Rezultat: " + Calculator.format(result));
            historyModel.addElement(line);
        } catch (NumberFormatException ex) {
            showError("Scrie un număr corect.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Eroare", JOptionPane.ERROR_MESSAGE);
    }
}
