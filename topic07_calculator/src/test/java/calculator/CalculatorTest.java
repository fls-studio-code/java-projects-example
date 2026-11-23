package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calc = new Calculator();

    @Test
    void basicOperations() {
        assertEquals(5, calc.add(2, 3), 0.0001);
        assertEquals(-1, calc.subtract(2, 3), 0.0001);
        assertEquals(6, calc.multiply(2, 3), 0.0001);
        assertEquals(2.5, calc.divide(5, 2), 0.0001);
        assertEquals(6, calc.calculate(2, "*", 3), 0.0001);
    }

    @Test
    void divisionByZeroThrows() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }

    @Test
    void parseNumberAcceptsCommaAndRejectsLetters() {
        assertEquals(3.5, Calculator.parseNumber("3,5"), 0.0001);
        assertThrows(NumberFormatException.class, () -> Calculator.parseNumber("abc"));
        assertThrows(NumberFormatException.class, () -> Calculator.parseNumber(""));
    }

    @Test
    void formatting() {
        assertEquals("5", Calculator.format(5.0));
        assertEquals("2.33", Calculator.format(7.0 / 3));
    }
}
