package com.venachain.protocol.core.methods.response;

import java.math.BigInteger;

import com.venachain.utils.Numeric;
import com.venachain.protocol.core.Response;

/**
 * shh_newFilter.
 */
public class ShhNewFilter extends Response<String> {

    public BigInteger getFilterId() {
        return Numeric.decodeQuantity(getResult());
    }
}
