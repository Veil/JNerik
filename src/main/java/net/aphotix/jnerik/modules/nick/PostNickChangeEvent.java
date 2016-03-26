package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.core.Event;
import net.aphotix.jnerik.core.User;

/**
 * Created by Nathan on 26/03/2016.
 */
public class PostNickChangeEvent implements Event {

    private final User userWhoChangedNick;
    private final String nickChangedTo;

    public PostNickChangeEvent(User userWhoChangedNick, String nickChangedTo) {

        this.userWhoChangedNick = userWhoChangedNick;
        this.nickChangedTo = nickChangedTo;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }

    public User getUserWhoChangedNick() {
        return userWhoChangedNick;
    }

    public String getNickChangedTo() {
        return nickChangedTo;
    }
}
