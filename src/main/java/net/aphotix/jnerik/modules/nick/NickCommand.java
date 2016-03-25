package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.core.Command;
import net.aphotix.jnerik.core.Responder;
import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.UserRegistry;
import net.aphotix.jnerik.exception.IllegalCommandFormat;

import java.util.List;

/**
 * Created by Nathan on 25/03/2016.
 */
public class NickCommand implements Command {

    private final static String NICK_CHANGE_RESPONSE = ":%s!%s NICK %s";
    private static final int ERR_NICKNAMEINUSE = 433;

    private final UserRegistry users;

    public NickCommand(UserRegistry users) {
        this.users = users;
    }

    @Override
    public void execute(User user, List<String> args, Responder responder) {

        try {
            final NickMessage message = new NickMessage(args);
            final String requestedNick = message.getRequestedNick();

            if (users.getUserByNick(requestedNick) != null || user.isRemote()) {
                user.setNick(requestedNick);
                responder.sendPropagated(user, NICK_CHANGE_RESPONSE, user.getNick(), user.getAddress(), requestedNick);
            } else {
                responder.sendError(user, ERR_NICKNAMEINUSE, user.getNick(), requestedNick);
            }

        } catch (IllegalCommandFormat illegalCommandFormat) {
            illegalCommandFormat.printStackTrace();
        }
    }
}
