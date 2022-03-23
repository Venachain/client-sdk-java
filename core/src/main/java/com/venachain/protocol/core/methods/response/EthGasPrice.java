package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.utils.Numeric;
import com.venachain.protocol.core.Response;

/**
 * eth_gasPrice.
 */
public class EthGasPrice extends Response<String> {
    public BigInteger getGasPrice() {
        return Numeric.decodeQuantity(getResult());
    }
}
