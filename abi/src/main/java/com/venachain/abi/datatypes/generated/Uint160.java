package com.venachain.abi.datatypes.generated;

import java.math.BigInteger;
import com.venachain.abi.datatypes.Uint;

/**
 * Auto generated code.
 * <p><strong>Do not modifiy!</strong>
 * <p>Please use com.venachain.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class Uint160 extends Uint {
    public static final Uint160 DEFAULT = new Uint160(BigInteger.ZERO);

    public Uint160(BigInteger value) {
        super(160, value);
    }

    public Uint160(long value) {
        this(BigInteger.valueOf(value));
    }
}
