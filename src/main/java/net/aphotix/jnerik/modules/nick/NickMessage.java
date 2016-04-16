package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.exception.IllegalCommandFormat;
import net.aphotix.jnerik.exception.IllegalRemoteCommandFormat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Used to parse a NICK command message received from a client.
 *
 * Performs basic validation such as nickname length does not exceed the provided max length, that the NICK
 * command does provide an argument and the nickname does not begin with a number.
 *
 * @author Veil (nathan@aphotix.net).
 */
class NickMessage {

    private static final int CLIENT_COMMAND_LENGTH = 1;
    private final String requestedNick;
    private final int maxNickLength;

    /**
     * Creates a new parsed message for a NICK command
     *
     * Expects at least one argument, if more arguments are provided they are ignored.
     *
     * @param args The arguments for the NICK command
     * @param maxNickLength The maximum length for a nickname
     *
     * @throws IllegalCommandFormat If no nickname is provided
     * @throws IllegalNickNameException If the nickname does not meet
     */
    public NickMessage(List<String> args, int maxNickLength) throws IllegalCommandFormat, IllegalNickNameException {
        this.maxNickLength = maxNickLength;

        if (args.size() >= CLIENT_COMMAND_LENGTH && !args.get(0).trim().isEmpty()) {
            final String requestedNick = args.get(0).trim();
            validate(requestedNick);
            this.requestedNick = requestedNick;
        } else {
            throw new IllegalCommandFormat("Command NICK expects at least one argument!");
        }
    }

    /**
     * Get the nickname being requested in this message
     *
     * @return {@link String} The nickname being requested
     */
    public String getRequestedNick() {
        return this.requestedNick;
    }

    /**
     * The validation function used to ensure that a nickname meets the IRCd's requirements
     *
     * @param requestedNick The nickname to validate
     *
     * @throws IllegalNickNameException If the nickname is not valid
     */
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
