package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.UserRegistry;

/**
 * Created by Nathan on 27/03/2016.
 */
class UserSessionRegistry implements UserRegistry {

    @Override
    public User getUserByNick(String nick) {
        return null;
    }

    public void registerUser(String previousNick, String newNick, User user) {

    }
}
