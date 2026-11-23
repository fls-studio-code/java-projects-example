package battleship;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void hitMissAlreadyInvalid() {
        Board board = new Board();
        board.placeShip(1, 1);
        assertEquals(ShotResult.HIT, board.shoot(1, 1));
        assertEquals(ShotResult.MISS, board.shoot(0, 0));
        assertEquals(ShotResult.ALREADY, board.shoot(0, 0));
        assertEquals(ShotResult.INVALID, board.shoot(5, 0));
        assertEquals(0, board.shipsLeft());
    }

    @Test
    void randomPlacementHasExactCount() {
        Board board = new Board();
        board.placeShipsRandom(3, new Random(1));
        assertEquals(3, board.shipsLeft());
    }

    @Test
    void gameEndsWhenShotsRunOut() {
        BattleshipGame game = new BattleshipGame(new Random(42));
        int shots = 0;
        outer:
        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                if (game.isOver()) {
                    break outer;
                }
                game.playerShoots(r, c);
                if (!game.isOver()) {
                    game.computerShoots();
                }
                shots++;
            }
        }
        assertTrue(game.isOver());
        assertTrue(shots <= BattleshipGame.MAX_SHOTS);
    }
}
