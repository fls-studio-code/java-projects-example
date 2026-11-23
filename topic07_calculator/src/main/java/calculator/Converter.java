package calculator;

/**
 * Convertor: temperatură, valută și distanță.
 */
public class Converter {
    public static final double EUR_RATE = 19.5; // 1 EUR = 19.5 lei (curs aproximativ, fix)

    public static final String[] MODES = {
        "°C → °F",
        "°F → °C",
        "lei → EUR",
        "EUR → lei",
        "km → mile",
        "mile → km"
    };

    public double celsiusToFahrenheit(double c) {
        return c * 9 / 5 + 32;
    }

    public double fahrenheitToCelsius(double f) {
        return (f - 32) * 5 / 9;
    }

    public double leiToEur(double lei) {
        return lei / EUR_RATE;
    }

    public double eurToLei(double eur) {
        return eur * EUR_RATE;
    }

    public double kmToMiles(double km) {
        return km * 0.621371;
    }

    public double milesToKm(double miles) {
        return miles / 0.621371;
    }

    // modeIndex = poziția din MODES
    public double convert(int modeIndex, double value) {
        switch (modeIndex) {
            case 0: return celsiusToFahrenheit(value);
            case 1: return fahrenheitToCelsius(value);
            case 2: return leiToEur(value);
            case 3: return eurToLei(value);
            case 4: return kmToMiles(value);
            case 5: return milesToKm(value);
            default: throw new IllegalArgumentException("Mod necunoscut");
        }
    }
}
