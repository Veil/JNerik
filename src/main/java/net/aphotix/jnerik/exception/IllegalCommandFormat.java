package net.aphotix.jnerik.exception;

/**
 * Created by Nathan on 25/03/2016.
 */
public class IllegalCommandFormat extends Exception {

    public IllegalCommandFormat(String message, Exception e) {
        super(message, e);
    }

    public IllegalCommandFormat(String message) {
        this(message, null);
    }

}
