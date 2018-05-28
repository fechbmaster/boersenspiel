package stockgame.core;

import stockgame.exceptions.CashAccountTransactionException;
import stockgame.exceptions.InsufficientBalanceException;

public final class CashAccount extends Asset {

    private long currentbalance = 0;

    public CashAccount(String name, long startingBalance) {
        this.name = name;
        this.currentbalance = startingBalance;
    }

    public String toString() {
        return "Das Konto " + this.getName() + " hat folgenden Kontostand: " + this.getBalance();
    }

    public void addCredit(long amount) {
        if (Long.MAX_VALUE - amount >= getBalance()) {
            setBalance(this.getBalance() + amount);
        } else {
            throw new CashAccountTransactionException();
        }
    }

    public void reduceCredit(long amount) throws InsufficientBalanceException {
        if (amount < getBalance()) { //Check if Balance would be below 0
            setBalance(this.getBalance() - amount);
        } else {
            throw new InsufficientBalanceException();
        }

    }

    public long getBalance() {
        return this.currentbalance;

    }

    private void setBalance(long newBalance) {
        this.currentbalance = newBalance;
    }
}