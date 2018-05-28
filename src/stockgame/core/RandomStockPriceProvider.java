package stockgame.core;

import java.util.Random;


public class RandomStockPriceProvider extends StockPriceProvider {

    private long valuechange;

//	/**
//	 * Sets completely random new Share.value
//	 */
//	public void updateShareRate(Share share){
//		if (share != null){
//		valuechange=((long)(Math.random()*10000));
//		share.setValue(valuechange);
//		}
//		else return;
//	}

    /**
     * Increases or reduces the Share.value by 0-1000
     */
    public void updateShareRate(Share share) {
        Random randomNumber = new Random();
        if (share != null) {
            valuechange = randomNumber.nextInt(1000);
            if (new Random().nextInt(2) % 2 == 0) {
                share.setValue(share.getValue() + valuechange);
            } else {
                if (share.getValue() - valuechange <= 0) {
                } // we don't want the companies to get broke
                else {
                    share.setValue(share.getValue() - valuechange);
                }
            }
        }
    }
}

