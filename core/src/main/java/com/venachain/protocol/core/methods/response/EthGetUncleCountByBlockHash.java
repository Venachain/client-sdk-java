package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.protocol.core.Response;
import com.venachain.utils.Numeric;

/**
 * eth_getUncleCountByBlockHash.
 */
public class EthGetUncleCountByBlockHash extends Response<String> {
    public BigInteger getUncleCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
