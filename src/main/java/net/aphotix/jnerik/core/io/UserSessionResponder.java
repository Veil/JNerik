package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.JNerikConfig;
import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.UserRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Nathan on 27/03/2016.
 */
public class UserSessionResponder implements Responder, UserSessionManager {

    private final Map<UUID, UserSession> sessions;
    private final JNerikConfig config;
    private final UserSessionRegistry registry;

    public UserSessionResponder(JNerikConfig config) {
        this.config = config;
        this.registry = new UserSessionRegistry();
        this.sessions = new HashMap<>();
    }

    @Override
    public void send(User user, String message, Object... params) {
        sessions.get(user.getId()).getSender().send(String.format(message, params));
    }

    @Override
    public void sendPropagated(User user, String message, Object... params) {

    }

    @Override
    public void sendError(User user, int errCode, Object... params) {
        send(user, config.getErrorFormat(errCode), params);
    }

    @Override
    public void createNew(UUID uid, MessageSender sender) {
        sessions.put(uid, new UserSession(uid, sender, false, registry));
    }

    @Override
    public void destroy(UUID uid) {
        sessions.remove(uid);
    }

    @Override
    public User getUserFromId(UUID uid) {
        return sessions.get(uid);
    }

    @Override
    public UserRegistry getUserRegistry() {
        return registry;
    }
}
