package battleship;

import java.util.Random;

/**
 * Logica jocului: tabla jucătorului, tabla calculatorului, loviturile rămase.
 */
public class BattleshipGame {
    public static final int SHIPS = 3;
    public static final int MAX_SHOTS = 15;

    private final Board playerBoard = new Board();   // aici trage calculatorul
    private final Board computerBoard = new Board(); // aici trage jucătorul
    private final Random random;
    private int shotsLeft = MAX_SHOTS;

    public BattleshipGame(Random random) {
        this.random = random;
        playerBoard.placeShipsRandom(SHIPS, random);
        computerBoard.placeShipsRandom(SHIPS, random);
    }

    // jucătorul trage în tabla calculatorului
    public ShotResult playerShoots(int row, int col) {
        ShotResult result = computerBoard.shoot(row, col);
        if (result == ShotResult.HIT || result == ShotResult.MISS) {
            shotsLeft--;
        }
        return result;
    }

    // calculatorul alege o celulă în care n-a mai tras; întoarce {rând, coloană}
    public int[] computerShoots() {
        int r;
        int c;
        do {
            r = random.nextInt(Board.SIZE);
            c = random.nextInt(Board.SIZE);
        } while (playerBoard.wasShot(r, c));
        playerBoard.shoot(r, c);
        return new int[] {r, c};
    }

    public boolean playerWon() {
        return computerBoard.shipsLeft() == 0;
    }

    public boolean computerWon() {
        return playerBoard.shipsLeft() == 0;
    }

    public boolean isOver() {
        return playerWon() || computerWon() || shotsLeft <= 0;
    }

    public int getShotsLeft() {
        return shotsLeft;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Board getComputerBoard() {
        return computerBoard;
    }
}
