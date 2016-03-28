package net.aphotix.jnerik.core.events;

import net.aphotix.jnerik.core.Event;
import net.aphotix.jnerik.core.EventRegistry;
import net.aphotix.jnerik.core.io.Responder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Veil (nathan@aphotix.net).
 */
public class BasicEventRegistry implements EventRegistry {

    private final Responder responder;
    private final Map<Class<? extends Event>, List<BiConsumer<Object, Responder>>> listenerMap;

    public BasicEventRegistry(Responder responder) {
        this.responder = responder;
        this.listenerMap = new HashMap<>();
    }

    @Override
    public void propagate(Event eventToSend) {
        List<BiConsumer<Object, Responder>> listeners = listenerMap.get(eventToSend.getClass());

        if (listeners != null) {

            for (BiConsumer<Object, Responder> listener : listeners) {
                listener.accept(eventToSend, responder);
            }
        }
    }

    /**
     * This looks nasty for now but we know type safety is ensured because we map classes as the key, there's probably
     * a better way to do this but that will have to wait...
     *
     * @param eventToListenFor The event class to listen for
     * @param hook The hook that reacts to the event
     * @param <T> The event type
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Event> void listenFor(Class<T> eventToListenFor, BiConsumer<T, Responder> hook) {
        listenerMap.computeIfAbsent(eventToListenFor, k -> new ArrayList<>()).add((BiConsumer<Object, Responder>) hook);
    }
}
