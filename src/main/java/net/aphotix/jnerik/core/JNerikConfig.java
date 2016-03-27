package net.aphotix.jnerik.core;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Describes a configuration which JNerik can read
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface JNerikConfig {

    /**
     * Get the list of addresses the IRCd should listen to.
     *
     * @return {@link List} The list of addresses
     */
    public List<InetSocketAddress> getListenAddresses();

    /**
     * Get an error format string from the code provided
     *
     * @param errorCode The error code
     *
     * @return {@link String} The error format string
     */
    public String getErrorFormat(int errorCode);
}
