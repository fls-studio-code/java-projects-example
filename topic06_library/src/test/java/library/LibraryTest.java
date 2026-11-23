package library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void startsWithAtLeastThreeBooks() {
        assertTrue(new Library().getAll().size() >= 3);
    }

    @Test
    void searchIgnoresCase() {
        Library lib = new Library();
        assertEquals(1, lib.searchByTitle("harap").size());
        assertEquals(0, lib.searchByTitle("xyz").size());
    }

    @Test
    void lendAndReturnChangeStatus() {
        Library lib = new Library();
        Book b = lib.getAll().get(0);
        assertTrue(lib.lend(b));
        assertFalse(b.isAvailable());
        assertFalse(lib.lend(b)); // nu poți împrumuta de două ori
        assertTrue(lib.giveBack(b));
        assertTrue(b.isAvailable());
    }

    @Test
    void addValidatesAndSortWorks() {
        Library lib = new Library();
        assertThrows(IllegalArgumentException.class, () -> lib.addBook(" ", "A", "Carte"));
        lib.addBook("Abecedar", "Autor", "Carte");
        lib.sortByTitle();
        assertEquals("Abecedar", lib.getAll().get(0).getTitle());
    }
}
