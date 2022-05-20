package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.protocol.core.Response;
import com.venachain.utils.Numeric;

/**
 * eth_getUncleCountByBlockNumber.
 */
public class EthGetUncleCountByBlockNumber extends Response<String> {
    public BigInteger getUncleCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
