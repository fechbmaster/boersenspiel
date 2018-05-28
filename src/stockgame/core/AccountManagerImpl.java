package stockgame.core;

import stockgame.exceptions.AccountElementNotFoundException;
import stockgame.exceptions.DuplicatePlayerNameException;
import stockgame.exceptions.InsufficientBalanceException;
import stockgame.exceptions.ShareElementNotFoundException;

public class AccountManagerImpl implements AccountManager {

    private int currPlayerArrayIndex = 0; //Always points at the next empty index to fill
    private Account[] playersArray = new Account[5];
    //	protected static RandomStockPriceProvider provider = new RandomStockPriceProvider();
//	protected static ConstStockPriceProvider provider = new ConstStockPriceProvider();
    private final StockPriceInfo provider;
    private final long startingMoney = 100000;


    public AccountManagerImpl(StockPriceInfo stockinfo) {
        this.provider = stockinfo;
        provider.startUpdate();
    }


    /**
     * Method adds new Player to Array and checks if the Player already exists
     *
     * @param name          The name of the new Player
     * @param startingMoney The value of the CashAmount the player holds at the beginning
     */
    public void newPlayer(String name) {
        if (findPlayer(name) != null) {
            throw new DuplicatePlayerNameException();
        }

        playersArray[currPlayerArrayIndex++] = new Account(name, startingMoney);
        resizePlayerArray();
    }

    /**
     * Method adds Array Space if Array is full
     */
    private void resizePlayerArray() {
        if (this.currPlayerArrayIndex == playersArray.length - 1) {
            Account[] newArray = new Account[playersArray.length + 5];

            for (int i = 0; i < playersArray.length; i++) {
                newArray[i] = playersArray[i];
            }

            playersArray = newArray;
        }
    }

    public void newShare(String name, long value) {
        provider.newShare(name, value);
    }


    /**
     * Method gives Functionality to the Player to buy Shares, checks if Share and Player exists,
     * if not throws ShareElementNotFoundException and InsufficientBalanceException if the Player
     * can't afford the Shares
     *
     * @param playerName is the Name of the Player that wants to buy Shares
     * @param shareName  is the name of the Share the Player wants to buy
     * @param amount     is the value of how many Shares the Player wants to purchase
     */
    public void buyShare(String playerName, String shareName, int amount) throws InsufficientBalanceException {

        Share shareToBuy = provider.getShare(shareName);
        Account searchedPlayer = findPlayer(playerName);

        if (shareToBuy == null) {  //findShare returns null reference if not found
            throw new ShareElementNotFoundException();
        } else if (searchedPlayer == null) {  //changed to null
            throw new AccountElementNotFoundException();
        } else {
            searchedPlayer.getCashAccount().reduceCredit(shareToBuy.getValue() * amount);
            searchedPlayer.buyShare(shareToBuy, amount);
        }
    }


    /**
     * Method prints out all available Shares
     */


    public void printAllAvailablePlayers() {
        StringBuilder allPlayers = new StringBuilder("Die vorhandenen Spieler sind: ");
        for (int i = 0; i < currPlayerArrayIndex; i++) {
            if (i > 0) {
                allPlayers.append(", ");
            }
            allPlayers.append(playersArray[i].getName());
        }
        System.out.println(allPlayers.toString());
    }


    /**
     * Method prints out all Assets a Player holds on, checks if Player exists, too
     *
     * @param playername is the Name of the Player you want to check
     */
    public void checkAllAssetsOfPlayer(String playername) {
        Account accToCheck = findPlayer(playername);
        if (accToCheck != null) {
            System.out.println("Kontostand: " + accToCheck.getCashBalance()
                    + ", Depotinhalt: " + accToCheck.getShareDeposit().toString());
        } else {
            throw new AccountElementNotFoundException();
        }
    }


    /**
     * Method gives the functionality to the Player to sellShares; checks if Player and Share
     * exists, too
     *
     * @param playerName the name of the Player that wants to sell
     * @param shareName  the name of the Share the Player wants to sell
     * @param amount     the amount of Shares the Player wants to sell
     */
    public void sellShare(String playerName, String shareName, int amount) {

        Share shareToFind = provider.getShare(shareName);
        Account searchedPlayer = findPlayer(playerName);
        if (shareToFind == null) {
            throw new ShareElementNotFoundException();
        } else if (searchedPlayer == null) {
            throw new AccountElementNotFoundException();
        } else {
            long totalValueOfToBeSoldShares = shareToFind.getValue() * amount;
            searchedPlayer.getCashAccount().addCredit(totalValueOfToBeSoldShares);
            searchedPlayer.sellShare(shareToFind, amount);
        }
    }

    /**
     * Method finds PlayerIndex in Array and if he exists, too
     *
     * @param name is the name of the Player
     * @return returns Array position and -1 if not found
     */
    private Account findPlayer(String name) {
        int count = 0;
        while (count < currPlayerArrayIndex) {
            if (playersArray[count].getName().equals(name)) {
                return playersArray[count];
            }
            count++;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Dieser AccountManager verwaltet " + (currPlayerArrayIndex - 1) + " Spieler.";

    }

    @Override
    public void checkAsset(String name) {
        provider.checkAsset(name);

    }

    @Override
    public long getShareValue(String name) {
        return provider.getShareValue(name);
    }


    public void printAllAvailableShares() {
        provider.printAllAvailableShares();
    }

    public void startUpdate() {
        provider.startUpdate();

    }


}
