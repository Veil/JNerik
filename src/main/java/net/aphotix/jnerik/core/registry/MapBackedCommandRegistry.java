package net.aphotix.jnerik.core.registry;

import net.aphotix.jnerik.core.Command;
import net.aphotix.jnerik.core.CommandRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple mapped backed command registry
 *
 * @author Veil (nathan@aphotix.net).
 */
public class MapBackedCommandRegistry implements CommandRegistry {

    private final Map<String, Command> commands = new HashMap<>();

    @Override
    public void register(String trigger, Command command) {
        commands.put(trigger, command);
    }

    @Override
    public Command getCommandByName(String command) {
        return commands.get(command);
    }
}
