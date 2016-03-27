package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.UserRegistry;

/**
 * The registry used by a {@link UserSessionResponder} to keep track of registered users.
 *
 * @author Veil (nathan@aphotix.net).
 */
class UserSessionRegistry implements UserRegistry {

    @Override
    public User getUserByNick(String nick) {
        return null;
    }

    public void registerUser(String previousNick, String newNick, User user) {

    }
}
