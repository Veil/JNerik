package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.*;

/**
 * A simple {@link Runnable} Which pulls messages off of the message channel and feeds them to commands from
 * the command registry.
 *
 * If the reader cannot find a valid command for the message it receives then it will send a ERR_UNKNOWNCOMMAND.
 *
 * @author Veil (nathan@aphotix.net).
 */
class MessageReader implements Runnable {

    private static final int ERR_UNKNOWNCOMMAND = 421;

    private final CommandRegistry commands;
    private final UserSessionRegistry users;
    private final MessageChannel channel;
    private final Responder responder;

    public MessageReader(CommandRegistry commands, UserSessionRegistry users,
                         MessageChannel channel, Responder responder) {
        this.commands = commands;
        this.users = users;
        this.channel = channel;
        this.responder = responder;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {

            try {
                final IRCMessage message = channel.receive();
                final Command command = commands.getCommandByName(message.getCommand());
                final Connection initiatingConnection = message.getInitiatingConnection();

                switch (initiatingConnection.getConnectionType()) {
                    case USER:
                        handleUserMessage(message, command, users.getUserById(initiatingConnection.getId()));
                        break;
                    case UNREGISTERED:
                        handleUserMessage(message, command, new ConnectionAwareUser(initiatingConnection, users));
                        break;
                    case SERVER:
                        // If we got the command from a server then we need to find the user by the nick it gave us
                        // however, we may have never seen this user before so we need to give it the chance to
                        // register.
                        User user = users.getUserByNick(message.getCommandNick());

                        if (user == null) {
                            user = new ConnectionAwareUser(initiatingConnection, users);
                        }
                        handleUserMessage(message, command, user);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleUserMessage(IRCMessage message, Command command, User user) {

        if (command != null) {
			command.execute(user, message.getArguments(), responder);
		} else {
			responder.sendError(user, ERR_UNKNOWNCOMMAND, user.getNick(), message.getCommand());
		}
    }
}
