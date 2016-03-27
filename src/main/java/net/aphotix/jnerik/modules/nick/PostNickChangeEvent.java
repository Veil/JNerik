package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.core.Event;
import net.aphotix.jnerik.core.User;

/**
 * This event is propagated when a nickname change has occurred.
 *
 * THIS EVENT IS NOT CANCELLABLE.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class PostNickChangeEvent implements Event {

    private final User userWhoChangedNick;
    private final String nickChangedFrom;
    private final String nickChangedTo;

    public PostNickChangeEvent(User userWhoChangedNick, String nickChangedFrom, String nickChangedTo) {
        this.userWhoChangedNick = userWhoChangedNick;
        this.nickChangedFrom = nickChangedFrom;
        this.nickChangedTo = nickChangedTo;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }

    /**
     * Get the user who changed their nickname
     *
     * @return {@link User} The user who changed their nickname
     */
    public User getUserWhoChangedNick() {
        return userWhoChangedNick;
    }

    /**
     * Get the nickname the user changed to
     *
     * @return {@link String} The new nickname
     */
    public String getNickChangedTo() {
        return nickChangedTo;
    }

    /**
     * Get the nickname the user had originally
     *
     * @return {@link String} The original nickname
     */
    public String getNickChangedFrom() {
        return nickChangedFrom;
    }
}
