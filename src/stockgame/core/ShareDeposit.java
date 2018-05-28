package stockgame.core;

import stockgame.exceptions.InsufficientBalanceException;
import stockgame.exceptions.SellAmountNegativeException;
import stockgame.exceptions.SellAmountTooLargeException;
import stockgame.exceptions.ShareNotOwnedException;

public class ShareDeposit extends Asset {

    private ShareItem[] shareItemArray = new ShareItem[5];
    private int currentTopArrayIndex = -1;

    public ShareDeposit(String name) {
        this.name = "Deposit von " + name;
    }

    public boolean addShare(Share shareToAdd, int buyAmount, CashAccount cashAcc) throws InsufficientBalanceException {

        if (buyAmount < 1) {
            System.out.println("Fehlerhafte Eingabe! (Anzahl < 0)");
            return false;
        }

        int shareArrayIndex = searchForEqualElementIndex(shareToAdd);

        if (shareArrayIndex == -1) { //share hasn't been found..
            addArrayElement(shareToAdd, buyAmount);
        } else {
            shareItemArray[shareArrayIndex].changeShareAmount(buyAmount);
        }
        return true;

    }

    private void addArrayElement(Share shareToAdd, int buyAmount) {

        if (this.currentTopArrayIndex == shareItemArray.length - 1) {
            ShareItem[] newArray = new ShareItem[shareItemArray.length + 5];

            for (int i = 0; i < shareItemArray.length; i++) {
                newArray[i] = shareItemArray[i];
            }

            shareItemArray = newArray;
        }
        currentTopArrayIndex++;
        shareItemArray[currentTopArrayIndex] = new ShareItem(shareToAdd, buyAmount);
    }

    public void reduceShare(Share shareToRemove, int sellAmount, CashAccount cashAcc) {
        if (sellAmount < 1) {
            throw new SellAmountNegativeException();
        }

        int shareIndex = searchForEqualElementIndex(shareToRemove);
        int ownedAmountOfShares = 0;

        if (shareIndex >= 0) {
            ownedAmountOfShares = shareItemArray[shareIndex].getAmount();
        } else {
            throw new ShareNotOwnedException();
        }


        if (ownedAmountOfShares < sellAmount) {
            throw new SellAmountTooLargeException();
        }

        if (ownedAmountOfShares == sellAmount) {
            deleteArrayItem(shareIndex);
        } else {
            shareItemArray[shareIndex].changeShareAmount(0 - sellAmount);
        }
    }

    private void deleteArrayItem(int shareIndex) {
        shareItemArray[shareIndex] = shareItemArray[currentTopArrayIndex - 1];
        shareItemArray[currentTopArrayIndex] = null;
        currentTopArrayIndex--;

        if (shareItemArray.length - currentTopArrayIndex > 5) {
            ShareItem[] newArray = new ShareItem[shareItemArray.length - 5];
            int helpCnt = 0;
            for (int i = 0; i < shareItemArray.length; i++) {
                if (shareItemArray[i] != null) {
                    newArray[helpCnt] = shareItemArray[i];
                    helpCnt++;
                }
            }
            shareItemArray = newArray;
        }

    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        description.append("Das Depot beinhaltet");
        if (currentTopArrayIndex < 0) {
            description.append(" keine Aktien");
            return description.toString();
        }

        description.append(" folgende Aktienpakete: \r\n");

        for (int i = 0; i <= currentTopArrayIndex; i++) {
            if (i > 0) {
                description.append(", ");
            }
            if (shareItemArray[i] != null) {
                description.append(shareItemArray[i].getName()).append(" (").append(shareItemArray[i].getTotalValue()).append(" cent)");
            }

        }
        description.append("\r\nDer Gesamtwert des Depots betraegt: ").append(getDepotValue());   // TODO GET RID OF THAT! :)
        return description.toString();
    }

    public long getDepotValue() {
        long help = 0;
        for (int i = 0; i <= currentTopArrayIndex; i++) {
            help += shareItemArray[i].getTotalValue();
        }
        return help;
    }

    private int searchForEqualElementIndex(Share checkMe) {
        for (int i = 0; i <= currentTopArrayIndex; i++) {
            if (checkMe.equals(shareItemArray[i].getShare())) {
                return i;
            }
        }
        return -1;
    }

}   