package net.aphotix.jnerik.core;

/**
 * Represents a user mode users can apply to themselves
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface UserMode {

    /**
     * Gets the flag representing this mode
     *
     * @return {@literal char} The mode flag
     */
    public char getModeFlag();

    /**
     * Checks if the user is permitted to set this mode in the current context
     *
     * @param user The user trying to set the mode
     * @param parameter Any parameter for this mode, can be null
     *
     * @return {@literal boolean} True if the user is allowed to set the mode, false if not
     */
    public boolean isPermitted(User user, String parameter);

}
