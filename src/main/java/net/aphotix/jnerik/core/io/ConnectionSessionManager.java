package net.aphotix.jnerik.core.io;

import java.util.UUID;

/**
 * Defines a class which manages user connection sessions.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface ConnectionSessionManager {

    /**
     * Creates a new session for a user
     *
     * @param connection The new connection to create a session for
     */
    public void createNew(Connection connection);

    /**
     * Destroy a session
     *
     * @param uid The id of the session to destroy
     */
    public void destroy(UUID uid);

    public Connection getConnection(UUID id);
}
