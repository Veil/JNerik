package net.aphotix.jnerik.core;

import java.util.UUID;

/**
 * Defines a user within the IRCd
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface User {

    /**
     * Get the id of this user
     *
     * @return {@link UUID} A unique id for this user
     */
    public UUID getId();

    /**
     * Get the nickname for this user
     *
     * @return {@link String} The nickname for this user
     */
    public String getNick();

    /**
     * Set the nickname for this user
     *
     * @param requestedNick The nickname to set
     */
    public void setNick(String requestedNick);

    /**
     * Get the host address from which this user is connecting from
     *
     * @return {@link String} The host address
     */
    public String getAddress();

    /**
     * Gets whether this user is connected to this server or not
     *
     * @return {@literal boolean} True if the user is not connected to this server, false if they are.
     */
    public boolean isRemote();
}
