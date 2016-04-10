package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.RawMessage;
import net.aphotix.jnerik.exception.MalformedIRCMessageException;

/**
 * A channel through which IRC messages can be pushed and expect to be read.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface MessageChannel {

    /**
     * Push an irc message into the channel for reading
     * @param message The message to be read
     */
    public void push(RawMessage message);

    /**
     * Take the next message to read an interpreted from this channel
     *
     * @return {@link IRCMessage} The next message to be read
     * @throws InterruptedException If the thread was interrupted waiting for the next message.
     */
    public IRCMessage receive() throws InterruptedException, MalformedIRCMessageException;

}
