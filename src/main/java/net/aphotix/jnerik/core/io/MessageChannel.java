package net.aphotix.jnerik.core.io;

/**
 * Created by Nathan on 26/03/2016.
 */
public interface MessageChannel {

    public void push(IRCMessage message);

    public IRCMessage take();

}
