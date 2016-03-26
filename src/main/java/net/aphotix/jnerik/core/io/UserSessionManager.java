package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;

import java.util.UUID;

/**
 * Created by Nathan on 26/03/2016.
 */
public interface UserSessionManager {

    public void createNew(UUID uid, MessageSender sender);

    public void destroy(UUID uid);

    public User getUserFromId(UUID uid);

}
