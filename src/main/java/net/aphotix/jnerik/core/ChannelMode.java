package net.aphotix.jnerik.core;

/**
 * Represents a mode which users or servers can set on a channel.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface ChannelMode {

    /**
     * The flag used to represent this mode
     *
     * @return {@literal char} The flag for this mode
     */
    public char getModeFlag();

    /**
     * Checks if a user is allowed to set this mode given the current context
     *
     * @param user The user trying to set the mode
     * @param channel The channel the mode is being set on
     * @param parameter Any parameters for this mode, can be {@literal null}
     *
     * @return {@literal boolean} True if the user is allowed to set the mode, false if not.
     */
    public boolean isPermitted(User user, Channel channel, String parameter);

}
