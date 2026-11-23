package hangman;

/**
 * Ce s-a întâmplat după o literă.
 */
public enum GuessResult {
    CORRECT,        // litera este în cuvânt
    WRONG,          // litera nu este – pierdem o viață
    ALREADY_USED,   // litera a mai fost spusă
    INVALID         // nu e o literă
}
