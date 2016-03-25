package net.aphotix.jnerik.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 24/03/2016.
 */
public class ModuleLoader {

    private final List<Module> loadedModules;
    private final ModuleConstructor constructor;

    public ModuleLoader(CommandRegistry commands, FlagRegistry flags, ModeRegistry modes, UserRegistry users) {
        constructor = new ModuleConstructor(commands, flags, modes, users);
        this.loadedModules = new ArrayList<>();
    }

    public void loadModules() {
        // TODO Work out the best way to load modules either dynamically or specifically
        List<Class<Module>> modules = new ArrayList<>();

        for (Class<Module> module : modules) {

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

    public void unloadModules() {
        loadedModules.forEach(Module::unload);
    }
}
