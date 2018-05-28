package stockgame.exceptions;

@SuppressWarnings("serial")
public class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException() {
        super("Geld reicht nicht aus!");
    }
}

 
