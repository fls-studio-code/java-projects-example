package hangman;

import java.util.ArrayList;
import java.util.Random;

/**
 * Cuvintele jocului, pe categorii. Unele au diacritice (ă, â, î, ș, ț).
 */
public class WordBank {
    private final Random random = new Random();

    public String[] getCategories() {
        return new String[] {"Animale", "Fructe", "Orașe"};
    }

    public String[] getLevels() {
        return new String[] {"Ușor (cuvânt scurt)", "Greu (cuvânt lung)"};
    }

    private String[] wordsFor(String category) {
        if (category.equals("Animale")) {
            return new String[] {"pisică", "câine", "urs", "vulpe", "elefant", "crocodil", "veveriță"};
        } else if (category.equals("Fructe")) {
            return new String[] {"măr", "pară", "prună", "banană", "portocală", "căpșună"};
        } else {
            return new String[] {"iași", "bălți", "chișinău", "orhei", "cahul", "soroca", "ungheni"};
        }
    }

    // alege un cuvânt la întâmplare; ușor = max 5 litere, greu = mai mult de 5
    public String randomWord(String category, boolean hard) {
        ArrayList<String> suitable = new ArrayList<>();
        for (String w : wordsFor(category)) {
            if (hard && w.length() > 5) {
                suitable.add(w);
            } else if (!hard && w.length() <= 5) {
                suitable.add(w);
            }
        }
        return suitable.get(random.nextInt(suitable.size()));
    }
}
