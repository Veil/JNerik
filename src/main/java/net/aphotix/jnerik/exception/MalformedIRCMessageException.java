package net.aphotix.jnerik.exception;

/**
 * @author Veil (nathan@aphotix.net).
 */
public class MalformedIRCMessageException extends Exception {

	public MalformedIRCMessageException(String message, Exception e) {
		super(message, e);
	}

	public MalformedIRCMessageException(String message) {
		this(message, null);
	}

}
