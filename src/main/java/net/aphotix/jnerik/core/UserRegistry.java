package net.aphotix.jnerik.core;

/**
 * Defines a registry where all registered users will reside.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface UserRegistry {

    /**
     * Get a user by their nickname
     *
     * @param nick The nickname of the user to return
     *
     * @return {@link User} The user associated with the nickname or {@literal null} if the user does not exist
     */
    public User getUserByNick(String nick);

}
