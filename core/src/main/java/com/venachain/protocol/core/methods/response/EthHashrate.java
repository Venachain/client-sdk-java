package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.utils.Numeric;
import com.venachain.protocol.core.Response;

/**
 * eth_hashrate.
 */
public class EthHashrate extends Response<String> {
    public BigInteger getHashrate() {
        return Numeric.decodeQuantity(getResult());
    }
}
