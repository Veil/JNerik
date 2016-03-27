package net.aphotix.jnerik.core.io;

/**
 * Defines an object through which raw messages can be sent (usually to some networked client)
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface MessageSender {

    /**
     * Send a raw message
     *
     * @param message The message to be sent
     */
    public void send(String message);

    /**
     * Get the address that these messages are going to
     *
     * @return {@link String} The address
     */
    public String getConnectionAddress();

}
