package util.ui.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 4/24/13
 * Time: 11:18 AM
 */
public class CommandNotFoundException extends RuntimeException {         //TODO Nicht vergessen beim try-catch"en"
    public CommandNotFoundException() {
        super("CommandNotFound");
    }
}
