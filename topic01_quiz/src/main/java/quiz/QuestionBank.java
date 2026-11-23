package quiz;

import java.util.ArrayList;

/**
 * Toate întrebările jocului, pe categorii.
 */
public class QuestionBank {
    public static final String ALL = "Toate";

    private final ArrayList<Question> questions = new ArrayList<>();

    public QuestionBank() {
        // Java
        questions.add(new Question("Java", "Ce tip de date păstrează numere întregi?", "String", "int", "boolean", 1));
        questions.add(new Question("Java", "Ce cuvânt creează un obiect nou?", "new", "class", "void", 0));
        questions.add(new Question("Java", "Cum se numește lista care crește singură?", "Array", "Scanner", "ArrayList", 2));
        questions.add(new Question("Java", "Ce metodă pornește programul?", "start()", "main()", "run()", 1));
        // Istorie
        questions.add(new Question("Istorie", "Cine a fost domnitorul Moldovei între 1457 și 1504?", "Ștefan cel Mare", "Mircea cel Bătrân", "Vlad Țepeș", 0));
        questions.add(new Question("Istorie", "În ce an a început Al Doilea Război Mondial?", "1914", "1939", "1945", 1));
        questions.add(new Question("Istorie", "Ce civilizație a construit piramidele de la Giza?", "Romanii", "Grecii", "Egiptenii", 2));
        // Sport
        questions.add(new Question("Sport", "Câți jucători are o echipă de fotbal pe teren?", "11", "9", "7", 0));
        questions.add(new Question("Sport", "În ce sport se folosește o rachetă și un fluturaș?", "Tenis", "Badminton", "Golf", 1));
        questions.add(new Question("Sport", "La câți ani se țin Jocurile Olimpice de vară?", "2", "3", "4", 2));
    }

    // lista de categorii pentru meniul de start
    public String[] getCategories() {
        return new String[] {ALL, "Java", "Istorie", "Sport"};
    }

    // întoarce întrebările dintr-o categorie (sau toate)
    public ArrayList<Question> getQuestions(String category) {
        ArrayList<Question> result = new ArrayList<>();
        for (Question q : questions) {
            if (category.equals(ALL) || q.getCategory().equals(category)) {
                result.add(q);
            }
        }
        return result;
    }
}
