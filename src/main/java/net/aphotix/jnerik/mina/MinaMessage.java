package net.aphotix.jnerik.mina;

import net.aphotix.jnerik.core.RawMessage;
import net.aphotix.jnerik.core.io.Connection;

/**
 * @author Veil (nathan@aphotix.net).
 */
public class MinaMessage implements RawMessage {

	private final Connection connection;
	private final String message;

	public MinaMessage(Connection connection, String message) {
		this.connection = connection;
		this.message = message;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public String getRawMessage() {
		return message;
	}
}
