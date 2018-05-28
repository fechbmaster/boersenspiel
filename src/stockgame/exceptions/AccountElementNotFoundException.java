package stockgame.exceptions;

@SuppressWarnings("serial")
public class AccountElementNotFoundException extends RuntimeException {

    public AccountElementNotFoundException() {
        super("Player element not found");
    }
}
