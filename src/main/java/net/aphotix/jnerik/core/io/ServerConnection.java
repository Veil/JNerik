package net.aphotix.jnerik.core.io;

import java.util.UUID;

/**
 * Created by Nathan on 30/03/2016.
 */
public class ServerConnection implements Connection, Server {

	@Override
	public ConnectionType getConnectionType() {
		return ConnectionType.SERVER;
	}

	@Override
	public UUID getId() {
		return null;
	}

	@Override
	public MessageSender getSender() {
		return null;
	}
}
