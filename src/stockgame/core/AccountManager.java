package stockgame.core;

import stockgame.exceptions.InsufficientBalanceException;

public interface AccountManager {

    void newPlayer(String name);

    void buyShare(String playername, String shareName, int amount) throws InsufficientBalanceException;

    void sellShare(String playername, String shareName, int amount);

    void checkAsset(String name);

    void checkAllAssetsOfPlayer(String playername);

    long getShareValue(String name);

    String toString();

    void printAllAvailablePlayers();

    void printAllAvailableShares();

}
