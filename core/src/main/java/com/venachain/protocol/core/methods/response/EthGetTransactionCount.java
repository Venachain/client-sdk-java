package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.utils.Numeric;
import com.venachain.protocol.core.Response;

/**
 * eth_getTransactionCount.
 */
public class EthGetTransactionCount extends Response<String> {
    public BigInteger getTransactionCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
