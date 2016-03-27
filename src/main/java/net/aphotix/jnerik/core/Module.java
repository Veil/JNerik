package net.aphotix.jnerik.core;

/**
 * Represents a module which can be loaded and add functionality to the IRCd
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface Module {

    /**
     * Load the module, is called once all core setup is completed.
     */
    public void load();

    /**
     * Unload the module, called when the server is shutting down or the module is being unloaded.
     */
    public void unload();

}
