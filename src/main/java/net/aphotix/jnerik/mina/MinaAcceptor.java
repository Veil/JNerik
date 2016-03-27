package net.aphotix.jnerik.mina;

import net.aphotix.jnerik.core.io.MessageChannel;
import net.aphotix.jnerik.core.io.MessageSender;
import net.aphotix.jnerik.core.io.UserSessionManager;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * The core IO handler for connecting clients. Uses Apache Mina to accept connections and received messages.
 *
 * This implementation simply pushes messages onto a message channel, parsing and interpreting those messages should
 * be handled by receiving messages through that channel.
 *
 * @see MessageChannel#receive()
 *
 * @author Veil (nathan@aphotix.net).
 */
public class MinaAcceptor extends IoHandlerAdapter {

    private final UserSessionManager sessionManager;
    private final MessageChannel channel;

    public MinaAcceptor(UserSessionManager sessionManager, MessageChannel channel) {
        this.sessionManager = sessionManager;
        this.channel = channel;
    }

    @Override
    public void sessionCreated(IoSession session) {
        UUID id = UUID.randomUUID();
        session.setAttribute("id", id);
        sessionManager.createNew(id, new MessageSender() {
            @Override
            public void send(String message) {
                session.write(message);
            }

            @Override
            public String getConnectionAddress() {
                return ((InetSocketAddress) session.getRemoteAddress()).getHostString();
            }
        });
    }

    @Override
    public void sessionClosed(IoSession session) {
        sessionManager.destroy((UUID) session.getAttribute("id"));
    }

    @Override
    public void messageReceived(IoSession session, Object rawMessage) {
        final String message = rawMessage.toString();
        channel.push(new MinaMessage(sessionManager.getUserFromId((UUID) session.getAttribute("id")), message));
    }
}
