package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.Server;

import java.util.UUID;

/**
 * Created by Nathan on 30/03/2016.
 */
public interface ServerRegistry {

	public void registerServer(UUID uid);

	public Server getServer(UUID uid);

}
