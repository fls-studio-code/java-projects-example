package quiz;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Logica jocului (fără fereastră): întrebarea curentă, scorul, recordul.
 */
public class QuizGame {
    private final ArrayList<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private static int bestScore = 0; // recordul rămâne cât timp programul e pornit

    public QuizGame(ArrayList<Question> questions, boolean shuffle) {
        this.questions = new ArrayList<>(questions);
        if (shuffle) {
            Collections.shuffle(this.questions); // amestecăm întrebările
        }
    }

    public Question getCurrentQuestion() {
        return questions.get(currentIndex);
    }

    // verifică răspunsul, adună punctul și trece la următoarea întrebare
    public boolean answer(int answerIndex) {
        boolean correct = getCurrentQuestion().isCorrect(answerIndex);
        if (correct) {
            score++;
        }
        currentIndex++;
        if (isFinished() && score > bestScore) {
            bestScore = score;
        }
        return correct;
    }

    public boolean isFinished() {
        return currentIndex >= questions.size();
    }

    public int getScore() {
        return score;
    }

    public int getTotal() {
        return questions.size();
    }

    public int getQuestionNumber() {
        return currentIndex + 1;
    }

    public static int getBestScore() {
        return bestScore;
    }
}
