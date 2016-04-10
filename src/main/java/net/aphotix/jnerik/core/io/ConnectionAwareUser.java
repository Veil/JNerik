package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;

import java.util.UUID;

/**
 * @author Veil (nathan@aphotix.net).
 */
public class ConnectionAwareUser implements User {

	private final Connection connection;
	private final UserSessionRegistry registry;

	private String nick;

	public ConnectionAwareUser(Connection connection, UserSessionRegistry registry) {
		this.connection = connection;
		this.registry = registry;
	}

	@Override
	public UUID getId() {
		return connection.getId();
	}

	@Override
	public String getNick() {
		return nick;
	}

	@Override
	public void setNick(String requestedNick) {
		this.nick = requestedNick;
		registry.registerUser(nick, requestedNick, this);
	}

	@Override
	public String getAddress() {
		return connection.getSender().getConnectionAddress();
	}

	@Override
	public boolean isRemote() {
		return connection.getConnectionType() == Connection.ConnectionType.SERVER;
	}
}
