package net.aphotix.jnerik.core;

import net.aphotix.jnerik.modules.nick.NickModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Loads all modules set to be included in the IRCd.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class ModuleLoader {

    private final List<Module> loadedModules;
    private final ModuleConstructor constructor;

    public ModuleLoader(CommandRegistry commands, FlagRegistry flags, ModeRegistry modes, UserRegistry users,
                        EventRegistry events) {
        constructor = new ModuleConstructor(commands, flags, modes, users, events);
        this.loadedModules = new ArrayList<>();
    }

    /**
     * Loads all modules.
     */
    public void loadModules() {
        // TODO Work out the best way to load modules either dynamically or specifically
        List<Class<? extends Module>> modules = Arrays.asList(NickModule.class);

        for (Class<? extends Module> module : modules) {

            try {
                Module newModule = constructor.construct(module);
                // TODO No reason not to add some kind of ordering here
                newModule.load();
                loadedModules.add(newModule);
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Unloads all previously loaded modules.
     */
    public void unloadModules() {
        loadedModules.forEach(Module::unload);
    }
}
