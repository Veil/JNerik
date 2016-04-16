package net.aphotix.jnerik.exception;

/**
 * @author Veil (nathan@aphotix.net).
 */
public class IllegalRemoteCommandFormat extends Exception {

	public IllegalRemoteCommandFormat(String message, Exception e) {
		super(message, e);
	}

	public IllegalRemoteCommandFormat(String message) {
		this(message, null);
	}

}
