package net.aphotix.jnerik.exception;

/**
 * Used by command parsers to indicate an incorrect format.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class IllegalCommandFormat extends Exception {

    public IllegalCommandFormat(String message, Exception e) {
        super(message, e);
    }

    public IllegalCommandFormat(String message) {
        this(message, null);
    }

}
