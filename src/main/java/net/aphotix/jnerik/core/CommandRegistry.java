package net.aphotix.jnerik.core;

/**
 * The registry under which all valid client commands can be found.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface CommandRegistry {

    /**
     * Register a new command with the registry
     *
     * @param trigger The string which will trigger the execution of the command added
     * @param command The command that can be executed
     */
    public void register(String trigger, Command command);

    /**
     * Get a command by its name
     *
     * @param command The comamand name
     *
     * @return {@link Command} The command matching the trigger name or {@literal null} if no command matches
     */
    public Command getCommandByName(String command);

}
