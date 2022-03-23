package com.venachain.protocol.core.methods.response;

import com.venachain.protocol.core.Response;

/**
 * eth_call.
 */
public class EthCall extends Response<String> {
    public String getValue() {
        return getResult();
    }
}
