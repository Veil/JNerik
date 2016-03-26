package net.aphotix.jnerik.core;

/**
 * Created by Nathan on 24/03/2016.
 */
public interface CommandRegistry {

    public void register(String trigger, Command command);

    public Command getCommandByName(String command);

}
