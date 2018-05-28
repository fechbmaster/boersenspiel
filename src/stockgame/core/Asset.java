package stockgame.core;

public abstract class Asset {

    protected String name;
//	private long value;

    public String getName() {
        return name;
    }


    @Override
    public abstract String toString();

    public boolean equals(Object assetToCompare) {

        if (assetToCompare instanceof Asset) {
            if (this.name.equals(((Asset) assetToCompare).getName()))
                return true;
        }
        return false;
    }
}