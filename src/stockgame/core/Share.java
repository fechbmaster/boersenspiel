package stockgame.core;

public class Share {
    private final String name;
    private long value;

    public Share(String name, long price) {
        this.name = name;
        this.value = price;
    }

    public long getValue() {
        return this.value;
    }

    public String getValueString() {
        return Long.toString(this.value);
    }

    public void setValue(long price) {
        this.value = price;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Die Aktie " + this.name + " hat einen Wert von " + this.value + "Cent";
    }

//    public boolean equals(String name) { // ausbessern! equals soll algemein �berschreiben werden
//    	if (this.name == name) {
//    		return true;
//    	} else {
//    		return false;
//    	}
//    }
//    
//    public boolean equals(Share shareToCompare) { //equals methode string
//    	if (this.name == shareToCompare.getName()) {
//    		return true;
//    	} else {
//    		return false;
//    	}
//    }

    public boolean equals(Object shareToCompare) {
        if (shareToCompare instanceof Share) {
            if (this.name.equals(((Share) shareToCompare).getName()))
                return true;
        }
        return false;
    }
}