package net.aphotix.jnerik.core;

import java.util.UUID;

/**
 * Created by Nathan on 24/03/2016.
 */
public interface User {

    public UUID getId();

    public String getNick();

    public void setNick(String requestedNick);

    public String getAddress();

    public boolean isRemote();
}
