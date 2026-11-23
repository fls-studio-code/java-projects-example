package grades;

/**
 * O notă: materia și valoarea (1–10).
 */
public class Grade {
    private final String subject;
    private final int value;

    public Grade(String subject, int value) {
        this.subject = subject;
        this.value = value;
    }

    public String getSubject() {
        return subject;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return subject + ": " + value;
    }
}
