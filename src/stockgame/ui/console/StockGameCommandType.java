package stockgame.ui.console;

import util.ui.CommandTypeInfo;

public enum StockGameCommandType implements CommandTypeInfo {

    HELP("help", " * list all commands"),
    EXIT("exit", " * exit program"),
    CREATEPLAYER("crp", "<name > * create a new Player by name", String.class),
    BUYSHARE("bus", "<playername> <sharename> <amount> * buy that amount of shares", String.class, String.class, int.class),
    SELLSHARE("ses", "<playername> <sharename> <amount> * sell that amount of shares", String.class, String.class, int.class),
    PRINT("pra", "<playername> * print all shares of a player");

    private String command;
    private String description;
    private Object[] params;


    StockGameCommandType(String cmd, String desc) {
        this.command = cmd;
        this.description = desc;
        this.params = new Object[]{"none"};    //dummy.. parms isn't accecced for exit
    }

    StockGameCommandType(String cmd, String desc, Class<String> param1) {
        this(cmd, desc);
        this.params = new Object[]{param1};
    }

    StockGameCommandType(String cmd, String desc, Class<String> param1, Class<String> param2, Class<Integer> param3) {
        this(cmd, desc);
        this.params = new Object[]{param1, param2, param3};
    }

    @Override
    public String getName() {
        return this.command;
    }

    @Override
    public String getHelpText() {
        return this.description;
    }

    @Override
    public Class<?>[] getParamTypes() {
        Class<?>[] paramTypes = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }
        return paramTypes;
    }

    public String getCommand() {
        return this.command;
    }
}
