package stockgame.exceptions;

@SuppressWarnings("serial")
public class DuplicatePlayerNameException extends RuntimeException {

    public DuplicatePlayerNameException() {
        super("Duplicate player name");
    }

}
