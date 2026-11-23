package rps;

/**
 * Gesturile jocului. Primele 3 sunt clasice, ultimele 2 – varianta Șopârlă-Spock.
 */
public enum Move {
    ROCK("Piatră"),
    PAPER("Hârtie"),
    SCISSORS("Foarfecă"),
    LIZARD("Șopârlă"),
    SPOCK("Spock");

    private final String title;

    Move(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // true dacă acest gest îl bate pe celălalt
    public boolean beats(Move other) {
        switch (this) {
            case ROCK:     return other == SCISSORS || other == LIZARD;
            case PAPER:    return other == ROCK || other == SPOCK;
            case SCISSORS: return other == PAPER || other == LIZARD;
            case LIZARD:   return other == PAPER || other == SPOCK;
            case SPOCK:    return other == ROCK || other == SCISSORS;
            default:       return false;
        }
    }
}
