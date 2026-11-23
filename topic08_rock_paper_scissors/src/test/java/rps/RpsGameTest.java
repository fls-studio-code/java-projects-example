package rps;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RpsGameTest {

    @Test
    void classicRules() {
        assertTrue(Move.ROCK.beats(Move.SCISSORS));
        assertTrue(Move.SCISSORS.beats(Move.PAPER));
        assertTrue(Move.PAPER.beats(Move.ROCK));
        assertFalse(Move.ROCK.beats(Move.PAPER));
    }

    @Test
    void everyMoveBeatsExactlyTwoOthers() {
        for (Move a : Move.values()) {
            int count = 0;
            for (Move b : Move.values()) {
                if (a.beats(b)) {
                    count++;
                }
            }
            assertEquals(2, count, a.name());
        }
    }

    @Test
    void scoreAndEndOfGame() {
        RpsGame game = new RpsGame(new Random(1));
        assertEquals(RoundResult.DRAW, game.playRound(Move.ROCK, Move.ROCK));
        game.playRound(Move.ROCK, Move.SCISSORS);
        game.playRound(Move.PAPER, Move.ROCK);
        assertFalse(game.isOver());
        game.playRound(Move.SCISSORS, Move.PAPER);
        assertTrue(game.isOver());
        assertEquals(3, game.getPlayerWins());
        assertEquals(4, game.getRounds());
    }

    @Test
    void classicModeUsesOnlyThreeMoves() {
        RpsGame game = new RpsGame(new Random(5));
        for (int i = 0; i < 100; i++) {
            assertTrue(game.randomComputerMove().ordinal() < 3);
        }
    }
}
