package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.events.BasicEventRegistry;
import net.aphotix.jnerik.core.io.MessageChannel;
import net.aphotix.jnerik.core.io.UserSessionResponder;
import net.aphotix.jnerik.core.registry.MapBackedCommandRegistry;
import net.aphotix.jnerik.core.registry.MapBackedFlagRegistry;
import net.aphotix.jnerik.core.registry.MapBackedModeRegistry;
import net.aphotix.jnerik.mina.MinaAcceptor;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/**
 * The main entry point for the IRCd.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class JNerikIrcds {

    public static void main(String[] args) throws IOException {
        // TODO Load some kind of config.
        final JNerikConfig config = new HardCodedConfig();

        final UserSessionResponder sessionResponder = new UserSessionResponder(config);
        final UserRegistry users = sessionResponder.getUserRegistry();

        final CommandRegistry commands = new MapBackedCommandRegistry();

        final ModuleLoader modules = new ModuleLoader(commands, new MapBackedFlagRegistry(),
                new MapBackedModeRegistry(), users, new BasicEventRegistry(sessionResponder));

        final MessageChannel channel = new QueueBackedMessageChannel();

        final MessageReader reader = new MessageReader(commands, channel, sessionResponder);

        Executors.newFixedThreadPool(1).submit(reader);

        modules.loadModules();

        startNetworkListeners(config, sessionResponder, channel);

        modules.unloadModules();
    }

    private static void startNetworkListeners(JNerikConfig config, UserSessionResponder sessionResponder,
                                              MessageChannel channel) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("UTF-8"))));

        acceptor.setHandler(new MinaAcceptor(sessionResponder, channel));
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        acceptor.bind(config.getListenAddresses());
    }

}
