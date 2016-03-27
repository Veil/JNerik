package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.IRCMessage;
import net.aphotix.jnerik.core.io.MessageChannel;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * A very simple message channel which uses a {@link LinkedBlockingQueue} to back message traversal.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class QueueBackedMessageChannel implements MessageChannel {

    private final LinkedBlockingQueue<IRCMessage> messageQueue = new LinkedBlockingQueue<>();

    @Override
    public void push(IRCMessage message) {
        messageQueue.add(message);
    }

    @Override
    public IRCMessage receive() throws InterruptedException {
        return messageQueue.take();
    }
}
