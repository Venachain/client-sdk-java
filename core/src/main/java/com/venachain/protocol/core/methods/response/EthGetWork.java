package com.venachain.protocol.core.methods.response;

import java.util.List;

import com.venachain.protocol.core.Response;

/**
 * eth_getWork.
 */
public class EthGetWork extends Response<List<String>> {

    public String getCurrentBlockHeaderPowHash() {
        return getResult().get(0);
    }

    public String getSeedHashForDag() {
        return getResult().get(1);
    }

    public String getBoundaryCondition() {
        return getResult().get(2);
    }
}
