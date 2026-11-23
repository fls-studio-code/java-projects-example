package hangman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanGameTest {

    @Test
    void maskOpensAllOccurrences() {
        HangmanGame game = new HangmanGame("mama");
        assertEquals("_ _ _ _", game.getMask());
        assertEquals(GuessResult.CORRECT, game.guess('a'));
        assertEquals("_ a _ a", game.getMask());
    }

    @Test
    void wrongLetterTakesLifeOnlyOnce() {
        HangmanGame game = new HangmanGame("urs");
        assertEquals(GuessResult.WRONG, game.guess('x'));
        assertEquals(GuessResult.ALREADY_USED, game.guess('x'));
        assertEquals(5, game.getLives());
        assertEquals(GuessResult.INVALID, game.guess('7'));
    }

    @Test
    void winAndLose() {
        HangmanGame win = new HangmanGame("măr");
        win.guess('m');
        win.guess('Ă');
        win.guess('r');
        assertTrue(win.isWon());

        HangmanGame lose = new HangmanGame("urs");
        for (char c : "abcdef".toCharArray()) {
            lose.guess(c);
        }
        assertTrue(lose.isLost());
    }

    @Test
    void wordBankRespectsLevel() {
        WordBank bank = new WordBank();
        for (int i = 0; i < 20; i++) {
            assertTrue(bank.randomWord("Fructe", false).length() <= 5);
            assertTrue(bank.randomWord("Orașe", true).length() > 5);
        }
    }
}
