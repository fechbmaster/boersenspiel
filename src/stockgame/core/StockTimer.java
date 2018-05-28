package stockgame.core;

import java.util.Timer;

public class StockTimer {
    private static Timer ticker;

    public StockTimer() {
    }

    public static Timer getInstance() {
        if (ticker == null)
            ticker = new Timer(true);
        return ticker;
    }
}
