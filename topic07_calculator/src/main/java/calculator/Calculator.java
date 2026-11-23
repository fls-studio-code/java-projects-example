package calculator;

/**
 * Logica calculatorului (fără fereastră) – ușor de testat cu JUnit.
 */
public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Împărțirea la zero nu este permisă!");
        }
        return a / b;
    }

    // calculează după simbolul operației
    public double calculate(double a, String operation, double b) {
        switch (operation) {
            case "+":
                return add(a, b);
            case "-":
                return subtract(a, b);
            case "*":
                return multiply(a, b);
            case "/":
                return divide(a, b);
            default:
                throw new IllegalArgumentException("Operație necunoscută: " + operation);
        }
    }

    // transformă textul în număr; acceptă și virgula (3,5)
    public static double parseNumber(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new NumberFormatException("Câmpul este gol.");
        }
        return Double.parseDouble(text.trim().replace(',', '.'));
    }

    // afișare frumoasă: 5.0 -> "5", 2.3333333 -> "2.33"
    public static String format(double value) {
        if (value == Math.rint(value) && Math.abs(value) < 1e15) {
            return String.valueOf((long) value);
        }
        return String.format("%.2f", value).replace(',', '.');
    }
}
