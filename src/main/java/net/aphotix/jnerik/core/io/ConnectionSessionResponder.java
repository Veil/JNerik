package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.JNerikConfig;
import net.aphotix.jnerik.core.ServerRegistry;
import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.UserRegistry;
import net.aphotix.jnerik.core.registry.MapBackedServerRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**A {@link ConnectionSessionManager} which provides the ability to send responses to those it manages.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class ConnectionSessionResponder implements Responder, ConnectionSessionManager {

    private final Map<UUID, Connection> sessions;
    private final JNerikConfig config;

    public ConnectionSessionResponder(JNerikConfig config) {
        this.config = config;
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
    public void createNew(Connection connection) {
        sessions.put(connection.getId(), connection);
    }

    @Override
    public void destroy(UUID uid) {
        sessions.remove(uid);
    }

    @Override
    public Connection getConnection(UUID id) {
        return sessions.get(id);
    }
}
