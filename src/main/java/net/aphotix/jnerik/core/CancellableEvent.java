package net.aphotix.jnerik.core;

/**
 * An event which handles cancel logic only. Any cancellable events (without any internal logic for cancellation) can
 * extend this class
 *
 * @author Veil (nathan@aphotix.net).
 */
public abstract class CancellableEvent implements Event {

    private boolean isCancelled;

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }
}
