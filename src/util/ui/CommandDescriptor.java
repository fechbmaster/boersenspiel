package util.ui;

public class CommandDescriptor {

    private CommandTypeInfo commandType;
    private Object[] params;

    public CommandTypeInfo getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandTypeInfo info) {
        this.commandType = info;
    }

    public Object[] getParams() {
        return this.params;
    }

    public void setParams(String[] input) {
        Class<?>[] cmdParamTypes = commandType.getParamTypes();
        Object[] paramArray = new Object[cmdParamTypes.length];
        for (int i = 0; i < cmdParamTypes.length; i++)			//Fehler ist hier für i=1 funktioniert ein einzelnes wort als eingabe, 
            paramArray[i] = convertType(cmdParamTypes[i], input[i+1]); //schmeißt dann aber bei bspw. crp barni eine null als param rein o.O werde daraus nicht ganz schlau
         	
        this.params = paramArray;
    }

    private Object convertType(Class<?> cmdParamType, Object o) {
        if (cmdParamType.equals(String.class)) {
            //nothing.. castable
        } else if (cmdParamType.equals(Long.class)) {
            Long.parseLong((String)o);
        } else if (cmdParamType.equals(Integer.class)) {
            Integer.parseInt((String)o);
        }
        return o;
    }

    public void setDescription(String description) {

    }
}
