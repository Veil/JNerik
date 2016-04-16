package net.aphotix.jnerik.core;

import java.util.List;
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

    public String getName();

    public void setName(String name);

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

    public List<UserMode> getModes();

    public void addMode(UserMode mode);

    public void addModes(List<UserMode> modes);

    public void setModes(List<UserMode> modes);

    public void removeMode(UserMode modes);

    public void removeModes(List<UserMode> modes);

    /**
     * Get the host address from which this user is connecting from
     *
     * @return {@link String} The host address
     */
    public String getAddress();

    public void setAddress(String address);

    public String getVhost();

    public void setVHost(String vHost);

    public String getServerHost();

    public void setServerHost(String serverHost);

    public long getLatestNickTimestamp();

    /**
     * Gets whether this user is connected to this server or not
     *
     * @return {@literal boolean} True if the user is not connected to this server, false if they are.
     */
    public boolean isRemote();
}
