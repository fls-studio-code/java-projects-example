package guess;

import java.util.Random;

/**
 * Logica jocului "Ghicește numărul" (fără fereastră).
 */
public class GuessGame {
    private final Difficulty difficulty;
    private final int secret;
    private int attempts = 0;
    private boolean won = false;
    private static int bestScore = 0; // cele mai puține încercări (0 = încă nu există)

    public GuessGame(Difficulty difficulty, Random random) {
        this.difficulty = difficulty;
        this.secret = random.nextInt(difficulty.getMax()) + 1; // de la 1 la max
    }

    // constructor pentru teste / demo: numărul este dat
    public GuessGame(Difficulty difficulty, int secret) {
        this.difficulty = difficulty;
        this.secret = secret;
    }

    public GuessResult guess(int number) {
        if (number < 1 || number > difficulty.getMax()) {
            return GuessResult.OUT_OF_RANGE; // nu consumă o încercare
        }
        attempts++;
        if (number < secret) {
            return GuessResult.TOO_LOW;
        }
        if (number > secret) {
            return GuessResult.TOO_HIGH;
        }
        won = true;
        if (bestScore == 0 || attempts < bestScore) {
            bestScore = attempts;
        }
        return GuessResult.CORRECT;
    }

    // "cald / rece": cât de aproape este încercarea
    public String getHint(int number) {
        int distance = Math.abs(number - secret);
        if (distance <= 3) {
            return "Foarte cald!";
        } else if (distance <= 10) {
            return "Cald";
        } else {
            return "Rece";
        }
    }

    public boolean isLost() {
        return !won && attempts >= difficulty.getMaxAttempts();
    }

    public boolean isOver() {
        return won || isLost();
    }

    public int getAttempts() {
        return attempts;
    }

    public int getAttemptsLeft() {
        return difficulty.getMaxAttempts() - attempts;
    }

    public int getSecret() {
        return secret;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public static int getBestScore() {
        return bestScore;
    }
}
