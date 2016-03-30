package net.aphotix.jnerik.mina;

import net.aphotix.jnerik.core.io.Connection;
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

    private final Connection connection;
    private final String command;
    private final List<String> arguments;
    private final String nick;

    public MinaMessage(Connection connection, String message) {
        this.connection = connection;

        switch (connection.getConnectionType()) {
            case USER:
            case UNREGISTERED: {

                if (message != null) {
                    final String[] split = message.split(" ");

                    //IRC protocol is annoying in this case
                    if (split[0].startsWith(":")) {
                        nick = split[0];

                        if (split.length > 1) {
                            command = split[1];
                        } else {
                            command = null;
                        }
                    } else {
                        command = split[0];
                        nick = null;
                    }
                    arguments = new ArrayList<>();

                    for (int i = 1; i < split.length; i++) {
                        final String argument = split[i].trim();

                        if (!argument.equals("")) {
                            arguments.add(argument);
                        }
                    }
                } else {
                    nick = null;
                    command = "";
                    arguments = Collections.emptyList();
                }
                break;
            }
            case SERVER:

                if (message != null) {
                    final String[] split = message.split(" ");

                    if (split.length < 2) {
                        throw new IllegalArgumentException("Received invalid command from a server connection! Ignoring...");
                    }

                    nick = split[0];
                    command = split[1];

                    arguments = new ArrayList<>();

                    for (int i = 2; i < split.length; i++) {
                        final String argument = split[i].trim();

                        if (!argument.equals("")) {
                            arguments.add(argument);
                        }
                    }
                } else {
                    command = "";
                    arguments = Collections.emptyList();
                }
            default:
                throw new IllegalArgumentException("Unknown connection type!");
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public Connection getInitiatingConnection() {
        return connection;
    }

    @Override
    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public String getCommandNick() {
        return nick;
    }
}
