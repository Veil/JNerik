package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;

/**
 * Defines a way to respond to a user provided. Provides abstraction on top of which clients should be notified of which
 * action so callers can be simplified.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface Responder {

    /**
     * Sends a response to the provided user ONLY.
     *
     * @see java.util.Formatter
     *
     * @param user The user to send the response to
     * @param message The message format to send to the user
     * @param params The parameters fulfilling the format
     */
    public void send(User user, String message, Object... params);

    /**
     * Sends a response to the provided user and any other clients who need to see the change too for client
     * synchronization.
     *
     * @see java.util.Formatter
     *
     * @param user The user this message is targetted to
     * @param message The message format to send
     * @param params The parameters fulfulling the format
     */
    public void sendPropagated(User user, String message, Object... params);

    /**
     * Send an error to the provided user
     *
     * @param user The user to send the error to
     * @param errCode The error code of the format string we need to send
     * @param params Parameters fulfilling the error code format
     */
    public void sendError(User user, int errCode, Object... params);
}
