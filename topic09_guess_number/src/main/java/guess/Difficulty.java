package guess;

/**
 * Nivelurile de dificultate: intervalul și numărul maxim de încercări.
 */
public enum Difficulty {
    EASY("Ușor (1–20, 10 încercări)", 20, 10),
    MEDIUM("Mediu (1–100, 10 încercări)", 100, 10),
    HARD("Greu (1–100, 7 încercări)", 100, 7);

    private final String title;
    private final int max;
    private final int maxAttempts;

    Difficulty(String title, int max, int maxAttempts) {
        this.title = title;
        this.max = max;
        this.maxAttempts = maxAttempts;
    }

    public int getMax() {
        return max;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    @Override
    public String toString() {
        return title; // așa apare în lista de alegere
    }
}
