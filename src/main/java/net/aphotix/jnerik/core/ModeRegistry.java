package net.aphotix.jnerik.core;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Nathan on 24/03/2016.
 */
public interface ModeRegistry {

    public void registerUserMode(char mode, Function<User, Boolean> permit);

    public void registerChannelMode(char mode, BiFunction<User, Channel, Boolean> permit);

}
