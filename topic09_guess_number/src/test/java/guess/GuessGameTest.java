package guess;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GuessGameTest {

    @Test
    void hintsAndWin() {
        GuessGame game = new GuessGame(Difficulty.MEDIUM, 42);
        assertEquals(GuessResult.TOO_LOW, game.guess(10));
        assertEquals(GuessResult.TOO_HIGH, game.guess(80));
        assertEquals(GuessResult.CORRECT, game.guess(42));
        assertEquals(3, game.getAttempts());
        assertTrue(game.isOver());
        assertTrue(GuessGame.getBestScore() > 0);
    }

    @Test
    void outOfRangeDoesNotCount() {
        GuessGame game = new GuessGame(Difficulty.EASY, 5);
        assertEquals(GuessResult.OUT_OF_RANGE, game.guess(50));
        assertEquals(0, game.getAttempts());
    }

    @Test
    void loseAfterMaxAttempts() {
        GuessGame game = new GuessGame(Difficulty.HARD, 50);
        for (int i = 1; i <= 7; i++) {
            game.guess(i);
        }
        assertTrue(game.isLost());
    }

    @Test
    void secretIsInRange() {
        Random r = new Random(7);
        for (int i = 0; i < 200; i++) {
            int s = new GuessGame(Difficulty.EASY, r).getSecret();
            assertTrue(s >= 1 && s <= 20);
        }
    }

    @Test
    void warmCold() {
        GuessGame game = new GuessGame(Difficulty.MEDIUM, 50);
        assertEquals("Foarte cald!", game.getHint(52));
        assertEquals("Cald", game.getHint(58));
        assertEquals("Rece", game.getHint(90));
    }
}
