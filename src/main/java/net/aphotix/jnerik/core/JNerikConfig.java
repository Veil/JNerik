package net.aphotix.jnerik.core;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by Nathan on 27/03/2016.
 */
public interface JNerikConfig {

    public List<InetSocketAddress> getListenAddresses();

    public String getErrorFormat(int errCode);
}
