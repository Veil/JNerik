package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;

import java.util.List;

/**
 * Describes a raw IRC message as received from some input source
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface IRCMessage {

    /**
     * The name of the command that this message intends to execute
     *
     * @return {@link String} The command name
     */
    public String getCommand();

    /**
     * The user who initiated this message
     *
     * @return {@link User} The initiating user
     */
    public Connection getInitiatingConnection();

    /**
     * Get the arguments for the command
     *
     * @return {@link List} The arguments
     */
    public List<String> getArguments();

	/**
     * Gets the nickname of the user who issued this command
     *
     * This can be null unless {@link #getInitiatingConnection()#getConnectionType()} returns
     * {@link net.aphotix.jnerik.core.io.Connection.ConnectionType#SERVER}
     *
     * @return {@link String} The nick of the user who issued this command
     */
    public String getCommandNick();

}
