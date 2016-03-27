package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.UserRegistry;

import java.util.UUID;

/**
 * Defines a class which manages user connection sessions.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface UserSessionManager {

    /**
     * Creates a new session for a user
     *
     * @param uid The id of new session
     * @param sender The sender used to communicate for this session
     */
    public void createNew(UUID uid, MessageSender sender);

    /**
     * Destroy a session
     *
     * @param uid The id of the session to destroy
     */
    public void destroy(UUID uid);

    /**
     * Get a user from the id provided
     *
     * @param uid The id of the user to get
     *
     * @return {@link User} The user matching the id or {@literal null}
     */
    public User getUserFromId(UUID uid);

    /**
     * Gets the registry that this manager maintains of registered users
     *
     * @return {@link UserRegistry} The registry of users
     */
    public UserRegistry getUserRegistry();

}
