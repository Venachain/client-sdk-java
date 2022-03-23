package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.utils.Numeric;
import com.venachain.protocol.core.Response;

/**
 * eth_getBlockTransactionCountByHash.
 */
public class EthGetBlockTransactionCountByHash extends Response<String> {
    public BigInteger getTransactionCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
