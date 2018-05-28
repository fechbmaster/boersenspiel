package util.ui.shell;

import util.ui.CommandDescriptor;
import util.ui.CommandTypeInfo;
import util.ui.exceptions.CommandNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;

public class CommandScanner {
    private CommandTypeInfo[] infoArray;
    private BufferedReader reader;
//	
//	/*
//	 * Um die Eingaben des Benutzers validieren zu k�nnen, also entscheiden zu k�nnen,
//	 * welches Kommando der Benutzer ausgew�hlt hat, ist es n�tig dem CommandScanner die Liste
//	 * der erlaubten Kommandos zu �bergeben. Dazu w�re das Enum an sich gut geeignet,
//	 * w�rde aber damit den CommandScanner auf den einzigen Anwendungsfall festlegen. 
//	 * Um das allgemeiner zu gestalten, f�hren wir ein Interface CommandTypeInfo ein, 
//	 * das die entsprechenden Zugriffsmethoden definiert und je nach Anwendungsfall verschieden
//	 * implementiert werden kann. An den CommandScanner soll (f�r die erlaubten Kommandos) 
//	 * ein Array von CommandTypeInfo-Objekten �bergeben werden.
//	 */
//	private CommandTypeInfo[] commandTypeInfos() {
//		//TODO Herausfinden wie wir das hier implementieren :)
//		return null;
//	}

    /*
     * Dem CommandScanner wird nur ein Array von CommandInfos �bergeben.
     * Dieses muss nicht notwendig aus einer enum hervorgehen. (Allgemeinheit).
     */
    public CommandScanner(CommandTypeInfo[] commandArray) {
        this.infoArray = commandArray;
    }

    public CommandScanner(CommandTypeInfo[] values, BufferedReader shellReader) {
        this.infoArray = values;
        this.reader = shellReader;
    }

    public void fillInCommandDesc(CommandDescriptor command) {
//        for (int i = 0; i < infoArray.length; i++) {
//            if (infoArray[i].getName().equals(command)) {
//                command.setDescription((CommandDescriptor) infoArray[i].getHelpText());
//            }
//        }
    }

    /*
     * Zur�ckliefern soll der CommandScanner neben der Aussage, welches Kommando eingegeben wurde
     * auch die Liste der vom Benutzer eingegebenen Parameter zum Kommando (schon mit dem richtigen
     * Typ f�r den nachfolgenden Methodenaufruf) , also bei BUYSHARE den Namen des K�ufers (als String),
     * den Namen der Aktie (als String) und die Anzahl (als Integer). Dazu stellt der Aufrufer ein Objekt
     * vom Typ CommandDescriptor zur Verf�gung, in den der Scanner seine Erkenntnisse per
     * Setter-Methoden eintragen kann
     */
    public CommandDescriptor getCommand() throws IOException {
        CommandDescriptor desc = new CommandDescriptor();
        String[] input = reader.readLine().split(" ");

//        if (inputString.contains(" ")) {
//            inputString.substring(inputString.indexOf(" ")).trim();
//            inputString.substring(0, inputString.indexOf(" ")).trim();
//        } else {
//            cmdString = inputString;
//        }

        for (int i = 0; i < infoArray.length; i++) {
            if (input[0].equals(infoArray[i].getCommand())) {
                desc.setCommandType(infoArray[i]);                
                desc.setParams(input);
                return desc;
            }
        }
        throw new CommandNotFoundException();
    }


}
