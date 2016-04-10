package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.Connection;
import net.aphotix.jnerik.core.io.IRCMessage;
import net.aphotix.jnerik.exception.MalformedIRCMessageException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A message which parses the {@link RawMessage} it's given immediately.
 *
 * @author Veil (nathan@aphotix.net).
 */
class ParsingMessage implements IRCMessage {

    private final Connection connection;
    private final String command;
    private final List<String> arguments;
    private final String nick;

	/**
     * Parse a raw message given into a more understable structure
     *
     * @param rawMessage The raw message to parse.
     *
     * @throws MalformedIRCMessageException If the raw message given could not be understood sensibly
     */
    public ParsingMessage(RawMessage rawMessage) throws MalformedIRCMessageException {
        this.connection = rawMessage.getConnection();
        final String message = rawMessage.getRawMessage();

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
                        throw new MalformedIRCMessageException("Received invalid command from a server connection! Ignoring...");
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
                throw new MalformedIRCMessageException("Unknown connection type!");
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
