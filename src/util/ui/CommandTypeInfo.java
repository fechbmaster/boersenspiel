package util.ui;

public interface CommandTypeInfo {
    public String getName();

    public String getHelpText();

    public Class<?>[] getParamTypes();

    public String getCommand();
}
