package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;

import java.util.List;

/**
 * Created by Nathan on 26/03/2016.
 */
public interface IRCMessage {

    public String getCommand();

    public User getInitiatingUser();

    public List<String> getArguments();

}
