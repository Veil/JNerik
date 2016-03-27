package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.core.*;
import net.aphotix.jnerik.core.io.Responder;
import net.aphotix.jnerik.exception.IllegalCommandFormat;

import java.util.List;

/**
 * Implements the core logic for the NICK command, allowing a user to request a new nickname.
 *
 * @author Veil (nathan@aphotix.net).
 */
class NickCommand implements Command {

    private static final String NICK_CHANGE_RESPONSE = ":%s!%s NICK %s";

    private static final int ERR_NICKNAMEINUSE = 433;
    private static final int ERR_NONICKNAMEGIVEN = 431;
    private static final int ERR_ERRONEOUSNICKNAME = 432;

    private final UserRegistry users;
    private final EventRegistry events;
    private final int maxNickLength;

    public NickCommand(UserRegistry users, EventRegistry events, int maxNickLength) {
        this.users = users;
        this.events = events;
        this.maxNickLength = maxNickLength;
    }

    @Override
    public void execute(User user, List<String> args, Responder responder) {

        try {
            final NickMessage message = new NickMessage(args, maxNickLength);
            final String requestedNick = message.getRequestedNick();

            // We should only fire nick change logic if the user ACTUALLY changed their nickname.
            if (!requestedNick.equals(user.getNick())) {

                final PreNickChangeEvent event = new PreNickChangeEvent(user, requestedNick);
                events.propagate(event);
                final String finalNick = event.getNickChangingTo();

                // If the user is remote then we don't have any control over whether they change nickname anyway
                // Modules which listen for this event and don't handle remote users properly will cause problems
                // but that's for module authors to figure out..
                if ((users.getUserByNick(requestedNick) == null && !event.isCancelled()) || user.isRemote()) {

                    if (!requestedNick.equalsIgnoreCase(finalNick)) {
                        message.validate(event.getNickChangingTo());
                    }
                    final String originalNick = user.getNick();
                    user.setNick(finalNick);
                    responder.sendPropagated(user, NICK_CHANGE_RESPONSE, originalNick, user.getAddress(), finalNick);

                    events.propagate(new PostNickChangeEvent(user, originalNick, finalNick));
                } else {
                    responder.sendError(user, ERR_NICKNAMEINUSE, user.getNick(), requestedNick);
                }
            }

        } catch (IllegalCommandFormat e) {
            responder.sendError(user, ERR_NONICKNAMEGIVEN, user.getNick());
        } catch (IllegalNickNameException e) {
            responder.sendError(user, ERR_ERRONEOUSNICKNAME, user.getNick());
        }
    }
}
