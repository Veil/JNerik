package net.aphotix.jnerik.modules.nick;

/**
 * Should be thrown when a nickname which does not meet validation requirements is requested.
 *
 * @author Veil (nathan@aphotix.net).
 */
class IllegalNickNameException extends Exception {

    public IllegalNickNameException(String message) {
        super(message);
    }
}
