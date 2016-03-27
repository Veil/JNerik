package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.Responder;

import java.util.function.BiConsumer;

/**
 * Created by Nathan on 24/03/2016.
 */
public interface EventRegistry {

    public void propogate(Event eventToSend);

    public <T extends Event> void listenFor(Class<T> eventToListenFor, BiConsumer<T, Responder> hook);

}
