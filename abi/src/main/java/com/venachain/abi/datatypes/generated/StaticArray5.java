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
public class StaticArray5<T extends Type> extends StaticArray<T> {
    public StaticArray5(List<T> values) {
        super(5, values);
    }

    @SafeVarargs
    public StaticArray5(T... values) {
        super(5, values);
    }
}
