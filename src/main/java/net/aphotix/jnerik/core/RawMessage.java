package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.Connection;

/**
 * @author Veil (nathan@aphotix.net).
 */
public interface RawMessage {

	public Connection getConnection();

	public String getRawMessage();

}
