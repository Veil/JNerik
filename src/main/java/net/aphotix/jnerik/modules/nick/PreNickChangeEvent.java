package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.core.CancellableEvent;
import net.aphotix.jnerik.core.User;

/**
 * Created by Nathan on 25/03/2016.
 */
public class PreNickChangeEvent extends CancellableEvent {

    private final User userChangingNick;

    private String nickChangingTo;

    public PreNickChangeEvent(User userChangingNick, String nickChangingTo) {
        this.userChangingNick = userChangingNick;
        this.nickChangingTo = nickChangingTo;
    }

    public User getUserChangingNick() {
        return userChangingNick;
    }

    public String getNickChangingTo() {
        return nickChangingTo;
    }

    public void setNickChangingTo(String newNick) {
        nickChangingTo = newNick;
    }

}
