package bank;

import java.util.ArrayList;

/**
 * Banca păstrează lista de conturi.
 */
public class Bank {
    private final ArrayList<Account> accounts = new ArrayList<>();

    public Bank() {
        // conturi de test (PIN-urile sunt în README)
        accounts.add(new Account("Ana", "1234", 500));
        accounts.add(new Account("Ion", "1111", 200));
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
