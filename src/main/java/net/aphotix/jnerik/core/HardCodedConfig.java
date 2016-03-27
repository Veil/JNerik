package net.aphotix.jnerik.core;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by Nathan on 27/03/2016.
 */
class HardCodedConfig implements JNerikConfig {

    @Override
    public List<InetSocketAddress> getListenAddresses() {
        return null;
    }
}
