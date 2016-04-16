package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.core.*;

/**
 * The module used to register the NICK command.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class NickModule implements Module {

    private static final String NICK_COMMAND = "NICK";

    private final CommandRegistry commands;
    private final UserRegistry users;
    private final EventRegistry events;
    private final ModeRegistry modes;

    public NickModule(CommandRegistry commands, UserRegistry users, EventRegistry events, ModeRegistry modes) {
        this.commands = commands;
        this.users = users;
        this.events = events;
        this.modes = modes;
    }

    @Override
    public void load() {
        commands.register(NICK_COMMAND, new NickCommand(users, modes, events, 8));
    }

    @Override
    public void unload() {
        // NICK is permanent
    }
}
