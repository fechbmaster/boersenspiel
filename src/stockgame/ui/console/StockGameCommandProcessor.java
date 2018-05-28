package stockgame.ui.console;

import stockgame.core.AccountManager;
import stockgame.core.StockTimer;
import stockgame.exceptions.AccountElementNotFoundException;
import stockgame.exceptions.InsufficientBalanceException;
import stockgame.exceptions.ShareElementNotFoundException;
import util.ui.CommandDescriptor;
import util.ui.shell.CommandScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

public class StockGameCommandProcessor {
    private BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
    private PrintWriter shellWriter = new PrintWriter(System.out, true);
    private AccountManager accountManager;
    private Timer ticker;


    public StockGameCommandProcessor(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void process() {
        CommandScanner commandScanner = new CommandScanner(StockGameCommandType.values(), shellReader);
        shellWriter.println("hello :)");

        while (true) { //die Schleife �ber all Kommandos, jeweils ein Durchlauf pro Eingabezeile



            //...
            CommandDescriptor command = new CommandDescriptor();
            try {
                command = commandScanner.getCommand();



            Object[] params = command.getParams();
            commandScanner.fillInCommandDesc(command);
            //...


            StockGameCommandType commandType = (StockGameCommandType) command.getCommandType();

            switch (commandType) {
                case EXIT:
                    shellWriter.println("Exiting program. Bye :)");
                    System.exit(0);
                    break;

                case HELP:
                    //DO STUFF :)
                    break;

                case CREATEPLAYER:
                    accountManager.newPlayer((String) params[0]);
                    shellWriter.println("Spieler " + params[0] + " erstellt.");
                    break;

                case BUYSHARE:
                    try {
                        accountManager.buyShare((String) params[0], (String) params[1], (Integer) params[2]);
                    } catch (InsufficientBalanceException e) {
                        shellWriter.println("Guthaben reicht nicht aus.");
                    } catch (AccountElementNotFoundException e) {
                        shellWriter.println("Spieler nicht gefunden.");
                    } catch (ShareElementNotFoundException e) {
                        shellWriter.println("Aktie nicht gefunden");
                    } catch (Exception e) {
                        // TODO Geeignete Fehlermeldung. Am besten Ausgabe von der Description aus dem Enum
                    }

                    break;

                case SELLSHARE:
                    //DO STUFF :)
                    break;


                case PRINT:
                    break;

                default:
                    break;
            }


            } catch (Exception e) {
                shellWriter.println("Unbekannter Befehl!");
            }
        }
    }
}
