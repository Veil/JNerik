package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;

import java.util.UUID;

/**
 * A user which holds the {@link MessageSender} with which to communicate to the user with and will update the user
 * registry when its nickname changes.
 *
 * @author Veil (nathan@aphotix.net).
 */
class UserSession implements User {

    private final UUID id;
    private final MessageSender sender;
    private final boolean isRemote;
    private final UserSessionRegistry registry;
    private final String address;

    private String nick;

    public UserSession(UUID id, MessageSender sender, boolean isRemote, UserSessionRegistry registry) {
        this.id = id;
        this.sender = sender;
        this.isRemote = isRemote;
        this.registry = registry;
        this.address = sender.getConnectionAddress();
    }

    /**
     * Get the sender used to communicate with this user.
     *
     * @return {@link MessageSender} The sender user to communicate with the client
     */
    public MessageSender getSender() {
        return sender;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getNick() {
        return nick;
    }

    /**
     * Will update the user registry as well to keep everything in sync
     *
     * @see User#setNick(String)
     */
    @Override
    public void setNick(String requestedNick) {
        registry.registerUser(nick, requestedNick, this);
        nick = requestedNick;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public boolean isRemote() {
        return isRemote;
    }
}
