package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.exception.IllegalCommandFormat;

import java.util.List;

/**
 * Created by Nathan on 25/03/2016.
 */
class NickMessage {

    private final String requestedNick;

    public NickMessage(List<String> args) throws IllegalCommandFormat {

        if (args.size() < 1) {
            this.requestedNick = args.get(0);
        } else {
            throw new IllegalCommandFormat("Command NICK expects at least one argument!");
        }
    }

    public String getRequestedNick() {
        return this.requestedNick;
    }

}
