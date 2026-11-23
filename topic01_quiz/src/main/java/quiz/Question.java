package quiz;

/**
 * O întrebare: textul, 3 variante (a / b / c) și indexul răspunsului corect.
 */
public class Question {
    private final String category;
    private final String text;
    private final String[] options;
    private final int correctIndex; // 0 = a, 1 = b, 2 = c

    public Question(String category, String text, String optionA, String optionB, String optionC, int correctIndex) {
        this.category = category;
        this.text = text;
        this.options = new String[] {optionA, optionB, optionC};
        this.correctIndex = correctIndex;
    }

    public String getCategory() {
        return category;
    }

    public String getText() {
        return text;
    }

    public String getOption(int index) {
        return options[index];
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    // verificăm dacă răspunsul ales este corect
    public boolean isCorrect(int answerIndex) {
        return answerIndex == correctIndex;
    }

    public String getCorrectText() {
        return options[correctIndex];
    }
}
