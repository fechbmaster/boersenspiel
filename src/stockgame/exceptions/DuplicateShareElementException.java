package stockgame.exceptions;

@SuppressWarnings("serial")
public class DuplicateShareElementException extends RuntimeException {

    public DuplicateShareElementException() {
        super("Duplicate Share existing in ShareArray");
    }

}
