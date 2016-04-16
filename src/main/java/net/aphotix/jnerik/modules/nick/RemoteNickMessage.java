package net.aphotix.jnerik.modules.nick;

import net.aphotix.jnerik.exception.IllegalCommandFormat;
import net.aphotix.jnerik.exception.IllegalRemoteCommandFormat;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Veil (nathan@aphotix.net).
 */
class RemoteNickMessage extends NickMessage {

	private static final int REMOTE_REGISTRATION_LENGTH = 8;

	private final boolean hasRegistrationArgs;

	private final int hopCount;
	private final String host;
	private final String userName;
	private final String serverToken;
	private final List<Character> uModes;
	private final String realName;
	private final long timestamp;


	/**
	 * Creates a new parsed message for a NICK command
	 * <p>
	 * Expects at least one argument, if more arguments are provided then this command is treated as a remote
	 * registration request
	 *
	 * @param args          The arguments for the NICK command
	 * @param maxNickLength The maximum length for a nickname
	 * @throws IllegalCommandFormat     If no nickname is provided
	 * @throws IllegalNickNameException If the nickname does not meet
	 */
	public RemoteNickMessage(List<String> args, int maxNickLength) throws IllegalCommandFormat, IllegalRemoteCommandFormat, IllegalNickNameException {
		super(args, maxNickLength);

		if (hasRegistrationArgs = (args.size() == REMOTE_REGISTRATION_LENGTH)) {

			try {
				hopCount = Integer.parseInt(args.get(1));
				userName = args.get(2);
				host = args.get(3);
				serverToken = args.get(4);
				uModes = args.get(5).chars().mapToObj(mode -> (char) mode).collect(Collectors.toList());
				realName = args.get(6);
				timestamp = Long.parseLong(args.get(7));
			} catch (RuntimeException e) {
				throw new IllegalRemoteCommandFormat("Received invalid command from a server, de-synchronization is VERY possible!!!", e);
			}
		} else {
			hopCount = 0;
			userName = null;
			host = null;
			serverToken = null;
			uModes = null;
			realName = null;
			timestamp = -1;
		}
	}

	public boolean hasRegistrationArgs() {
		return hasRegistrationArgs;
	}

	public int getHopCount() {
		return hopCount;
	}

	public String getUserName() {
		return userName;
	}

	public String getServerToken() {
		return serverToken;
	}

	public List<Character> getUModes() {
		return uModes;
	}

	public String getRealName() {
		return realName;
	}

	public String getHost() {
		return host;
	}

	public long getTimestamp() {
		return timestamp;
	}
}
