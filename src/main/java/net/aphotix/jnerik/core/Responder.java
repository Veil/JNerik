package net.aphotix.jnerik.core;

/**
 * Created by Nathan on 25/03/2016.
 */
public interface Responder {

    public void send(User user, String message, Object... params);

    public void sendPropagated(User user, String message, Object... params);

    public void sendError(User user, int errCode, Object... params);
}
