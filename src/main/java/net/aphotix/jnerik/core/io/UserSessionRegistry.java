package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.UserRegistry;

import java.util.UUID;

/**
 * The registry used by a {@link ConnectionSessionResponder} to keep track of registered users.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class UserSessionRegistry implements UserRegistry {

    private final ConnectionSessionManager sessionManager;

    public UserSessionRegistry(ConnectionSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public User getUserByNick(String nick) {
        return null;
    }

    public User getUserById(UUID id) {
        return null;
    }

	/**
     * Register a user with this registry, or update it if the user exists.
     *
     * WARNING: Never call {@link User#setNick(String)} in here, we'll end up with a horrible recursion.
     *
     * @param previousNick The previous nickname
     * @param newNick The new nickname
     * @param user The user changing nickname or registering
     */
    public void registerUser(String previousNick, String newNick, UserConnection user) {
        sessionManager.createNew(user);
        //TODO Other registry stuffs
    }
}
