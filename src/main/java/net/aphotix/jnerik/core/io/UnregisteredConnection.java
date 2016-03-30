package net.aphotix.jnerik.core.io;

import java.util.UUID;

/**
 * Created by Nathan on 30/03/2016.
 */
public class UnregisteredConnection implements Connection {

	private final UUID uid;
	private final MessageSender sender;

	public UnregisteredConnection(UUID uid, MessageSender sender) {
		this.uid = uid;
		this.sender = sender;
	}

	@Override
	public ConnectionType getConnectionType() {
		return ConnectionType.UNREGISTERED;
	}

	@Override
	public UUID getId() {
		return uid;
	}

	@Override
	public MessageSender getSender() {
		return sender;
	}
}
