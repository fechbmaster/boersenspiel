package stockgame.exceptions;

@SuppressWarnings("serial")
public class SellAmountNegativeException extends RuntimeException {

    public SellAmountNegativeException() {
        super("Negative Sell amount");
    }

}
