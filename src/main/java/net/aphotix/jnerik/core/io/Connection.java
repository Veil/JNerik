package net.aphotix.jnerik.core.io;

import java.util.UUID;

/**
 * Created by Nathan on 30/03/2016.
 */
public interface Connection {

	public enum ConnectionType {
		USER,
		SERVER,
		UNREGISTERED;
	}

	public ConnectionType getConnectionType();

	public UUID getId();

	public MessageSender getSender();

}
