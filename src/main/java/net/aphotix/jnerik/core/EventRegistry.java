package net.aphotix.jnerik.core;

import net.aphotix.jnerik.core.io.Responder;

import java.util.function.BiConsumer;

/**
 * A registry through which events can be propagated and listeners registered.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface EventRegistry {

    /**
     * Propagate an event to registered listeners.
     *
     * @param eventToSend The event to propagate.
     */
    public void propagate(Event eventToSend);

    /**
     * Register a listener for an event.
     *
     * @param eventToListenFor The event class to listen for
     * @param hook The hook that reacts to the event
     * @param <T> The type of event.
     */
    public <T extends Event> void listenFor(Class<T> eventToListenFor, BiConsumer<T, Responder> hook);

}
