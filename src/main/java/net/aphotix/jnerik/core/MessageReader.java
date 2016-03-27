package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.IRCMessage;
import net.aphotix.jnerik.core.io.MessageChannel;
import net.aphotix.jnerik.core.io.Responder;

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
    private final MessageChannel channel;
    private final Responder responder;

    public MessageReader(CommandRegistry commands, MessageChannel channel, Responder responder) {
        this.commands = commands;
        this.channel = channel;
        this.responder = responder;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {

            try {
                final IRCMessage message = channel.receive();
                final Command command = commands.getCommandByName(message.getCommand());
                final User initiatingUser = message.getInitiatingUser();

                if (command != null) {
                    command.execute(initiatingUser, message.getArguments(), responder);
                } else {
                    responder.sendError(initiatingUser, ERR_UNKNOWNCOMMAND, initiatingUser.getNick(),
                            message.getCommand());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

    }
}
