package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;

import java.util.UUID;

/**
 * Created by Nathan on 30/03/2016.
 */
public class UserConnection implements Connection, User {

	private final MessageSender sender;
	private final String address;
	private final UserSessionRegistry registry;
	private final UUID id;

	private String nick;

	public UserConnection(Connection wrappedConnection, UserSessionRegistry registry) {
		this.sender = wrappedConnection.getSender();
		this.address = this.sender.getConnectionAddress();
		this.id = wrappedConnection.getId();
		this.registry = registry;
	}

	@Override
	public ConnectionType getConnectionType() {
		return ConnectionType.USER;
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public MessageSender getSender() {
		return sender;
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
		return address;
	}

	@Override
	public boolean isRemote() {
		return false;
	}
}
