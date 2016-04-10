package net.aphotix.jnerik.core.io;

import java.util.UUID;

/**
 * Created by Nathan on 10/04/2016.
 */
public class SwitchableConnection implements Connection {

	private final UUID id;
	private final MessageSender sender;

	private volatile ConnectionType connectionType;

	public SwitchableConnection(UUID id, MessageSender sender, ConnectionType connectionType) {
		this.id = id;
		this.sender = sender;
		this.connectionType = connectionType;
	}

	@Override
	public ConnectionType getConnectionType() {
		return null;
	}

	@Override
	public UUID getId() {
		return null;
	}

	@Override
	public MessageSender getSender() {
		return null;
	}

	public void setConnectionType(ConnectionType connectionType) {

	}
}
