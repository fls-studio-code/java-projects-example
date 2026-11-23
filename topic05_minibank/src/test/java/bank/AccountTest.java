package bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void depositAndWithdraw() throws Exception {
        Account a = new Account("Ana", "1234", 100);
        a.deposit(50);
        a.withdraw(30);
        assertEquals(120, a.getBalance(), 0.001);
        assertEquals(3, a.getHistory().size());
    }

    @Test
    void cannotWithdrawMoreThanBalance() {
        Account a = new Account("Ana", "1234", 100);
        assertThrows(InsufficientFundsException.class, () -> a.withdraw(500));
        assertThrows(IllegalArgumentException.class, () -> a.deposit(-5));
        assertEquals(100, a.getBalance(), 0.001);
    }

    @Test
    void blockedAfterThreeWrongPins() {
        Account a = new Account("Ana", "1234", 100);
        assertFalse(a.checkPin("0000"));
        assertFalse(a.checkPin("0000"));
        assertFalse(a.checkPin("0000"));
        assertTrue(a.isBlocked());
        assertFalse(a.checkPin("1234")); // chiar și PIN-ul corect nu mai merge
    }

    @Test
    void transferAndChangePin() throws Exception {
        Account a = new Account("Ana", "1234", 100);
        Account b = new Account("Ion", "1111", 0);
        a.transferTo(b, 40);
        assertEquals(60, a.getBalance(), 0.001);
        assertEquals(40, b.getBalance(), 0.001);
        assertThrows(IllegalArgumentException.class, () -> a.changePin("1234", "12a4"));
        a.changePin("1234", "4321");
        assertTrue(a.checkPin("4321"));
    }
}
