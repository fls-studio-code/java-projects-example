package bank;

/**
 * Excepția noastră: nu sunt destui bani pe cont.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
