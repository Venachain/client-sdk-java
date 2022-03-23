package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.utils.Numeric;
import com.venachain.protocol.core.Response;

/**
 * eth_getBalance.
 */
public class EthGetBalance extends Response<String> {
    public BigInteger getBalance() {
        return Numeric.decodeQuantity(getResult());
    }
}
