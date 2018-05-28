package stockgame.core;

import stockgame.exceptions.DuplicateShareElementException;

import java.util.Timer;
import java.util.TimerTask;


abstract class StockPriceProvider implements StockPriceInfo {
    private Share[] availableShares = new Share[5];
    private int currShareArrayIndex = 0; //Same thing here.

    public StockPriceProvider() {

    }

    @Override
    public boolean isShareListed(String shareName) {
        if (findShare(shareName) == null) {
            return true;
        }
        return false;
    }


    @Override
    public long getCurrentShareRate(String shareName) {
        Share shareToFind = findShare(shareName);
        return shareToFind.getValue();
    }

    @Override
    public Share[] getAllSharesAsSnapShot() {
        Share[] snapshot = new Share[currShareArrayIndex];
        for (int i = 0; i < currShareArrayIndex; i++) {
            snapshot[i] = new Share(availableShares[i].getName(), availableShares[i].getValue());
        }
        return snapshot;
    }

    protected void updateShareRates() {
        for (int i = 0; i < availableShares.length; i++) {
            updateShareRate(availableShares[i]);
        }
    }

    abstract void updateShareRate(Share share);

    //	public void startUpdate(Timer ticker){
    public void startUpdate() {
        Timer timer = StockTimer.getInstance();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                updateShareRates();
            }
        }, 2000, 1000);
    }

    public Share getShare(String name) {
        return findShare(name);
    }


    /**
     * Method adds new available Shares the Player can buy to an Array and checks if Share already
     * exists
     *
     * @param name  The Name of the new Share
     * @param value The course of the new Share
     */
    public void newShare(String name, long value) {
        if (findShare(name) != null) {
            throw new DuplicateShareElementException();
        }

        availableShares[currShareArrayIndex++] = new Share(name, value);
        resizeShareArray();
    }

    /**
     * Method checks where a Share is initialized in Array and if it exists,
     *
     * @param name is the name of the Share
     * @return returns Array position and -1 if not found
     */
    private Share findShare(String name) {
        int count = 0;
        while (count < currShareArrayIndex) {
            if (availableShares[count].getName().equals(name)) {
                return availableShares[count];
            }
            count++;
        }
        return null;
    }

    /**
     * Method adds Array Spaces if Array is full
     */
    private void resizeShareArray() {
        if (this.currShareArrayIndex == availableShares.length - 1) {
            Share[] newArray = new Share[availableShares.length + 5];

            for (int i = 0; i < availableShares.length; i++) {
                newArray[i] = availableShares[i];
            }
            availableShares = newArray;
        }
    }

    public void printAllAvailableShares() {
        StringBuilder allShares = new StringBuilder("Die Verfuebaren Aktien sind: ");
        for (int i = 0; i < currShareArrayIndex; i++) {
            if (i > 0) {
                allShares.append(", ");
            }
            allShares.append(availableShares[i].getName());
        }
        System.out.println(allShares.toString());
    }

    /**
     * Method prints out the value of the Shares; checks if Share exists, too
     *
     * @param name is the name of the Share you want to check
     */
    public long getShareValue(String name) {
        Share checkme = findShare(name);
        if (checkme != null) {
            return checkme.getValue();
        } else {
            return 0;
        }
    }

    /**
     * Method prints out the String of an Share
     *
     * @param name of the Share you want to check
     */
    public void checkAsset(String name) {
        Share shareToFind = findShare(name);
        if (shareToFind != null) {
            System.out.println(shareToFind.toString());
        } else {
            System.out.println("Gesuchte Aktie nicht gefunden.");
        }
    }


}


