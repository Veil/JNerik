package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.core.CommandRegistry;
import net.aphotix.jnerik.core.EventRegistry;
import net.aphotix.jnerik.core.Module;
import net.aphotix.jnerik.core.UserRegistry;

/**
 * Created by Nathan on 25/03/2016.
 */
public class NickModule implements Module {

    private static final String NICK_COMMAND = "NICK";

    private final CommandRegistry commands;
    private final UserRegistry users;
    private final EventRegistry events;

    public NickModule(CommandRegistry commands, UserRegistry users, EventRegistry events) {
        this.commands = commands;
        this.users = users;
        this.events = events;
    }

    @Override
    public void load() {
        commands.register(NICK_COMMAND, new NickCommand(users, events, 8));
    }

    @Override
    public void unload() {
        // NICK is permanent
    }
}
