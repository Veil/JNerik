package net.aphotix.jnerik.core;

/**
 * A registry of valid modes that JNerik supports
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface ModeRegistry {

    /**
     * Register a new user mode
     *
     * @param mode The mode to register
     */
    public void registerUserMode(UserMode mode);

    /**
     * Register a new channel mode
     *
     * @param mode The mode to register
     */
    public void registerChannelMode(ChannelMode mode);

    /**
     * Gets the channel mode for the flag provided
     *
     * @param flag The flag to get the mode for
     *
     * @return {@link ChannelMode} The mode the flag matches or {@literal null} if no mode is found
     */
    public ChannelMode getChanMode(char flag);

    /**
     * Gets the user mode for the flag provided
     *
     * @param flag The flag to get the mode for
     *
     * @return {@link UserMode} The mode the flag matches or {@literal null} if no mode is found
     */
    public UserMode getUserMode(char flag);

}
