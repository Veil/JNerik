package net.aphotix.jnerik.core.io;

import net.aphotix.jnerik.core.User;
import net.aphotix.jnerik.core.UserMode;

import java.util.List;
import java.util.UUID;

/**
 * @author Veil (nathan@aphotix.net).
 */
public class ConnectionAwareUser implements User {

	private final Connection connection;
	private final UserSessionRegistry registry;

	private String nick;
	private long latestTimestamp;

	public ConnectionAwareUser(Connection connection, UserSessionRegistry registry) {
		this.connection = connection;
		this.registry = registry;
	}

	@Override
	public UUID getId() {
		return connection.getId();
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {

	}

	@Override
	public String getNick() {
		return nick;
	}

	@Override
	public void setNick(String requestedNick) {
		this.nick = requestedNick;
		registry.registerUser(nick, requestedNick, this);
		latestTimestamp = System.currentTimeMillis();
	}

	@Override
	public List<UserMode> getModes() {
		return null;
	}

	@Override
	public void addMode(UserMode mode) {

	}

	@Override
	public void addModes(List<UserMode> modes) {

	}

	@Override
	public void setModes(List<UserMode> modes) {

	}

	@Override
	public void removeMode(UserMode modes) {

	}

	@Override
	public void removeModes(List<UserMode> modes) {

	}

	@Override
	public String getAddress() {
		return connection.getSender().getConnectionAddress();
	}

	@Override
	public void setAddress(String address) {

	}

	@Override
	public String getVhost() {
		return null;
	}

	@Override
	public void setVHost(String vHost) {

	}

	@Override
	public String getServerHost() {
		return null;
	}

	@Override
	public void setServerHost(String serverHost) {

	}

	@Override
	public long getLatestNickTimestamp() {
		return latestTimestamp;
	}

	@Override
	public boolean isRemote() {
		return connection.getConnectionType() == Connection.ConnectionType.SERVER;
	}
}
