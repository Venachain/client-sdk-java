package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.protocol.core.Response;
import com.venachain.utils.Numeric;

/**
 * eth_blockNumber.
 */
public class EthBlockNumber extends Response<String> {
    public BigInteger getBlockNumber() {
        return Numeric.decodeQuantity(getResult());
    }
}
