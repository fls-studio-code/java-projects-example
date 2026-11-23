package bank;

import java.util.ArrayList;

/**
 * Contul bancar: proprietar, PIN, sold și istoricul operațiilor.
 */
public class Account {
    public static final int MAX_PIN_ATTEMPTS = 3;

    private final String owner;
    private String pin;
    private double balance;
    private int failedAttempts = 0;
    private final ArrayList<String> history = new ArrayList<>();

    public Account(String owner, String pin, double balance) {
        this.owner = owner;
        this.pin = pin;
        this.balance = balance;
        history.add("Cont deschis cu " + balance + " lei");
    }

    // verificăm PIN-ul; după 3 greșeli contul se blochează
    public boolean checkPin(String enteredPin) {
        if (isBlocked()) {
            return false;
        }
        if (pin.equals(enteredPin)) {
            failedAttempts = 0;
            return true;
        }
        failedAttempts++;
        return false;
    }

    public boolean isBlocked() {
        return failedAttempts >= MAX_PIN_ATTEMPTS;
    }

    public int getAttemptsLeft() {
        return MAX_PIN_ATTEMPTS - failedAttempts;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Suma trebuie să fie mai mare decât 0.");
        }
        balance += amount;
        history.add("Depunere: +" + amount + " lei");
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Suma trebuie să fie mai mare decât 0.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Fonduri insuficiente! Sold: " + balance + " lei");
        }
        balance -= amount;
        history.add("Retragere: -" + amount + " lei");
    }

    // transfer către alt cont
    public void transferTo(Account other, double amount) throws InsufficientFundsException {
        if (other == this) {
            throw new IllegalArgumentException("Nu poți transfera în același cont.");
        }
        withdraw(amount);
        other.deposit(amount);
        history.add("  (transfer către " + other.getOwner() + ")");
        other.history.add("  (transfer de la " + owner + ")");
    }

    public void changePin(String oldPin, String newPin) {
        if (!pin.equals(oldPin)) {
            throw new IllegalArgumentException("PIN-ul vechi este greșit.");
        }
        if (newPin.length() != 4) {
            throw new IllegalArgumentException("PIN-ul nou trebuie să aibă exact 4 cifre.");
        }
        for (int i = 0; i < newPin.length(); i++) {
            if (!Character.isDigit(newPin.charAt(i))) {
                throw new IllegalArgumentException("PIN-ul poate avea doar cifre.");
            }
        }
        pin = newPin;
        history.add("PIN schimbat");
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return owner; // așa apare în lista de conturi
    }
}
