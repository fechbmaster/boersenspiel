package stockgame.exceptions;

@SuppressWarnings("serial")
public class ShareElementNotFoundException extends RuntimeException {

    public ShareElementNotFoundException() {
        super("Share element not found");
    }
}
