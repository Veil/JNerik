package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.core.*;
import net.aphotix.jnerik.core.io.Responder;
import net.aphotix.jnerik.exception.IllegalCommandFormat;
import net.aphotix.jnerik.exception.IllegalRemoteCommandFormat;

import java.util.List;
import java.util.stream.Collectors;

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
    private final ModeRegistry modes;
    private final EventRegistry events;
    private final int maxNickLength;

    public NickCommand(UserRegistry users, ModeRegistry modes, EventRegistry events, int maxNickLength) {
        this.users = users;
        this.modes = modes;
        this.events = events;
        this.maxNickLength = maxNickLength;
    }

    @Override
    public void execute(User user, List<String> args, Responder responder) {

        try {

            if (user.isRemote()) {
                handleRemoteNick(user, responder, new RemoteNickMessage(args, maxNickLength));
            } else {
                handleLocalNick(user, responder, new NickMessage(args, maxNickLength));
            }

        } catch (IllegalCommandFormat e) {
            responder.sendError(user, ERR_NONICKNAMEGIVEN, user.getNick());
        } catch (IllegalRemoteCommandFormat e) {
            // TODO Handle this scenario...it shouldn't happen often but what do we do if it does? Potentially kill?
        } catch (IllegalNickNameException e) {
            responder.sendError(user, ERR_ERRONEOUSNICKNAME, user.getNick());
        }
    }

    private void handleLocalNick(User user, Responder responder, NickMessage message) {

        final String requestedNick = message.getRequestedNick();

        if (currentNickIsDifferent(requestedNick, user)) {
            final PreNickChangeEvent event = sendNickChangeEvent(user, responder, message);

            if (!event.isCancelled()) {

                if ((users.getUserByNick(requestedNick) != null)) {
                    responder.sendError(user, ERR_NICKNAMEINUSE, user.getNick(), requestedNick);
                } else {
                    changeNickName(user, responder, requestedNick);
                }
            }
        }
    }

    private void handleRemoteNick(User user, Responder responder, RemoteNickMessage message) {
        final String requestedNick = message.getRequestedNick();

        if (currentNickIsDifferent(requestedNick, user)) {
            // Don't handle any event cancellations, it's a remote change...we can't cancel anyway!
            sendNickChangeEvent(user, responder, message);

            final User existing = users.getUserByNick(requestedNick);

            // We can't just deny here, we have to handle appropriately
            if (existing != null) {

                if (existing.getLatestNickTimestamp() < message.getTimestamp()) {
                    responder.sendKill(user, "Nick collision: %s <- %s", message.getServerToken(), user.getServerHost());
                    return;
                } else if (existing.getLatestNickTimestamp() == message.getTimestamp()) {
                    responder.sendKill(user, "Nick collision: %s <-> %s", message.getServerToken(), user.getServerHost());
                    responder.sendKill(existing, "Nick collision: %s <-> %s", message.getServerToken(), user.getServerHost());
                    return;
                } else {
                    responder.sendKill(existing, "Nick collision: %s -> %s", message.getServerToken(), user.getServerHost());
                }
            }

            if (message.hasRegistrationArgs()) {
                // Do registration
                doRemoteRegistration(user, message);

            } else {
                changeNickName(user, responder, requestedNick);
            }
        }
    }

    private void doRemoteRegistration(User user, RemoteNickMessage message) {
        user.setName(message.getRealName());
        user.setNick(message.getRequestedNick());
        user.setServerHost(message.getServerToken());
        final List<UserMode> userModes = message.getUModes()
				.stream()
				.map(modes::getUserMode)
				.collect(Collectors.toList());

        user.setModes(userModes);
        user.setAddress(message.getHost());
    }

    private PreNickChangeEvent sendNickChangeEvent(User user, Responder responder, NickMessage message) {
        final PreNickChangeEvent event = new PreNickChangeEvent(user, message.getRequestedNick(), responder);
        events.propagate(event);
        return event;
    }

    private void changeNickName(User user, Responder responder, String requestedNick) {
        final String originalNick = user.getNick();
        user.setNick(requestedNick);
        responder.sendPropagated(user, NICK_CHANGE_RESPONSE, originalNick, user.getAddress(), requestedNick);

        events.propagate(new PostNickChangeEvent(user, originalNick, requestedNick));
    }

    private boolean currentNickIsDifferent(String requestedNick, User user) {
        return !requestedNick.equalsIgnoreCase(user.getNick());
    }
}
