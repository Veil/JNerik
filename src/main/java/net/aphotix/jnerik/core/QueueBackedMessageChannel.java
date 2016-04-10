package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.IRCMessage;
import net.aphotix.jnerik.core.io.MessageChannel;
import net.aphotix.jnerik.exception.MalformedIRCMessageException;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * A very simple message channel which uses a {@link LinkedBlockingQueue} to back message traversal.
 *
 * It uses {@link ParsingMessage} to parse the Raw message on each call to {@link #receive()}, making pushing much
 * quicker.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class QueueBackedMessageChannel implements MessageChannel {

    private final LinkedBlockingQueue<RawMessage> messageQueue = new LinkedBlockingQueue<>();

    @Override
    public void push(RawMessage message) {
        messageQueue.add(message);
    }

    @Override
    public IRCMessage receive() throws InterruptedException, MalformedIRCMessageException {
        return new ParsingMessage(messageQueue.take());
    }
}
