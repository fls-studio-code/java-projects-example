package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    private final Converter conv = new Converter();

    @Test
    void temperature() {
        assertEquals(212, conv.celsiusToFahrenheit(100), 0.0001);
        assertEquals(0, conv.fahrenheitToCelsius(32), 0.0001);
    }

    @Test
    void currencyAndDistance() {
        assertEquals(1, conv.leiToEur(Converter.EUR_RATE), 0.0001);
        assertEquals(195, conv.eurToLei(10), 0.0001);
        assertEquals(10, conv.milesToKm(conv.kmToMiles(10)), 0.0001);
    }
}
