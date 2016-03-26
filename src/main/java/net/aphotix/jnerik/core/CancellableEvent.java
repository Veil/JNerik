package net.aphotix.jnerik.core;

/**
 * Created by Nathan on 26/03/2016.
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
