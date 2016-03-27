package net.aphotix.jnerik.core.registry;

import net.aphotix.jnerik.core.ChannelMode;
import net.aphotix.jnerik.core.ModeRegistry;
import net.aphotix.jnerik.core.UserMode;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple registry using maps to register {@link UserMode}s and {@link ChannelMode}s
 *
 * @author Veil (nathan@aphotix.net).
 */
public class MapBackedModeRegistry implements ModeRegistry {

    private final Map<Character, UserMode> userModes = new HashMap<>();
    private final Map<Character, ChannelMode> channelModes = new HashMap<>();

    @Override
    public void registerUserMode(UserMode mode) {
        userModes.put(mode.getModeFlag(), mode);
    }

    @Override
    public void registerChannelMode(ChannelMode mode) {
        channelModes.put(mode.getModeFlag(), mode);
    }

    @Override
    public ChannelMode getChanMode(char flag) {
        return channelModes.get(flag);
    }

    @Override
    public UserMode getUserMode(char flag) {
        return userModes.get(flag);
    }
}
