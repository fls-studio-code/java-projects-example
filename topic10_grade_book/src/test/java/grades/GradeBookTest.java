package grades;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GradeBookTest {

    @Test
    void averageBestWorst() {
        GradeBook book = new GradeBook();
        book.addGrade("Mate", 8);
        book.addGrade("Info", 9);
        assertEquals("8.5", book.getAverageText());
        assertEquals("Info", book.getBest().getSubject());
        assertEquals("Mate", book.getWorst().getSubject());
        assertTrue(book.isPassed());
    }

    @Test
    void onlyOneToTenAccepted() {
        GradeBook book = new GradeBook();
        assertThrows(IllegalArgumentException.class, () -> book.addGrade("Mate", 0));
        assertThrows(IllegalArgumentException.class, () -> book.addGrade("Mate", 11));
        assertThrows(IllegalArgumentException.class, () -> book.addGrade(" ", 5));
        assertTrue(book.isEmpty());
    }

    @Test
    void searchAndReport() {
        GradeBook book = new GradeBook();
        book.addGrade("Matematica", 4);
        book.addGrade("Româna", 5);
        assertEquals(1, book.searchBySubject("mate").size());
        assertFalse(book.isPassed()); // media 4.5
        assertTrue(book.getReport().contains("NEPROMOVAT"));
    }

    @Test
    void saveAndLoad(@TempDir Path dir) throws Exception {
        String file = new File(dir.toFile(), "note.txt").getPath();
        GradeBook book = new GradeBook();
        book.addGrade("Mate", 7);
        book.addGrade("Info", 10);
        book.saveToFile(file);

        GradeBook other = new GradeBook();
        other.loadFromFile(file);
        assertEquals(2, other.getGrades().size());
        assertEquals("8.5", other.getAverageText());
    }
}
