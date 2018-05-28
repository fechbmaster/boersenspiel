package stockgame.exceptions;

@SuppressWarnings("serial")
public class SellAmountTooLargeException extends RuntimeException {

    public SellAmountTooLargeException() {
        super("Trying to sell more than Account owns");
    }

}
