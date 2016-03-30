package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.Responder;
import net.aphotix.jnerik.core.io.Server;

import java.util.List;

/**
 * Defines a command that can be carried out by a client.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface Command {

    /**
     * Execute a command under the context of the user who initiated it.
     *
     * @param user The user under whose context we run this command
     * @param args The arguments for the command
     * @param responder The responder used to respond to clients
     */
    public void execute(User user, List<String> args, Responder responder);

}
