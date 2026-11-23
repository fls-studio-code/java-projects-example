package battleship;

/**
 * Rezultatul unei lovituri.
 */
public enum ShotResult {
    HIT,      // lovit
    MISS,     // ratat
    ALREADY,  // în celula asta s-a tras deja
    INVALID   // coordonate în afara tablei
}
