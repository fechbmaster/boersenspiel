package stockgame.core;


public interface StockPriceInfo {

    boolean isShareListed(String shareName);

    long getCurrentShareRate(String shareName);

    Share[] getAllSharesAsSnapShot();

    Share getShare(String shareName);

    void newShare(String name, long value);

    void printAllAvailableShares();

    long getShareValue(String name);

    void checkAsset(String name);

    void startUpdate();
}


	


