package com.venachain.protocol.core.methods.response;

import com.venachain.protocol.core.Response;

/**
 * eth_mining.
 */
public class EthMining extends Response<Boolean> {
    public boolean isMining() {
        return getResult();
    }
}
