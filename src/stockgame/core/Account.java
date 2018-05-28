package stockgame.core;

import stockgame.exceptions.InsufficientBalanceException;

public class Account {
    private final CashAccount ca;
    private final String accountName;
    private final ShareDeposit sd;

    /**
     * Constructor
     *
     * @param name            is the final Representation of this players Account
     * @param startingBalance specifies the starting value of the associated CashAccount
     */
    protected Account(String name, long startingBalance) {
        this.accountName = name;
        this.ca = new CashAccount(name, startingBalance);
        this.sd = new ShareDeposit(name);
    }

    /**
     * Try to buy a share
     *
     * @param shareToAdd reference to the share that will be bought.
     * @param amount     specifies how many shares shold be bought
     * @return success via true / false boolean
     * @throws InsufficientBalanceException if Balance is Insufficient
     */
    protected boolean buyShare(Share shareToAdd, int amount) throws InsufficientBalanceException {
        return sd.addShare(shareToAdd, amount, this.ca);
    }

    /**
     * Sell a specified Share of this Account
     *
     * @param shareToSell Share reference
     * @param amount      the number of shares to be sold
     */
    protected void sellShare(Share shareToSell, int amount) {
        sd.reduceShare(shareToSell, amount, this.ca);
    }

    /**
     * Returns the current Balance
     *
     * @return current balance of the associated CashAccount as a long value in cents
     */
    protected long getCashBalance() {
        return ca.getBalance();
    }

    /**
     * @return the name of this element
     */
    protected String getName() {
        return this.accountName;
    }

    /**
     * Returns the String representation of a ShareDeposit
     *
     * @return String
     */
    protected ShareDeposit getShareDeposit() {
        return this.sd;
    }

    protected CashAccount getCashAccount() {
        return this.ca;
    }


//	@Override
//	public boolean equals(Object obj) { //Test von barni, muss in jeder Klasse eine equal eingef�hrt werden? 
//		Account account;				//Ist nur ein Testmuster f�r mich...
//    	if (obj instanceof Account) {	//Antwort von Chris :D : LAUT Abnahme 1. Termin => Wenn wir sie Nutzen, JA!
//    		account=(Account) obj;
//    	} else {
//    		return false;
//    	}
//    	if (!(this.ca.getBalance() == account.ca.getBalance())){
//    		return false;
//    	}
//    	if (!(this.ca.getName() == account.ca.getName())){
//    		return false;
//    	}
//    	if (!(this.playerName == account.playerName)){
//    		return false;
//    	}
//    	if (!(this.sd.getName() == account.sd.getName())){
//    		return false;
//    	}
//    	if (!(this.sd.getDepotValue() == account.sd.getDepotValue())){
//    		return false;
//    	}
//    	return true;
//    }

    /**
     * Check if name of two elements is Equal.
     *
     * @return true or false
     */
    public boolean equals(Object accountNameToCompare) {
        if (accountNameToCompare instanceof Account) {
            if (this.accountName.equals(((Account) accountNameToCompare).getName())) {
                return true;
            }
        }
        return false;
    }
}
