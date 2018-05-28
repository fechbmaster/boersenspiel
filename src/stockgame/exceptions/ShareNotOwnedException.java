package stockgame.exceptions;

@SuppressWarnings("serial")
public class ShareNotOwnedException extends RuntimeException {

    public ShareNotOwnedException() {
        super("Account does not posess a Share of this kind");
    }

}
