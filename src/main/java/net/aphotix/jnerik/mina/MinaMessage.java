package net.aphotix.jnerik.mina;

import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.io.IRCMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A basic implementation of an {@link IRCMessage} Which is constructed from a mina received message.
 *
 * @author Veil (nathan@aphotix.net).
 */
class MinaMessage implements IRCMessage {

    private final User user;
    private final String command;
    private final List<String> arguments;

    public MinaMessage(User user, String message) {
        this.user = user;

        if (message != null) {
            final String[] split = message.split(" ");
            command = split[0];
            arguments = new ArrayList<>();

            for (int i = 1; i < split.length; i++) {
                final String argument = split[i].trim();

                if (!argument.equals("")) {
                    arguments.add(argument);
                }
            }
        } else {
            command = "";
            arguments = Collections.emptyList();
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public User getInitiatingUser() {
        return user;
    }

    @Override
    public List<String> getArguments() {
        return arguments;
    }
}
