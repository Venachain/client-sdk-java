package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.protocol.core.Response;
import com.venachain.utils.Numeric;

/**
 * net_peerCount.
 */
public class NetPeerCount extends Response<String> {

    public BigInteger getQuantity() {
        return Numeric.decodeQuantity(getResult());
    }
}
