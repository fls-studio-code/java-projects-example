package grades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Catalogul: lista de note, media, cea mai bună / slabă notă, raportul, salvarea în fișier.
 */
public class GradeBook {
    public static final double PASS_AVERAGE = 5.0;

    private final ArrayList<Grade> grades = new ArrayList<>();

    // adaugă nota doar dacă este între 1 și 10
    public void addGrade(String subject, int value) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Scrie materia.");
        }
        if (value < 1 || value > 10) {
            throw new IllegalArgumentException("Nota trebuie să fie între 1 și 10.");
        }
        grades.add(new Grade(subject.trim(), value));
    }

    public void removeGrade(int index) {
        grades.remove(index);
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public boolean isEmpty() {
        return grades.isEmpty();
    }

    public double getAverage() {
        if (grades.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Grade g : grades) {
            sum += g.getValue();
        }
        return sum * 1.0 / grades.size();
    }

    // media rotunjită la o zecimală, ca text: "8.5"
    public String getAverageText() {
        return String.format("%.1f", getAverage()).replace(',', '.');
    }

    public boolean isPassed() {
        return !grades.isEmpty() && getAverage() >= PASS_AVERAGE;
    }

    public Grade getBest() {
        Grade best = null;
        for (Grade g : grades) {
            if (best == null || g.getValue() > best.getValue()) {
                best = g;
            }
        }
        return best;
    }

    public Grade getWorst() {
        Grade worst = null;
        for (Grade g : grades) {
            if (worst == null || g.getValue() < worst.getValue()) {
                worst = g;
            }
        }
        return worst;
    }

    // caută notele la o materie (o parte din nume e suficientă)
    public ArrayList<Grade> searchBySubject(String text) {
        ArrayList<Grade> result = new ArrayList<>();
        String lower = text.trim().toLowerCase();
        for (Grade g : grades) {
            if (g.getSubject().toLowerCase().contains(lower)) {
                result.add(g);
            }
        }
        return result;
    }

    // raport scurt: lista + media + promovat / nepromovat
    public String getReport() {
        if (grades.isEmpty()) {
            return "Nu sunt note.";
        }
        StringBuilder sb = new StringBuilder("RAPORT\n\n");
        for (Grade g : grades) {
            sb.append("• ").append(g).append("\n");
        }
        sb.append("\nMedia: ").append(getAverageText());
        sb.append("\nCea mai bună: ").append(getBest());
        sb.append("\nCea mai slabă: ").append(getWorst());
        sb.append("\nRezultat: ").append(isPassed() ? "PROMOVAT" : "NEPROMOVAT");
        return sb.toString();
    }

    // salvare în fișier text: un rând = "materie;notă"
    public void saveToFile(String fileName) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (Grade g : grades) {
                out.println(g.getSubject() + ";" + g.getValue());
            }
        }
    }

    public void loadFromFile(String fileName) throws IOException {
        ArrayList<Grade> loaded = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    try {
                        int value = Integer.parseInt(parts[1].trim());
                        if (value >= 1 && value <= 10) {
                            loaded.add(new Grade(parts[0].trim(), value));
                        }
                    } catch (NumberFormatException ex) {
                        // rândul greșit îl sărim
                    }
                }
            }
        }
        grades.clear();
        grades.addAll(loaded);
    }
}
