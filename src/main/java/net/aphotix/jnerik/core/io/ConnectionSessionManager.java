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
     * @param id The new connection id to create a session for
     */
    public void createNew(UUID id, MessageSender sender);

    /**
     * Destroy a session
     *
     * @param uid The id of the session to destroy
     */
    public void destroy(UUID uid);

    public Connection getConnection(UUID id);

	/**
     * Register a new connection for a previously unregistered connection
     *
     * @param id The connection id to update
     * @param type The type of connection to register as
     */
    public void registerConnection(UUID id, Connection.ConnectionType type);
}
