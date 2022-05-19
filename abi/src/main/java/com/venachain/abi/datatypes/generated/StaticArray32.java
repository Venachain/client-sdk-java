package com.venachain.abi.datatypes.generated;

import java.util.List;
import com.venachain.abi.datatypes.StaticArray;
import com.venachain.abi.datatypes.Type;

/**
 * Auto generated code.
 * <p><strong>Do not modifiy!</strong>
 * <p>Please use com.venachain.codegen.AbiTypesGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class StaticArray32<T extends Type> extends StaticArray<T> {
    public StaticArray32(List<T> values) {
        super(32, values);
    }

    @SafeVarargs
    public StaticArray32(T... values) {
        super(32, values);
    }
}
