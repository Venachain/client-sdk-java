package com.venachain.tx.gas;

import java.math.BigInteger;

import com.venachain.tx.ManagedTransaction;

public class DefaultGasProvider extends StaticGasProvider {
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(250000000);
    public static final BigInteger GAS_PRICE = ManagedTransaction.GAS_PRICE;

    public DefaultGasProvider() {
        super(GAS_PRICE, GAS_LIMIT);
    }
}
