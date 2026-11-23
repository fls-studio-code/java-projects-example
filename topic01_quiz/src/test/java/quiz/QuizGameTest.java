package quiz;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuizGameTest {

    @Test
    void bankHasAtLeastFiveQuestions() {
        QuestionBank bank = new QuestionBank();
        assertTrue(bank.getQuestions(QuestionBank.ALL).size() >= 5);
        assertEquals(4, bank.getQuestions("Java").size());
    }

    @Test
    void scoreCountsOnlyCorrectAnswers() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question("T", "1+1?", "2", "3", "4", 0));
        list.add(new Question("T", "2+2?", "3", "4", "5", 1));
        QuizGame game = new QuizGame(list, false);
        assertTrue(game.answer(0));   // corect
        assertFalse(game.answer(2));  // greșit
        assertTrue(game.isFinished());
        assertEquals(1, game.getScore());
        assertEquals(2, game.getTotal());
        assertTrue(QuizGame.getBestScore() >= 1);
    }
}
