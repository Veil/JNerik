package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.core.CancellableEvent;
import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.io.Responder;

import java.util.Objects;

/**
 * This event is propagated before a user changes their nickname. Listeners on this event are able to transform the
 * nickname in some way should they want to or cancel the nickname change altogether.
 *
 * Any changes in nickname are subject to the same validation rules.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class PreNickChangeEvent extends CancellableEvent {

    private final User userChangingNick;
    private final Responder responder;

    private String nickChangingTo;

    public PreNickChangeEvent(User userChangingNick, String nickChangingTo, Responder responder) {
        this.responder = responder;
        Objects.requireNonNull(userChangingNick);
        Objects.requireNonNull(nickChangingTo);

        this.userChangingNick = userChangingNick;
        this.nickChangingTo = nickChangingTo;
    }

    /**
     * Get the user changing their nickname
     *
     * @return {@link User} The user changing their nickname
     */
    public User getUserChangingNick() {
        return userChangingNick;
    }

    /**
     * Get the nickname the user is changing to
     *
     * @return {@link String} The nickname the user is requesting a change to
     */
    public String getNickChangingTo() {
        return nickChangingTo;
    }

    public Responder getResponder() {
        return responder;
    }
}
