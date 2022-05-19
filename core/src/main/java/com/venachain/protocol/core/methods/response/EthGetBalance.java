package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.protocol.core.Response;
import com.venachain.utils.Numeric;

/**
 * eth_getBalance.
 */
public class EthGetBalance extends Response<String> {
    public BigInteger getBalance() {
        return Numeric.decodeQuantity(getResult());
    }
}
