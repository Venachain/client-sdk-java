package com.venachain.abi;


import com.venachain.abi.datatypes.Event;
import com.venachain.crypto.Hash;
import com.venachain.utils.Numeric;


public class VenachainEventEncoder {

    private VenachainEventEncoder() { }

    public static String encode(Event event) {
        String methodSignature = buildMethodSignature(event.getName());
        return buildEventSignature(methodSignature);
    }

    private static String buildMethodSignature(String methodName) {
        StringBuilder result = new StringBuilder();
        result.append(methodName);
        return result.toString();
    }

    private static String buildEventSignature(String methodSignature) {
        byte[] input = methodSignature.getBytes();
        byte[] hash = Hash.sha3(input);
        return Numeric.toHexString(hash);
    }
}

