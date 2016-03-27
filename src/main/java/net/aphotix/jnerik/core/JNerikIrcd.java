package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.UserSessionResponder;
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
 * Created by Nathan on 26/03/2016.
 */
public class JNerikIrcd {

    public static void main(String[] args) throws IOException {
        // TODO Load some kind of config.
        JNerikConfig config = new HardCodedConfig();

        UserSessionResponder userManagement = new UserSessionResponder(config);

        final ModuleLoader modules = new ModuleLoader(null, null, null, null, null);

        final MessageReader reader = new MessageReader(null, null, userManagement);

        Executors.newFixedThreadPool(1).submit(reader);

        modules.loadModules();

        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("UTF-8"))));

        acceptor.setHandler(new MinaAcceptor(null, null));
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        acceptor.bind(config.getListenAddresses());

        modules.unloadModules();
    }

}
