/* package stockgame.ui.console;


import stockgame.core.AccountManager;
import stockgame.core.AccountManagerImpl;
import stockgame.exceptions.AccountElementNotFoundException;
import stockgame.exceptions.InsufficientBalanceException;
import stockgame.exceptions.SellAmountTooLargeException;
import stockgame.exceptions.ShareElementNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandProcessor {
    private static AccountManager mgr;

    public CommandProcessor(AccountManagerImpl accmgr) throws IOException {
        CommandProcessor.mgr = accmgr;
        command();
    }

    protected static void command() throws IOException {
        String input1 = "", remainingInput = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            input1 = reader.readLine().trim();
            if (input1.length() > 0) {
                if (input1.contains(" ")) { //"buy BMW Chris 3
                    remainingInput = input1.substring(input1.indexOf(" "));
                    input1 = input1.substring(0, input1.indexOf(" ")); //What to do?!?

                }
                process(input1, remainingInput.trim());
                System.out.print(" >> ");
//                System.out.println("String 1 :" +input1 + " - String2: " + input2 + " - String3: " + input3);
            }
        }
    }


    private static void process(String whatToDo, String remainingString) {
        String workingString = remainingString;
        String playerName = "";
        String shareName = "";
        int amount = 0;

        switch (whatToDo) {
            case "buy":

                try {
//			"BMW Chris 3"
                    shareName = workingString.substring(0, workingString.indexOf(" ")).trim();
                    //sharename "BMW"
                    workingString = workingString.substring(workingString.indexOf(" ")).trim();
                    //"Chris 3"
                    playerName = workingString.substring(0, workingString.indexOf(" ")).trim();

                    amount = Integer.parseInt(workingString.substring(workingString.lastIndexOf(" ") + 1));

                    mgr.buyShare(playerName, shareName, amount);
                } catch (InsufficientBalanceException e1) {
                    System.err.println("Guthaben reicht nicht aus!");
                } catch (AccountElementNotFoundException e) {
                    System.err.println("Spieler nicht gefunden");
                } catch (ShareElementNotFoundException e) {
                    System.err.println("Aktie nicht gefunden");
                } catch (Exception e2) {
                    System.err.println("Kauf fehlgeschlagen! Syntax: buy aktienname spielername anzahl");
                }

                break;
            case "sell":
                try {

                    //			"BMW Chris 3"
                    shareName = workingString.substring(0, workingString.indexOf(" ")).trim();
                    //sharename "BMW"
                    workingString = workingString.substring(workingString.indexOf(" ")).trim();
                    //"Chris 3"
                    playerName = workingString.substring(0, workingString.indexOf(" ")).trim();
                    amount = Integer.parseInt(workingString.substring(workingString.lastIndexOf(" ")).trim());
                    mgr.sellShare(playerName, shareName, amount);

                } catch (SellAmountTooLargeException e) {
                    System.err.println("Verkauf fehlgeschlagen! So viele Aktien dieses Typs besitzt du nicht!");
                } catch (ShareElementNotFoundException e) {
                    System.err.println("Du besitzt diese Aktie nicht!");
                } catch (Exception e) {
                    System.err.println("Verkauf fehlgeschlagen! Syntax: sell aktienname spielername anzahl");
                }
                break;
            case "print":
                try {
                    if (workingString.contentEquals("shares")) {
                        mgr.printAllAvailableShares();
                    } else if (workingString.contentEquals("players")) {
                        mgr.printAllAvailablePlayers();
                    } else if (workingString.contains("assets") && workingString.contains(" ")) {
                        mgr.checkAllAssetsOfPlayer(workingString.substring(workingString.indexOf(" ")).trim());
                    } else {
                        System.err.println("Syntax: print shares / players / assets spielername");
                    }
                } catch (Exception e) {
                    System.out.println("Anzeige Fehlgeschlagen! Syntax: print assets spielername");
                }

                break;

            default:
                System.out.println("Verfuegbare Befehle: buy / sell / print");
        }

    }
}

*/