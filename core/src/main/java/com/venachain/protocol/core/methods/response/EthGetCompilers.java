package com.venachain.protocol.core.methods.response;

import java.util.List;

import com.venachain.protocol.core.Response;

/**
 * eth_getCompilers.
 */
public class EthGetCompilers extends Response<List<String>> {
    public List<String> getCompilers() {
        return getResult();
    }
}
