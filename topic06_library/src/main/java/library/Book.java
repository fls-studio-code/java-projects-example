package library;

/**
 * O carte sau o revistă din bibliotecă.
 */
public class Book implements Comparable<Book> {
    private final String title;
    private final String author;
    private final String type;    // "Carte" sau "Revistă"
    private boolean available = true;

    public Book(String title, String author, String type) {
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getStatusText() {
        return available ? "disponibilă" : "împrumutată";
    }

    // pentru sortare după titlu (fără să conteze literele mari/mici)
    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }
}
