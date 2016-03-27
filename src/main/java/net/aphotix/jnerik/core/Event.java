package net.aphotix.jnerik.core;

/**
 * An event that can be propagated through the event registry and trigger any event listeners.
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface Event {

    /**
     * Checks if this event has been cancelled and consequently if the whole process the event is supposed to represent
     * should be cancelled.
     *
     * @return {@literal boolean} True if cancelled, false if not.
     */
    public boolean isCancelled();

    /**
     * Sets if this event should be cancelled and consequently any further processing the event represents should be
     * cancelled.
     *
     * @param cancel If the event is cancelled.
     */
    public void setCancelled(boolean cancel);

}
