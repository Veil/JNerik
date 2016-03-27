package net.aphotix.jnerik.core;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A registry of valid modes that JNerik supports
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface ModeRegistry {

    /**
     * Register a new user mode
     *
     * @param mode The mode to register
     * @param permit The function called to determine if a user can set the mode.
     */
    public void registerUserMode(char mode, Function<User, Boolean> permit);

    /**
     * Register a new channel mode
     *
     * @param mode The mode to register
     * @param permit The function called to determine if a user can set the mode on the channel provided.
     */
    public void registerChannelMode(char mode, BiFunction<User, Channel, Boolean> permit);

}
