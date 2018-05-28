package stockgame.exceptions;

@SuppressWarnings("serial")
public class CashAccountTransactionException extends RuntimeException {

    public CashAccountTransactionException() {
        super("Transaction failed");
    }

}
