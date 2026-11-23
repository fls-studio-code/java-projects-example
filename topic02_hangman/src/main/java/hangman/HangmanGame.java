package hangman;

import java.util.ArrayList;

/**
 * Logica Spânzurătorii (fără fereastră).
 */
public class HangmanGame {
    public static final int MAX_LIVES = 6;

    private final String word;
    private final ArrayList<Character> usedLetters = new ArrayList<>();
    private int lives = MAX_LIVES;

    public HangmanGame(String word) {
        this.word = word.toLowerCase();
    }

    public GuessResult guess(char letter) {
        letter = Character.toLowerCase(letter);
        if (!Character.isLetter(letter)) {
            return GuessResult.INVALID;
        }
        if (usedLetters.contains(letter)) {
            return GuessResult.ALREADY_USED; // nu o numărăm de două ori
        }
        usedLetters.add(letter);
        if (word.indexOf(letter) >= 0) {
            return GuessResult.CORRECT;
        }
        lives--;
        return GuessResult.WRONG;
    }

    // masca: litere ghicite și "_" pentru restul, de ex. "p _ s _ c _"
    public String getMask() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (usedLetters.contains(c)) {
                sb.append(c);
            } else {
                sb.append('_');
            }
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public boolean isWon() {
        for (int i = 0; i < word.length(); i++) {
            if (!usedLetters.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isLost() {
        return lives <= 0;
    }

    public boolean isOver() {
        return isWon() || isLost();
    }

    public int getLives() {
        return lives;
    }

    public int getMistakes() {
        return MAX_LIVES - lives;
    }

    public String getWord() {
        return word;
    }

    public String getUsedLettersText() {
        StringBuilder sb = new StringBuilder();
        for (char c : usedLetters) {
            sb.append(c).append(' ');
        }
        return sb.toString().trim();
    }
}
