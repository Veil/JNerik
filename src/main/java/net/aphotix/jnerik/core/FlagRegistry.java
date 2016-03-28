package net.aphotix.jnerik.core;

/**
 * A registry of all flags that can be given to a user.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface FlagRegistry {

    /**
     * Register new flag with the registry
     *
     * @param flag The flag to register
     */
    public void registerFlag(Flag flag);

    /**
     * Get a flag from the registry matching this char representation
     *
     * @param flag The flag to find
     *
     * @return {@link Flag} Or {@literal null} if no flag matches the given char
     */
    public Flag getFlag(char flag);

}
