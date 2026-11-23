package library;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Biblioteca: lista de cărți și operațiile cu ea.
 */
public class Library {
    private final ArrayList<Book> books = new ArrayList<>();

    public Library() {
        // câteva cărți încă de la pornire
        books.add(new Book("Amintiri din copilărie", "Ion Creangă", "Carte"));
        books.add(new Book("Harap-Alb", "Ion Creangă", "Carte"));
        books.add(new Book("Micul prinț", "Antoine de Saint-Exupéry", "Carte"));
        books.add(new Book("Luceafărul", "Mihai Eminescu", "Carte"));
        books.add(new Book("National Geographic nr. 5", "Redacția", "Revistă"));
    }

    public ArrayList<Book> getAll() {
        return books;
    }

    public void addBook(String title, String author, String type) {
        if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Titlul și autorul nu pot fi goale.");
        }
        books.add(new Book(title.trim(), author.trim(), type));
    }

    // căutare după o parte din titlu
    public ArrayList<Book> searchByTitle(String text) {
        ArrayList<Book> result = new ArrayList<>();
        String lower = text.trim().toLowerCase();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(lower)) {
                result.add(b);
            }
        }
        return result;
    }

    // împrumută cartea; întoarce false dacă e deja împrumutată
    public boolean lend(Book book) {
        if (!book.isAvailable()) {
            return false;
        }
        book.setAvailable(false);
        return true;
    }

    // returnează cartea; întoarce false dacă nu era împrumutată
    public boolean giveBack(Book book) {
        if (book.isAvailable()) {
            return false;
        }
        book.setAvailable(true);
        return true;
    }

    public void sortByTitle() {
        Collections.sort(books);
    }

    public int countAvailable() {
        int count = 0;
        for (Book b : books) {
            if (b.isAvailable()) {
                count++;
            }
        }
        return count;
    }
}
