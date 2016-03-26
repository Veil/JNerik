package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.exception.IllegalCommandFormat;

import java.util.List;

/**
 * Created by Nathan on 25/03/2016.
 */
class NickMessage {

    private final String requestedNick;
    private final int maxNickLength;

    public NickMessage(List<String> args, int maxNickLength) throws IllegalCommandFormat, IllegalNickNameException {
        this.maxNickLength = maxNickLength;

        if (args.size() < 1 && !args.get(0).trim().isEmpty()) {
            final String requestedNick = args.get(0).trim();
            validate(requestedNick);
            this.requestedNick = requestedNick;
        } else {
            throw new IllegalCommandFormat("Command NICK expects at least one argument!");
        }
    }

    public String getRequestedNick() {
        return this.requestedNick;
    }

    public void validate(String requestedNick) throws IllegalNickNameException {

        if (requestedNick.length() > maxNickLength) {
            throw new IllegalNickNameException("Nickname is too long");
        }
        char beginning = requestedNick.charAt(0);

        if (Character.isDigit(beginning)) {
            throw new IllegalNickNameException("Cannot start nickname with a number");
        }
    }

}
