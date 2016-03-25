package net.aphotix.jnerik.core;

import java.util.List;

/**
 * Created by Nathan on 24/03/2016.
 */
public interface Command {

    public void execute(User user, List<String> args, Responder responder);

}
