package com.venachain.abi;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import org.junit.Test;
import com.venachain.abi.datatypes.Int;
import com.venachain.abi.datatypes.Uint;
import com.venachain.abi.datatypes.Utf8String;
import com.venachain.abi.datatypes.generated.Int64;
import com.venachain.abi.datatypes.generated.Uint64;


public class VenachainTypeEncoderTest {


    @Test
    public void testUintEncode() {
        Uint zero = new Uint64(BigInteger.ZERO);
        assertThat(VenachainTypeEncoder.encode(zero),
                is("0000000000000000"));

        Uint maxLong = new Uint64(BigInteger.valueOf(Long.MAX_VALUE));
        assertThat(VenachainTypeEncoder.encode(maxLong),
                is("7fffffffffffffff"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInvalidUintEncode() {
        new Uint64(BigInteger.valueOf(-1));
    }


    @Test
    public void testIntEncode() {
        Int zero = new Int64(BigInteger.ZERO);
        assertThat(VenachainTypeEncoder.encode(zero),
                is("0000000000000000"));

        Int maxLong = new Int64(BigInteger.valueOf(Long.MAX_VALUE));
        assertThat(VenachainTypeEncoder.encode(maxLong),
                is("7fffffffffffffff"));

        Int minLong = new Int64(BigInteger.valueOf(Long.MIN_VALUE));
        assertThat(VenachainTypeEncoder.encode(minLong),
                is("8000000000000000"));

        Int minusOne = new Int64(BigInteger.valueOf(-1));
        assertThat(VenachainTypeEncoder.encode(minusOne),
                is("ffffffffffffffff"));
    }

    @Test
    public void testUtf8String() {
        Utf8String string = new Utf8String("Hello, world!");
        assertThat(VenachainTypeEncoder.encode(string),
                is("48656c6c6f2c20776f726c6421"));
    }
}

