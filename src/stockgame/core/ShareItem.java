package stockgame.core;

public class ShareItem extends Asset {

    private final Share shareItem;
    private int shareAmount;
    private long initialValue;

    public int getAmount() {
        return this.shareAmount;
    }

    @Override
    public String toString() {
        return this.getName() + " mit " + this.shareAmount + " Elementen. Der Aktuelle Wert betr�gt " + getTotalValue() + ", gekauft wurde das Paket f�r " + this.initialValue + "cent";
    }

    protected Share getShare() {
        return this.shareItem;
    }

    protected void changeShareAmount(int amount) {
        this.shareAmount += amount;
    }

    protected ShareItem(Share shareItem, int amount) {

        this.shareItem = shareItem;
        this.shareAmount = amount;

        this.name = this.shareItem.getName() + " Aktienpaket";

        this.initialValue = this.shareItem.getValue() * this.shareAmount;
    }

    protected long getTotalValue() {
        return (shareItem.getValue() * shareAmount);
    }
}

