package net.aphotix.jnerik.core;

/**
 * Represents a flag which can be applied to a user. Flags differ from modes as they represent levels of access
 * within the IRCd administration hierarchy itself. They are set by a server and cannot be set by a user.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface Flag {

    /**
     * Get the char representation of this flag
     *
     * @return {@literal char} The char representing this flag
     */
    public char getFlag();

}
